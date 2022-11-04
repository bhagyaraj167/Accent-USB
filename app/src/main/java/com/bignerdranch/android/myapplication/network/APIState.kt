package com.bignerdranch.android.myapplication.network

data class APIState<out T>(val status: Status, val data: T?, val message: String?){
    companion object{
        fun <T> success(data: T?): APIState<T>{
            return APIState(Status.SUCCESS, data = data, null)
        }
        fun <T> error(message: String?): APIState<T>{
            return APIState(Status.ERROR,null,message)
        }
        fun <T> loading(): APIState<T>{
            return APIState(Status.LOADING,null,null)
        }
    }
}




enum class Status{
    LOADING,
    SUCCESS,
    ERROR
}