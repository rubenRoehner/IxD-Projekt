package com.example.ixd_projekt

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_settings.view.*
import kotlinx.android.synthetic.main.queue_text_item.view.*
import kotlinx.android.synthetic.main.settings_item.view.*
import java.util.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_settings, container, false)
        layout.settings_backButton.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.main_container, selectedFragment)?.commit()
        }

        val recyclerView = layout.settings_recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = SettingsAdapter()
        // Inflate the layout for this fragment
        return layout
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity.setVisibilitySettingsButton(View.GONE)
    }

    override fun onDetach() {
        super.onDetach()
        mainActivity.setVisibilitySettingsButton(View.VISIBLE)
    }

    private inner class SettingsAdapter: RecyclerView.Adapter<SettingsCustomViewHolder>() {

        private val settingTitles = arrayListOf("", "Data Saver", "Aus", "Wiedergabe", "Offline-Modus", "Cross-Fade", "Nahtlose Wiedergabe",
            "Automix", "Unangemessene Inhalte erlauben", "Nicht spielbare Songs anzeigen", "Übertragungsstatus", "AutoPlay", "Canvas",
            "Geräte", "Mit einem Gerät verbinden", "Nur lokale Geräte anzeigen")

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SettingsCustomViewHolder {
            var itemView = when(viewType) {
                0 -> layoutInflater.inflate(R.layout.settings_profile_item, parent, false)

                1 -> layoutInflater.inflate(R.layout.queue_text_item, parent, false)

                else -> layoutInflater.inflate(R.layout.settings_item, parent, false)
            }
            return SettingsCustomViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 16
        }

        override fun getItemViewType(position: Int): Int {
            return if (position == 0) {
                0
            } else if(position == 1 || position == 3 || position == 13) {
                1
            } else {
                2
            }
        }

        override fun onBindViewHolder(holder: SettingsCustomViewHolder, position: Int) {
            when(position) {
                0 -> {

                }
                1, 3, 13 -> {
                    holder.itemView.textItem_label.text = settingTitles[position]
                }
                else -> {
                    holder.itemView.settingItem_title.text = settingTitles[position]
                    holder.itemView.settingsItem_switch.isChecked = Random().nextBoolean()
                }
            }
        }

    }

    private class SettingsCustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


}
