package com.example.matadordefome;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity: Classe principal do aplicativo.
 * Esta Activity é responsável por exibir a tela principal da aplicação.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Define o layout associado a esta Activity
        // Certifique-se de que o arquivo XML "activity_main" está configurado corretamente
        setContentView(R.layout.activity_main);
    }
}
