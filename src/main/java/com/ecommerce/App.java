package com.ecommerce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {

    static List<Cliente> clientes = new ArrayList<Cliente>();
    static List<Produto> produtos = new ArrayList<Produto>();

    /**
     * @param args
     * @throws NumberFormatException
     * @throws IOException
     */

    public static void main(String[] args) throws NumberFormatException, IOException {

        System.out.println("========== SEJA BEM VINDO!========");

        menuInicial();

    }

    private static void menuInicial() throws NumberFormatException, IOException {
        int opcao = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
           
            System.out.println(
                    "Escolha as opções: \n (1) Cadastrar Produto \n (2) Cadastrar Cliente \n (3) Cadastrar Pedido \n (0) Sair");
            opcao = Integer.parseInt(reader.readLine());

        } while ( opcao > 3);
        cadastrar(opcao);

    }

    private static void cadastrar(int opcao) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        switch (opcao) {
            case 1:
                System.out.println("========== CADASTRAR PRODUTO ========");
                do {
                    Produto produto = new Produto();
                    System.out.println("Insira o nome do produto:");
                    produto.setNome(reader.readLine());
                    System.out.println("Insira a descrição do produto:");
                    produto.setDescricao(reader.readLine());
                    System.out.println("Insira o Valor do produto:");
                    produto.setPreco(Double.parseDouble(reader.readLine()));
                    produto.setId(produtos.size() + 1);
                    produtos.add(produto);
                    System.out.println("Deseja adicionar outro produto?: \n (1) Sim \n (2) Retornar Menu");
                    int escolha = Integer.parseInt(reader.readLine());
                    if (escolha == 2)
                        break;

                } while (true);
                menuInicial();
                break;

            case 2:
                System.out.println("========== CADASTRAR CLIENTE ========");
                do {
                    Cliente cliente = new Cliente();
                    System.out.println("Insira o nome do cliente:");
                    cliente.setNome(reader.readLine());
                    System.out.println("Insira o telefone do cliente:");
                    cliente.setteTefone(reader.readLine());
                    System.out.println("Insira endereço do cliente:");
                    cliente.setEndereco(reader.readLine());
                    cliente.setId(clientes.size() + 1);
                    clientes.add(cliente);
                    System.out.println("Deseja adicionar outro cliente?: \n (1) Sim \n (2) Voltar ao menu");
                    int escolha = Integer.parseInt(reader.readLine());
                    if (escolha == 2)
                        break;

                } while (true);

                menuInicial();
                break;

            case 3:
                System.out.println("========== CADASTRAR PEDIDOS ========");
                // do {
                Pedido pedido = new Pedido();
                if(clientes.size()==0){
                    System.out.println("====Nenhum cliente cadastrado====\n Cadastre um cliente antes! Voce será redirecionado ao menu. ");
                    menuInicial();
                break;
                }

               
                System.out.println("Selecione o cliente pelo seu índice: ");
                for (int i = 0; i < clientes.size(); i++) {
                    System.out.println("(" + i + "): " + clientes.get(i).getNome());
                }
                int indiceCliente = Integer.parseInt(reader.readLine());
                String nomeCliente = clientes.get(indiceCliente).getNome();
                pedido.setCliente(nomeCliente);
                if(produtos.size()==0){
                    System.out.println("====Nenhum produto cadastrado====\n Cadastre um produto antes! Voce será redirecionado ao menu. ");
                    menuInicial();
                break;
                }

                do {
                    System.out.println("Selecione o produto pelo seu índice: ");
                    for (int i = 0; i < produtos.size(); i++) {

                        System.out.println("(" + i + ")=> " + "Produto:" + produtos.get(i).getNome() + " === Valor: "
                                + produtos.get(i).getPreco());

                    }
                    int indiceProduto = Integer.parseInt(reader.readLine());
                    String produtoSelecionado = produtos.get(indiceProduto).getNome();
                    double valorProdutoSelecionado = produtos.get(indiceProduto).getPreco();

                    pedido.getProdutos().add(produtoSelecionado);
                    pedido.setValorTotal(valorProdutoSelecionado);

                    System.out.println("Deseja adicionar outro produto? \n (1) Sim \n (2) Não ");
                    int escolha = Integer.parseInt(reader.readLine());
                    if (escolha == 2) {

                        System.out.println(" ====== RELATÓRIO PEDIDO =======");
                        System.out.println("Número pedido: 1\nNome Cliente: " + pedido.getCliente() + "\nProdutos:"+ pedido.getProdutos() + "\nTotal do Pedido: R$ " + pedido.getValorTotal());
                        break;

                    }

                } while (true);
                

            default:
                System.out.println("======= SESSÃO ENCERRADA =====");
                break;
        }

    }

}
