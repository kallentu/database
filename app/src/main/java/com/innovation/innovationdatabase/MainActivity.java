package com.innovation.innovationdatabase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    //Views changed by database
    TextView idView;
    EditText productBox;
    EditText descriptionBox;

    //Finds the views needed for database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.product);
        descriptionBox = (EditText) findViewById(R.id.productDescription);
    }

    //Adds new Product given from the views
    public void newProduct (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        String description =
                descriptionBox.getText().toString();

        Product product =
                new Product(productBox.getText().toString(), description);

        dbHandler.addProduct(product);
        productBox.setText("");
        descriptionBox.setText("");
    }

    //Find product in database onClick
    public void lookupProduct (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Product product =
                dbHandler.findProduct(productBox.getText().toString());

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));

            descriptionBox.setText(String.valueOf(product.getDescription()));

            //Gets file path of image and sets it to the image view
            File imgFile = new  File(String.valueOf(product.getDescription()));

            if(imgFile.exists()){

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

                ImageView myImage = (ImageView) findViewById(R.id.picture);

                myImage.setImageBitmap(myBitmap);

            }

        } else {
            idView.setText("No Match Found");
        }
    }

    //Removes the product from database
    public void removeProduct (View view) {
        DBHandler dbHandler = new DBHandler(this, null,
                null, 1);

        //If deleted, will return true
        boolean result = dbHandler.deleteProduct(
                productBox.getText().toString());

        if (result)
        {
            idView.setText("Record Deleted");
            productBox.setText("");
            descriptionBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }
}
