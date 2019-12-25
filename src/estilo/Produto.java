
package estilo;

public class Produto {
    private int codProd;
    private int codLocal;
    private String descricao;
    private int qtdEstoque;
    private double precounitario;

    public Produto(int codProd, int codLocal,String descricao, int qtdEstoque, double precounitario) {
        this.codProd = codProd;
        this.codLocal = codLocal;
        this.descricao = descricao;
        this.qtdEstoque = qtdEstoque;
        this.precounitario = precounitario;
    }

    public Produto() {
        
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getPrecounitario() {
        return precounitario;
    }

    public void setPrecounitario(double precounitario) {
        this.precounitario = precounitario;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }
    public static double getValorParcial(double preco, int qtd){
        return preco * qtd;
    }
}
