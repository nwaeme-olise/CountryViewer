package com.olisemeka.countryviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olisemeka.countryviewer.R
import com.olisemeka.countryviewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}