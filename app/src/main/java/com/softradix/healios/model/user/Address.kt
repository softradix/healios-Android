package com.softradix.healios.model.user

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity
data class Address (

	@SerializedName("street") val street : String,
	@SerializedName("suite") val suite : String,
	@SerializedName("city") val city : String,
	@SerializedName("zipcode") val zipcode : String,

)