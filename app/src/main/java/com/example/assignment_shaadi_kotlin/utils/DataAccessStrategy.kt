package com.example.assignment_shaadi_kotlin.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.assignment_shaadi_kotlin.data.entities.Result
import com.example.assignment_shaadi_kotlin.utils.Resource.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

fun <T, A> performGetOperation(databaseQuery: ()  -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: (T, A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val responseStatus = networkCall.invoke()
            if (responseStatus.status == Status.SUCCESS) {
                val source = databaseQuery.invoke().map {
                    Log.e("source", it.toString())
                    saveCallResult(it, responseStatus.data!!)
                    Resource.success(it)
                }
                emitSource(source)
            } else if (responseStatus.status == Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
                Log.e("mm",responseStatus.message)

            }
        }



