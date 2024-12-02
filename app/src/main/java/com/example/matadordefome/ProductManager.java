package com.example.matadordefome;

import java.util.ArrayList;
import java.util.List;

/**
 * ProductManager: Classe responsável por gerenciar a lista de produtos da aplicação.
 * Inclui métodos para adicionar, recuperar e inicializar os produtos disponíveis.
 */
public class ProductManager {

    // Lista estática que armazena todos os produtos
    private static List<Product> allProducts = new ArrayList<>();

    /**
     * Adiciona um produto à lista.
     *
     * @param product Produto a ser adicionado.
     */
    public static void addProduct(Product product) {
        allProducts.add(product);
    }

    /**
     * Retorna a lista completa de produtos.
     *
     * @return Lista de todos os produtos.
     */
    public static List<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Inicializa a lista de produtos, adicionando itens padrão.
     * Verifica se a lista já foi inicializada para evitar duplicações.
     */
    public static void initializeProducts() {
        // Verifica se a lista está vazia antes de inicializar
        if (allProducts.isEmpty()) {
            addProduct(new Product("Hamburguer de carne bovina", "29,99", "@drawable/ic_hamburguer", false, "Comidas"));
            addProduct(new Product("Pizza de Queijo", "49,99", "@drawable/ic_pizza", false, "Comidas"));
            addProduct(new Product("Refrigerante", "9,99", "@drawable/ic_refri", false, "Bebidas"));
            addProduct(new Product("Batata Frita Especial", "15,99", "@drawable/ic_fritas", true, "Comidas"));
            addProduct(new Product("Cachorro Quente", "24,99", "@drawable/ic_hotdog", true, "Comidas"));
            addProduct(new Product("Sobremesa Gelada", "14,99", "@drawable/ic_milkshake", true, "Sobremesas"));
        }
    }
}
