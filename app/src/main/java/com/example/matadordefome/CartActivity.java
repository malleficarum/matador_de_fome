package com.example.matadordefome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

    // Declaração das variáveis de UI
    private RecyclerView cartRecyclerView;  // RecyclerView para exibir os itens do carrinho
    private CartAdapter cartAdapter;  // Adapter para o RecyclerView
    private TextView totalPriceText;  // TextView para exibir o preço total do carrinho
    private Button checkoutButton;  // Botão para finalizar a compra
    private List<CartItem> cartItems;  // Lista de itens no carrinho
    private double totalPrice = 0.0;  // Preço total do carrinho

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);  // Define o layout da atividade

        // Inicialização dos componentes da interface
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        totalPriceText = findViewById(R.id.totalPriceText);
        checkoutButton = findViewById(R.id.checkoutButton);

        // Recebe os itens do carrinho enviados pela Activity anterior via Intent
        Intent intent = getIntent();
        cartItems = intent.getParcelableArrayListExtra("cartItems");  // Obtém a lista de itens do carrinho

        // Log para depuração, mostrando o número de itens no carrinho
        Log.d("MainActivity", "Itens enviados ao carrinho: " + cartItems.size());

        // Se o carrinho estiver vazio, cria uma lista vazia
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            Log.d("CartActivity", "Carrinho vazio!");
        } else {
            Log.d("CartActivity", "Carrinho carregado com " + cartItems.size() + " itens.");
        }

        // Inicializa o RecyclerView e configura o layout e adapter
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));  // Define o layout como LinearLayout
        cartAdapter = new CartAdapter(cartItems);  // Cria o adapter com os itens do carrinho
        cartRecyclerView.setAdapter(cartAdapter);  // Define o adapter para o RecyclerView

        // Atualiza o preço total do carrinho
        updateTotalPrice();

        // Configura o clique no botão de finalizar compra
        checkoutButton.setOnClickListener(v -> {
            Log.d("CartActivity", "Compra Finalizada!");

            // Passa os itens do carrinho para a PaymentActivity via Intent
            Intent paymentIntent = new Intent(CartActivity.this, PaymentActivity.class);
            paymentIntent.putParcelableArrayListExtra("cartItems", new ArrayList<>(cartItems));  // Envia os itens do carrinho
            startActivity(paymentIntent);  // Inicia a PaymentActivity
        });
    }

    // Atualiza o preço total do carrinho, levando em conta a quantidade de cada item
    private void updateTotalPrice() {
        totalPrice = 0.0;  // Zera o preço total
        // Itera sobre todos os itens do carrinho e soma o preço multiplicado pela quantidade
        for (CartItem item : cartItems) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        // Atualiza a TextView com o preço total formatado
        totalPriceText.setText("Preço Total: R$ " + String.format("%.2f", totalPrice));
        Log.d("CartActivity", "Preço Total: R$ " + totalPrice);  // Log de depuração
    }

    // Função para atualizar os itens no carrinho, chamada quando o carrinho é alterado
    public void updateCart(List<CartItem> updatedCartItems) {
        this.cartItems = updatedCartItems;  // Atualiza a lista de itens do carrinho
        cartAdapter.updateItems(updatedCartItems);  // Atualiza o adapter com os novos itens
        updateTotalPrice();  // Recalcula o preço total após a alteração
    }
}