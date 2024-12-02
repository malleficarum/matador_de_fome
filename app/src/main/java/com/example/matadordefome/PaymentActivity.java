package com.example.matadordefome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.RadioButton;

import java.util.List;

/**
 * PaymentActivity: Tela de pagamento do aplicativo.
 * Responsável por exibir o resumo do carrinho e permitir a seleção do metodo de pagamento.
 */
public class PaymentActivity extends AppCompatActivity {

    // Declaração dos elementos de interface do usuário
    private RecyclerView paymentRecyclerView; // RecyclerView para exibir os itens do carrinho
    private TextView totalPriceTextView; // Exibe o preço total do pedido
    private Button paymentButton; // Botão para finalizar o pagamento
    private CartAdapter paymentAdapter; // Adapter para gerenciar os itens do carrinho
    private List<CartItem> cartItems; // Lista dos itens do carrinho
    private double totalPrice = 0.0; // Total do pedido

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout associado à Activity
        setContentView(R.layout.activity_payment);

        // Inicializando os elementos de interface do usuário
        paymentRecyclerView = findViewById(R.id.paymentRecyclerView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        paymentButton = findViewById(R.id.paymentButton);

        // Recebe os itens do carrinho através da Intent
        Intent intent = getIntent();
        cartItems = intent.getParcelableArrayListExtra("cartItems");

        // Calcula o total dos itens no carrinho
        for (CartItem item : cartItems) {
            totalPrice += item.getTotalPrice(); // Soma o preço total de cada item
        }

        // Exibe o valor total formatado
        totalPriceTextView.setText(String.format("Total: R$ %.2f", totalPrice));

        // Configura o RecyclerView para exibir os itens do carrinho
        paymentAdapter = new CartAdapter(cartItems);
        paymentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        paymentRecyclerView.setAdapter(paymentAdapter);

        // Configura o clique no botão de pagamento
        paymentButton.setOnClickListener(v -> finalizePayment());
    }

    /**
     * Metodo para finalizar o pagamento.
     * Obtém o metodo de pagamento selecionado e exibe uma mensagem de sucesso.
     */
    private void finalizePayment() {
        // Obtém o RadioGroup com as opções de metodo de pagamento
        RadioGroup paymentMethodGroup = findViewById(R.id.paymentMethodGroup);

        // Recupera o ID do RadioButton selecionado
        int selectedId = paymentMethodGroup.getCheckedRadioButtonId();

        // Verifica qual metodo de pagamento foi escolhido
        RadioButton selectedPaymentMethod = findViewById(selectedId);
        String paymentMethod = selectedPaymentMethod.getText().toString(); // Texto do metodo de pagamento selecionado

        // Exibe uma mensagem de sucesso com o metodo de pagamento escolhido
        Toast.makeText(this, "Pagamento realizado com sucesso via " + paymentMethod, Toast.LENGTH_SHORT).show();

        // Finaliza a Activity de pagamento
        finish();
    }
}
