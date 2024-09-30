package com.waleed.livechallengesept.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.waleed.livechallengesept.data.ComicModel
import com.waleed.livechallengesept.data.ComicRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(private val repository: ComicRepository):ViewModel() {
    private val _comicSuccess = MutableStateFlow<ComicModel?>(null)
    val comicSuccess = _comicSuccess.asStateFlow()
    private val _loading = MutableStateFlow<Boolean>(true)
    val loading = _loading.asStateFlow()
    private val _error = MutableStateFlow<String>("")
    val error = _error.asStateFlow()

    init {
        fetchComic()
    }

    private fun fetchComic() {
        viewModelScope.launch (Dispatchers.IO){
            _loading.emit(true)
            try {
                val response = repository.fetchComic()
                if(response.isSuccessful){
                    _comicSuccess.emit(response.body())
                }else{
                    _error.emit(response.message())
                }
            }catch (e:Exception){
                e.localizedMessage?.let { _error.emit(it) }
            }finally {
                _loading.emit(false)
            }
        }
    }
}