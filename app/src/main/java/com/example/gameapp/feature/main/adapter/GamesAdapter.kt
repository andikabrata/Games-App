package com.example.gameapp.feature.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.common.extension.converDate
import com.example.gameapp.common.extension.linearLayoutManager
import com.example.gameapp.common.extension.loadImage
import com.example.gameapp.databinding.ItemGamesLayoutBinding
import com.example.gameapp.feature.main.model.GamesModel

/**
 * @author Andika Bratadirja
 * @date 18/05/2024
 */
class GamesAdapter(
    val onItemClicked: (GamesModel) -> Unit
) : RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private var list: ArrayList<GamesModel> = ArrayList()
    private var listFiltered: List<GamesModel> = ArrayList()
    private var tempListFiltered: List<GamesModel> = ArrayList()

    private val adapter by lazy {
        GenresAdapter()
    }

    fun addData(list: List<GamesModel>) {
        this.list = list as ArrayList<GamesModel>
        listFiltered = list
        tempListFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGamesLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listFiltered.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.rvGendres.apply {
                    linearLayoutManager(RecyclerView.HORIZONTAL)
                }
                adapter.addData(list = genres)
                binding.rvGendres.adapter = adapter

                binding.ivGames.loadImage(background_image)
                binding.tvGamenName.text = name
                binding.tvValueReleaseDate.text = released.converDate()
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    fun filter(query: String) {
        listFiltered = if (query.isEmpty()) {
            tempListFiltered
        } else {
            listFiltered.filter { it.name.contains(query, ignoreCase = true) }
        }

        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemGamesLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}