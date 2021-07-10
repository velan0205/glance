package com.example.glance.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.glance.base.BaseViewModel
import com.example.glance.main.model.Posts
import com.example.glance.main.model.Users
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class ViewModelMainActivity @Inject constructor() : BaseViewModel() {

    val liveDataPosts: MutableLiveData<List<Posts>> = MutableLiveData()
    val liveDataUsers: MutableLiveData<Int> = MutableLiveData()

    val liveError: MutableLiveData<String> = MutableLiveData()
    val progress = MutableLiveData(true)

    fun fetchPosts() = viewModelScope.launch(Dispatchers.IO) {
        val postResponse = apiInterface.getPosts().execute()
        val usersResponse = apiInterface.getUsers().execute()

        if (postResponse.isSuccessful && postResponse.body() != null) {
            val result = postResponse.body()
            liveDataPosts.postValue(result)
        } else {
            val message: String = if (postResponse.errorBody() != null) {
                postResponse.errorBody().toString()
            } else {
                "Something went wrong!"
            }
            liveError.postValue(message)
        }
        if (usersResponse.isSuccessful && usersResponse.body() != null) {
            val result = usersResponse.body()
            val count = result.toString().length
            liveDataUsers.postValue(count)
        } else {
            val message: String = if (usersResponse.errorBody() != null) {
                usersResponse.errorBody().toString()
            } else {
                "Something went wrong!"
            }
            liveError.postValue(message)
        }
        progress.postValue(false)

    }

}