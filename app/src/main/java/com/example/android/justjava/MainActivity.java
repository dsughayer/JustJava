package com.example.android.justjava;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.name;


/**

 * This app displays an order form to order coffee.

 */

public class MainActivity extends AppCompatActivity {

    int quantity = 2;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }
    /**

     * This method is called when the plus button is clicked.

     */

    public void increment(View view) {
        if (quantity == 100){
            Toast.makeText(this, "You cannot order more thant 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    /**

     * This method is called when the minus button is clicked.

     */

    public void decrement(View view) {
        if (quantity == 1){
            Toast.makeText(this, "You cannot order less thant 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**

     * This method is called when the order button is clicked.

     */

    public void submitOrder(View view) {

        EditText nameField = (EditText)findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox)findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox)findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price =  calculatePrice(hasWhippedCream, hasChocolate);

        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        displayMessage(priceMessage);
    }
    /**
     * Calculates the price of the order.
     * @param addWhippedCream shows whether the customer wants whipped cream topping
     * @param addChocolate shows whether the customer wants chocolate topping
     * @ return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        //price for one cup of coffee
        int basePrice = 5;

        //Add $1 if customer wants whipped cream
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        //Add $2 if customer wants Chocolate
        if (addChocolate){
            basePrice = basePrice +  2;
        }

        //Calculate total order of the price
        return quantity * basePrice;
    }

/*
* Create summary of the order
*
*@ param price of the order
* @param addWhippedCream specifies whether the customer wants whipped cream topping or not
* @param addChocolate specifies whether the customer wants chocolate or not
* @return text summary
*/
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate){
        String priceMessage = "Name: " + name;
        priceMessage = priceMessage + "\nAdd Whipped Cream?  " + addWhippedCream;
        priceMessage = priceMessage + "\nAdd Chocolate?  " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + quantity;
        priceMessage = priceMessage + "\nTotal: $" + price;
        priceMessage = priceMessage +"\n Thank You!";
        return (priceMessage);
    }

    /**

     * This method displays the given quantity value on the screen.

     */

    private void displayQuantity(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);

        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}