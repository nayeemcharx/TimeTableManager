package com.example.timetablemanager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import java.util.*

class DatabaseOperations(context: Context): SQLiteOpenHelper(
    context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Tasks.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DatabaseInfo.SQL_CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DatabaseInfo.SQL_DELETE_TABLE_QUERY)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    // add, fetch, modify/update, delete

    fun addItem(dbo: DatabaseOperations, task: Task) {
        val db = dbo.writableDatabase
        val itemName = task.taskName
        val startTime= task.startTime
        val endTime= task.endTime
        val date=task.dateToDo
        val contentValues = ContentValues().apply {
            put(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME, itemName)
            put(DatabaseInfo.TableInfo.COLUMN_ITEM_START, startTime)
            put(DatabaseInfo.TableInfo.COLUMN_ITEM_END,endTime )
            put(DatabaseInfo.TableInfo.COLUMN_DATE,date)
        }

        val rowID = db.insert(DatabaseInfo.TableInfo.TABLE_NAME, null, contentValues)
    }

    fun getTodaysItems(dbo: DatabaseOperations): Cursor {
        val db = dbo.readableDatabase
        val projection = arrayOf(
            BaseColumns._ID,
            DatabaseInfo.TableInfo.COLUMN_ITEM_NAME,
            DatabaseInfo.TableInfo.COLUMN_ITEM_START,
            DatabaseInfo.TableInfo.COLUMN_ITEM_END,
            DatabaseInfo.TableInfo.COLUMN_DATE)
        val selection = DatabaseInfo.TableInfo.COLUMN_DATE+ "=?"
        val selectionArgs = arrayOf(getDateAsString())
        val sortOrder = null

        val cursor = db.query(
            DatabaseInfo.TableInfo.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            sortOrder
        )
        return cursor
    }
//
//    fun updateItem(dbo: DatabaseOperations, oldItem: TodoItem, newItem: TodoItem) {
//        val db = dbo.writableDatabase
//        val itemName = newItem.name
//        val isItemUrgent = newItem.isUrgent
//        val itemUrgency = if (isItemUrgent) 1 else 0
//        val itemDate = newItem.getDateAsString()
//
//        val contentValues = ContentValues().apply {
//            put(DatabaseInfo.TableInfo.COLUMN_ITEM_NAME, itemName)
//            put(DatabaseInfo.TableInfo.COLUMN_ITEM_URGENCY, itemUrgency)
//            put(DatabaseInfo.TableInfo.COLUMN_DATE, itemDate)
//        }
//
//        val selection = "${DatabaseInfo.TableInfo.COLUMN_ITEM_NAME} LIKE ?"
//        val selectionArgs = arrayOf(oldItem.name)
//
//        val count = db.update(DatabaseInfo.TableInfo.TABLE_NAME, contentValues, selection, selectionArgs)
//    }
//
//    fun deleteItem(dbo: DatabaseOperations, todoItem: TodoItem) {
//        val db = dbo.writableDatabase
//        val selection = "${DatabaseInfo.TableInfo.COLUMN_ITEM_NAME} LIKE ?"
//        val selectionArgs = arrayOf(todoItem.name)
//
//        val deletedRows = db.delete(DatabaseInfo.TableInfo.TABLE_NAME, selection, selectionArgs)
//    }
    fun getDateAsString(): String {
        val date = Calendar.getInstance()
        val year = date.get(Calendar.YEAR).toString()
        val month = date.get(Calendar.MONTH).toString()
        val day = date.get(Calendar.DAY_OF_MONTH).toString()
        return "$year/$month/$day"
    }
}