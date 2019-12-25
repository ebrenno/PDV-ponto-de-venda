/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

/**
 *
 * @author brenno
 */
public enum DatabasesURL {
    MY_SQL("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/ponto_de_venda","root","root"),
    MY_SQL_TEST("com.mysql.cj.jdbc.Driver","jdbc:mysql://localhost:3306/ponto_de_venda_teste","root","root");

    private DatabasesURL(String DRIVER, String URL, String USER, String PASS) {
        this.DRIVER = DRIVER;
        this.URL = URL;
        this.USER = USER;
        this.PASS = PASS;
    }

    public final String DRIVER;
    public final String URL;
    public final String USER;
    public final String PASS;
}
