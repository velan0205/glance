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
/*
{
    "id": 1,
    "name": "Leanne Graham",
    "username": "Bret",
    "email": "Sincere@april.biz",
    "address":
    {
        "street": "Kulas Light",
        "suite": "Apt. 556",
        "city": "Gwenborough",
        "zipcode": "92998-3874",
        "geo": {
        "lat": "-37.3159",
        "lng": "81.1496"
    }
    },
    "phone": "1-770-736-8031 x56442",
    "website": "hildegard.org",
    "company":
    {
        "name": "Romaguera-Crona",
        "catchPhrase": "Multi-layered client-server neural-net",
        "bs": "harness real-time e-markets"
    }
}*/