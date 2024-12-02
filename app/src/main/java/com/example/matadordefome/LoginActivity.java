package com.example.matadordefome;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * LoginActivity: Tela de login do aplicativo.
 * Permite que o usuário insira suas credenciais para acessar a aplicação.
 */
public class LoginActivity extends AppCompatActivity {

    // Declaração dos elementos de interface do usuário
    private EditText emailEditText; // Campo de entrada para o email
    private EditText passwordEditText; // Campo de entrada para a senha
    private Button loginButton; // Botão para realizar o login
    private TextView registerRedirectText; // Texto para redirecionar ao registro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Define o layout XML associado a esta Activity
        setContentView(R.layout.activity_login);

        // Inicializa os elementos de interface do usuário
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        registerRedirectText = findViewById(R.id.registerText);

        // Configura o comportamento do botão de login
        loginButton.setOnClickListener(v -> {
            // Obtém os valores inseridos pelo usuário
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Valida se o campo de email foi preenchido
            if (TextUtils.isEmpty(email)) {
                emailEditText.setError("Email é necessário");
                return; // Interrompe a execução se o campo estiver vazio
            }

            // Valida se o campo de senha foi preenchido
            if (TextUtils.isEmpty(password)) {
                passwordEditText.setError("Senha é necessária");
                return; // Interrompe a execução se o campo estiver vazio
            }

            // Autentica o usuário usando o metodo da classe UserManager
            User loggedInUser = UserManager.authenticateUser(email, password);
            if (loggedInUser != null) {
                // Login bem-sucedido
                Toast.makeText(LoginActivity.this, "Login bem-sucedido!", Toast.LENGTH_SHORT).show();

                // Redireciona para a tela de listagem de produtos
                Intent intent = new Intent(LoginActivity.this, ProductListActivity.class);
                startActivity(intent);

                // Finaliza a Activity atual para impedir que o usuário volte para a tela de login
                finish();
            } else {
                // Exibe uma mensagem caso as credenciais sejam inválidas
                Toast.makeText(LoginActivity.this, "Credenciais inválidas", Toast.LENGTH_SHORT).show();
            }
        });

        // Configura o clique no texto "Cadastre-se" para redirecionar para a tela de registro
        registerRedirectText.setOnClickListener(v -> {
            // Inicia a Activity de registro
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        // Finaliza a aplicação ao pressionar o botão "voltar" na tela de login
        finish();
    }
}