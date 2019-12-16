package com.example.ixd_projekt


import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.queue_text_item.view.*
import kotlinx.android.synthetic.main.search_categorie_item.view.*
import java.util.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_search, container, false)
        val recyclerView = layout.findViewById<RecyclerView>(R.id.search_recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        recyclerView.adapter = SearchAdapter()
        recyclerView.isNestedScrollingEnabled = false
        return layout
    }

    private inner class SearchAdapter: RecyclerView.Adapter<SearchViewHolder>() {

        private val categoryTexts = arrayListOf("Electronic/\nDance", "Pop", "Rock", "Hip Hop", "Podcasts", "Für dich erstellt", "Charts", "Neuerscheinungen", "Radio", "Entdecken", "Konzerte", "Stimmung", "Techno", "Chill", "Gaming", "Fitness")
        private val categoryColors = arrayListOf("#9CF0E0", "#C3F0C9", "#EC1E32", "#4000F3", "#F59B23", "#9FC3D1", "#4C917E", "#9FC3D1", "#F573A1", "#B59BC8", "#FF6436", "#509BF6", "#6F6F6F","#C49687", "#FF6436", "#FFC864")

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            return when (viewType) {
                0 -> {
                    SearchViewHolder(layoutInflater.inflate(R.layout.queue_text_item, parent, false))
                }
                1 -> {
                    SearchViewHolder(View(parent.context))
                }
                else -> {
                    SearchViewHolder(layoutInflater.inflate(R.layout.search_categorie_item, parent, false))
                }
            }
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun getItemViewType(position: Int): Int {
            return if(position == 0 || position == 6) {
                0
            } else if (position == 1 || position == 7){
                1
            } else {
                2
            }
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            if (position == 0) {
                holder.itemView.textItem_label.text = "Deine Top Genres"
            } else if (position == 6) {
                holder.itemView.textItem_label.text = "Alles durchsuchen"
            } else {
                if (position < 6 && position != 1) {
                    holder.itemView.search_categoryLabel.text = categoryTexts[position - 2]
                    holder.itemView.search_category_layout.backgroundTintList = ColorStateList.valueOf(Color.parseColor(categoryColors[position - 2]))
                } else if (position != 7){
                    holder.itemView.search_categoryLabel?.text = categoryTexts[position - 4]
                    holder.itemView.search_category_layout?.backgroundTintList = ColorStateList.valueOf(Color.parseColor(categoryColors[position - 4]))
                }

            }
        }

    }

    private class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}
