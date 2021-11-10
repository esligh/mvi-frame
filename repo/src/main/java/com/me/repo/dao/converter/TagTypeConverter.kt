package com.me.home.dao.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.me.repo.entity.home.Tag


class TagTypeConverter {
    @TypeConverter
    fun stringToObject(value: String): List<Tag> {
        val listType = object : TypeToken<List<Tag>>() {
        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun objectToString(list: List<Tag>): String {
        return Gson().toJson(list)
    }
}