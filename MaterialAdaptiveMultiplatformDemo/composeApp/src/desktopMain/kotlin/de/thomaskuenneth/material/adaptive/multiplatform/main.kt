package de.thomaskuenneth.material.adaptive.multiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import materialadaptivemultiplatformdemo.composeapp.generated.resources.Res
import materialadaptivemultiplatformdemo.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
    ) {
        MaterialAdaptiveDemo()
    }
}
