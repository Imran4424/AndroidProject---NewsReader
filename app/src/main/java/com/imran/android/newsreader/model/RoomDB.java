package com.imran.android.newsreader.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.imran.android.newsreader.Constants;

/**
 * Created by Shah Md Imran Hossain on 04, December, 2020
 */

@Database(entities = {ArticleData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    // create database instance
    private static RoomDB database;

    // define database name
    private static String DATABASE_NAME = Constants.DATABASE_NAME;

    public synchronized static RoomDB getInstance(Context context) {
        // Check condition
        if (database == null) {
            // when database is null
            // initialize database
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class,
                    DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        // Return database
        return database;
    }

    // create dao
    public abstract ArticleDao articleDao();
}