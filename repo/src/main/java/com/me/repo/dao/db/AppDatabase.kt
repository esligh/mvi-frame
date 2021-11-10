package com.me.repo.dao.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.me.repo.dao.ArticleDao
import com.me.repo.dao.RemoteKey
import com.me.repo.dao.RemoteKeyDao
import com.me.repo.entity.home.ArticleData

@Database(
    entities = [ArticleData::class, RemoteKey::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        private const val DB_NAME = "app.db"

        @Volatile
        private var instance: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return instance ?: Room.databaseBuilder(context, AppDatabase::class.java,
                DB_NAME
            )
                .build().also {
                    instance = it
                }
        }
    }
}