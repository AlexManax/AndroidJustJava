/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int numberOfOrderedCoffees = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        TextView textView = new TextView(this);
//        textView.setText("ddd");
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
        String message =
                (((CheckBox) findViewById(R.id.checkBox1)).isChecked() ? "Weeping cream\n" : "") +
                        (((CheckBox) findViewById(R.id.checkBox2)).isChecked() ? "Chocolate\n" : "") +
                        getString(R.string.Cofee) + numberOfOrderedCoffees + "\n" +
                        "Total: " + NumberFormat.getCurrencyInstance().format((calculatePrice(numberOfOrderedCoffees))) + "\nThank you!";
//        displayPrice(message);
//        Intent.
        String[] strArr = new String[2];
        strArr[0] = "xx@xx.xx";
        strArr[1] = "j@jj.jj";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, strArr);
        intent.putExtra(Intent.EXTRA_SUBJECT, ("JustJava cafee for " + ((EditText) findViewById(R.id.textEditField)).getText()));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */



    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void plus(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        numberOfOrderedCoffees = Integer.parseInt((String) quantityTextView.getText()) + 1;
        if (numberOfOrderedCoffees > 99) {
            numberOfOrderedCoffees = 99;
            Toast.makeText(this, "Not allowed more than 99", Toast.LENGTH_SHORT).show();
        }
        display(numberOfOrderedCoffees);
    }

    public void minus(View view) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        numberOfOrderedCoffees = Integer.parseInt((String) quantityTextView.getText()) - 1;
        if (numberOfOrderedCoffees < 1) {
            numberOfOrderedCoffees = 1;
            Toast.makeText(this, "Not allowed less than 1", Toast.LENGTH_SHORT).show();
        }
        display(numberOfOrderedCoffees);
    }

    private int calculatePrice(int number) {
        int x = (5 + (((CheckBox) findViewById(R.id.checkBox1)).isChecked() ? 1 : 0) +
                (((CheckBox) findViewById(R.id.checkBox2)).isChecked() ? 2 : 0)) * numberOfOrderedCoffees;
        Log.i("Val", "" + x);
        return x;
    }

}