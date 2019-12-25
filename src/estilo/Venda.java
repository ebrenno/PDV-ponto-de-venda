package estilo;

import java.util.Date;

public class Venda {

    private int qtdVenda;
    private double valorTotal;
    private int codCli;
    private int codProd;
    private int codLocal;
    private Date dataVenda;

    public Venda(int qtdVenda, double valorTotal, int codCli, int codProd, int codLocal, Date dataVenda) {
        this.qtdVenda = qtdVenda;
        this.valorTotal = valorTotal;
        this.codCli = codCli;
        this.codProd = codProd;
        this.codLocal = codLocal;
        this.dataVenda = dataVenda;
    }

    public Venda() {
    }

    public int getQtdVenda() {
        return qtdVenda;
    }

    public void setQtdVenda(int qtdVenda) {
        this.qtdVenda = qtdVenda;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    public int getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(int codLocal) {
        this.codLocal = codLocal;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
}
