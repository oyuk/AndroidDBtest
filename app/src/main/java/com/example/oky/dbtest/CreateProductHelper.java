package com.example.oky.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by oky on 2014/08/20.
 */
public class CreateProductHelper extends SQLiteOpenHelper{

    public static CreateProductHelper singleton = null;

    /* データベース名 */
    private final static String DB_NAME = "DBTest";
    /* データベースのバージョン */
    private final static int DB_VER = 1;

    public final static String TABLE_NAME = "memo";
    public final static String COL_TITLE = "title";
    public final static String COL_CONTENT = "content";

    private final static String CREATE = "CREATE TABLE "+TABLE_NAME
            +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_TITLE+" TEXT, "+COL_CONTENT+" TEXT);";


    public static synchronized CreateProductHelper getInstance(Context context){
        if(singleton == null){
            singleton = new CreateProductHelper(context);
        }
        return singleton;
    }

    public CreateProductHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public  CreateProductHelper(Context context){
        super(context,DB_NAME,null,DB_VER);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


    public long insert(SQLiteDatabase db,CustomListItem item){

        long id = -1;

        try{

            ContentValues value = new ContentValues();
            value.put(COL_TITLE,item.getTitle());
            value.put(COL_CONTENT,item.getContent());

            db.beginTransaction();
            id = db.insert(TABLE_NAME,null,value);
            db.setTransactionSuccessful();
            db.endTransaction();

        }catch (Exception e){
            Log.e("ERROR", e.toString());
        }

        db.close();

        return id;
    }


    public ArrayList<CustomListItem> retrive(SQLiteDatabase db){

        ArrayList<CustomListItem> retriveData = new ArrayList<CustomListItem>();

        try{

            String columns[] = {"_id",COL_TITLE,COL_CONTENT};

            Cursor cursor = db.query(TABLE_NAME,columns,null,null,null,null,"_id");

            while (cursor.moveToNext()){
                retriveData.add(new CustomListItem(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }

        }catch (Exception e){
            Log.e("ERROR",e.toString());
        }

        db.close();

        return retriveData;
    }

    public void update(String title,String content,Long id){

        ContentValues value = new ContentValues();
        value.put("title",title);
        value.put("content",content);

        SQLiteDatabase db = this.getWritableDatabase();

        String condition = "_id = '"+id+"'";

        db.beginTransaction();
        db.update(CreateProductHelper.TABLE_NAME,value,condition,null);
        db.setTransactionSuccessful();
        db.endTransaction();

    }

    public void delete(){


        
    }

}
