package com.example.recipe2.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String FAVOURITES_TABLE = "FAVOURITES_TABLE";
    public static final String ID = "ID";
    public static final String RECIPE_NAME = "RECIPE_NAME";
    public static final String RECIPE_THUMBNAIL = "RECIPE_THUMBNAIL";
    public static final String CLICKED = "CLICKED";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "favourites.dp", null, '1');
    }
/*

 */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + FAVOURITES_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RECIPE_NAME + " TEXT, " + RECIPE_THUMBNAIL + " TEXT, " + CLICKED + " INTEGER)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean addone(FavouriteModel favouriteModel){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(RECIPE_NAME,favouriteModel.getRecipe_name());
        cv.put(RECIPE_THUMBNAIL,favouriteModel.getRecipe_thumb());
        cv.put(CLICKED,favouriteModel.getClicked());
        long insert = db.insert(FAVOURITES_TABLE, null, cv);
        if(insert==-1){
            return false;
        }
        else
            return true;
    }

    public List<FavouriteModel> getRecipes(){
        List<FavouriteModel> returnList=new ArrayList<>();
        String queryString="SELECT * FROM "+FAVOURITES_TABLE;
        SQLiteDatabase dp=this.getReadableDatabase();
        Cursor cursor = dp.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int id=cursor.getInt(0);
                String recipeName=cursor.getString(1);
                String recipeThumbnail=cursor.getString(2);
                int clicked=cursor.getInt(3);
                FavouriteModel favouriteModel=new FavouriteModel(id,recipeName,recipeThumbnail,clicked);
                returnList.add(favouriteModel);
            }while(cursor.moveToNext());
        }
        else{
            //failure. dont do anything for now..
        }
        cursor.close();
        dp.close();
        return returnList;
    }

    public boolean deleteOne(FavouriteModel favouriteModel){
        SQLiteDatabase db=this.getWritableDatabase();
        String queryString="DELETE FROM " + FAVOURITES_TABLE + " WHERE " + ID+ " = "+ favouriteModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else
            return false;
    }
}
