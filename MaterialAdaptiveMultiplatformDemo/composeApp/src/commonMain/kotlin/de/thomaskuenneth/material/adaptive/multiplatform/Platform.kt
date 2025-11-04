package de.thomaskuenneth.material.adaptive.multiplatform

import androidx.compose.runtime.Composable

@Composable
expect fun BackHandler(enabled: Boolean = true, onBack: () -> Unit)
