package de.thomaskuenneth.material.adaptive.multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform