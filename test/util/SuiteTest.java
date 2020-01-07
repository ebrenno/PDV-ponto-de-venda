/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.ClienteTest;
import dao.DescontoTest;
import dao.ProdutoTest;
import dao.VendaTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ClienteTest.class,
    DescontoTest.class,
    ProdutoTest.class,
    VendaTest.class
})
public class SuiteTest {

}
