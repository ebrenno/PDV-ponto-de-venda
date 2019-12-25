package dao;

import estilo.Desconto;
import estilo.Produto;
import estilo.Venda;
import gui.Tabela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO {

    public static void aplicar(Connection conexao, int codProd, int codCli, int codLocal, int quantidade) throws  Exception {

        PreparedStatement ps;
        double total = 0;

        try {
            conexao.setAutoCommit(false);
            ProdutoDAO.retirarEstoque(conexao, codProd, quantidade);

            double precoUnitario = ProdutoDAO.getPrecoUnitario(conexao, codProd);
            total = Produto.getValorParcial(precoUnitario, quantidade);
            boolean temBonus = ClienteDAO.temBonus(conexao, codCli);
            int percentual = DescontoDAO.getPercentualSobreQuantidade(conexao, codProd, quantidade);
            if (temBonus && percentual > 0) {
                total = Desconto.calcularDescontoSobreQuantidade(total, percentual);
                ClienteDAO.descontarBonus(conexao, codCli);
            }
            if (ProdutoDAO.foiFabricadoNoLocal(conexao, codProd, codLocal)) {
                total = Desconto.calcularDezPorCento(total);
            }

            ps = conexao.prepareStatement("insert into venda (codcli,codprod,codlocal,qtd_venda,valor_total,data_venda) values(?,?,?,?,?,?);");
            ps.setInt(1, codCli);
            ps.setInt(2, codProd);
            ps.setInt(3, codLocal);
            ps.setInt(4, quantidade);
            ps.setDouble(5, total);
            ps.setString(6, getData());
            ps.executeUpdate();
            conexao.commit();
            
        }catch (Exception ex) {
            try {
                conexao.rollback();
            } catch (SQLException ex1) {
            }
            throw ex;
        } finally {
            try {

                conexao.setAutoCommit(true);

            } catch (SQLException ex) {
            }
        }

    }

    public static String getData() {
        Date data = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String mysqlData = formato.format(data);
        return mysqlData;
    }

    public static List<Tabela> recuperarItensVendidos(Connection conexao, int codCli) {
        List<Tabela> lista = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conexao.prepareStatement("select descricao,qtd_venda,preco_unitario,valor_total from venda join produto using(codprod) where codcli = ?;");
            ps.setInt(1, codCli);
            rs = ps.executeQuery();

            while (rs.next()) {
                Tabela view = new Tabela();
                view.setDescricao(rs.getString("descricao"));
                view.setQtdVenda(rs.getInt("qtd_venda"));
                view.setPrecoUnitario(rs.getDouble("preco_unitario"));
                view.setValorTotal(rs.getDouble("valor_total"));
                lista.add(view);

            }

        } catch (SQLException e) {
        }

        return lista;

    }

    public static List<Venda> recuperarVendas(Connection conexao, int codCli) {
        List<Venda> lista = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = conexao.prepareStatement("select codcli,codprod,codlocal,qtd_venda,valor_total,data_venda from venda where codcli = ?;");
            ps.setInt(1, codCli);
            rs = ps.executeQuery();

            while (rs.next()) {
                Venda venda = new Venda();
                venda.setCodCli(rs.getInt("codcli"));
                venda.setCodProd(rs.getInt("codprod"));
                venda.setCodLocal(rs.getInt("codlocal"));
                venda.setQtdVenda(rs.getInt("qtd_venda"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setValorTotal(rs.getDouble("valor_total"));
                lista.add(venda);

            }

        } catch (SQLException e) {
        }

        return lista;

    }

    public static void excluir(Connection conexao, Venda venda) {
        PreparedStatement ps;
        try {
            conexao.setAutoCommit(false);
            double preco_unitario = ProdutoDAO.getPrecoUnitario(conexao, venda.getCodProd());
            boolean fabricadoNoLocal = ProdutoDAO.foiFabricadoNoLocal(conexao, venda.getCodProd(), venda.getCodLocal());
            double valorParcial = Produto.getValorParcial(preco_unitario, venda.getQtdVenda());
            int percentual = DescontoDAO.getPercentualSobreQuantidade(conexao,venda.getCodProd(),venda.getQtdVenda());

            if (fabricadoNoLocal) {
                valorParcial = Desconto.calcularDezPorCento(valorParcial);
            }
            valorParcial = Desconto.calcularDescontoSobreQuantidade(valorParcial, percentual);
            if (valorParcial == venda.getValorTotal() && percentual != 0) {
                ClienteDAO.devolverBonus(conexao, venda.getCodCli());
            }
            ProdutoDAO.adicionarAoEstoque(conexao, venda.getCodProd(), venda.getQtdVenda());
            ps = conexao.prepareStatement("delete from venda where codcli = ? and codprod = ? and codlocal = ?;");
            ps.setInt(1, venda.getCodCli());
            ps.setInt(2, venda.getCodProd());
            ps.setInt(3, venda.getCodLocal());
            ps.executeUpdate();
            conexao.commit();

        } catch (SQLException ex) {
            try {
                conexao.rollback();
            } catch (SQLException ex1) {
            }
        } finally {
            try {
                conexao.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
