package com.example.tmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tmdb.presentor.screens.main.MainScreenFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchFragment()
    }

    private fun launchFragment() {
        val fragment = MainScreenFragment.getInstanceMainFragment(this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}