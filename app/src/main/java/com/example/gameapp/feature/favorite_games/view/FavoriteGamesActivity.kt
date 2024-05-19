package com.example.gameapp.feature.favorite_games.view

import android.graphics.Typeface
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.common.extension.linearLayoutManager
import com.example.gameapp.common.extension.startActivity
import com.example.gameapp.core.base.BaseVMActivity
import com.example.gameapp.core.db.entity.GamesFavoriteEntity
import com.example.gameapp.databinding.ActivityFavoriteGamesBinding
import com.example.gameapp.feature.detail_movie.view.DetailGamesActivity
import com.example.gameapp.feature.favorite_games.adapter.FavoriteGamesAdapter
import com.example.gameapp.feature.favorite_games.view_model.FavoriteGamesViewModel

class FavoriteGamesActivity : BaseVMActivity<FavoriteGamesViewModel, ActivityFavoriteGamesBinding>(),
    FavoriteGamesAdapter.onItemClick {
    private var listFavoriteGames: ArrayList<GamesFavoriteEntity> = arrayListOf()

    private val mAdapter by lazy {
        FavoriteGamesAdapter(listener = this) {
            startActivity<DetailGamesActivity> {
                putExtra("idGames", it.idGame)
            }
        }
    }

    override fun getViewModel() = FavoriteGamesViewModel::class.java

    override fun getViewBinding(): ActivityFavoriteGamesBinding {
        return ActivityFavoriteGamesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initToolbar()
        setUpAdapter()

        viewModel.getListFavorite().observe(this) {
            listFavoriteGames.clear()
            listFavoriteGames.addAll(it)
            mAdapter.addData(listFavoriteGames)
            binding.rvFavoriteGames.apply {
                adapter = mAdapter
            }
        }
    }

    override fun observerViewModel() {}

    override fun onDeleteClicked(position: Int, idGame: Int) {
        viewModel.deleteGamesFavorite(idGames = idGame)
        mAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "Games Berhasil di Delete", Toast.LENGTH_SHORT).show()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.tvToolbarTitle.text = "FAVORITE GAMES"
        binding.toolbar.tvToolbarTitle.setTypeface(null, Typeface.BOLD)
    }

    private fun setUpAdapter() {
        binding.rvFavoriteGames.apply {
            linearLayoutManager(RecyclerView.VERTICAL)
        }
    }
}