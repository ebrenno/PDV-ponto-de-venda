/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author brenno
 */
public class ProdutoTest {

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
    public void isMadeInLocal() throws Exception {

        boolean flag = ProdutoDAO.foiFabricadoNoLocal(conexao, 3, 1);
        Assert.assertTrue(flag);

    }

    @Test
    public void DeveRetornarPrecoProduto() throws Exception {

        double preco = ProdutoDAO.getPrecoUnitario(conexao, 4);
        Assert.assertTrue(preco == 5.10);

    }

    @Test
    public void temProdutoEmEstoque() throws Exception {

        boolean flag = ProdutoDAO.temEmEstoque(conexao, 1, 5);
        Assert.assertTrue(flag);

    }

    @Test
    public void retirar() throws Exception {
        int codProd = 1;
        int quantidadeRetirada = 3;
        int quantidadeAnterior = ProdutoDAO.getQuantidadeDisponivel(conexao, codProd);
        ProdutoDAO.retirarEstoque(conexao, codProd, quantidadeRetirada);
        Assert.assertTrue(ProdutoDAO.getQuantidadeDisponivel(conexao, codProd) == quantidadeAnterior - quantidadeRetirada);

    }

    @Test
    public void DeveDevolverProdutoAoEstoque() throws Exception {

        int quantidade = 3;
        ProdutoDAO.retirarEstoque(conexao, 1, quantidade);
        int antes = ProdutoDAO.getQuantidadeDisponivel(conexao, 1);
        ProdutoDAO.adicionarAoEstoque(conexao, 1, quantidade);
        int depois = ProdutoDAO.getQuantidadeDisponivel(conexao, 1);
        Assert.assertTrue(antes + quantidade == depois);

    }
}
