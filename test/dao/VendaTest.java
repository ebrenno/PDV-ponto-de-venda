/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import estilo.Venda;
import conexao.DatabaseFactory;
import conexao.DatabasesURL;
import exception.EstoqueInsuficienteException;
import gui.Tabela;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.List;
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
public class VendaTest {

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
    public void NaoExistemComprasFeitas() throws Exception {

        List<Tabela> lista = VendaDAO.recuperarItensVendidos(conexao, 1);
        Assert.assertTrue(lista.isEmpty());

    }

    @Test
    public void DeveEfetuarVenda() throws Exception {

        VendaDAO.aplicar(conexao, 1, 1, 1, 1);
        List<Tabela> lista = VendaDAO.recuperarItensVendidos(conexao, 1);
        Assert.assertTrue(lista.size() == 1);

    }

    @Test(expected = EstoqueInsuficienteException.class)
    public void DeveCancelarVendaPorFaltaDeEstoque() throws Exception {

        VendaDAO.aplicar(conexao, 1, 1, 1, 9999);

    }

    @Test
    public void deveExcluirVenda() throws Exception {

        VendaDAO.aplicar(conexao, 1, 1, 1, 1);
        List<Venda> vendas = VendaDAO.recuperarVendas(conexao, 1);
        Assert.assertTrue(vendas.size() == 1);

        VendaDAO.excluir(conexao, vendas.get(0));
        vendas = VendaDAO.recuperarVendas(conexao, 1);
        Assert.assertTrue(vendas.isEmpty());

    }

    @Test
    public void DeveDevolverBonusAoExcluirVenda() throws Exception {

        VendaDAO.aplicar(conexao, 2, 1, 1, 5);
        int bonusAntes = ClienteDAO.getBonus(conexao, 1);

        List<Venda> vendas = VendaDAO.recuperarVendas(conexao, 1);
        VendaDAO.excluir(conexao, vendas.get(0));

        int bonusDepois = ClienteDAO.getBonus(conexao, 1);
        Assert.assertTrue(bonusAntes + 100 == bonusDepois);

    }

    @Test
    public void NaoDeveAplicarDescontoporBonusNaVenda() throws Exception {

        int bonusAntes = ClienteDAO.getBonus(conexao, 1);
        VendaDAO.aplicar(conexao, 1, 1, 1, 1);
        int bonusDepois = ClienteDAO.getBonus(conexao, 1);
        Assert.assertTrue("bonus antes: " + bonusAntes + ". bonus depois: " + bonusDepois, bonusAntes == bonusDepois);

    }

    @Test
    public void DeveAplicarVendaComDescontoPorBonus() throws Exception {

        int bonusAntes = ClienteDAO.getBonus(conexao, 1);
        VendaDAO.aplicar(conexao, 2, 1, 1, 5);
        int bonusDepois = ClienteDAO.getBonus(conexao, 1);
        Assert.assertTrue("bonus antes: " + bonusAntes + ". bonus depois: " + bonusDepois, bonusAntes == bonusDepois + 100);

    }

    @Test
    public void NaoDevolverBonusAoExcluirVenda() throws Exception {

        VendaDAO.aplicar(conexao, 1, 1, 1, 1);
        int bonusAntes = ClienteDAO.getBonus(conexao, 1);

        List<Venda> vendas = VendaDAO.recuperarVendas(conexao, 1);
        VendaDAO.excluir(conexao, vendas.get(0));

        int bonusDepois = ClienteDAO.getBonus(conexao, 1);

        Assert.assertTrue("bonus antes: " + bonusAntes + ". bonus depois: " + bonusDepois, bonusAntes == bonusDepois);

    }
}
