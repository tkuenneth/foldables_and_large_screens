package de.thomaskuenneth.material.adaptive.multiplatform

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import materialadaptivemultiplatformdemo.composeapp.generated.resources.Res
import materialadaptivemultiplatformdemo.composeapp.generated.resources.tab_home
import materialadaptivemultiplatformdemo.composeapp.generated.resources.tab_info
import org.jetbrains.compose.resources.StringResource


enum class AppDestinations(
    val labelRes: StringResource,
    val icon: ImageVector,
    val contentDescription: StringResource = labelRes,
) {
    Home(Res.string.tab_home, Icons.Default.Home), Info(Res.string.tab_info, Icons.Default.Info),
}
