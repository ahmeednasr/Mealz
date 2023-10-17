package com.example.mealzapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.CategoryResponse
import com.example.domain.usecase.GetMeals
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val getMealsUseCase: GetMeals
) : ViewModel() {
    private val _category: MutableStateFlow<CategoryResponse?> = MutableStateFlow(null)
    val category: StateFlow<CategoryResponse?> = _category

    private val mealsJob: Job = Job()
    fun getMeals() {
        viewModelScope.launch(mealsJob) {
            try {
                _category.value = getMealsUseCase()

            } catch (e: Exception) {
                Log.e("MealsViewModel", e.message.toString())
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        mealsJob.cancel()
    }
}