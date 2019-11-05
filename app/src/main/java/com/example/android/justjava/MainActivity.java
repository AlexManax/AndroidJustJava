/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int numberOfOrderedCoffees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("quantity", numberOfOrderedCoffees);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        numberOfOrderedCoffees = savedInstanceState.getInt("quantity");
        display(numberOfOrderedCoffees);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(numberOfOrderedCoffees);
    }

    /**
     * This method displays the given quantity value on the screen.
     */

    public void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void plus(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        numberOfOrderedCoffees = Integer.parseInt((String) quantityTextView.getText()) + 1;
        display(numberOfOrderedCoffees);
    }

    public void minus(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        numberOfOrderedCoffees = Integer.parseInt((String) quantityTextView.getText()) - 1;
        display(numberOfOrderedCoffees);
    }
}