package com.ordolabs.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ordolabs.data.dao.NotesDao

@Database(entities = [], version = 1, exportSchema = false)
abstract class CollectDB : RoomDatabase() {

    abstract val notesDao: NotesDao
}