package com.innovation.innovationdatabase;

/**
 * Created by KallenTu on 9/11/2016.
 */

/*
com.innovation.innovationdatabase.Product class contains private data members for the internal storage of data columns from database entries and a set of methods to get and set those values.
 */

public class Product {
    //Holds product id, product and description
    private int _id;
    private String _product;
    private String _description;

    //com.innovation.innovationdatabase.Product default constructor
    public Product() {}

    //com.innovation.innovationdatabase.Product constructor with parameters to set
    public Product(int id, String product, String description) {
        this._id = id;
        this._product = product;
        this._description = description;
    }

    //com.innovation.innovationdatabase.Product constructor without id
    public Product(String product, String description) {
        this._product = product;
        this._description = description;
    }

    //Updates id
    public void setID(int id) {
        this._id = id;
    }

    //Returns id
    public int getID() {
        return this._id;
    }

    //Sets product
    public void setProduct(String product) {
        this._product = product;
    }

    //Returns product
    public String getProduct() {
        return this._product;
    }

    //Updates description
    public void setDescription(String description) {
        this._description = description;
    }

    //Returns description
    public String getDescription() {
        return this._description;
    }

}
