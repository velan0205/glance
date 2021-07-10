package com.example.glance.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.glance.base.BaseViewModel
import com.example.glance.main.model.Posts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ViewModelMainActivity @Inject constructor() : BaseViewModel() {
    val liveDataPosts: MutableLiveData<List<Posts>> = MutableLiveData()
    val liveDataUsers: MutableLiveData<Int> = MutableLiveData()
    var liveDataTime: MutableLiveData<Long> = MutableLiveData()
    val liveError: MutableLiveData<String> = MutableLiveData()
    val progress = MutableLiveData(true)
    private val startTime = Calendar.getInstance().time

    fun fetchPosts() = viewModelScope.launch(Dispatchers.IO) {
        val postResponse = apiInterface.getPosts().execute()
        val usersResponse = apiInterface.getUsers().execute()

        if (postResponse.isSuccessful && postResponse.body() != null) {
            val result = postResponse.body()
            liveDataPosts.postValue(result)
        } else {
            liveError.postValue(postResponse.message())
        }
        if (usersResponse.isSuccessful && usersResponse.body() != null) {
            val result = usersResponse.body()
            val count = result.toString().length
            liveDataUsers.postValue(count)
        } else {
            liveError.postValue(usersResponse.message())
        }
        val endTime = Calendar.getInstance().time
        val timeTaken = endTime.time - startTime.time
        liveDataTime.postValue(timeTaken)
        progress.postValue(false)
    }

}