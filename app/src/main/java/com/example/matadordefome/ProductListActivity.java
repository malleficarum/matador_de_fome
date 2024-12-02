package com.example.matadordefome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/**
 * ProductListActivity: Tela principal que exibe a lista de produtos, produtos famosos,
 * e oferece funcionalidades de busca e navegação por categorias.
 */
public class ProductListActivity extends AppCompatActivity {

    private TextView welcomeTextView; // Exibe a saudação ao usuário
    private EditText searchBar; // Barra de busca para filtrar produtos
    private RecyclerView productRecyclerView; // RecyclerView para a lista geral de produtos
    private RecyclerView famousRecyclerView; // RecyclerView para produtos famosos
    private ProductAdapter productAdapter; // Adaptador para a lista geral de produtos
    private ProductAdapter famousAdapter; // Adaptador para produtos famosos
    private List<Product> productList; // Lista completa de produtos
    private ImageView cartButton; // Botão que abre o carrinho

    // Lista estática que representa os itens no carrinho
    private static List<CartItem> cartItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        Log.d("ProductListActivity", "onCreate chamado"); // Log para depuração

        // Inicializando elementos da interface
        welcomeTextView = findViewById(R.id.welcomeTextView);
        searchBar = findViewById(R.id.searchBar);
        productRecyclerView = findViewById(R.id.productRecyclerView);
        famousRecyclerView = findViewById(R.id.famousRecyclerView);
        cartButton = findViewById(R.id.cartButton);

        // Botões de categorias
        LinearLayout comidasButton = findViewById(R.id.comidasButton);
        LinearLayout bebidasButton = findViewById(R.id.bebidasButton);
        LinearLayout sobremesasButton = findViewById(R.id.sobremesasButton);

        // Obtendo o usuário logado e configurando a saudação
        User currentUser = UserManager.getLoggedInUser();
        if (currentUser != null) {
            Log.d("ProductListActivity", "Usuário logado: " + currentUser.getName());
            welcomeTextView.setText("Bem-vindo, " + currentUser.getName() + "!");
        } else {
            Log.d("ProductListActivity", "Nenhum usuário logado");
            welcomeTextView.setText("Bem-vindo!");
        }

        // Inicializando a lista de produtos
        productList = new ArrayList<>();
        productList.add(new Product("Hamburguer de carne bovina", "29.99", "ic_hamburguer", false, "Comidas"));
        productList.add(new Product("Pizza de Queijo", "49.99", "@drawable/ic_pizza", false, "Comidas"));
        productList.add(new Product("Refrigerante", "9.99", "@drawable/ic_refri", false, "Bebidas"));
        productList.add(new Product("Batata Frita Especial", "15.99", "@drawable/ic_fritas", true, "Comidas"));
        productList.add(new Product("Cachorro Quente", "24.99", "@drawable/ic_hotdog", true, "Comidas"));
        productList.add(new Product("Sobremesa Gelada", "14.99", "@drawable/ic_milkshake", true, "Sobremesas"));

        Log.d("ProductListActivity", "Lista de produtos inicializada com " + productList.size() + " itens");

        // Configurando o RecyclerView e o adaptador para produtos
        productAdapter = new ProductAdapter(productList, this);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setAdapter(productAdapter);

        // Configurando o RecyclerView e o adaptador para produtos famosos
        List<Product> famousProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.isFamous()) {
                famousProducts.add(product);
                Log.d("ProductListActivity", "Produto famoso adicionado: " + product.getName());
            }
        }

        famousAdapter = new ProductAdapter(famousProducts, this);
        famousRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        famousRecyclerView.setAdapter(famousAdapter);

        // Configurando a barra de busca
        searchBar.setOnEditorActionListener((v, actionId, event) -> {
            String query = searchBar.getText().toString().trim();
            Log.d("ProductListActivity", "Busca realizada: " + query);
            filterProducts(query); // Filtra os produtos com base na busca
            return true;
        });

        // Configurando os botões de categorias
        comidasButton.setOnClickListener(v -> openCategory("Comidas"));
        bebidasButton.setOnClickListener(v -> openCategory("Bebidas"));
        sobremesasButton.setOnClickListener(v -> openCategory("Sobremesas"));

        // Configurando o botão do carrinho
        cartButton.setOnClickListener(v -> {
            Log.d("ProductListActivity", "Botão do carrinho clicado");
            Intent intent = new Intent(ProductListActivity.this, CartActivity.class);
            intent.putParcelableArrayListExtra("cartItems", new ArrayList<>(cartItems)); // Passa os itens do carrinho
            startActivity(intent);
        });
    }

    /**
     * Filtra a lista de produtos com base em uma query de busca.
     *
     * @param query Termo de busca.
     */
    private void filterProducts(String query) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(product);
                Log.d("ProductListActivity", "Produto encontrado na busca: " + product.getName());
            }
        }
        productAdapter.updateList(filteredList); // Atualiza a lista no adaptador
    }

    /**
     * Abre a Activity para uma categoria específica.
     *
     * @param category Categoria selecionada.
     */
    private void openCategory(String category) {
        Log.d("ProductListActivity", "Abrindo categoria: " + category);
        Intent intent = new Intent(this, CategoryActivity.class);
        intent.putExtra("category", category); // Passa a categoria como extra
        startActivity(intent);
    }

    /**
     * Adiciona um produto ao carrinho.
     *
     * @param product Produto a ser adicionado.
     */
    public static void addToCart(Product product) {
        for (CartItem item : cartItems) {
            if (item.getName().equals(product.getName())) {
                Log.d("Cart", "Produto encontrado. Incrementando quantidade: " + item.getName());
                item.incrementQuantity();
                return;
            }
        }
        Log.d("Cart", "Adicionando novo produto ao carrinho: " + product.getName());

        // Obter o ID do recurso da imagem a partir do nome do recurso
        int imageResId = getImageResourceByName(product.getImageResource());

        cartItems.add(new CartItem(
                product.getName(),
                1,
                Double.parseDouble(product.getPrice()),
                imageResId // Passando o ID do recurso da imagem
        ));
    }

    /**
     * Obtém o ID de recurso de uma imagem a partir do nome.
     *
     * @param resourceName Nome do recurso.
     * @return ID do recurso.
     */
    private static int getImageResourceByName(String resourceName) {
        try {
            return R.drawable.class.getField(resourceName).getInt(null);
        } catch (Exception e) {
            Log.e("Cart", "Erro ao obter o recurso de imagem: " + resourceName, e);
            return R.drawable.placeholder_image; // Retorna uma imagem de placeholder caso não encontre
        }
    }
}
