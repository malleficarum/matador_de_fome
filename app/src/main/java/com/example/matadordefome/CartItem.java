package com.example.matadordefome;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem implements Parcelable {

    // Variáveis que armazenam as informações de um item do carrinho
    private String name;  // Nome do produto
    private int quantity;  // Quantidade do produto no carrinho
    private double price;  // Preço unitário do produto
    private int imageResource;  // Recurso de imagem (ID) do produto

    // Construtor que inicializa os atributos de um item no carrinho
    public CartItem(String name, int quantity, double price, int imageResource) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imageResource = imageResource;  // Inicializa a imagem
    }

    // Metodo para incrementar a quantidade do item no carrinho
    public void incrementQuantity() {
        this.quantity++;  // Aumenta a quantidade em 1
    }

    // Métodos Getters: Permitem acessar as propriedades do CartItem

    public String getName() {
        return name;  // Retorna o nome do produto
    }

    public int getQuantity() {
        return quantity;  // Retorna a quantidade do produto no carrinho
    }

    public double getPrice() {
        return price;  // Retorna o preço unitário do produto
    }

    // Metodo que calcula o preço total com base na quantidade
    public double getTotalPrice() {
        return price * quantity;  // Preço total = preço unitário * quantidade
    }

    public int getImageResource() {
        return imageResource;  // Retorna o ID do recurso de imagem
    }

    // Setter para a imagem do produto (caso precise atualizar a imagem)
    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;  // Atualiza o ID do recurso de imagem
    }

    // Métodos Parcelable (necessários para enviar o CartItem entre atividades)

    // Construtor que lê o CartItem de um Parcel (usado para restaurar o objeto de um Intent)
    protected CartItem(Parcel in) {
        name = in.readString();  // Lê o nome do produto
        quantity = in.readInt();  // Lê a quantidade
        price = in.readDouble();  // Lê o preço unitário
        imageResource = in.readInt();  // Lê o recurso de imagem (ID)
    }

    // Criador (Creator) que ajuda a criar e restaurar o CartItem de um Parcel
    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);  // Cria um novo CartItem a partir de um Parcel
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];  // Cria um array de CartItems
        }
    };

    // Metodo necessário para a interface Parcelable, retorna um valor de descrição do conteúdo
    @Override
    public int describeContents() {
        return 0;  // Não há objetos com descrições especiais
    }

    // Metodo necessário para a interface Parcelable, escreve o CartItem para um Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);  // Escreve o nome do produto
        dest.writeInt(quantity);  // Escreve a quantidade
        dest.writeDouble(price);  // Escreve o preço unitário
        dest.writeInt(imageResource);  // Escreve o recurso de imagem (ID)
    }
}
