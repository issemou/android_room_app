package com.example.wordroom.data.repository

import androidx.annotation.WorkerThread
import com.example.wordroom.data.Word
import com.example.wordroom.data.WordDAO
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDAO) {

    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}