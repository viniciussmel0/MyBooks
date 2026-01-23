package com.viniciusmelo.mybooks.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viniciusmelo.mybooks.entity.BookEntity
import com.viniciusmelo.mybooks.repository.BookRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BookRepository.getInstance(application.applicationContext)

    private val _books = MutableLiveData<List<BookEntity>>()
    val books: LiveData<List<BookEntity>> = _books

    init {
        if (repository.getAllBooks().isEmpty()) {
            repository.loadInitialData()
        }
    }

    fun getAllBooks() {
        _books.value = repository.getAllBooks()
    }

    fun favorite(id: Int) {
        repository.toggleFavoriteStatus(id)
    }
}