package com.example.solarisproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StoreActivity extends AppCompatActivity {

    private TextView textTotalPrice;
    private Button buttonAddToCart;
    private int totalPrice = 0;
    private List<Product> productList;
    private ViewPager2 viewPager;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        textTotalPrice = findViewById(R.id.text_total_price);
        buttonAddToCart = findViewById(R.id.button_add_to_cart);

        productList = getProductList();

        viewPager = findViewById(R.id.viewPager);
        adapter = new ProductAdapter(productList, product -> {});
        viewPager.setAdapter(adapter);

        // Mostrar el precio inicial
        updateTotalPrice();

        // Configurar acción del botón "Agregar al carrito"
        buttonAddToCart.setOnClickListener(v -> {
            addToCart();
            updateTotalPrice();
        });
    }

    private List<Product> getProductList() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Panel Solar 100W", "Panel solar de 100W de alta eficiencia.", 50000, R.drawable.ic_solar_panel2));
        products.add(new Product("Panel Solar 200W", "Panel solar de 200W con tecnología avanzada.", 90000, R.drawable.ic_solar_panel2));
        products.add(new Product("Inversor Solar", "Inversor solar de alta eficiencia para uso doméstico.", 120000, R.drawable.ic_solar_panel2));
        // Añade más productos según sea necesario
        return products;
    }

    private void addToCart() {
        int currentItem = viewPager.getCurrentItem();
        Product currentProduct = productList.get(currentItem);
        totalPrice += currentProduct.getPrice();
    }

    private void updateTotalPrice() {
        String formattedPrice = NumberFormat.getNumberInstance(Locale.getDefault()).format(totalPrice);
        textTotalPrice.setText(getString(R.string.clp_price_format, formattedPrice));
    }
}
