package com.karlomaricevic.socialdeal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.karlomaricevic.socialdeal.designSystem.theme.SocialDealAppTheme
import com.karlomaricevic.socialdeal.feature.core.components.SocialDealAppNavigation
import com.karlomaricevic.socialdeal.feature.core.navigation.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialDealAppTheme {
                SocialDealAppNavigation(navigator)
            }
        }
    }
}
