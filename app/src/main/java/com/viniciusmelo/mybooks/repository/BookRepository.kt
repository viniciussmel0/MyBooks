package com.viniciusmelo.mybooks.repository

import android.content.ContentValues
import android.content.Context
import com.viniciusmelo.mybooks.entity.BookEntity
import com.viniciusmelo.mybooks.helper.DatabaseConstants

class BookRepository private constructor(context: Context) {

    private var database = BookDatabaseHelper(context)

    companion object {
        private lateinit var instance: BookRepository

        fun getInstance(context: Context): BookRepository {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = BookRepository(context)
                }
            }
            return instance
        }
    }

    // Retorna todos os livros armazenados
    fun getAllBooks():List<BookEntity> {
        val db = database.readableDatabase
        val books = mutableListOf<BookEntity>()

        val cursor = db.query(DatabaseConstants.BOOK.TABLE_NAME, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.TITLE))
                val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                books.add(BookEntity(id, title, author, favorite, genre))

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return books
    }

    // Retorna todos os livros marcados como favoritos
    fun getFavoriteBooks():List<BookEntity> {
        val db = database.readableDatabase
        val books = mutableListOf<BookEntity>()

        val cursor = db.query(
            DatabaseConstants.BOOK.TABLE_NAME,
            null,
            "${DatabaseConstants.BOOK.COLUMNS.FAVORITE} = ?",
            arrayOf("1"),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.ID))
                val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.TITLE))
                val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.AUTHOR))
                val genre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.GENRE))
                val favorite: Boolean = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

                books.add(BookEntity(id, title, author, favorite, genre))

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return books
    }

    // Busca um livro pelo ID
    fun getBookById(id: Int): BookEntity? {
        val db = database.readableDatabase

        val cursor = db.query(
            DatabaseConstants.BOOK.TABLE_NAME,
            null,
            "${DatabaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        var book: BookEntity? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.TITLE))
            val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.AUTHOR))
            val genre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.GENRE))
            val favorite: Boolean = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseConstants.BOOK.COLUMNS.FAVORITE)) == 1

            book = BookEntity(id, title, author, favorite, genre)
        }

        cursor.close()
        db.close()

        return book
    }

    // Remove um likvro pelo ID
    fun deleteBook(id: Int): Boolean {
        val db = database.writableDatabase

         val rowsDeleted = db.delete(
            DatabaseConstants.BOOK.TABLE_NAME,
            "${DatabaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString())
        )

        return rowsDeleted > 0
    }

    // Alterna entre true e false o atributo "favorite"
    fun toggleFavoriteStatus(id: Int) {
        val book = getBookById(id)
        val newFavoriteStatus = if (book?.favorite == true) 0 else 1

        val db = database.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseConstants.BOOK.COLUMNS.FAVORITE, newFavoriteStatus)
        }
        db.update(
            DatabaseConstants.BOOK.TABLE_NAME,
            values,
            "${DatabaseConstants.BOOK.COLUMNS.ID} = ?",
            arrayOf(id.toString())
        )

        db.close()
    }

}