package com.example.kt6_hashteg

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import com.example.kt6_hashteg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: SuggestAdapter
    private var list : ArrayList<String> = arrayListOf()
    private var replaceWords = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveText()
        hs()
        initAdapter()

    }

    private fun saveText() {
        binding.btnPush.setOnClickListener {
            searchHash()
            binding.etInput.text.clear()
        }
    }

    private fun searchHash() {
        val words = binding.etInput.text.split(" ")

        for (word in words) {
            if (word.startsWith("#")) {
                val newWord = word.replace(Regex("[-+.^:;?!#,]") , "")
                list.add(newWord)
            }
        }
    }

    private fun initAdapter(){
        adapter = SuggestAdapter(list , this::clickListener)
        binding.ryHashtag.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun clickListener(hashTags : String) {
        binding.etInput.setText(binding.etInput.text.toString().replace(replaceWords, ""))
        binding.etInput.setText("${binding.etInput.text}#$hashTags ")
        binding.etInput.setSelection(binding.etInput.length())
    }

    private fun hs() {
        binding.etInput.setOnClickListener {
            if (list.isNotEmpty()) {
                binding.ryHashtag.isVisible = true
            }
        }

        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val words = text?.split(" ")

                if (words != null) {
                    for (word in words) {
                        replaceWords = word
                        binding.ryHashtag.isVisible = word.startsWith("#")
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }
}