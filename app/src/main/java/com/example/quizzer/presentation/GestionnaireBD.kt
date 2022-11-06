package com.example.quizzer.presentation

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class GestionnaireBD(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NOM, factory, DATABASE_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE " + TABLE_NAME + "(" +
                ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT,"+
                COL1 + " TEXT"+")")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + DATABASE_NOM);
        onCreate(db);
    }

    companion object{

        private val DATABASE_NOM = "QUIZZER"

        private val DATABASE_VERSION = 1

        val TABLE_NAME = "question"

        val ID_COL = "id"

        val COL1 = "col1"

        val COL2 = "col2"
    }
}