package com.example.desafio_android_joao_ricardi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.desafio_android_joao_ricardi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val PUBLIC_KEY = "5384a6e199c6db9b7f51478b732652b1"
        val PRIVATE_KEY = "2f283f65e31df630e1b0135ac5759e410b897b44"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)


    }
}