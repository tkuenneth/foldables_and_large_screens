package de.thomaskuenneth.material_adaptive_demo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.PaneAdaptedValue
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldPaneScope
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirectiveWithTwoPanesOnMediumWidth
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun HomePane(navigationState: NavigationState) {
    val coroutineScope = rememberCoroutineScope()
    val navigator = rememberListDetailPaneScaffoldNavigator<Int>(
        scaffoldDirective = calculatePaneScaffoldDirectiveWithTwoPanesOnMediumWidth(
            currentWindowAdaptiveInfo()
        )
    )
    var currentIndex by rememberSaveable { mutableIntStateOf(-1) }
    val onItemClicked: (Int) -> Unit = { id ->
        currentIndex = id
        coroutineScope.launch {
            navigator.navigateTo(
                pane = ListDetailPaneScaffoldRole.Detail, contentKey = id
            )
        }
    }
    val detailVisible =
        navigator.scaffoldValue[ListDetailPaneScaffoldRole.Detail] == PaneAdaptedValue.Expanded
    if (detailVisible && currentIndex == -1) currentIndex = 0
    NavigationHelper(
        navigator = navigator,
        navigationState = navigationState,
        coroutineScope = coroutineScope
    )
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            MyList(
                onItemClicked = onItemClicked,
                currentIndex = currentIndex,
                detailVisible = detailVisible
            )
        },
        detailPane = {
            MyListDetail(
                currentIndex = currentIndex,
                listHidden = navigator.scaffoldValue[ListDetailPaneScaffoldRole.List] == PaneAdaptedValue.Hidden
            )
        })
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThreePaneScaffoldPaneScope.MyList(
    onItemClicked: (Int) -> Unit, currentIndex: Int, detailVisible: Boolean
) {
    AnimatedPane {
        LazyColumn {
            items(20) {
                ListItem(
                    headlineContent = { Text("${it + 1}") },
                    modifier = Modifier.clickable { onItemClicked(it) },
                    colors = when (detailVisible) {
                        false -> ListItemDefaults.colors()
                        true -> if (currentIndex == it) {
                            ListItemDefaults.colors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                headlineColor = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        } else {
                            ListItemDefaults.colors()
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThreePaneScaffoldPaneScope.MyListDetail(
    currentIndex: Int, listHidden: Boolean
) {
    AnimatedPane {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                "${currentIndex + 1}",
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            if (listHidden) {
                Text(
                    text = stringResource(R.string.list_hidden),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
            }
        }
    }
}
