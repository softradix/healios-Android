package com.softradix.healios

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.softradix.healios.room.database.AppDatabase


class HealiosApp : Application() {
    companion object {
        var appDatabase: AppDatabase? = null

    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        appDatabase = AppDatabase.getInstance(this)
    }


}