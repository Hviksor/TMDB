package com.example.tmdb.presentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tmdb.R
import com.example.tmdb.presentor.screens.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MainFragment())
            .commit()
    }
}