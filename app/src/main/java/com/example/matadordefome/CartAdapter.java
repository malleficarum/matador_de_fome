package com.example.matadordefome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.content.ContextCompat;
import android.util.Log;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;  // Lista de itens do carrinho

    // Construtor do CartAdapter que recebe a lista de itens do carrinho
    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        Log.d("CartAdapter", "CartAdapter criado com " + cartItems.size() + " itens.");
    }

    // Cria e retorna uma nova instância do ViewHolder (cart_item.xml) para cada item no RecyclerView
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("CartAdapter", "onCreateViewHolder chamado.");
        // Infla o layout do item do carrinho
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);  // Retorna um novo CartViewHolder
    }

    // Vincula os dados de um item (posição) da lista aos elementos de UI do ViewHolder
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Log.d("CartAdapter", "onBindViewHolder chamado para posição " + position);
        CartItem item = cartItems.get(position);  // Obtém o item da lista no índice atual
        Log.d("CartAdapter", "Item vinculado: " + item.getName());

        // Configura os dados do item nos elementos da interface
        holder.productName.setText(item.getName());
        holder.productPrice.setText(String.format("R$ %.2f", item.getPrice()));
        holder.productQuantity.setText(String.valueOf(item.getQuantity()));
        holder.productTotal.setText(String.format("R$ %.2f", item.getTotalPrice()));

        // Verifica se o item tem um recurso de imagem válido e configura a imagem do produto
        if (item.getImageResource() != 0) {
            Log.d("CartAdapter", "Loading image for product: " + item.getName());
            holder.productImage.setImageDrawable(ContextCompat.getDrawable(holder.productImage.getContext(), item.getImageResource()));
        } else {
            Log.d("CartAdapter", "No image resource for product: " + item.getName());
            holder.productImage.setImageResource(R.drawable.placeholder_image);  // Imagem de placeholder se não houver recurso
        }

        // Configura a ação do botão de remover item do carrinho
        holder.removeButton.setOnClickListener(v -> {
            Log.d("CartAdapter", "Removing item: " + item.getName());
            cartItems.remove(position);  // Remove o item da lista
            notifyItemRemoved(position);  // Notifica o RecyclerView para atualizar a visualização
            // Exibe um toast notificando a remoção do item
            Toast.makeText(v.getContext(), item.getName() + " removido do carrinho", Toast.LENGTH_SHORT).show();
            // Atualiza o carrinho na CartActivity
            ((CartActivity) v.getContext()).updateCart(cartItems);
        });
    }

    // Retorna o número total de itens no carrinho
    @Override
    public int getItemCount() {
        Log.d("CartAdapter", "getItemCount chamado. Itens: " + cartItems.size());
        return cartItems.size();
    }

    // ViewHolder que representa um item individual no RecyclerView
    public static class CartViewHolder extends RecyclerView.ViewHolder {

        // Referências para os componentes de UI do item (nome, preço, quantidade, imagem, etc.)
        TextView productName, productPrice, productQuantity, productTotal;
        Button removeButton;
        ImageView productImage;

        // Construtor do ViewHolder que encontra e inicializa os componentes da interface do item
        public CartViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            productTotal = itemView.findViewById(R.id.totalPrice);
            removeButton = itemView.findViewById(R.id.removeButton);
            productImage = itemView.findViewById(R.id.productImage);
        }
    }

    // Função para atualizar os itens do carrinho, chamada quando a lista de itens é modificada
    public void updateItems(List<CartItem> updatedCartItems) {
        this.cartItems = updatedCartItems;  // Atualiza a lista de itens
        notifyDataSetChanged();  // Notifica o RecyclerView para atualizar a visualização
    }
}
