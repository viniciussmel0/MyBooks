package com.viniciusmelo.mybooks.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.viniciusmelo.mybooks.R
import com.viniciusmelo.mybooks.databinding.ItemBookBinding
import com.viniciusmelo.mybooks.entity.BookEntity

class BookViewHolder(private val item : ItemBookBinding) : RecyclerView.ViewHolder(item.root) {

    fun bind(book: BookEntity) {
        item.textviewTitle.text = book.title
        item.textviewGenre.text = book.genre
        item.textviewAuthor.text = book.author

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