package com.example.ixd_projekt

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_queue.*
import kotlinx.android.synthetic.main.queue_song_item.view.*
import kotlinx.android.synthetic.main.queue_text_item.view.*

class QueueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_queue)

        queue_closeButton.setOnClickListener {
            finish()
        }

        queue_recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        queue_recyclerView.adapter = QueueAdapter()
    }

    private inner class QueueAdapter: RecyclerView.Adapter<CustomViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val itemView = when (viewType) {
                1 -> {
                    layoutInflater.inflate(R.layout.queue_text_item, parent, false)
                }
                0 -> {
                    layoutInflater.inflate(R.layout.queue_song_item, parent, false)
                }
                3 -> {
                    layoutInflater.inflate(R.layout.queue_now_playing, parent, false)
                }
                else -> {
                    layoutInflater.inflate(R.layout.queue_album_item, parent, false)
                }
            }
            return CustomViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 14
        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 0 || position == 2 || position == 11) {
                1
            } else if (position == 5){
                2
            } else if (position == 1){
                3
            } else {
                0
            }
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            when (position) {
                0 -> {
                    holder.itemView.textItem_label.text = "Aktueller Titel"
                }
                2 -> {
                    holder.itemView.textItem_label.text = "Nächster Titel in der Warteschlange"
                    holder.itemView.queue_textItem_shuffle.visibility = View.VISIBLE
                    var state = false
                    holder.itemView.queue_textItem_shuffle.setOnClickListener {
                        if (state) {
                            holder.itemView.queue_textItem_shuffle.imageTintList = ColorStateList.valueOf(Color.parseColor("#1DB954"))
                        } else {
                            holder.itemView.queue_textItem_shuffle.imageTintList = ColorStateList.valueOf(Color.WHITE)
                        }
                        state = !state
                    }
                }
                11 -> {
                    holder.itemView.textItem_label.text = "Nächster aus: Playlist/Album"
                }
                in 6..9 -> {
                    holder.itemView.queue_songItem_layout.setPadding((24 * density).toInt(), 0, 0, 0)
                }
            }
        }

    }

    private class CustomViewHolder(view: View): RecyclerView.ViewHolder(view)
}
