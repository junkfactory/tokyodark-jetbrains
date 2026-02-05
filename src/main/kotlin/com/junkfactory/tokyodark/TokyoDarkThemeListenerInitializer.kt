package com.junkfactory.tokyodark

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity

/**
 * Project activity that initializes the theme change listener on project startup.
 * 
 * This runs once per project but subscribes to an application-level theme change listener.
 * The listener itself is application-wide, but we only need to initialize it once.
 */
class TokyoDarkThemeListenerInitializer : ProjectActivity {
    
    companion object {
        private val LOG = Logger.getInstance(TokyoDarkThemeListenerInitializer::class.java)
        // Static flag to ensure theme listener is only initialized once across all projects
        private var themeListenerInitialized = false
    }

    override suspend fun execute(project: Project) {
        // Only initialize the application-level theme listener once
        // This is synchronized to prevent race conditions when multiple projects open simultaneously
        synchronized(TokyoDarkThemeListenerInitializer::class) {
            if (!themeListenerInitialized) {
                themeListenerInitialized = true
                LOG.info("=== TokyoDarkThemeListenerInitializer: Setting up theme change listener ===")
                initializeThemeChangeListener(project)
            }
        }
    }
    
    private fun initializeThemeChangeListener(project: Project) {
        try {
            LOG.info("=== TokyoDarkThemeListenerInitializer: Initializing theme listener ===")
            
            // Connect to the application message bus with the project as parent
            // The connection will be automatically cleaned up when the project closes
            val connection = ApplicationManager.getApplication().messageBus.connect(project)
            
            // Create and subscribe the listener
            val listener = LafManagerListener { source ->
                handleThemeChange(source)
            }
            
            connection.subscribe(LafManagerListener.TOPIC, listener)
            
            LOG.info("=== Theme change listener registered successfully ===")
        } catch (e: Exception) {
            LOG.error("Failed to register theme change listener", e)
        }
    }
    
    private fun handleThemeChange(source: LafManager) {
        try {
            LOG.info("=== Theme Change Listener Fired ===")
            
            // Get current theme with null-safety
            val currentTheme = source.currentUIThemeLookAndFeel
            if (currentTheme == null) {
                LOG.warn("currentUIThemeLookAndFeel is null, skipping Islands compatibility check")
                return
            }
            
            val themeName = currentTheme.name
            if (themeName.isNullOrBlank()) {
                LOG.warn("Theme name is null or blank, skipping Islands compatibility check")
                return
            }
            
            LOG.info("Theme changed to: '$themeName'")
            
            // Only check for Islands theme compatibility issues if Islands is NOT supported
            if (VersionUtils.isIslandsSupported()) {
                LOG.info("Islands theme is supported on this version (${VersionUtils.getCurrentBuild()}) - no notification needed")
                return
            }
            
            // Show notification when Islands theme is selected on unsupported version
            if (themeName.contains("Islands", ignoreCase = true)) {
                LOG.info("Islands theme ('$themeName') selected on unsupported version (${VersionUtils.getCurrentBuild()}) - showing notification")
                showIslandsCompatibilityNotification()
            } else {
                LOG.debug("Non-Islands theme selected: '$themeName'")
            }
        } catch (e: Exception) {
            LOG.error("Error in theme change listener", e)
        }
    }
    
    private fun showIslandsCompatibilityNotification() {
        try {
            LOG.info("=== Attempting to show Islands Compatibility Notification ===")
            
            val notificationGroupManager = NotificationGroupManager.getInstance()
            if (notificationGroupManager == null) {
                LOG.error("NotificationGroupManager is null - cannot create notification")
                return
            }
            
            val notificationGroup = notificationGroupManager.getNotificationGroup("TokyoDark Theme")
            if (notificationGroup == null) {
                LOG.error("Notification group 'TokyoDark Theme' not found in plugin.xml")
                LOG.error("Make sure <notificationGroup id=\"TokyoDark Theme\" displayType=\"BALLOON\"/> is defined in plugin.xml")
                return
            }
            
            val fullVersion = VersionUtils.getFullVersion()
            val currentBuild = VersionUtils.getCurrentBuild()
            val minimumBuild = VersionUtils.ISLANDS_MINIMUM_BUILD
            
            val title = "TokyoDark Islands Theme Compatibility"
            val message = "The TokyoDark Islands theme have rendering issues on IntelliJ $fullVersion (build $currentBuild). " +
                "Please upgrade to IntelliJ 2025.3 or later (build $minimumBuild+)."
            
            LOG.info("Creating notification: title='$title'")
            val notification = notificationGroup.createNotification(title, message, NotificationType.WARNING)
            
            // Get the active project to display the notification in
            val projectManager = com.intellij.openapi.project.ProjectManager.getInstance()
            val activeProject = projectManager.openProjects.firstOrNull()
            
            if (activeProject != null) {
                LOG.info("Notifying active project: ${activeProject.name}")
                notification.notify(activeProject)
            } else {
                LOG.warn("No active project found, attempting to notify with null (fallback)")
                notification.notify(null)
            }
            LOG.info("Islands compatibility notification shown successfully to user")
            
        } catch (e: Exception) {
            LOG.error("Exception while showing Islands compatibility notification: ${e.message}", e)
        }
    }
}
