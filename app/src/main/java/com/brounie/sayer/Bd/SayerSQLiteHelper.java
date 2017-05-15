package com.brounie.sayer.Bd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.brounie.sayer.Models.Armadora;
import com.brounie.sayer.Models.Base;
import com.brounie.sayer.Models.Color;
import com.brounie.sayer.Models.Formula;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ajamaica on 24/03/17.
 */

public class SayerSQLiteHelper extends SQLiteOpenHelper {

    // database version
    private static final int database_VERSION = 1;

    // database name
    private static final String database_NAME = "BreweryDB";
    private static final String table_ARMADORA = "armadora";
    private static final String table_COLOR = "color";
    private static final String table_BASE = "base";
    private static final String table_FORMULA = "formula";

    //visit armadora
    private static final String armadora_ID = "id";
    private static final String armadora_NAME = "name";
    private static final String armadora_NAMECOMPLETE = "nameComplete";
    private static final String armadora_OBJECTID = "objectId";

    //COLOR
    private static final String color_ID = "id";
    private static final String color_OBJECTID = "objectId";
    private static final String color_IDSAYER = "idSayer";
    private static final String color_ARMADORA = "armadora";
    private static final String color_COLORCODE = "colorCode";
    private static final String color_COLORNODE = "colorNode";
    private static final String color_COLCODE = "colCode";
    private static final String color_COLORNAME = "colorName";
    private static final String color_SAYER = "sayer";
    private static final String color_STDNBR = "stdNbr";
    private static final String color_FIRSTYEAR = "firstYear";
    private static final String color_LASTYEAR = "lastYear";
    private static final String color_VARIATION = "variation";
    private static final String color_HEXADECIMAL = "hexadecimal";
    private static final String color_ARMADORASAYER = "armadoraSayer";

    //BASE
    private static final String base_ID = "id";
    private static final String base_OBJECTID = "objectId";
    private static final String base_PRECIO = "precio";
    private static final String base_MATERIAL = "material";
    private static final String base_IDSAYER = "idSayer";
    private static final String base_PESO = "peso";
    private static final String base_TEXTOMAT = "textoMat";
    private static final String base_MATERIALNAME = "materialName";

    //FORMULA
    private static final String formula_ID = "id";
    private static final String formula_OBJECTID = "objectId";
    private static final String formula_IDSAYER = "idSayer";
    private static final String formula_PRECIOP = "precioP";
    private static final String formula_LINEA = "linea";
    private static final String formula_FONDO = "fondo";
    private static final String formula_PORC = "porc";
    private static final String formula_COLORCODE = "colorCode";
    private static final String formula_BASE = "base";
    private static final String formula_PESO = "peso";
    private static final String formula_COSTOMER = "costomer";
    private static final String formula_ACOMULADO = "acomulado";
    private static final String formula_ARMADORA = "armadora";
    private static final String formula_ARMADORASAYER = "armadoraSayer";


    public SayerSQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ARMADORA_TABLE = "CREATE TABLE "+table_ARMADORA+ " ( " +
                " "+armadora_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " "+armadora_NAME+" TEXT, " +
                " "+armadora_NAMECOMPLETE+"  TEXT," +
                "  "+armadora_OBJECTID+" TEXT )";

        String CREATE_COLOR_TABLE = "CREATE TABLE "+table_COLOR+ " ( " +
                " "+color_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " "+color_OBJECTID+" TEXT, " +
                " "+color_IDSAYER+" INTEGER, " +
                " "+color_ARMADORA+"  TEXT," +
                " "+color_COLORCODE+"  TEXT," +
                " "+color_COLORNODE+"  TEXT," +
                " "+color_COLCODE+"  TEXT," +
                " "+color_COLORNAME+"  TEXT," +
                " "+color_SAYER+"  TEXT," +
                " "+color_STDNBR+"  TEXT," +
                " "+color_FIRSTYEAR+" INTEGER, " +
                " "+color_LASTYEAR+" INTEGER, " +
                " "+color_VARIATION+"  TEXT," +
                "  "+color_HEXADECIMAL+" TEXT," +
                " "+color_ARMADORASAYER+" TEXT )";

        String CREATE_BASE_TABLE = "CREATE TABLE "+table_BASE+ " ( " +
                " "+base_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " "+base_OBJECTID+" TEXT, " +
                " "+base_PRECIO+" double, " +
                " "+base_MATERIAL+"  TEXT," +
                " "+base_IDSAYER+"  INTEGER," +
                " "+base_PESO+"  TEXT," +
                " "+base_TEXTOMAT+"  TEXT," +
                "  "+base_MATERIALNAME+" TEXT )";

