package com.example.multiappproyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BD) {

        BD.execSQL("CREATE TABLE IF NOT EXISTS users (userid INTEGER PRIMARY KEY, nombre text, score int, level int, repetitions int)");
        BD.execSQL("CREATE TABLE IF NOT EXISTS spacedrepetitions (userid INTEGER PRIMARY KEY, nombre text, repetition text, quality text, easiness text, interval text, nextPractice text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int i, int i1) {

    }
}
