/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import estilo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brenno
 */
public class ClienteDAO {

    public static List<Cliente> listaCliente(Connection conexao) {
        List<Cliente> lista = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conexao.prepareStatement("select * from cliente order by codcli;");
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setCodCli(rs.getInt("codcli"));
                c.setNome(rs.getString("nome"));
                c.setBonus(rs.getInt("bonus"));
                c.setPerfil(rs.getString("perfil"));
                c.setStatus(rs.getString("status"));
                lista.add(c);

            }

        } catch (SQLException e) {
        }

        return lista;
    }

    public static int getBonus(Connection conexao, int codCli) throws SQLException {
        PreparedStatement ps;
        ResultSet rs;

        ps = conexao.prepareStatement("select bonus from cliente where codcli = ?;");
        ps.setInt(1, codCli);
        rs = ps.executeQuery();
        rs.next();
        int bonus = rs.getInt("bonus");
        return bonus;
    }

    public static boolean temBonus(Connection conexao, int codCli) throws SQLException {
        int bonus = getBonus(conexao, codCli);
        return bonus >= 100;
    }

    public static void descontarBonus(Connection conexao, int codCli) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement("update cliente set bonus = bonus - 100 where codcli = ?;");
        ps.setInt(1, codCli);
        ps.executeUpdate();
    }

    public static void devolverBonus(Connection conexao, int codCli) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement("update cliente set bonus = bonus + 100 where codcli = ?;");
        ps.setInt(1, codCli);
        ps.executeUpdate();
    }

}
