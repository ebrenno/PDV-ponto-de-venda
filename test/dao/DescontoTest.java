package dao;

import conexao.DatabaseFactory;
import conexao.DatabasesURL;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
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
public class DescontoTest {

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
    public void afer() {
        DatabaseFactory.fecharConexao(conexao);
    }

    @Test
    public void IsPercentualEquals5() throws Exception {

        int percentual = DescontoDAO.getPercentualSobreQuantidade(conexao, 2, 5);
        Assert.assertTrue(percentual == 5);

    }

    @Test
    public void PercentualEqualsZero() throws Exception {

        int percentual = DescontoDAO.getPercentualSobreQuantidade(conexao, 1, 1);
        Assert.assertTrue(percentual == 0);

    }

}
