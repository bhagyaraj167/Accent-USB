package com.bignerdranch.android.myapplication

import android.content.Intent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bignerdranch.android.TestingHostActivity
import com.bignerdranch.android.myapplication.ui.theme.MyApplicationTheme
import com.bignerdranch.android.myapplication.view.ITunesDetailsScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItunesDetailsScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestingHostActivity>()
    private val intents by lazy { MutableStateFlow(composeTestRule.activity.intent) }

    val LocalIntents = staticCompositionLocalOf<StateFlow<Intent>> {
        throw UnsupportedOperationException("Must be set via CompositionLocalProvider")
    }

    fun setContent() {
        composeTestRule.setContent {
            MyApplicationTheme {
                CompositionLocalProvider(
                    LocalIntents provides intents
                ) {
                    ITunesDetailsScreen(
                        "testtrackName",
                        "https://is4-ssl.mzstatic.com/image/thumb/Video/41/81/14/mzi.wdsoqdmh.jpg/30x30bb.jpg",
                        "longDescText"
                    )
                }
            }

        }
    }

    @Test
    fun validateTrackNameDetail() {
        setContent()
        composeTestRule.onNodeWithTag("detailedtrackName", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun validateImageDetail() {
        setContent()
        composeTestRule.onNodeWithTag("detailedImage", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun validateSearchComponent() {
        setContent()
        composeTestRule.onNodeWithTag("detailedLongDesc", useUnmergedTree = true)
            .assertIsDisplayed()
    }
}