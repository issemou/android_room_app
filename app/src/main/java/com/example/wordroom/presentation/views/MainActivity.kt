package com.example.wordroom.presentation.views

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordroom.R
import com.example.wordroom.data.Word
import com.example.wordroom.presentation.adapter.WordListAdapter
import com.example.wordroom.presentation.viewmodel.WordViewModel
import com.example.wordroom.presentation.viewmodel.WordViewModelFactory

class MainActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        val fab = findViewById<Button>(R.id.AddWord)
        val input = findViewById<EditText>(R.id.WordInput)
        fab.setOnClickListener {
            if (TextUtils.isEmpty(input.text)) {
                Toast.makeText(
                    applicationContext,
                    "Error Please Enter Correct Text",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val word = Word(0,input.text.toString())
                wordViewModel.insert(word)
            }
        }
    }
}