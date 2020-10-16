package com.jidokhants.mukyojeong;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jidokhants.mukyojeong.model.Food;

import java.util.ArrayList;

public class MukDBHelper extends SQLiteOpenHelper {
    private static volatile MukDBHelper instance;
    private static final String DB_NAME = "mukyojeong.db";
    private static final int DB_VERSION = 1;

    private SQLiteDatabase db = null;

    private MukDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (db == null)
            db = getWritableDatabase();
    }

    public static MukDBHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (MukDBHelper.class) {
                if (instance == null) {
                    instance = new MukDBHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.disableWriteAheadLogging();
    }

    public ArrayList<Food> getAllFood() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        Cursor cursor;

        cursor = db.rawQuery(MukDBContract.SQL_FOOD_SELECT, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String dBGroup = cursor.getString(1);
            String commercial = cursor.getString(2);
            String name = cursor.getString(3);
            String from = cursor.getString(4);
            String subCategory = cursor.getString(5);
            double servingSize = cursor.getDouble(6);
            String unit = cursor.getString(7);
            double totalGram = cursor.getDouble(8);
            double totalML = cursor.getDouble(9);
            double calorie = cursor.getDouble(10);
            double moisture = cursor.getDouble(11);
            double protein = cursor.getDouble(12);
            double fat = cursor.getDouble(13);
            double carbohydrate = cursor.getDouble(14);
            double sugars = cursor.getDouble(15);
            double fiber = cursor.getDouble(16);
            double calcium = cursor.getDouble(17);
            double fe = cursor.getDouble(18);
            double magnesium = cursor.getDouble(19);
            double phosphorus = cursor.getDouble(20);
            double potassium = cursor.getDouble(21);
            double salt = cursor.getDouble(22);
            double zinc = cursor.getDouble(23);
            double copper = cursor.getDouble(24);
            double manganese = cursor.getDouble(25);
            double selenium = cursor.getDouble(26);
            double iodine = cursor.getDouble(27);
            double chlorine = cursor.getDouble(28);
            double vitaminA = cursor.getDouble(29);
            double vitaminARE = cursor.getDouble(30);
            double retinol = cursor.getDouble(31);
            double betaCarotene = cursor.getDouble(32);
            double vitaminB = cursor.getDouble(33);
            double vitaminD = cursor.getDouble(34);
            double panto = cursor.getDouble(35);
            double vitaminB6 = cursor.getDouble(36);
            double biotin = cursor.getDouble(37);
            double vitaminC = cursor.getDouble(38);
            double omega3FattyAcids = cursor.getDouble(39);
            double omega6FattyAcids = cursor.getDouble(40);

            Food food = new Food(id, dBGroup, commercial, name, from, subCategory, servingSize, unit, totalGram, totalML,
                    calorie, moisture, protein, fat, carbohydrate, sugars, fiber, calcium, fe, magnesium, phosphorus, potassium,
                    salt, zinc, copper, manganese, selenium, iodine, chlorine, vitaminA, vitaminARE, retinol, betaCarotene, vitaminB,
                    vitaminD, panto, vitaminB6, biotin, vitaminC, omega3FattyAcids, omega6FattyAcids);
            foodList.add(food);
        }
        return foodList;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}