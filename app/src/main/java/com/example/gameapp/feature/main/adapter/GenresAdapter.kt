package com.example.gameapp.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.databinding.ItemGenreLayoutBinding
import com.example.gameapp.feature.main.model.Genres

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
class GenresAdapter : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    private var list: ArrayList<Genres> = ArrayList()
    private var listFiltered: ArrayList<Genres> = ArrayList()
//    private var tempGames = ""

    fun addData(list: List<Genres>) {
        this.list = list as ArrayList<Genres>
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGenreLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                val nameGenres = if ((listFiltered.size - 1) != position) {
                    "$name, "
                } else {
                    name
                }
                binding.tvGendres.text = nameGenres
//                tempGames = nameGenres
            }
        }
    }

    /*internal fun getTempGames(): String {
        return tempGames
    }*/

    inner class ViewHolder(val binding: ItemGenreLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}