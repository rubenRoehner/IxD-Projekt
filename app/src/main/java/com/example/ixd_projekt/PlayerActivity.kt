package com.example.ixd_projekt

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PixelFormat
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.core.graphics.toColor
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_player.*
import kotlinx.android.synthetic.main.quick_settings_layout.view.*

var displayWidth: Int = 0

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        window.setFormat(PixelFormat.RGBA_8888)

        displayWidth = resources.displayMetrics.widthPixels

        player_backButton.setOnClickListener {
            finish()
        }

        player_queueButton.setOnClickListener {
            val intent = Intent(this, QueueActivity::class.java)
            startActivity(intent)
        }

        player_settingsMenu_button.setOnClickListener {
            if (player_quick_settingsRV.visibility == View.VISIBLE) {
                player_quick_settingsRV.visibility = View.GONE
                player_settingsIndicator.visibility = View.VISIBLE
                player_settingsMenu_button.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp)
            } else {
                player_quick_settingsRV.visibility = View.VISIBLE
                player_settingsIndicator.visibility = View.GONE
                player_settingsMenu_button.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp)
            }
        }
        player_settingsMenu_button.performClick()

        player_moreButton.setOnClickListener {
            val dialog = MenuDialog(this, MenuDialog.MenuDialogType.SONG)
            dialog.show()
        }

        player_quick_settingsRV.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        player_quick_settingsRV.adapter = QuickSettingsAdapter()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        window.setFormat(PixelFormat.RGBA_8888)
    }

    private inner class QuickSettingsAdapter: RecyclerView.Adapter<CustomViewHolder>() {

        private val settingsTexts = arrayListOf("Offline-Modus", "Private Session", "Crossfade")

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = layoutInflater.inflate(R.layout.quick_settings_layout, parent, false)
            val layoutParams = view.layoutParams
            layoutParams.width = displayWidth / 4
            view.layoutParams = layoutParams
            return CustomViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 4
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.itemView.setOnClickListener {

            }
            if (position == this.itemCount - 1) {
                holder.itemView.quickSetting_toggleButton.background = resources.getDrawable(R.drawable.ic_arrow_forward_black_24dp, resources.newTheme())
                holder.itemView.quickSetting_toggleButton.backgroundTintList = ColorStateList.valueOf(Color.WHITE)
                holder.itemView.quickSetting_toggleButton.scaleX = 0.8F
                holder.itemView.quickSetting_toggleButton.scaleY = 0.8F
                holder.itemView.quickSetting_label.text = "Einstellungen"
                holder.itemView.quickSetting_toggleButton.setOnClickListener {
                    toSettings = true
                    this@PlayerActivity.onBackPressed()

                }
            } else {
                holder.itemView.quickSetting_label.text = settingsTexts[position]
            }

            if (position == 0 || position == 2) {
                holder.itemView.quickSetting_toggleButton.isChecked = true
            }
        }


    }
    private class CustomViewHolder(view: View): RecyclerView.ViewHolder(view)
}
