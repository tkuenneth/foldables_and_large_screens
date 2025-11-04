package de.thomaskuenneth.material.adaptive.multiplatform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldPaneScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ThreePaneScaffoldPaneScope.ColoredBox(
    textColor: Color,
    backgroundColor: Color,
    shouldShowButton: Boolean,
    resIdMessage: StringResource,
    resIdButton: StringResource,
    onClick: () -> Unit = {}
) {
    AnimatedPane {
        Box(
            modifier = Modifier.background(backgroundColor), contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = stringResource(resIdMessage),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(
                    Alignment.Center
                ),
                color = textColor
            )
            if (shouldShowButton) {
                Button(onClick = onClick, modifier = Modifier.padding(all = 32.dp)) {
                    Text(
                        text = stringResource(resIdButton)
                    )
                }
            }
        }
    }
}
