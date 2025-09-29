package com.viniciusmelo.mybooks.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.viniciusmelo.mybooks.entity.BookEntity
import com.viniciusmelo.mybooks.repository.BookRepository

class DetailsViewModel : ViewModel() {

    private val repository: BookRepository = BookRepository()

    private val _book = MutableLiveData<BookEntity>()
    val book: LiveData<BookEntity> = _book

    fun getBookById(id: Int) {
        _book.value = repository.getBookById(id)
    }


}