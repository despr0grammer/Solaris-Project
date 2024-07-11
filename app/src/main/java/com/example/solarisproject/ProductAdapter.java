package com.example.solarisproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }

    public ProductAdapter(List<Product> productList, OnProductClickListener listener) {
        this.productList = productList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product, listener);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textName;
        private TextView textDescription;
        private TextView textPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.product_image);
            textName = itemView.findViewById(R.id.product_name);
            textDescription = itemView.findViewById(R.id.product_description);
            textPrice = itemView.findViewById(R.id.product_price);
        }

        public void bind(final Product product, final OnProductClickListener listener) {
            imageView.setImageResource(product.getImageResId());
            textName.setText(product.getName());
            textDescription.setText(product.getDescription());
            String formattedPrice = NumberFormat.getNumberInstance(Locale.getDefault()).format(product.getPrice());
            textPrice.setText(itemView.getContext().getString(R.string.clp_price_format, formattedPrice));

            itemView.setOnClickListener(v -> listener.onProductClick(product));
        }
    }
}
