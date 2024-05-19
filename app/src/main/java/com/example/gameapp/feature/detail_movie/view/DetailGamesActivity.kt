package com.example.gameapp.feature.detail_movie.view

import android.os.Bundle
import android.text.Spanned
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.common.extension.ViewState
import com.example.gameapp.common.extension.linearLayoutManager
import com.example.gameapp.common.extension.loadImage
import com.example.gameapp.common.extension.toContent
import com.example.gameapp.common.extension.toError
import com.example.gameapp.common.extension.toLoading
import com.example.gameapp.core.base.BaseVMActivity
import com.example.gameapp.databinding.ActivityDetailGamesBinding
import com.example.gameapp.feature.detail_movie.adapter.DeveloperAdapter
import com.example.gameapp.feature.detail_movie.view_model.DetailGamesViewModel
import com.example.gameapp.feature.main.adapter.GenresAdapter

class DetailGamesActivity : BaseVMActivity<DetailGamesViewModel, ActivityDetailGamesBinding>() {
    private var tempListGenreName = arrayListOf("")
    private var tempGames = ""

    private val adapterGenres by lazy {
        GenresAdapter()
    }

    private val adapterDevelopers by lazy {
        DeveloperAdapter()
    }

    override fun getViewModel() = DetailGamesViewModel::class.java

    override fun getViewBinding(): ActivityDetailGamesBinding {
        return ActivityDetailGamesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        viewModel.idGames.value = intent.getIntExtra("idGames", 0)
        setUpAdapter()
        initViewModel()

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivFavorite.setOnClickListener {
            tempGames = if (tempListGenreName.size == 1) {
                tempListGenreName.joinToString(separator = " ")
            } else {
                tempListGenreName.joinToString(separator = ", ")
            }

            viewModel.insertGamesFavoriteDb(
                detailGamesModel = viewModel.tempDetailGames.value!!,
                genres = tempGames
            )
            Toast.makeText(this, "Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun observerViewModel() {
        viewModel.detailGamesLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    binding.stateLayout.toContent()
                    state.data.let {
                        val description: Spanned = HtmlCompat.fromHtml(it.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
                        viewModel.tempDetailGames.value = it
                        binding.tvGamenName.text = it.name
                        binding.tvValueDescription.text = description
                        binding.ivGames.loadImage(it.background_image)
                        adapterGenres.addData(it.genres)
                        adapterDevelopers.addData(it.developers)

                        tempListGenreName.clear()
                        it.genres.forEach {
                            tempListGenreName.add(it.name)
                        }
                    }
                    binding.rvGendres.adapter = adapterGenres
                    binding.rvDevelopers.adapter = adapterDevelopers
                }

                is ViewState.Failed -> {
                    binding.stateLayout.toError {
                        initViewModel()
                    }
                }

                else -> {}
            }
        }

    }

    private fun setUpAdapter() {
        binding.rvGendres.apply { linearLayoutManager(RecyclerView.HORIZONTAL) }

        binding.rvDevelopers.apply { linearLayoutManager(RecyclerView.HORIZONTAL) }
    }

    private fun initViewModel() {
        viewModel.getListGames()
    }

}