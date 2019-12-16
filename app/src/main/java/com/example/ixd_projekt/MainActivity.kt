package com.example.ixd_projekt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

var selectedFragment = Fragment()
var toSettings = false
lateinit var mainActivity: MainActivity
var density: Float = 0F

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = this
        setContentView(R.layout.activity_main)
        selectedFragment = HomeFragment()

        density = resources.displayMetrics.density

        supportFragmentManager.beginTransaction().replace(R.id.main_container, HomeFragment()).commit()
        main_navView.setOnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.nav_item_start -> {
                    selectedFragment = HomeFragment()
                }

                R.id.nav_item_search -> {
                    selectedFragment = SearchFragment()
                }

                R.id.nav_item_library -> {
                    selectedFragment = LibraryFragment()
                }
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.main_container, selectedFragment)
            transaction.commit()
            return@setOnNavigationItemSelectedListener true
        }

        main_player_container.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java)
            startActivity(intent)
        }

        main_settingsButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.main_container, SettingsFragment()).commit()
        }



    }

    override fun onBackPressed() {
        selectedFragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_container, selectedFragment).commit()
    }

    override fun onResume() {
        super.onResume()
        if (toSettings) {
            main_settingsButton.callOnClick()
            toSettings = false
        }
    }

    fun setVisibilitySettingsButton(visibility: Int) {
        main_settingsButton.visibility = visibility
    }

}
