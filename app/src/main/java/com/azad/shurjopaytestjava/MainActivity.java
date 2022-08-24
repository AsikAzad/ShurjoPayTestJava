package com.azad.shurjopaytestjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shurjopay.sdk.v2.model.ErrorSuccess;
import com.shurjopay.sdk.v2.model.RequiredData;
import com.shurjopay.sdk.v2.payment.PaymentResultListener;
import com.shurjopay.sdk.v2.payment.ShurjoPaySDK;
import com.shurjopay.sdk.v2.utils.Constants;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button payButton = findViewById(R.id.payButton);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePayment();
            }
        });

    }

    private void makePayment() {

        RequiredData data = new RequiredData(
                "user_name",               // username
                "password",             // password
                "BB",                          // prefix
                "BDT",                       // currency
                10.0,                        // amount
                "BB123",                     // order id
                null,                  // discount amount
                null,                     // discount percentage
                "Mr.X",                 // customer name
                "0123456789",                     // customer phone number
                "x@y.com",                         // customer email
                "Mohakhali, Dhaka",                // customer address
                "Dhaka",                              // customer city
                "Dhaka", // customer state
                "1212", // customer post code
                "Bangladesh", // customer  country
                "https://www.sandbox.shurjopayment.com/response",
                "https://www.sandbox.shurjopayment.com/response",
                "127.0.0.1",
                null, // Any value you want to pass for reference
                null, // Any value you want to pass for reference
                null, // Any value you want to pass for reference
                null // Any value you want to pass for reference
        );

        ShurjoPaySDK.Companion.getInstance().makePayment(
                this,
                Constants.SDK_TYPE_SANDBOX,
                data,
                new PaymentResultListener() {
                    @Override
                    public void onSuccess(@NonNull ErrorSuccess errorSuccess) {
                        Toast.makeText(
                                MainActivity.this,
                                "onSuccess: transactionInfo = " + errorSuccess.getTransactionInfo(),
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    @Override
                    public void onFailed(@NonNull ErrorSuccess errorSuccess) {
                        Toast.makeText(
                                MainActivity.this,
                                "onFailed: transactionInfo = " + errorSuccess.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    @Override
                    public boolean onBackButtonListener(@NonNull ErrorSuccess errorSuccess) {
                        return false;
                    }
                }
        );
    }
}