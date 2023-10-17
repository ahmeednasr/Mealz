package com.example.mealzapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MealsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getMeals()
        val mealsAdapter = MealsAdapter()
        val rv: RecyclerView = findViewById(R.id.category_rv)
        lifecycleScope.launch {
            viewModel.category.collect {
                mealsAdapter.submitList(it?.categories)
                rv.adapter = mealsAdapter
            }
        }

    }

}