package com.ecommerce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {

    static List<Cliente> clientes = new ArrayList<Cliente>();
    static List<Produto> produtos = new ArrayList<Produto>();
    static List<Pedido> pedidos = new ArrayList<Pedido>();

    /**
     * @param args
     * @throws NumberFormatException
     * @throws IOException
     * @throws InterruptedException
     */

    public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException {

        System.out.println("========== SEJA BEM VINDO!========");

        menuInicial("");

    }

    private static void menuInicial(String mensagem) throws NumberFormatException, IOException, InterruptedException {
        int opcao = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Aviso caso seja selecionada opção que não está na lista
        if (!mensagem.isEmpty()) {
            clearScreen();
            System.out.println(mensagem);
            TimeUnit.SECONDS.sleep(1);
            clearScreen();

        }

        System.out.println(
                "Escolha as opções: \n (1) Cadastrar Produto \n (2) Cadastrar Cliente \n (3) Cadastrar Pedido \n (4) Relatório Pedidos \n (0) Sair");
        opcao = Integer.parseInt(reader.readLine());

        if (opcao > 4) menuInicial("Opção inválida. Tente novamente!");
        menuOpcao(opcao);

    }

    private static void menuOpcao(int opcao) throws IOException, NumberFormatException, InterruptedException {
        switch (opcao) {
            case 1:
                TimeUnit.SECONDS.sleep(1);
                cadastrarProduto();
                clearScreen();
                menuInicial("");
                break;

            case 2:
                TimeUnit.SECONDS.sleep(1);
                cadastrarCliente();
                TimeUnit.SECONDS.sleep(1);
                clearScreen();
                menuInicial("");
                break;

            case 3:
                clearScreen();
                System.out.println("========== CADASTRAR PEDIDOS ========");
                if (clientes.size() == 0) {
                    System.out.println(
                            "==== Nenhum cliente cadastrado! ====\n Cadastre um cliente antes! Voce será redirecionado ao menu. ");
                    TimeUnit.SECONDS.sleep(5);
                    clearScreen();
                    menuInicial("");
                    break;
                }
                if (produtos.size() == 0) {
                    System.out.println(
                            "====Nenhum produto cadastrado====\n Cadastre um produto antes! Voce será redirecionado ao menu. ");
                    TimeUnit.SECONDS.sleep(5);
                    clearScreen();
                    menuInicial("");
                    break;
                }

                cadastrarPedido();
                TimeUnit.SECONDS.sleep(1);
                clearScreen();
                menuInicial("");
                break;
            case 4:
                clearScreen();
                System.out.println(" ====== RELATÓRIO PEDIDO =======");
                if (pedidos.size() == 0) {
                    System.out.println(
                            "====Não foi possível gerar relatório, pois não há pedidos cadastrados ====\n Cadastre um pedido antes! Voce será redirecionado ao menu. ");
                    TimeUnit.SECONDS.sleep(5);
                    clearScreen();
                    menuInicial("");
                    break;
                }

                relatorioPedido();
                TimeUnit.SECONDS.sleep(2);
                clearScreen();
                menuInicial("");
                break;

               
            default:
                System.out.println("======= SESSÃO ENCERRADA =====");
                break;
        }

    }

    private static void relatorioPedido() throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Selecione o número do seu pedido: ");
        for (int i = 0; i < pedidos.size(); i++) {
            System.out.println("(" + pedidos.get(i).getId() + "): " + pedidos.get(i).getCliente());
        }

        int idPedido = Integer.parseInt(reader.readLine());
        clearScreen();

        System.out.println(" ====== RELATÓRIO PEDIDO =======");
        System.out.println("Número Pedido: " + pedidos.get(idPedido-1).getId());
        System.out.println("Cliente: " + pedidos.get(idPedido-1).getCliente());
        System.out.println("Produtos: " );
        List<String> itens = pedidos.get(idPedido-1).getProdutos();
        for (int i = 0; i < itens.size(); i++) {
            System.out.println("Item "+ (i+1) +":"  + itens.get(i));
        }
        System.out.println("Total do Pedido: R$ " + pedidos.get(idPedido-1).getValorTotal()+"\n=================== \n");

        System.out.println("Deseja gerar outro relatório de pedido?: \n (1) Sim \n (2) Não");
        int escolha = Integer.parseInt(reader.readLine());
        if (escolha == 1) relatorioPedido();
    }

    /**
     * @throws IOException
     * @throws NumberFormatException
     * 
     */
    private static void cadastrarPedido() throws NumberFormatException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pedido pedido = new Pedido();

        System.out.println("Selecione o cliente pelo seu índice: ");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println("(" + i + "): " + clientes.get(i).getNome());
        }
        int indiceCliente = Integer.parseInt(reader.readLine());
        String nomeCliente = clientes.get(indiceCliente).getNome();
        pedido.setCliente(nomeCliente);
        pedido.setId(pedidos.size() + 1);

        int escolha = 0;
        int qtdeIten = 1;
        do {
            clearScreen();
            System.out.println("========== CADASTRAR PEDIDOS ========");
            System.out.println("Selecione o item " + qtdeIten+" do pedido pelo seu índice: ");
            for (int i = 0; i < produtos.size(); i++) {

                System.out.println("(" + i + ")=> " + "Produto:" + produtos.get(i).getNome() + " === Valor: "
                        + produtos.get(i).getPreco());

            }
            int indiceProduto = Integer.parseInt(reader.readLine());
            String produtoSelecionado = produtos.get(indiceProduto).getNome();
            double valorProdutoSelecionado = produtos.get(indiceProduto).getPreco();

            pedido.getProdutos().add(produtoSelecionado);
            pedido.setValorTotal(valorProdutoSelecionado);
            qtdeIten +=1;

            System.out.println("============== \n Deseja adicionar outro produto? \n (1) Sim \n (2) Não ");
            escolha = Integer.parseInt(reader.readLine());
        } while (escolha == 1);
        pedidos.add(pedido);
    }

    private static void cadastrarCliente() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        clearScreen();
        System.out.println("========== CADASTRAR CLIENTE ========");
        Cliente cliente = new Cliente();
        System.out.println("Insira o nome do cliente:");
        cliente.setNome(reader.readLine());
        System.out.println("Insira o telefone do cliente:");
        cliente.setTelefone(reader.readLine());
        System.out.println("Insira endereço do cliente:");
        cliente.setEndereco(reader.readLine());
        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);
        System.out.println("============== \n Deseja adicionar outro cliente?: \n (1) Sim \n (2) Não");
        int escolha = Integer.parseInt(reader.readLine());
        if (escolha == 1) cadastrarCliente();

    }

    private static void cadastrarProduto() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        clearScreen();
        System.out.println("========== CADASTRAR PRODUTO ========");
        Produto produto = new Produto();
        System.out.println("Insira o nome do produto:");
        produto.setNome(reader.readLine());
        System.out.println("Insira a descrição do produto:");
        produto.setDescricao(reader.readLine());
        System.out.println("Insira o Valor do produto:");
        produto.setPreco(Double.parseDouble(reader.readLine()));
        produto.setId(produtos.size() + 1);
        produtos.add(produto);
        System.out.println("============== \n Deseja adicionar outro produto?: \n (1) Sim \n (2) Não");
        int escolha = Integer.parseInt(reader.readLine());
        if (escolha == 1) cadastrarProduto();

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
