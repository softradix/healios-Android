package com.softradix.healios.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Comments(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name = "postId")
    @SerializedName("postId")
    val postId: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "email")
    @SerializedName("email")
    val email: String,

    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String
)

