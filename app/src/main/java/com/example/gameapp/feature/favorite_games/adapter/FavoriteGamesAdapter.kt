package com.example.gameapp.feature.favorite_games.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.common.extension.converDate
import com.example.gameapp.common.extension.loadImage
import com.example.gameapp.core.db.entity.GamesFavoriteEntity
import com.example.gameapp.databinding.ItemGamesFavoriteBinding

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
class FavoriteGamesAdapter(private val listener: onItemClick, val onItemClicked: (GamesFavoriteEntity) -> Unit) :
    RecyclerView.Adapter<FavoriteGamesAdapter.ViewHolder>() {
    private var list: ArrayList<GamesFavoriteEntity> = ArrayList()
    private var listFiltered: ArrayList<GamesFavoriteEntity> = ArrayList()

    fun addData(list: List<GamesFavoriteEntity>) {
        this.list = list as ArrayList<GamesFavoriteEntity>
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteGamesAdapter.ViewHolder {
        val binding = ItemGamesFavoriteBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteGamesAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                binding.ivGames.loadImage(image ?: "")
                binding.tvGamenName.text = gameName
                binding.tvValueReleaseDate.text = releaseDate?.converDate()
                binding.tvValueGenres.text = genres
                binding.ivDelete.setOnClickListener {
                    listener.onDeleteClicked(position = position, idGame = idGame ?: 0)
                }
                binding.itemLayout.setOnClickListener {
                    onItemClicked(this)
                }
            }
        }
    }

    override fun getItemCount() = listFiltered.size

    inner class ViewHolder(val binding: ItemGamesFavoriteBinding) : RecyclerView.ViewHolder(binding.root)

    interface onItemClick {
        fun onDeleteClicked(position: Int, idGame: Int)
    }
}