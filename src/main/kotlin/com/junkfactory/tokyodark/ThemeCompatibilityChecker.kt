package com.junkfactory.tokyodark

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.extensions.ExtensionPointName
import com.intellij.ide.ui.UIThemeProvider

/**
 * Checks theme compatibility and availability based on IntelliJ version.
 * Provides methods to determine which theme variants should be registered.
 */
/**
 * Extension point name for theme providers
 */
val THEME_PROVIDER_EP: ExtensionPointName<UIThemeProvider> =
    ExtensionPointName.create("com.intellij.themeProvider")

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
     * Theme metadata extracted from JSON file
     */
    data class ThemeMetadata(
        val name: String,
        val isDark: Boolean,
        val author: String? = null,
        val parentTheme: String? = null
    )

    /**
     * Loads and parses theme JSON file from resources
     * @param themePath Path to the theme JSON file (e.g., "/themes/TokyoDark.theme.json")
     * @return ThemeMetadata object with parsed data, or null if parsing fails
     */
    private fun loadThemeJson(themePath: String): ThemeMetadata? {
        return try {
            LOG.debug("Loading theme JSON from: $themePath")
            
            // Get resource stream
            val resourceStream = this::class.java.getResourceAsStream(themePath)
            if (resourceStream == null) {
                LOG.warn("Theme file not found in resources: $themePath")
                return null
            }
            
            // Read file content with proper encoding
            val jsonContent = resourceStream.use { stream ->
                String(stream.readBytes(), Charsets.UTF_8)
            }
            
            // Validate basic JSON structure
            if (jsonContent.trim().isEmpty() || !jsonContent.trim().startsWith("{")) {
                LOG.warn("Invalid JSON structure in theme file: $themePath")
                return null
            }
            
            // Parse theme metadata
            parseThemeMetadata(jsonContent)
            
        } catch (e: Exception) {
            LOG.error("Failed to load theme JSON from $themePath: ${e.message}", e)
            null
        }
    }

    /**
     * Parses theme metadata from JSON content using simple regex extraction
     * @param jsonContent Raw JSON content as string
     * @return ThemeMetadata object with extracted data, or null if parsing fails
     */
    private fun parseThemeMetadata(jsonContent: String): ThemeMetadata? {
        return try {
            // Extract name field using regex
            val nameRegex = "\"name\"\\s*:\\s*\"([^\"]+)\"".toRegex()
            val nameMatch = nameRegex.find(jsonContent)
            val name = nameMatch?.groupValues?.get(1)
                ?: throw IllegalArgumentException("Theme JSON missing required 'name' field")
            
            // Extract dark field using regex
            val darkRegex = "\"dark\"\\s*:\\s*(true|false)".toRegex()
            val isDark = darkRegex.find(jsonContent)?.groupValues?.get(1)?.toBoolean() ?: true
            
            // Extract optional author field
            val authorRegex = "\"author\"\\s*:\\s*\"([^\"]*)\"".toRegex()
            val author = authorRegex.find(jsonContent)?.groupValues?.get(1)
            
            // Extract optional parentTheme field
            val parentThemeRegex = "\"parentTheme\"\\s*:\\s*\"([^\"]*)\"".toRegex()
            val parentTheme = parentThemeRegex.find(jsonContent)?.groupValues?.get(1)
            
            ThemeMetadata(
                name = name,
                isDark = isDark,
                author = author,
                parentTheme = parentTheme
            )
            
        } catch (e: Exception) {
            LOG.error("Failed to parse theme metadata from JSON: ${e.message}", e)
            null
        }
    }

    /**
     * Dynamically discovers all registered themes via ExtensionPointName with JSON metadata parsing
     * @return List of ThemeConfig objects discovered from plugin.xml with enhanced metadata
     */
    fun getAllRegisteredThemes(): List<ThemeConfig> {
        return try {
            LOG.debug("Discovering themes via ExtensionPointName with JSON metadata parsing")

            val themeConfigs = mutableListOf<ThemeConfig>()
            val extensions = THEME_PROVIDER_EP.extensionList

            LOG.debug("Found ${extensions.size} theme provider extensions")

            for (themeProvider in extensions) {
                try {
                    val id = themeProvider.id
                    val path = themeProvider.path
                    if (id == null || path == null) {
                        LOG.warn("Theme provider has null id or path: ${themeProvider.javaClass.simpleName}")
                        continue
                    }

                    // Try to load theme metadata from JSON file
                    val metadata = loadThemeJson(path)
                    
                    // Determine theme variant based on ID and path
                    val variant = when {
                        id.contains("islands", ignoreCase = true) || path.contains("islands", ignoreCase = true) -> {
                            ThemeVariant.ISLANDS
                        }
                        else -> {
                            ThemeVariant.TRADITIONAL
                        }
                    }

                    // Extract name from JSON metadata first, then fallback to ID-based extraction
                    val name = metadata?.name ?: when {
                        id == "com.junkfactory.tokyodark" -> "TokyoDark"
                        id == "com.junkfactory.tokyodark-storm" -> "TokyoDark Storm"
                        id == "com.junkfactory.tokyodark-contrast" -> "TokyoDark Contrast"
                        id == "com.junkfactory.tokyodark-islands" -> "Tokyo Dark Islands"
                        else -> {
                            // Fallback: extract from ID after last dot and replace dashes with spaces
                            id.substringAfterLast(".", id ?: "Unknown")
                                .replace("-", " ")
                                .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                        }
                    }

                    // Extract dark mode from JSON metadata first, then fallback to default
                    val isDark = metadata?.isDark ?: true

                    val themeConfig = ThemeConfig(
                        id = id,
                        name = name,
                        path = path,
                        variant = variant,
                        isDark = isDark
                    )

                    themeConfigs.add(themeConfig)
                    
                    if (metadata != null) {
                        LOG.debug("Discovered theme with JSON metadata: ${themeConfig.name} (${themeConfig.id}) - ${themeConfig.variant}")
                        LOG.debug("  Metadata: dark=${metadata.isDark}, author=${metadata.author}, parentTheme=${metadata.parentTheme}")
                    } else {
                        LOG.debug("Discovered theme with fallback metadata: ${themeConfig.name} (${themeConfig.id}) - ${themeConfig.variant}")
                    }

                } catch (e: Exception) {
                    LOG.error("Error processing theme provider: ${themeProvider.javaClass.name}", e)
                }
            }

            LOG.info("Discovered ${themeConfigs.size} themes dynamically with JSON metadata parsing")
            themeConfigs

        } catch (e: Exception) {
            LOG.error("Error discovering themes via ExtensionPointName with JSON parsing", e)

            // Fallback to hardcoded themes if dynamic discovery fails
            LOG.warn("Falling back to hardcoded theme list")
            getHardcodedThemes()
        }
    }

    /**
     * Gets hardcoded theme list as fallback for dynamic discovery
     * @return Hardcoded list of ThemeConfig objects
     * 
     * Original hardcoded themes (kept as backup for rollback):
     * - TokyoDark (com.junkfactory.tokyodark)
     * - TokyoDark Storm (com.junkfactory.tokyodark-storm) 
     * - TokyoDark Contrast (com.junkfactory.tokyodark-contrast)
     * - Tokyo Dark Islands (com.junkfactory.tokyodark-islands)
     */
    private fun getHardcodedThemes(): List<ThemeConfig> {
        return listOf(
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
    }

    /**
     * List of all available themes (now dynamically discovered)
     * @return List of ThemeConfig objects
     */
    val ALL_THEMES: List<ThemeConfig> by lazy {
        getAllRegisteredThemes()
    }

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
                LOG.info(
                    "Islands theme is NOT supported on current version: ${VersionUtils.getFullVersion()}. " +
                            "Minimum required: ${VersionUtils.ISLANDS_MINIMUM_BUILD}"
                )
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