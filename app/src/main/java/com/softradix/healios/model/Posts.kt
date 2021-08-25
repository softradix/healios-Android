package com.softradix.healios.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Posts(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    val userId: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String
):Serializable
