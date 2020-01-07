package dao;

import conexao.DatabaseFactory;
import conexao.DatabasesURL;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.Collection;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ScriptURI;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author brenno
 */
public class ClienteTest {

    static Connection conexao;

    @BeforeClass
    public static void beforeClass() throws Exception {
        conexao = DatabaseFactory.getConn(DatabasesURL.MY_SQL_TEST);
        ScriptRunner sr = new ScriptRunner(conexao);
        File file = new File(ScriptURI.scriptDDL());
        Reader reader = new FileReader(file);
        sr.runScript(reader);
        DatabaseFactory.fecharConexao(conexao);
    }

    @Before
    public void before() throws Exception {
        conexao = DatabaseFactory.getConn(DatabasesURL.MY_SQL_TEST);
        ScriptRunner sr = new ScriptRunner(conexao);
        File file = new File(ScriptURI.scriptDML());
        Reader reader = new FileReader(file);
        sr.runScript(reader);

    }

    @After
    public void after() {
        DatabaseFactory.fecharConexao(conexao);
    }

    @Test
    public void Cliente7NaoTemBonus() throws Exception {
        
        boolean flag = ClienteDAO.temBonus(conexao, 7);
        Assert.assertFalse(flag);

    }

    @Test
    public void ClienteDeveGastarBonus() throws Exception {
        int bonusAnterior = ClienteDAO.getBonus(conexao, 1);
        ClienteDAO.descontarBonus(conexao, 1);

        int bonusAtualizado = ClienteDAO.getBonus(conexao, 1);
        Assert.assertTrue(bonusAtualizado + 100 == bonusAnterior);

    }

    @Test
    public void DeveDevolverBonus() throws Exception {

        int codCli = 1;
        int bonusAntes = ClienteDAO.getBonus(conexao, codCli);
        ClienteDAO.devolverBonus(conexao, codCli);
        int bonusDepois = ClienteDAO.getBonus(conexao, codCli);
        Assert.assertTrue(bonusAntes + 100 == bonusDepois);

    }

}
