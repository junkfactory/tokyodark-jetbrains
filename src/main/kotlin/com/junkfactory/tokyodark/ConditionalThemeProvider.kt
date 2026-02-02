package com.junkfactory.tokyodark

import com.intellij.ide.plugins.DynamicPluginListener
import com.intellij.ide.plugins.IdeaPluginDescriptor
import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.Disposable
import org.jetbrains.annotations.NotNull

/**
 * Startup activity that initializes theme compatibility checking
 * and logs available themes based on IntelliJ version.
 * 
 * This service runs on plugin startup and determines which themes
 * should be available based on the current IntelliJ version.
 */
class ConditionalThemeProvider : ProjectActivity, DynamicPluginListener, Disposable {
    
    private val LOG = Logger.getInstance(ConditionalThemeProvider::class.java)
    private var isInitialized = false
    
    companion object {
        private val COMPANION_LOG = Logger.getInstance(ConditionalThemeProvider::class.java)
        
        init {
            // Register dynamic plugin listener to handle plugin lifecycle
            ApplicationManager.getApplication().messageBus.connect()
                .subscribe(DynamicPluginListener.TOPIC, object : DynamicPluginListener {
                    override fun pluginLoaded(pluginDescriptor: IdeaPluginDescriptor) {
                        if (pluginDescriptor.pluginId?.idString == "com.junkfactory.tokyodark") {
                            COMPANION_LOG.info("TokyoDark plugin loaded - checking theme compatibility")
                        }
                    }
                    
                    override fun beforePluginUnload(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
                        // Cleanup if needed
                    }
                    
                    override fun pluginUnloaded(pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
                        // Cleanup if needed
                    }
                })
        }
    }
    
    /**
     * Executes on project startup
     */
    override suspend fun execute(project: Project) {
        if (!isInitialized) {
            initializeThemes()
        }
    }
    
    /**
     * Initialize theme compatibility checking
     */
    private fun initializeThemes() {
        try {
            isInitialized = true
            
            // Get version information
            val versionInfo = ThemeCompatibilityChecker.getVersionInfo()
            val islandsSupported = ThemeCompatibilityChecker.isIslandsThemeSupported()
            
            // Log theme availability
            LOG.info("Initializing TokyoDark Theme Plugin")
            LOG.info("Running on: $versionInfo")
            LOG.info("Islands theme support: ${if (islandsSupported) "ENABLED" else "DISABLED"}")
            
            // Get available themes
            val availableThemes = ThemeCompatibilityChecker.getThemesForCurrentVersion()
            
            LOG.info("Available themes (${availableThemes.size}):")
            availableThemes.forEach { theme ->
                LOG.info("  - ${theme.name} (ID: ${theme.id})")
            }
            
            if (!islandsSupported) {
                LOG.info("Note: Tokyo Dark Islands theme is not available on this IntelliJ version.")
                LOG.info("      Upgrade to IntelliJ 2025.3 or later to use the Islands theme.")
            }
            
            // Log full summary for debugging
            if (LOG.isDebugEnabled) {
                LOG.debug(ThemeCompatibilityChecker.getAvailabilitySummary())
            }
            
        } catch (e: Exception) {
            LOG.error("Failed to initialize TokyoDark theme compatibility checker", e)
        }
    }
    
    /**
     * Called when plugin is loaded dynamically
     */
    override fun pluginLoaded(@NotNull pluginDescriptor: IdeaPluginDescriptor) {
        if (pluginDescriptor.pluginId?.idString == "com.junkfactory.tokyodark") {
            LOG.info("TokyoDark plugin dynamically loaded")
            initializeThemes()
        }
    }
    
    /**
     * Called before plugin is unloaded
     */
    override fun beforePluginUnload(@NotNull pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
        if (pluginDescriptor.pluginId?.idString == "com.junkfactory.tokyodark") {
            LOG.info("TokyoDark plugin unloading (update: $isUpdate)")
        }
    }
    
    /**
     * Called after plugin is unloaded
     */
    override fun pluginUnloaded(@NotNull pluginDescriptor: IdeaPluginDescriptor, isUpdate: Boolean) {
        if (pluginDescriptor.pluginId?.idString == "com.junkfactory.tokyodark") {
            LOG.info("TokyoDark plugin unloaded (update: $isUpdate)")
            isInitialized = false
        }
    }
    
    /**
     * Get plugin descriptor
     */
    private fun getPluginDescriptor(): IdeaPluginDescriptor? {
        // Simplified approach - return null as the plugin descriptor is not essential for theme functionality
        // This method can be implemented later if needed using proper 2025.3 APIs
        return null
    }
    
    /**
     * Check if running in compatible IntelliJ version
     */
    fun isCompatible(): Boolean {
        return try {
            val build = ApplicationInfo.getInstance().build.asString()
            // Check if build number is valid (not empty or malformed)
            build.isNotBlank() && build.contains(".")
        } catch (e: Exception) {
            LOG.error("Error checking compatibility", e)
            false
        }
    }
    
    /**
     * Get available theme count for current version
     */
    fun getAvailableThemeCount(): Int {
        return ThemeCompatibilityChecker.getThemesForCurrentVersion().size
    }
    
    /**
     * Check if Islands theme is available on current version
     */
    fun isIslandsThemeAvailable(): Boolean {
        return ThemeCompatibilityChecker.isIslandsThemeSupported()
    }
    
    override fun dispose() {
        isInitialized = false
        LOG.info("ConditionalThemeProvider disposed")
    }
}