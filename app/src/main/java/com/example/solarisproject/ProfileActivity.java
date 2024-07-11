package com.example.solarisproject;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.solarisproject.data.DatabaseHelper;
import com.example.solarisproject.data.UserContract;
import java.util.Random;

public class ProfileActivity extends AppCompatActivity {

    private TextView textName;
    private TextView textEmail;
    private TextView textPhone;
    private TextView textAge;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textName = findViewById(R.id.profile_name);
        textEmail = findViewById(R.id.profile_email);
        textPhone = findViewById(R.id.profile_phone);
        textAge = findViewById(R.id.profile_age);

        dbHelper = new DatabaseHelper(this);

        loadProfileData();
        generatePurchaseHistory();
    }

    private void loadProfileData() {
        SharedPreferences prefs = getSharedPreferences("user_prefs", MODE_PRIVATE);
        String email = prefs.getString("email", null);

        if (email != null) {
            Cursor cursor = dbHelper.getUserProfile(email);
            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME));
                String lastName = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_LAST_NAME));
                String age = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_AGE));
                String phone = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_PHONE));

                textName.setText(name + " " + lastName);
                textEmail.setText(email);
                textPhone.setText(phone);
                textAge.setText(age);

                cursor.close();
            }
        }
    }

    private void generatePurchaseHistory() {
        LinearLayout layoutPurchaseHistory = findViewById(R.id.layout_purchase_history);
        layoutPurchaseHistory.removeAllViews();

        Random random = new Random();
        int orderCount = random.nextInt(6) + 1;

        for (int i = 0; i < orderCount; i++) {
            int orderNumber = random.nextInt(100000);
            int amount = random.nextInt(100000) + 10000;

            TextView orderTextView = new TextView(this);
            orderTextView.setText("Orden#" + orderNumber);
            orderTextView.setTextSize(14);
            orderTextView.setTextColor(getResources().getColor(android.R.color.white));

            TextView amountTextView = new TextView(this);
            amountTextView.setText("Monto: $" + amount);
            amountTextView.setTextSize(16);
            amountTextView.setTextColor(getResources().getColor(android.R.color.white));
            amountTextView.setPadding(0, 4, 0, 0);

            View dividerView = new View(this);
            dividerView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 1));
            dividerView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            dividerView.setPadding(0, 8, 0, 8);

            layoutPurchaseHistory.addView(orderTextView);
            layoutPurchaseHistory.addView(amountTextView);
            layoutPurchaseHistory.addView(dividerView);
        }
    }
}
