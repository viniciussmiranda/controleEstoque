import java.util.*;

public class ControleEstoque {
    static List<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Programa iniciado. Digite as opções conforme o menu:");

        while (true) {
            int opcao = scanner.nextInt();
            scanner.nextLine(); // consumir quebra de linha

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
                    System.out.println("Opcao invalida");
            }
        }

        scanner.close();
    }

    static void cadastrarProduto(Scanner sc) {
    System.out.println("Digite o nome do produto:");
    String nome = sc.nextLine();

    System.out.println("Digite a quantidade em estoque:");
    int qtd = sc.nextInt();

    System.out.println("Digite o preço unitário:");
    double preco = sc.nextDouble();
    sc.nextLine();

    System.out.println("Digite a categoria:");
    String categoria = sc.nextLine();

    System.out.println("Digite a quantidade mínima:");
    int qtdMin = sc.nextInt();
    sc.nextLine();

    produtos.add(new Produto(nome, qtd, preco, categoria, qtdMin));
    System.out.println("Produto cadastrado com sucesso.");
}

    static void listarProdutos() {
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }

    static void filtrarPorCategoria(Scanner sc) {
        String cat = sc.nextLine();
        for (Produto p : produtos) {
            if (p.getCategoria().equalsIgnoreCase(cat)) {
                System.out.println(p);
            }
        }
    }

    static void ordenarProdutos() {
        produtos.sort(Comparator.comparing(Produto::getNomeDescricao));
        System.out.println("Produtos ordenados");
    }

    static void removerProduto(Scanner sc) {
        String nome = sc.nextLine();
        produtos.removeIf(p -> p.getNomeDescricao().equalsIgnoreCase(nome));
    }

    static void atualizarPreco(Scanner sc) {
        String nome = sc.nextLine();
        double novoPreco = sc.nextDouble();
        sc.nextLine();
        for (Produto p : produtos) {
            if (p.getNomeDescricao().equalsIgnoreCase(nome)) {
                p.setPrecoUnitario(novoPreco);
                break;
            }
        }
    }

    static void listarComSubtotal() {
        Map<String, Double> subtotalPorCategoria = new HashMap<>();
        for (Produto p : produtos) {
            subtotalPorCategoria.put(
                p.getCategoria(),
                subtotalPorCategoria.getOrDefault(p.getCategoria(), 0.0) + p.getSubtotal()
            );
        }
        double totalGeral = 0;
        for (String cat : subtotalPorCategoria.keySet()) {
            System.out.println(cat);
            System.out.println(subtotalPorCategoria.get(cat));
            totalGeral += subtotalPorCategoria.get(cat);
        }
        System.out.println(totalGeral);
    }
}
