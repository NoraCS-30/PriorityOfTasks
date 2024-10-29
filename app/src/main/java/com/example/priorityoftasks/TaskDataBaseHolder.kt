package com.example.priorityoftasks

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDataBaseHolder(context : Context): SQLiteOpenHelper(context , DBNAME,null, DBversion) {
    companion object {
        private const val DBNAME="Tasks.db"
        private const val DBversion = 1
        private const val TABLE_NAME = "TASK"
        private const val ID = "id"
        private const val TITLE = "title"
        private const val DESCRIPTION = "description"
        private const val PRIORITY = "priority"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val guery ="CREATE TABLE $TABLE_NAME ("+
                "$ID INTEGER PRIMARY KEY, "+
                "$TITLE TEXT, "+
                "$DESCRIPTION TEXT, " +
                "$PRIORITY TEXT " +
                " )"
        db?.execSQL(guery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val drop="DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(drop)
        onCreate(db)
    }
    fun insertTask(task :Task){
        val db=writableDatabase
        val values= ContentValues().apply {
            put(TITLE,task.title)
            put(DESCRIPTION,task.description)
            put(PRIORITY,task.priority)
        }
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    fun getAllTasks(): List<Task>{
        val taskList= mutableListOf<Task>()
        val db=readableDatabase
        val sql="SELECT * FROM $TABLE_NAME"
        val cursor=db.rawQuery(sql,null)
        while (cursor.moveToNext()){
            val id =cursor.getInt(cursor.getColumnIndexOrThrow(ID))
            val title =cursor.getString(cursor.getColumnIndexOrThrow(TITLE))
            val description =cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
           val priority=cursor.getString(cursor.getColumnIndexOrThrow(PRIORITY))
            val TASK=Task(id, title, description,priority)
            taskList.add(TASK)
        }
        cursor.close()
        db.close()
        return taskList
    }

    fun deleteTask(taskId :Int){
        val db=writableDatabase
        val whereClause="$ID = ?"
        val whereArgs= arrayOf(taskId.toString())
        db.delete(TABLE_NAME,whereClause,whereArgs)
        db.close()
    }

}