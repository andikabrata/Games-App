package com.example.gameapp.feature.detail_movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.databinding.ItemDeveloperLayoutBinding
import com.example.gameapp.feature.detail_movie.model.Developers

/**
 * @author Andika Bratadirja
 * @date 19/05/2024
 */
class DeveloperAdapter : RecyclerView.Adapter<DeveloperAdapter.ViewHolder>() {

    private var list: ArrayList<Developers> = ArrayList()
    private var listFiltered: ArrayList<Developers> = ArrayList()

    fun addData(list: List<Developers>) {
        this.list = list as ArrayList<Developers>
        listFiltered = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeveloperAdapter.ViewHolder {
        val binding = ItemDeveloperLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeveloperAdapter.ViewHolder, position: Int) {
        with(holder) {
            with(listFiltered[position]) {
                val nameDevelopers = if ((listFiltered.size - 1) != position) {
                    "$name, "
                } else {
                    name
                }
                binding.tvDeveloper.text = nameDevelopers
            }
        }
    }

    override fun getItemCount() = listFiltered.size

    inner class ViewHolder(val binding: ItemDeveloperLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}