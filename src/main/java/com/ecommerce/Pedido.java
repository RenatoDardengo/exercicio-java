package com.ecommerce;


import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private String cliente;             //Não é array, pois 1 pedido só pode ter 1 cliente (1 objeto cliente).
    private List<String> produtos;      // É array, pois 1 pedido pode ter vários produtos
    private double valorTotal;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal += valorTotal;
    }

    public List<String> getProdutos() {
        if(this.produtos==null){
            this.produtos= new ArrayList<String>();
        }
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

}
