package com.example.matadordefome;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private TextView categoryTitle;  // Elemento TextView para exibir o título da categoria
    private RecyclerView productRecyclerView;  // RecyclerView para listar os produtos
    private ProductAdapter productAdapter;  // Adapter para popular o RecyclerView com os produtos filtrados
    private List<Product> productList;  // Lista de todos os produtos
    private List<Product> filteredProducts;  // Lista de produtos filtrados pela categoria

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Inicializando os elementos da interface
        categoryTitle = findViewById(R.id.categoriesTitle);  // Acessa o TextView para o título da categoria
        productRecyclerView = findViewById(R.id.productRecyclerView);  // Acessa o RecyclerView que irá listar os produtos

        // Recuperando a categoria passada pela intent
        String category = getIntent().getStringExtra("category");  // Obtém o nome da categoria passada pela Activity anterior
        Log.d("CategoryActivity", "Categoria recebida: " + category);  // Log para verificar o valor da categoria recebida

        // Verifica se a categoria foi passada e configura o título da categoria
        if (category != null && categoryTitle != null) {
            categoryTitle.setText("Categoria: " + category);  // Exibe a categoria no título
        } else {
            Log.e("CategoryActivity", "Categoria não foi passada ou 'categoryTitle' é nulo");  // Log de erro se a categoria não foi passada corretamente
        }

        // Inicializando os produtos
        ProductManager.initializeProducts();  // Certifique-se de que esse metodo inicializa corretamente a lista de produtos
        productList = ProductManager.getAllProducts();  // Obtém a lista de todos os produtos disponíveis

        // Filtrando os produtos pela categoria recebida
        filteredProducts = new ArrayList<>();  // Cria uma nova lista para armazenar os produtos filtrados pela categoria
        for (Product product : productList) {
            if (product.getCategory().equalsIgnoreCase(category)) {
                filteredProducts.add(product);  // Adiciona à lista os produtos que pertencem à categoria
            }
        }

        // Verifica se algum produto foi encontrado para a categoria
        if (filteredProducts.isEmpty()) {
            Log.e("CategoryActivity", "Nenhum produto encontrado para a categoria: " + category);  // Log de erro se nenhum produto for encontrado
        }

        // Configura o RecyclerView com os produtos filtrados
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));  // Define o layout do RecyclerView como uma lista linear

        // Cria o adapter para o RecyclerView, passando a lista de produtos filtrados
        // Note que o segundo parâmetro está fazendo referência à ProductListActivity
        productAdapter = new ProductAdapter(filteredProducts, (ProductListActivity) getParent());  // Certifique-se de que a Activity é uma ProductListActivity
        productRecyclerView.setAdapter(productAdapter);  // Define o adapter para o RecyclerView

        // Notifica o adapter caso haja alguma modificação na lista de produtos
        productAdapter.notifyDataSetChanged();  // Notifica o adapter para garantir que ele seja atualizado
    }
}
