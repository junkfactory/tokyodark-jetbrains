package com.junkfactory.tokyodark

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.application.ApplicationInfo

/**
 * Checks theme compatibility and availability based on IntelliJ version.
 * Provides methods to determine which theme variants should be registered.
 */
object ThemeCompatibilityChecker {
    
    private val LOG = Logger.getInstance(ThemeCompatibilityChecker::class.java)
    
    /**
     * Theme variant types supported by the plugin
     */
    enum class ThemeVariant {
        TRADITIONAL,    // TokyoDark, TokyoDark Storm, TokyoDark Contrast
        ISLANDS         // TokyoDark Islands
    }
    
    /**
     * Theme configuration data class
     */
    data class ThemeConfig(
        val id: String,
        val name: String,
        val path: String,
        val variant: ThemeVariant,
        val isDark: Boolean = true
    )
    
    /**
     * List of all available themes
     */
    val ALL_THEMES = listOf(
        // Traditional themes - available on all versions
        ThemeConfig(
            id = "com.junkfactory.tokyodark",
            name = "TokyoDark",
            path = "/themes/TokyoDark.theme.json",
            variant = ThemeVariant.TRADITIONAL,
            isDark = true
        ),
        ThemeConfig(
            id = "com.junkfactory.tokyodark-storm",
            name = "TokyoDark Storm",
            path = "/themes/TokyoDarkStorm.theme.json",
            variant = ThemeVariant.TRADITIONAL,
            isDark = true
        ),
        ThemeConfig(
            id = "com.junkfactory.tokyodark-contrast",
            name = "TokyoDark Contrast",
            path = "/themes/TokyoDarkContrast.theme.json",
            variant = ThemeVariant.TRADITIONAL,
            isDark = true
        ),
        // Islands theme - only available on 2025.3+
        ThemeConfig(
            id = "com.junkfactory.tokyodark-islands",
            name = "Tokyo Dark Islands",
            path = "/themes/TokyoDarkIslands.theme.json",
            variant = ThemeVariant.ISLANDS,
            isDark = true
        )
    )
    
    /**
     * Checks if Islands themes are supported on current IntelliJ version
     * @return true if Islands themes should be registered
     */
    fun isIslandsThemeSupported(): Boolean {
        return try {
            val supported = VersionUtils.isIslandsSupported()
            if (supported) {
                LOG.info("Islands theme is supported on current version: ${VersionUtils.getFullVersion()}")
            } else {
                LOG.info("Islands theme is NOT supported on current version: ${VersionUtils.getFullVersion()}. " +
                        "Minimum required: ${VersionUtils.ISLANDS_MINIMUM_BUILD}")
            }
            supported
        } catch (e: Exception) {
            LOG.error("Error checking Islands theme compatibility", e)
            false
        }
    }
    
    /**
     * Gets list of themes that should be registered for current IntelliJ version
     * @return List of ThemeConfig objects to register
     */
    fun getThemesForCurrentVersion(): List<ThemeConfig> {
        val islandsSupported = isIslandsThemeSupported()
        
        return ALL_THEMES.filter { theme ->
            when (theme.variant) {
                ThemeVariant.TRADITIONAL -> true  // Always available
                ThemeVariant.ISLANDS -> islandsSupported  // Only on 2025.3+
            }
        }
    }
    
    /**
     * Gets the current IntelliJ version info for display
     * @return Version information string
     */
    fun getVersionInfo(): String {
        return "IntelliJ ${VersionUtils.getFullVersion()} (Build: ${VersionUtils.getCurrentBuild()})"
    }
    
    /**
     * Gets theme availability summary for debugging/logging
     * @return Summary string of available themes
     */
    fun getAvailabilitySummary(): String {
        val themes = getThemesForCurrentVersion()
        val islandsStatus = if (isIslandsThemeSupported()) "available" else "unavailable"
        
        return buildString {
            appendLine("TokyoDark Theme Plugin - Version: ${getVersionInfo()}")
            appendLine("Islands theme support: $islandsStatus")
            appendLine("Available themes (${themes.size}):")
            themes.forEach { theme ->
                appendLine("  - ${theme.name} (${theme.variant})")
            }
        }
    }
}