/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;


/**
 *
 * @author moc3jvl
 */
public class Produto {
    private int id;
    private String nomeProduto;
    private double valorUnitario;
    private String marca;
    private String categoria;

    public Produto(int id, String nomeProduto, double valorUnitario, String marca, String categoria) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.valorUnitario = valorUnitario;
        this.marca = marca;
        this.categoria = categoria;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
