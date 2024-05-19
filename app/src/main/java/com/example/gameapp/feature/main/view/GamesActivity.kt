package com.example.gameapp.feature.main.view

import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.example.gameapp.R
import com.example.gameapp.common.extension.ViewState
import com.example.gameapp.common.extension.linearLayoutManager
import com.example.gameapp.common.extension.startActivity
import com.example.gameapp.common.extension.toContent
import com.example.gameapp.common.extension.toError
import com.example.gameapp.common.extension.toLoading
import com.example.gameapp.core.base.BaseVMActivity
import com.example.gameapp.databinding.ActivityGamesBinding
import com.example.gameapp.feature.detail_movie.view.DetailGamesActivity
import com.example.gameapp.feature.favorite_games.view.FavoriteGamesActivity
import com.example.gameapp.feature.main.adapter.GamesAdapter
import com.example.gameapp.feature.main.view_model.GamesViewModel

class GamesActivity : BaseVMActivity<GamesViewModel, ActivityGamesBinding>() {

    private val adapter by lazy {
        GamesAdapter {
            startActivity<DetailGamesActivity> {
                putExtra("idGames", it.id)
            }
        }
    }

    override fun getViewModel() = GamesViewModel::class.java

    override fun getViewBinding(): ActivityGamesBinding {
        return ActivityGamesBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        initToolbar()
        setUpAdapter()
        initViewModel()

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter(text.toString())
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    override fun observerViewModel() {
        viewModel.listGamesLiveData.onResult { state ->
            when (state) {
                is ViewState.Loading -> {
                    binding.stateLayout.toLoading()
                }

                is ViewState.Success -> {
                    binding.stateLayout.toContent()
                    if (state.data.results?.isNotEmpty()!!) {
                        state.data.results.let { adapter.addData(it) }
                        binding.rvListGames.adapter = adapter
                    }
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

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(false)
            setDisplayShowHomeEnabled(true)
        }
        binding.toolbar.tvToolbarTitle.text = "RAWG GAMES"
        binding.toolbar.tvToolbarTitle.setTypeface(null, Typeface.BOLD)
    }

    private fun setUpAdapter() {
        binding.rvListGames.apply {
            linearLayoutManager(RecyclerView.VERTICAL)
        }
    }

    private fun initViewModel() {
        viewModel.getListGames()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_custom -> {
                // Handle custom action
                startActivity<FavoriteGamesActivity> {}
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}