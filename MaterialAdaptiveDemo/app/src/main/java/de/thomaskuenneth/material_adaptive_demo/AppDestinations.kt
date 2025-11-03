package de.thomaskuenneth.material_adaptive_demo

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector


enum class AppDestinations(
    @param:StringRes val labelRes: Int,
    val icon: ImageVector,
    @param:StringRes val contentDescription: Int = labelRes,
) {
    Home(R.string.tab_home, Icons.Default.Home), Info(R.string.tab_info, Icons.Default.Info),
}
