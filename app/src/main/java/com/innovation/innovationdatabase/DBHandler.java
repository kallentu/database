package com.innovation.innovationdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KallenTu on 9/11/2016.
 */
public class DBHandler extends SQLiteOpenHelper{

    //Declares constants for the database name, table name, table columns and database version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "productDB.db";
    private static final String TABLE_PRODUCTS = "products";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_DESCRIPTION = "description";

    //Constructor for database
    public DBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Products table created when database is first initialized
    //SQLiteDatabase object passed through execSQL() method as an argument to onCreate()
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PRODUCT + " TEXT,"
                + COLUMN_DESCRIPTION + " INTEGER" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    //Called when the handler is invoked with a greater database version number from the one previously used
    //Removes old database and replaces with a new one
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Inserts database records
    public void addProduct(Product product) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT, product.getProduct());
        values.put(COLUMN_DESCRIPTION, product.getDescription());
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Method to query the database
    //Argument is a String object containing the name of the product to be located
    public Product findProduct(String product) {
        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCT + " =  \"" + product + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product prod = new Product();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            prod.setID(Integer.parseInt(cursor.getString(0)));
            prod.setProduct(cursor.getString(1));
            prod.setDescription(cursor.getString(2));
            cursor.close();
        } else {
            prod = null;
        }
        db.close();
        return prod;
    }

    //Method deletes certain entries
    //Argument is the entry to be deleted in the form of a Product object
    //Success or otherwise of the deletion will be reflected in a Boolean return value
    public boolean deleteProduct(String product) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCT + " =  \"" + product + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Product prod = new Product();

        if (cursor.moveToFirst()) {
            prod.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(prod.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
