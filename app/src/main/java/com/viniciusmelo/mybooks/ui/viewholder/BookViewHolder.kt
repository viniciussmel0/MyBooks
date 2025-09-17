package com.viniciusmelo.mybooks.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.viniciusmelo.mybooks.R
import com.viniciusmelo.mybooks.databinding.ItemBookBinding
import com.viniciusmelo.mybooks.entity.BookEntity
import com.viniciusmelo.mybooks.ui.listener.BookListener

class BookViewHolder(private val item : ItemBookBinding, private val listener: BookListener) : RecyclerView.ViewHolder(item.root){

    fun bind(book: BookEntity) {
        item.textviewTitle.text = book.title
        item.textviewGenre.text = book.genre
        item.textviewAuthor.text = book.author

        item.textviewTitle.setOnClickListener { listener.onClick(book.id) }

        setGenreBackground(book.genre)
        updateFavoriteIcon(book.favorite)

    }

    private fun updateFavoriteIcon(favorite: Boolean) {
        if (favorite) {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            item.imageviewFavorite.setImageResource(R.drawable.ic_favorite_empty)
        }
    }

    private fun setGenreBackground(genre: String) {
        when (genre) {
            "Terror" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_red)
            }
            "Fantasia" -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_fantasy)
            }
            else -> {
                item.textviewGenre.setBackgroundResource(R.drawable.rounded_label_teal)
            }
        }
    }



}