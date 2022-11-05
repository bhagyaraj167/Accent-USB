package com.bignerdranch.android.myapplication.navigation

sealed class Screen(val route: String){
    object ItunesListScreen: Screen("ItunesListScreen")
    object ItunesDetailsScreen: Screen("ItunesDetailsScreen")

    fun withargs(vararg args:String):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}
