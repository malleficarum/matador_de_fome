package com.example.matadordefome;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.util.Log;

/**
 * ProductAdapter: Classe adaptadora para exibir uma lista de produtos em um RecyclerView.
 * Gerencia a exibição de informações do produto e as interações com os itens da lista.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList; // Lista de produtos a ser exibida
    private ProductListActivity activity; // Referência à Activity para interações com o carrinho

    /**
     * Construtor do ProductAdapter.
     *
     * @param productList Lista de produtos a ser exibida.
     * @param activity Referência à Activity que manipula o carrinho.
     */
    public ProductAdapter(List<Product> productList, ProductListActivity activity) {
        this.productList = productList;
        this.activity = activity; // Inicializa a referência à Activity
    }

    /**
     * Cria e retorna um novo ViewHolder para representar um item na lista.
     *
     * @param parent ViewGroup pai onde o item será adicionado.
     * @param viewType Tipo de view (usado para múltiplos layouts, se necessário).
     * @return Um novo ProductViewHolder.
     */
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla o layout do item (item_product.xml)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    /**
     * Vincula os dados do produto a um ViewHolder.
     *
     * @param holder O ViewHolder onde os dados serão configurados.
     * @param position A posição do item na lista.
     */
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        // Obtém o produto correspondente à posição atual
        Product product = productList.get(position);

        // Define o nome e o preço do produto
        holder.productName.setText(product.getName());
        holder.productPrice.setText("R$ " + product.getPrice());

        // Carrega a imagem do produto usando o recurso indicado
        int imageResId = holder.itemView.getContext().getResources().getIdentifier(
                product.getImageResource(),
                "drawable",
                holder.itemView.getContext().getPackageName()
        );

        // Define a imagem no ImageView, se encontrada
        if (imageResId != 0) {
            holder.productImage.setImageResource(imageResId);
        } else {
            Log.e("ProductAdapter", "Imagem não encontrada: " + product.getImageResource());
        }

        // Configura o clique no botão "Comprar"
        holder.buyButton.setOnClickListener(v -> {
            // Adiciona o produto ao carrinho usando o método da Activity
            Log.d("ProductAdapter", "Produto adicionado ao carrinho: " + product.getName());
            ProductListActivity.addToCart(product);
            Toast.makeText(holder.itemView.getContext(), product.getName() + " adicionado ao carrinho", Toast.LENGTH_SHORT).show();
        });
    }

    /**
     * Retorna o número de itens na lista.
     *
     * @return Número total de produtos.
     */
    @Override
    public int getItemCount() {
        return productList.size();
    }

    /**
     * Atualiza a lista de produtos exibida pelo adapter.
     *
     * @param newList Nova lista de produtos.
     */
    public void updateList(List<Product> newList) {
        productList.clear(); // Limpa a lista atual
        productList.addAll(newList); // Adiciona os novos produtos
        notifyDataSetChanged(); // Notifica o adapter sobre as mudanças
    }

    /**
     * ProductViewHolder: Classe interna que representa cada item da lista.
     * Gerencia os elementos visuais do layout de cada produto.
     */
    static class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productPrice; // Exibe o nome e preço do produto
        ImageView productImage; // Exibe a imagem do produto
        Button buyButton; // Botão para comprar o produto

        /**
         * Construtor do ViewHolder.
         *
         * @param itemView A view raiz do item.
         */
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productImage = itemView.findViewById(R.id.productImage);
            buyButton = itemView.findViewById(R.id.buyButton);
        }
    }
}
