public class Produto {
    private String nomeDescricao;
    private int qtdEstoque;
    private double precoUnitario;
    private String categoria;
    private int qtdMinima;

    public Produto(String nomeDescricao, int qtdEstoque, double precoUnitario, String categoria, int qtdMinima) {
        this.nomeDescricao = nomeDescricao;
        this.qtdEstoque = qtdEstoque;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
        this.qtdMinima = qtdMinima;
    }

    public String getNomeDescricao() {
        return nomeDescricao;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getQtdMinima() {
        return qtdMinima;
    }

    public void setPrecoUnitario(double novoPreco) {
        this.precoUnitario = novoPreco;
    }

    public double getSubtotal() {
        return qtdEstoque * precoUnitario;
    }

    @Override
    public String toString() {
        return nomeDescricao + " " + qtdEstoque + " " + precoUnitario + " " + categoria + " " + qtdMinima;
    }
}
