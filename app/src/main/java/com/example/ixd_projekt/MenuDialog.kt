package com.example.ixd_projekt

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.menu_dialog_item.view.*
import kotlinx.android.synthetic.main.menudialog_first_item.view.*
import kotlinx.android.synthetic.main.menudialog_layout.*


class MenuDialog(context: Context, type: MenuDialogType): Dialog(context, R.style.Theme_AppCompat_Light_NoActionBar) {

    enum class MenuDialogType {
        PLAYLIST,
        SONG,
        ALBUM
    }

    private val dialogType = type

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawableResource(R.drawable.menudialog_bg)
        window?.statusBarColor = context.resources.getColor(R.color.black, null)

        setContentView(R.layout.menudialog_layout)

        menuDialog_recyclerView.setOnClickListener {
            dismiss()
        }

        menuDialog_recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        menuDialog_recyclerView.adapter = MenuDialogAdapter(dialogType)

    }

    private inner class MenuDialogAdapter(type: MenuDialogType): RecyclerView.Adapter<MenuDialogViewHolder>() {

        private val dialogType = type

        private val menuSongTexts = arrayListOf("Gefällt mir", "Zu Playlist hinzufügen","In die Warteschlange", "Album anzeigen", "Künstler anzeigen", "Teilen", "Sleeptimer", "Gehe zu Song-Radio", "Unangemessen Inhalte melden", "Songinfos anzeigen")
        private val menuSongIcons = arrayListOf(R.drawable.ic_favorite_black_24dp, R.drawable.ic_add_black_24dp, R.drawable.ic_queue_music_black_24dp, R.drawable.ic_album_black_24dp, R.drawable.ic_account_circle_black_24dp, R.drawable.ic_share_black_24dp, R.drawable.ic_brightness_3_black_24dp, R.drawable.ic_radio_black_24dp, R.drawable.ic_flag_black_24dp, R.drawable.ic_group_black_24dp)
        private val menuPlaylistTexts = arrayListOf("In die Warteschlange", "Songs hinzuügen", "Als Gemeinsam freigeben", "Playlist bearbeiten", "Playlist löschen", "Download entfernen", "Gehe zu Playlist-Radio", "In Playlist suchen", "Playlist sortieren", "Teilen", "Geheim halten", "Zur Startsite hinzufügen")
        private val menuPlaylistIcons = arrayListOf(R.drawable.ic_queue_music_black_24dp, R.drawable.ic_add_black_24dp, R.drawable.ic_group_black_24dp, R.drawable.ic_edit_black_24dp, R.drawable.ic_close_black_24dp, R.drawable.ic_file_download_black_24dp, R.drawable.ic_radio_black_24dp, R.drawable.ic_search_black_24dp, R.drawable.ic_menu_black_24dp, R.drawable.ic_share_black_24dp, R.drawable.ic_share_black_24dp, R.drawable.ic_home_black_24dp)
        private val menuAlbumTexts = arrayListOf("Gefällt mir", "Gehe zu Album-Radio", "Zu Playlist hinzufügen", "In die Warteschlange", "Alle Songs mit Gefällt mir markieren", "Teilen", "Zur Startseite hinzufügen")

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDialogViewHolder {
            return if (viewType == 0) {
                val itemView = layoutInflater.inflate(R.layout.menudialog_first_item, parent, false)
                MenuDialogViewHolder(itemView)
            } else {
                val itemView = layoutInflater.inflate(R.layout.menu_dialog_item, parent, false)
                MenuDialogViewHolder(itemView)
            }
        }

        override fun getItemCount(): Int {
            return when(dialogType) {
                MenuDialogType.PLAYLIST -> menuPlaylistTexts.size + 1
                MenuDialogType.ALBUM -> menuAlbumTexts.size + 1
                MenuDialogType.SONG -> menuSongTexts.size + 1
            }
        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 0) {
                0
            } else {
                1
            }
        }

        override fun onBindViewHolder(holder: MenuDialogViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                dismiss()
            }
            if (position != 0) {
                when (dialogType) {
                    MenuDialogType.SONG -> {
                        holder.itemView.menuDialog_item_label.text = menuSongTexts[position - 1]
                        holder.itemView.menuDialog_itemIcon.setImageResource(menuSongIcons[position - 1])
                    }
                    MenuDialogType.ALBUM -> {
                        holder.itemView.menuDialog_item_label.text = menuAlbumTexts[position - 1]
                    }
                    MenuDialogType.PLAYLIST -> {
                        holder.itemView.menuDialog_item_label.text = menuPlaylistTexts[position - 1]
                        holder.itemView.menuDialog_itemIcon.setImageResource(menuPlaylistIcons[position - 1])
                    }
                }
            }

            if (position == 0) {
                when (dialogType) {
                    MenuDialogType.SONG -> holder.itemView.menuDialog_first_title.text = "Song Title"
                    MenuDialogType.ALBUM -> holder.itemView.menuDialog_first_title.text = "Album Title"
                    MenuDialogType.PLAYLIST -> holder.itemView.menuDialog_first_title.text = "Playlist Title"
                }
            }

        }

    }

    private class MenuDialogViewHolder(view: View): RecyclerView.ViewHolder(view)
}