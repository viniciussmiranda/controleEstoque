import java.util.Scanner;

public class ControleEstoque {
    static final int MAX_PRODUTOS = 100;
    static Produto[] produtos = new Produto[MAX_PRODUTOS];
    static int totalProdutos = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("escolha uma das opções do menu:");

        while (true) {
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 0) break;

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    filtrarPorCategoria(scanner);
                    break;
                case 4:
                    ordenarProdutos();
                    break;
                case 5:
                    removerProduto(scanner);
                    break;
                case 6:
                    atualizarPreco(scanner);
                    break;
                case 7:
                    listarComSubtotal();
                    break;
                default:
                    System.out.println("opção inválida");
            }
        }
        scanner.close();
    }

    static void cadastrarProduto(Scanner sc) {
        if (totalProdutos >= MAX_PRODUTOS) {
            System.out.println("limite de produtos atingido.");
            return;
        }
        System.out.println("digite o nome do produto:");
        String nome = sc.nextLine();
        System.out.println("digite a quantidade em estoque:");
        int qtd = sc.nextInt();
        System.out.println("digite o preço unitário:");
        double preco = sc.nextDouble();
        sc.nextLine();
        System.out.println("digite a categoria:");
        String categoria = sc.nextLine();
        System.out.println("digite a quantidade mínima:");
        int qtdMin = sc.nextInt();
        sc.nextLine();

        produtos[totalProdutos++] = new Produto(nome, qtd, preco, categoria, qtdMin);
        System.out.println("produto cadastrado com sucesso!");
    }

    static void listarProdutos() {
        for (int i = 0; i < totalProdutos; i++) {
            System.out.println(produtos[i]);
        }
    }

    static void filtrarPorCategoria(Scanner sc) {
        String cat = sc.nextLine();
        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getCategoria().equalsIgnoreCase(cat)) {
                System.out.println(produtos[i]);
            }
        }
    }

    static void ordenarProdutos() {
        for (int i = 0; i < totalProdutos - 1; i++) {
            for (int j = 0; j < totalProdutos - i - 1; j++) {
                if (produtos[j].getNomeDescricao().compareToIgnoreCase(produtos[j + 1].getNomeDescricao()) > 0) {
                    Produto temp = produtos[j];
                    produtos[j] = produtos[j + 1];
                    produtos[j + 1] = temp;
                }
            }
        }
        System.out.println("produtos ordenados");
    }

    static void removerProduto(Scanner sc) {
        String nome = sc.nextLine();
        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getNomeDescricao().equalsIgnoreCase(nome)) {
              
                for (int j = i; j < totalProdutos - 1; j++) {
                    produtos[j] = produtos[j + 1];
                }
                produtos[--totalProdutos] = null;
                System.out.println("produto removido.");
                return;
            }
        }
        System.out.println("produto não encontrado.");
    }

    static void atualizarPreco(Scanner sc) {
        String nome = sc.nextLine();
        double novoPreco = sc.nextDouble();
        sc.nextLine();
        for (int i = 0; i < totalProdutos; i++) {
            if (produtos[i].getNomeDescricao().equalsIgnoreCase(nome)) {
                produtos[i].setPrecoUnitario(novoPreco);
                System.out.println("Preço atualizado.");
                return;
            }
        }
        System.out.println("produto não encontrado.");
    }

    static void listarComSubtotal() {
    
        String[] categorias = new String[MAX_PRODUTOS];
        double[] subtotais = new double[MAX_PRODUTOS];
        int totalCategorias = 0;

        for (int i = 0; i < totalProdutos; i++) {
            String cat = produtos[i].getCategoria();
            double subtotal = produtos[i].getSubtotal();
            int idx = -1;
            for (int j = 0; j < totalCategorias; j++) {
                if (categorias[j].equalsIgnoreCase(cat)) {
                    idx = j;
                    break;
                }
            }
            if (idx == -1) {
                categorias[totalCategorias] = cat;
                subtotais[totalCategorias] = subtotal;
                totalCategorias++;
            } else {
                subtotais[idx] += subtotal;
            }
        }

        double totalGeral = 0;
        for (int i = 0; i < totalCategorias; i++) {
            System.out.println(categorias[i]);
            System.out.println(subtotais[i]);
            totalGeral += subtotais[i];
        }
        System.out.println(totalGeral);
    }
}


class Produto {
    private String nome;
    private int quantidade;
    private double precoUnitario;
    private String categoria;
    private int quantidadeMinima;

    public Produto(String nome, int quantidade, double precoUnitario, String categoria, int quantidadeMinima) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getNomeDescricao() { return nome; }
    public String getCategoria() { return categoria; }
    public void setPrecoUnitario(double preco) { this.precoUnitario = preco; }
    public double getSubtotal() { return quantidade * precoUnitario; }

    public String toString() {
        return nome + " | " + quantidade + " | " + precoUnitario + " | " + categoria + " | " + quantidadeMinima;
    }
}
