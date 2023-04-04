package com.example.testurmactivities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testurmactivities.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        binding.button.setOnClickListener {
            binding.count.text = (binding.count.text.toString().toInt() + 1).toString()
        }
        setContentView(binding.root)

    }

}