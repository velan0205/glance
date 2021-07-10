package com.example.glance.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Posts(val userId: String, val id: String, val title: String, val body: String) : Parcelable


@Parcelize
class Users(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val address: Address, val phone: String, val website: String, val company: Company
) : Parcelable


@Parcelize
class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
) : Parcelable

@Parcelize
class Geo(val lat: String, val lng: String) : Parcelable

@Parcelize
class Company(val name: String, val catchPhrase: String, val bs: String) : Parcelable

