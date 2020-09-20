package com.ordolabs.data.repository

import com.ordolabs.data.dao.NotesDao
import com.ordolabs.domain.repository.ICollectDBNotesRepository

class NotesRepository(private val dao: NotesDao): ICollectDBNotesRepository {

}