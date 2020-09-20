package com.ordolabs.collect.di

import androidx.room.Room
import com.ordolabs.data.CollectDB
import com.ordolabs.data.dao.NotesDao
import com.ordolabs.data.repository.NotesRepository
import com.ordolabs.domain.repository.ICollectDBNotesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DBSourceModule = module {

    single {
        Room
            .databaseBuilder(androidContext(), CollectDB::class.java, "collect_db")
            .build()
    }

    // DAOs
    single {
        val db: CollectDB = get()
        provideNotesDao(db)
    }

    // Repositories
    single<ICollectDBNotesRepository> {
        val dao: NotesDao = get()
        NotesRepository(dao)
    }
}

internal fun provideNotesDao(db: CollectDB): NotesDao {
    return db.notesDao
}