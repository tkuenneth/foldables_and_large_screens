package de.thomaskuenneth.material.adaptive.multiplatform

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_EXPANDED_LOWER_BOUND
import androidx.window.core.layout.WindowWidthSizeClass
import materialadaptivemultiplatformdemo.composeapp.generated.resources.Res
import materialadaptivemultiplatformdemo.composeapp.generated.resources.app_name
import materialadaptivemultiplatformdemo.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialAdaptiveDemo() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.Home) }
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val customNavSuiteType =
        if (adaptiveInfo.windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_EXPANDED_LOWER_BOUND)) {
            NavigationSuiteType.NavigationDrawer
        } else {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
        }
    val navigationState = remember { NavigationState() }
    Scaffold(
        contentWindowInsets = WindowInsets(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.app_name)) },
                navigationIcon = {
                    if (navigationState.canNavigateBack) {
                        IconButton(
                            onClick = {
                                navigationState.navigateBack()
                            }
                        ) {
                            Icon(
                                Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = stringResource(Res.string.back)
                            )
                        }
                    }
                })
        }) { paddingValues ->
        NavigationSuiteScaffold(
            modifier = Modifier.padding(paddingValues),
            layoutType = customNavSuiteType,
            navigationSuiteItems = {
                AppDestinations.entries.forEach {
                    item(
                        selected = it == currentDestination,
                        onClick = { currentDestination = it },
                        icon = {
                            Icon(
                                imageVector = it.icon,
                                contentDescription = stringResource(it.contentDescription)
                            )
                        },
                        label = {
                            Text(
                                text = stringResource(it.labelRes)
                            )
                        },
                    )
                }
            }) {
            when (currentDestination) {
                AppDestinations.Home -> {
                    HomePane(navigationState)
                }

                AppDestinations.Info -> {
                    InfoPane(navigationState)
                }
            }
        }
    }
}