        String CREATE_FORMULA_TABLE = "CREATE TABLE "+table_FORMULA+ " ( " +
                " "+formula_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " "+formula_OBJECTID+" TEXT, " +
                " "+formula_IDSAYER+" INTEGER, " +
                " "+formula_PRECIOP+"  INTEGER," +
                " "+formula_LINEA+"  TEXT," +
                " "+formula_FONDO+"  TEXT," +
                " "+formula_PORC+"  double," +
                " "+formula_COLORCODE+"  TEXT," +
                " "+formula_BASE+"  TEXT," +
                " "+formula_PESO+"  INTEGER," +
                " "+formula_COSTOMER+" INTEGER, " +
                " "+formula_ACOMULADO+" INTEGER, " +
                " "+formula_ARMADORA+"  TEXT," +
                " "+formula_ARMADORASAYER+" TEXT )";


        db.execSQL(CREATE_ARMADORA_TABLE);
        db.execSQL(CREATE_COLOR_TABLE);
        db.execSQL(CREATE_BASE_TABLE);
        db.execSQL(CREATE_FORMULA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_ARMADORA);
        db.execSQL("DROP TABLE IF EXISTS "+table_COLOR);
        db.execSQL("DROP TABLE IF EXISTS "+table_BASE);
        db.execSQL("DROP TABLE IF EXISTS "+table_FORMULA);
        this.onCreate(db);
    }

    //Armadora
    public void createArmadora(Armadora armadora) {

        SQLiteDatabase db = this.getWritableDatabase();
       ContentValues values = new ContentValues();
        values.put(armadora_NAME, armadora.getName());
        values.put(armadora_NAMECOMPLETE, armadora.getNameComplete());
        values.put(armadora_OBJECTID, armadora.getObjectId());
        db.insert(table_ARMADORA, null, values);
        db.close();
    }

    public List getArmadoras(){
        List armadoras = new LinkedList();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM "+table_ARMADORA+" " +
                "ORDER BY "+armadora_NAMECOMPLETE+" COLLATE NOCASE ASC ", null);

        Armadora armadora = null;
        if (cursor.moveToFirst()) {
            do {
                armadora = new Armadora();
                armadora.setId(Integer.parseInt(cursor.getString(0)));
                armadora.setName(cursor.getString(1));
                armadora.setNameComplete(cursor.getString(2));
                armadora.setObjectId(cursor.getString(3));
                armadoras.add(armadora);

            } while (cursor.moveToNext());
        }
        return armadoras;
    }

    public List getArmadorasSearch(String key){
        List armadoras = new LinkedList();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM "+table_ARMADORA+" " +
                "WHERE "+armadora_NAMECOMPLETE+" LIKE '%" + key + "%'" +
                " ORDER BY "+armadora_NAMECOMPLETE+" COLLATE NOCASE ASC ", null);

        Armadora armadora = null;
        if (cursor.moveToFirst()) {
            do {
                armadora = new Armadora();
                armadora.setId(Integer.parseInt(cursor.getString(0)));
                armadora.setName(cursor.getString(1));
                armadora.setNameComplete(cursor.getString(2));
                armadora.setObjectId(cursor.getString(3));
                armadoras.add(armadora);

            } while (cursor.moveToNext());
        }
        return armadoras;
    }

    //Color
    public void createColor(Color color) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(color_OBJECTID, color.getObjectId());
        values.put(color_IDSAYER, color.getIdSayer());
        values.put(color_ARMADORA, color.getArmadora());
        values.put(color_COLORCODE, color.getColorCode());
        values.put(color_COLORNODE, color.getColorNode());
        values.put(color_COLCODE, color.getColCode());
        values.put(color_COLORNAME, color.getColorName());
        values.put(color_SAYER, color.getSayer());
        values.put(color_STDNBR, color.getStdNbr());
        values.put(color_LASTYEAR, color.getLastYear());
        values.put(color_FIRSTYEAR, color.getFirstYear());
        values.put(color_VARIATION, color.getVariation());
        values.put(color_HEXADECIMAL,color.getHexadecimal());
        values.put(color_ARMADORASAYER,color.getArmadoraSayer());


        db.insert(table_COLOR, null, values);
        db.close();
    }

    public List getColores(String objectId){
        List colores = new LinkedList();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM "+table_COLOR+" WHERE "+color_ARMADORA+" = '" + objectId + "' ORDER BY "+color_COLORNAME+" COLLATE NOCASE ASC", null);

        Color color = null;
        if (cursor.moveToFirst()) {
            do {
                color = new Color();
                color.setId(Integer.parseInt(cursor.getString(0)));
                color.setObjectId(cursor.getString(1));
                color.setIdSayer(Integer.parseInt(cursor.getString(2)));
                color.setArmadora(cursor.getString(3));
                color.setColorCode(cursor.getString(4));
                color.setColorNode(cursor.getString(5));
                color.setColCode(cursor.getString(6));
                color.setColorName(cursor.getString(7));
                color.setSayer(cursor.getString(8));
                color.setStdNbr(cursor.getString(9));
                color.setFirstYear(Integer.parseInt(cursor.getString(10)));
                color.setLastYear(Integer.parseInt(cursor.getString(11)));
                color.setVariation(cursor.getString(12));
                color.setHexadecimal(cursor.getString(13));
                color.setArmadoraSayer(cursor.getString(14));
                colores.add(color);

            } while (cursor.moveToNext());
        }
        return colores;
    }

    public List getColoresSearch(String objectId,String key){
        List colores = new LinkedList();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM "+table_COLOR+"  WHERE "
                +color_ARMADORA+" = '" + objectId + "' AND "
                +color_COLORNAME+" " +
                "LIKE '%" + key + "%'" +
                " ORDER BY "+
                color_COLORNAME+" " +
                "COLLATE NOCASE ASC", null);

        Color color = null;
        if (cursor.moveToFirst()) {
            do {
                color = new Color();
                color.setId(Integer.parseInt(cursor.getString(0)));
                color.setObjectId(cursor.getString(1));
                color.setIdSayer(Integer.parseInt(cursor.getString(2)));
                color.setArmadora(cursor.getString(3));
                color.setColorCode(cursor.getString(4));
                color.setColorNode(cursor.getString(5));
                color.setColCode(cursor.getString(6));
                color.setColorName(cursor.getString(7));
                color.setSayer(cursor.getString(8));
                color.setStdNbr(cursor.getString(9));
                color.setFirstYear(Integer.parseInt(cursor.getString(10)));
                color.setLastYear(Integer.parseInt(cursor.getString(11)));
                color.setVariation(cursor.getString(12));
                color.setHexadecimal(cursor.getString(13));
                color.setArmadoraSayer(cursor.getString(14));
                colores.add(color);

            } while (cursor.moveToNext());
        }
        return colores;
    }

    //Base
    public void createBase(Base base) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(base_OBJECTID, base.getObjectId());
        values.put(base_PRECIO, base.getPrecio());
        values.put(base_MATERIAL, base.getMaterial());
        values.put(base_IDSAYER, base.getIdSayer());
        values.put(base_PESO, base.getPeso());
        values.put(base_TEXTOMAT, base.getTextoMat());
        values.put(base_MATERIALNAME,base.getMaterialName());

        db.insert(table_BASE, null, values);
        db.close();
    }

    public Base getNameBase(String material){
        List bases = new LinkedList();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM "+table_BASE+" WHERE "+base_MATERIAL+" = '" + material + "' ", null);

        Base base = null;
        if (cursor.moveToFirst()) {
           // do {

            base = new Base();
            base.setId(Integer.parseInt(cursor.getString(0)));
            base.setObjectId(cursor.getString(1));
            base.setPrecio(cursor.getDouble(2));
            base.setMaterial(cursor.getString(3));
            base.setIdSayer(Integer.parseInt(cursor.getString(4)));
            base.setPeso(cursor.getInt(5));
            base.setTextoMat(cursor.getString(6));
            base.setMaterialName(cursor.getString(7));

            bases.add(base);

           // } while (cursor.moveToNext());
        }

        if(cursor != null)
            cursor.close();

        return base;//base.getTextoMat();
    }

    //Formula
    public void createFormula(Formula formula) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(formula_OBJECTID, formula.getObjectId());
        values.put(formula_IDSAYER, formula.getIdSayer());
        values.put(formula_PRECIOP, formula.getPrecioP());
        values.put(formula_LINEA, formula.getLinea());
        values.put(formula_FONDO, formula.getFondo());
        values.put(formula_PORC, formula.getPorc());
        values.put(formula_COLORCODE, formula.getColorCode());
        values.put(formula_BASE, formula.getBase());
        values.put(formula_PESO, formula.getPeso());
        values.put(formula_COSTOMER, formula.getCostomer());
        values.put(formula_ACOMULADO, formula.getAcomulado());
        values.put(formula_ARMADORA, formula.getArmadora());
        values.put(formula_ARMADORASAYER,formula.getArmadoraSayer());

        db.insert(table_FORMULA, null, values);
        db.close();
    }

    public List getFormula(String colCode){
        List formulas = new LinkedList();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT *  FROM "+table_FORMULA+" WHERE "+formula_COLORCODE+" = '" +colCode+ "'", null);

        Formula formula = null;
        if (cursor.moveToFirst()) {
            do {
                formula = new Formula();
                formula.setId(Integer.parseInt(cursor.getString(0)));
                formula.setObjectId(cursor.getString(1));
                formula.setIdSayer(cursor.getInt(2));
                formula.setPrecioP(cursor.getInt(3));
                formula.setLinea(cursor.getString(4));
                formula.setFondo(cursor.getString(5));
                formula.setPorc(cursor.getDouble(6));
                formula.setColorCode(cursor.getString(7));
                formula.setBase(cursor.getString(8));
                formula.setPeso(Integer.parseInt(cursor.getString(9)));
                formula.setCostomer(cursor.getInt(10));
                formula.setAcomulado(cursor.getInt(11));
                formula.setArmadora(cursor.getString(12));
                formula.setArmadoraSayer(cursor.getString(13));
                formulas.add(formula);

            } while (cursor.moveToNext());
        }
        return formulas;
    }

}