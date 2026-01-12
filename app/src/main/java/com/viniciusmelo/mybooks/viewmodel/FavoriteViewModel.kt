package com.viniciusmelo.mybooks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viniciusmelo.mybooks.entity.BookEntity
import com.viniciusmelo.mybooks.repository.BookRepository

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    private val repository = BookRepository.getInstance(application.applicationContext)

    fun getFavoriteBooks() {
        _books.value = repository.getFavoriteBooks()
    }

    fun favorite(id: Int) {
        repository.toggleFavoriteStatus(id)
    }
}