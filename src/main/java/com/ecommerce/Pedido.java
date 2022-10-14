package com.ecommerce;


import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private String cliente;
    private List<String> produtos;
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
