/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Estilo.Localidade;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalidadeDAO {

    public static List<Localidade> listaLocalidade(Connection conexao) {
        List<Localidade> lista = new ArrayList<>();

        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = conexao.prepareStatement("select codlocal,nome from localidade order by codlocal;");
            rs = ps.executeQuery();

            while (rs.next()) {
                Localidade l = new Localidade();
                l.setCodLocal(rs.getInt("codlocal"));
                l.setNome(rs.getString("nome"));

                lista.add(l);

            }

        } catch (SQLException e) {
        }

        return lista;
    }

}
