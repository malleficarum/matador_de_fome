package com.example.matadordefome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * RegisterActivity: Tela de cadastro de novos usuários.
 * Permite que os usuários se registrem fornecendo nome, email, endereço e senha.
 */
public class RegisterActivity extends AppCompatActivity {

    // Elementos de interface do usuário
    private EditText nameEditText;         // Campo de entrada para o nome
    private EditText emailEditText;       // Campo de entrada para o email
    private EditText addressEditText;     // Campo de entrada para o endereço
    private EditText passwordEditText;    // Campo de entrada para a senha
    private Button registerButton;        // Botão para efetuar o registro
    private TextView loginRedirectText;   // Texto que redireciona para a tela de login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Define o layout XML associado

        // Inicializando os elementos de UI
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailRegisterEditText);
        addressEditText = findViewById(R.id.addressEditText);
        passwordEditText = findViewById(R.id.passwordRegisterEditText);
        registerButton = findViewById(R.id.registerButton);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        // Configurando o comportamento do botão de registro
        registerButton.setOnClickListener(v -> {
            // Obtém os valores inseridos nos campos de texto
            String name = nameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            // Valida os campos obrigatórios
            if (name.isEmpty()) {
                nameEditText.setError("Nome é necessário");
                return;
            }
            if (email.isEmpty()) {
                emailEditText.setError("Email é necessário");
                return;
            }
            if (address.isEmpty()) {
                addressEditText.setError("Endereço é necessário");
                return;
            }
            if (password.isEmpty()) {
                passwordEditText.setError("Senha é necessária");
                return;
            }

            // Cria um novo usuário e o adiciona ao gerenciador de usuários
            User newUser = new User(name, email, address, password);
            UserManager.addUser(newUser);

            // Exibe uma mensagem de sucesso
            Toast.makeText(RegisterActivity.this, "Cadastro bem-sucedido!", Toast.LENGTH_SHORT).show();

            // Redireciona o usuário para a tela de login
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);

            // Fecha a atividade atual para evitar que o usuário volte a ela com o botão de voltar
            finish();
        });

        // Configurando o clique no texto "Faça login" para redirecionar para a tela de login
        loginRedirectText.setOnClickListener(v -> {
            // Redireciona para a tela de login
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);

            // Fecha a atividade atual
            finish();
        });
    }
}
