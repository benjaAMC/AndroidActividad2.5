package com.bmdi.taskmaster2;
//importo librerias para usar SQLite
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.jetbrains.annotations.Nullable;
public class DataHelper extends SQLiteOpenHelper{
    public DataHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE tarea(id INTERGER PRIMARY KEY AUTOINCREMENT ,nombre TEXT, tarea TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS tarea");
        db.execSQL("CREATE TABLE tarea(id INTERGER PRIMARY KEY AUTOINCREMENT ,nombre TEXT, tarea TEXT)");
    }
}
