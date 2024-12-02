package com.example.matadordefome;

/**
 * Product: Classe que representa um produto no aplicativo.
 * Armazena informações sobre o nome, preço, imagem, popularidade e categoria do produto.
 */
public class Product {

    // Atributos da classe Product
    private String name; // Nome do produto
    private String price; // Preço do produto, representado como uma String
    private String imageResource; // Caminho ou URL para o recurso da imagem do produto
    private boolean isFamous; // Indica se o produto é popular ou famoso
    private String category; // Categoria à qual o produto pertence (ex: "Bebidas", "Comidas")

    /**
     * Construtor da classe Product.
     * Inicializa os atributos do produto.
     *
     * @param name Nome do produto
     * @param price Preço do produto
     * @param imageResource Caminho ou URL da imagem do produto
     * @param isFamous Indica se o produto é famoso/popular
     * @param category Categoria do produto
     */
    public Product(String name, String price, String imageResource, boolean isFamous, String category) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
        this.isFamous = isFamous;
        this.category = category;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return Nome do produto.
     */
    public String getName() {
        return name;
    }

    /**
     * Retorna o preço do produto.
     *
     * @return Preço do produto.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Retorna o recurso da imagem do produto.
     *
     * @return Caminho ou URL da imagem do produto.
     */
    public String getImageResource() {
        return imageResource;
    }

    /**
     * Verifica se o produto é famoso/popular.
     *
     * @return Verdadeiro se o produto é famoso, falso caso contrário.
     */
    public boolean isFamous() {
        return isFamous;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return Categoria do produto.
     */
    public String getCategory() {
        return category;
    }
}
