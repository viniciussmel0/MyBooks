package com.viniciusmelo.mybooks.helper

class DatabaseConstants {
    object BOOK {
        const val TABLE_NAME = "Books"

        object COLUMNS {
            const val ID = "Id"
            const val TITLE = "Title"
            const val AUTHOR = "Author"
            const val GENRE = "Genre"
            const val FAVORITE = "Favorite"
        }
    }
}