package com.morteza.electricshutter_app

import android.app.Application
import androidx.room.Room
import com.morteza.electricshutter_app.data.db.AppDatabase

class ShutterApp : Application() {

    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "shutter_database"
        ).fallbackToDestructiveMigration().build()
    }
}
