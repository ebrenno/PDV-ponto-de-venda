/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brenno
 */
public class DatabaseFactory {

    private static Connection conn = null;

    public static Connection getConn(DatabasesURL DATABASE) throws ClassNotFoundException, SQLException {

        if (conn == null || conn.isClosed()) {
            return conn = DriverManager.getConnection(DATABASE.URL, DATABASE.USER, DATABASE.PASS);
        }
        return conn;

    }

    public static void fecharConexao(Connection conexao) {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
