package com.bignerdranch.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class TestingHostActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    override fun getFilesDir(): File {
        return File(super.getFilesDir(), "testDir")
    }
}