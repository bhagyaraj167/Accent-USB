package com.bignerdranch.android.myapplication

import android.content.Intent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bignerdranch.android.TestingHostActivity
import com.bignerdranch.android.myapplication.model.Itunes
import com.bignerdranch.android.myapplication.model.Result
import com.bignerdranch.android.myapplication.network.APIState
import com.bignerdranch.android.myapplication.network.Status
import com.bignerdranch.android.myapplication.ui.theme.MyApplicationTheme
import com.bignerdranch.android.myapplication.view.getItunes
import com.bignerdranch.android.myapplication.viewmodel.ItunesViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItunesScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<TestingHostActivity>()
    private val intents by lazy { MutableStateFlow(composeTestRule.activity.intent) }

    private val ItunesScreenViewModelSuccess = mockk<ItunesViewModel> {
        val mockKAdditionalAnswerScope = every { itunes } returns MutableStateFlow(
            APIState(
                Status.SUCCESS,
                Itunes(
                    resultCount = 1,
                    listOf(Result(artistId = 9,
                        trackViewUrl = "tets",
                        trackPrice = 33.0,
                        kind = "testkind",
                        trackName = "text",
                        artistName = "test",
                        artistViewUrl = "",
                        artworkUrl100 = "",
                        collectionCensoredName = "testcollectionCensoredName",
                        artworkUrl30 = "https://is4-ssl.mzstatic.com/image/thumb/Video/41/81/14/mzi.wdsoqdmh.jpg/30x30bb.jpg"))
                ),
                null)).asStateFlow()
    }

    private val ItunesScreenViewModelLoading = mockk<ItunesViewModel> {
        every { itunes } returns MutableStateFlow(
            APIState(
                Status.LOADING,
                null,
                null)).asStateFlow()
    }
    val LocalIntents = staticCompositionLocalOf<StateFlow<Intent>> {
        throw UnsupportedOperationException("Must be set via CompositionLocalProvider")
    }
    fun setContentForSuccess() {
        composeTestRule.setContent {
            MyApplicationTheme {
                CompositionLocalProvider(
                    LocalIntents provides intents
                ) {
                    getItunes(itunesViewModel = ItunesScreenViewModelSuccess,
                        navController  = rememberNavController())
                }
            }
        }
    }

    fun setContentForLoading() {
        composeTestRule.setContent {
            getItunes(itunesViewModel = ItunesScreenViewModelLoading, rememberNavController())
        }
    }

    @Test
    fun validateTrackName() {
        setContentForSuccess()
        composeTestRule.onNodeWithTag("trackname", useUnmergedTree = true)
            .assertIsDisplayed()
    }
    @Test
    fun validateSearchComponent() {
        setContentForSuccess()
        composeTestRule.onNodeWithTag("searchComponent", useUnmergedTree = true)
            .assertIsDisplayed()
    }
    @Test
    fun validateDropdown() {
        setContentForSuccess()
        composeTestRule.onNodeWithTag("dropDown", useUnmergedTree = true)
            .assertIsDisplayed()
    }
    @Test
    fun validateKindName() {
        setContentForSuccess()
        composeTestRule.onNodeWithTag("kindText", useUnmergedTree = true)
            .assertIsDisplayed()
    }
    @Test
    fun validateTrackPrice() {
        setContentForSuccess()
        composeTestRule.onNodeWithTag("trackPriceText", useUnmergedTree = true)
            .assertIsDisplayed()
    }
}