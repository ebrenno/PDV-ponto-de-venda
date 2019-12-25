package dao;

import estilo.Produto;
import exception.EstoqueInsuficienteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static List<Produto> listaProduto(Connection conexao) {
        List<Produto> lista = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        try {

            ps = conexao.prepareStatement("select codprod,descricao from produto order by codprod;");
            rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setCodProd(rs.getInt("codprod"));
                p.setDescricao(rs.getString("descricao"));

                lista.add(p);

            }

        } catch (SQLException e) {
        }

        return lista;
    }

    public int getQtdEstoque(Connection conexao, int codPro) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        ps = conexao.prepareStatement("select qtd_estoque from produto where codprod = ?;");
        ps.setInt(1, codPro);
        rs = ps.executeQuery();
        rs.next();

        int quantidadeEstoque = rs.getInt("qtd_estoque");
        return quantidadeEstoque;
    }

    public static boolean temEmEstoque(Connection conexao, int codPro, int quantidadeDesejada) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        ps = conexao.prepareStatement("select qtd_estoque from produto where codprod = ? and qtd_estoque>= ?;");
        ps.setInt(1, codPro);
        ps.setInt(2, quantidadeDesejada);
        rs = ps.executeQuery();
        return rs.next();
    }

    public static int getQuantidadeDisponivel(Connection conexao, int codPro) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        ps = conexao.prepareStatement("select qtd_estoque from produto where codprod = ?;");
        ps.setInt(1, codPro);
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt("qtd_estoque");
    }

    public static void retirarEstoque(Connection conexao, int codPro, int quantidade) throws SQLException, Exception {

        PreparedStatement ps;

        if (temEmEstoque(conexao, codPro, quantidade)) {

            ps = conexao.prepareStatement("update produto set qtd_estoque = qtd_estoque - ? where codprod = ?");
            ps.setInt(1, quantidade);
            ps.setInt(2, codPro);
            ps.executeUpdate();

        } else {
            
            //JOptionPane.showMessageDialog(null, "Produto sem estoque", "Erro!", JOptionPane.ERROR_MESSAGE);
            throw new EstoqueInsuficienteException();
        }

    }

    public static double getPrecoUnitario(Connection conexao, int codProd) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;
        ps = conexao.prepareStatement("select preco_unitario from produto where codprod = ?;");
        ps.setInt(1, codProd);
        rs = ps.executeQuery();
        rs.next();
        double preco = rs.getDouble("preco_unitario");
        return preco;
    }

    public static Produto getProduto(Connection conexao, int codProd) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        ps = conexao.prepareStatement("select codprod,descricao from produto where codprod = ?;");
        ps.setInt(1, codProd);
        rs = ps.executeQuery();
        rs.first();
        int codLocal = rs.getInt("codlocal");
        String descricao = rs.getString("descricao");
        int qtdEstoque = rs.getInt("qtd_estoque");
        double precoUnitario = rs.getInt("preco_unitario");
        Produto p = new Produto(codProd, codLocal, descricao, qtdEstoque, precoUnitario);
        return p;
    }

    public static boolean foiFabricadoNoLocal(Connection conexao, int codProd, int codLocal) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        ps = conexao.prepareStatement("select codprod from produto where codprod = ? and codlocal = ?;");
        ps.setInt(1, codProd);
        ps.setInt(2, codLocal);
        rs = ps.executeQuery();

        return rs.next();
    }

    public static void adicionarAoEstoque(Connection conexao, int codProd, int quantidade) throws SQLException {
        PreparedStatement ps;

        ps = conexao.prepareStatement("update produto set qtd_estoque = qtd_estoque + ? where codprod = ?;");
        ps.setInt(1, quantidade);
        ps.setInt(2, codProd);
        ps.executeUpdate();

    }

}
