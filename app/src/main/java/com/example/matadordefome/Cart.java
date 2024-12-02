package com.example.matadordefome;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class Cart {

    // Lista estática para armazenar os itens do carrinho
    private static List<CartItem> cartItems = new ArrayList<>();

    // Adiciona um item ao carrinho ou atualiza a quantidade caso o item já exista
    public static void addToCart(Product product) {
        // Verifica se o produto já está no carrinho
        for (CartItem item : cartItems) {
            // Se o produto já estiver no carrinho, incrementa a quantidade
            if (item.getName().equals(product.getName())) {
                item.incrementQuantity();
                Log.d("Cart", "Produto atualizado: " + item.getName() + ". Nova quantidade: " + item.getQuantity());
                return;  // Sai da função após atualizar
            }
        }

        // Se o produto não estiver no carrinho, adiciona um novo item
        String priceString = product.getPrice(); // Preço do produto, já vindo do objeto Product como String

        // Verifica se o preço contém vírgula (formato utilizado em alguns países) e substitui por ponto
        if (priceString.contains(",")) {
            priceString = priceString.replace(",", ".");
        }

        // Tenta converter a string de preço para o tipo double
        try {
            // Converte a string para double
            double price = Double.parseDouble(priceString);

            // Converte o nome do recurso de imagem para o formato correto (int)
            int imageResource = getImageResourceFromString(product.getImageResource());

            // Adiciona um novo item ao carrinho com a quantidade inicial de 1
            cartItems.add(new CartItem(product.getName(), 1, price, imageResource));
            Log.d("Cart", "Produto adicionado ao carrinho: " + product.getName() + ", Preço: " + price);
        } catch (NumberFormatException e) {
            // Caso o formato do preço seja inválido, exibe um erro no log
            Log.e("Cart", "Erro ao converter o preço para double: " + priceString);
            e.printStackTrace();
        }
    }

    // Função para converter a String do imageResource (nome do recurso) para int (ID do recurso)
    private static int getImageResourceFromString(String imageResourceName) {
        int resID = -1; // Caso não encontre um recurso válido, retornará -1
        try {
            // Usa reflexão para obter o recurso da imagem a partir do nome (String)
            resID = R.drawable.class.getField(imageResourceName).getInt(null);
        } catch (Exception e) {
            // Caso ocorra erro ao obter o recurso da imagem, exibe um erro no log
            Log.e("Cart", "Erro ao obter o recurso da imagem: " + imageResourceName);
        }
        return resID;
    }

    // Retorna a lista de itens atualmente no carrinho
    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    // Limpa todos os itens do carrinho
    public static void clearCart() {
        cartItems.clear();
    }
}
