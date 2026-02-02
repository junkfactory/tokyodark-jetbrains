package com.junkfactory.tokyodark

import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBPanel
import com.intellij.util.ui.JBUI
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.BorderFactory
import javax.swing.Box
import javax.swing.BoxLayout
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSeparator

/**
 * Settings page for TokyoDark theme showing version compatibility and theme information.
 * Accessible via Settings → Appearance → TokyoDark Theme
 */
class TokyoDarkSettingsConfigurable : Configurable {
    
    companion object {
        const val DISPLAY_NAME = "TokyoDark Theme"
        const val ID = "com.junkfactory.tokyodark.settings"
    }
    
    private var mainPanel: JPanel? = null
    
    override fun getDisplayName(): String = DISPLAY_NAME
    
    override fun createComponent(): JComponent {
        mainPanel = createMainPanel()
        return mainPanel!!
    }
    
    private fun createMainPanel(): JPanel {
        val panel = JBPanel<JBPanel<*>>(BorderLayout())
        panel.border = JBUI.Borders.empty(20)
        
        // Header section
        val headerPanel = createHeaderPanel()
        panel.add(headerPanel, BorderLayout.NORTH)
        
        // Content section with version info and themes
        val contentPanel = createContentPanel()
        panel.add(contentPanel, BorderLayout.CENTER)
        
        return panel
    }
    
    private fun createHeaderPanel(): JPanel {
        val panel = JPanel(BorderLayout())
        panel.border = JBUI.Borders.emptyBottom(20)
        
        val titleLabel = JLabel("🎨 TokyoDark Theme")
        titleLabel.font = Font(Font.DIALOG, Font.BOLD, 24)
        titleLabel.foreground = JBColor(Color(122, 162, 247), Color(122, 162, 247))
        panel.add(titleLabel, BorderLayout.NORTH)
        
        val subtitleLabel = JLabel("A comprehensive dark theme for JetBrains IDEs")
        subtitleLabel.font = Font(Font.DIALOG, Font.PLAIN, 12)
        subtitleLabel.foreground = JBColor.GRAY
        panel.add(subtitleLabel, BorderLayout.SOUTH)
        
        return panel
    }
    
    private fun createContentPanel(): JPanel {
        val panel = JPanel(GridBagLayout())
        val gbc = GridBagConstraints()
        gbc.anchor = GridBagConstraints.NORTHWEST
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.weightx = 1.0
        gbc.insets = JBUI.insets(0, 0, 20, 0)
        
        var row = 0
        
        // IntelliJ Version Information
        gbc.gridy = row++
        panel.add(createVersionInfoPanel(), gbc)
        
        // Separator
        gbc.gridy = row++
        gbc.insets = JBUI.insets(10, 0, 20, 0)
        panel.add(JSeparator(), gbc)
        gbc.insets = JBUI.insets(0, 0, 20, 0)
        
        // Theme Availability Section
        gbc.gridy = row++
        panel.add(createThemeAvailabilityPanel(), gbc)
        
        // Separator
        gbc.gridy = row++
        gbc.insets = JBUI.insets(10, 0, 20, 0)
        panel.add(JSeparator(), gbc)
        gbc.insets = JBUI.insets(0, 0, 20, 0)
        
        // Theme Descriptions
        gbc.gridy = row++
        panel.add(createThemeDescriptionsPanel(), gbc)
        
        // Spacer to push everything to top
        gbc.gridy = row++
        gbc.weighty = 1.0
        gbc.fill = GridBagConstraints.BOTH
        panel.add(Box.createVerticalGlue(), gbc)
        
        return panel
    }
    
    private fun createVersionInfoPanel(): JPanel {
        val panel = JPanel(BorderLayout())
        
        val titleLabel = JLabel("💻 IntelliJ Version Information")
        titleLabel.font = Font(Font.DIALOG, Font.BOLD, 14)
        titleLabel.foreground = JBColor(Color(255, 158, 100), Color(255, 158, 100))
        panel.add(titleLabel, BorderLayout.NORTH)
        
        val infoPanel = JPanel()
        infoPanel.layout = BoxLayout(infoPanel, BoxLayout.Y_AXIS)
        infoPanel.border = JBUI.Borders.emptyLeft(20)
        
        try {
            val fullVersion = VersionUtils.getFullVersion()
            val rawBuildNumber = ApplicationInfo.getInstance().build.asString()
            val cleanBuildNumber = VersionUtils.getCurrentBuild()
            val islandsSupported = VersionUtils.isIslandsSupported()
            
            infoPanel.add(createInfoRow("IntelliJ Version:", fullVersion))
            infoPanel.add(Box.createVerticalStrut(8))
            infoPanel.add(createInfoRow("Build:", rawBuildNumber))
            infoPanel.add(Box.createVerticalStrut(4))
            infoPanel.add(createInfoRow("Clean Build:", cleanBuildNumber))
            infoPanel.add(Box.createVerticalStrut(8))
            
            val islandsStatus = if (islandsSupported) {
                "✅ Supported (2025.3+)"
            } else {
                "❌ Not Supported (requires 2025.3+)"
            }
            infoPanel.add(createInfoRow("Islands Theme:", islandsStatus))
            
            if (!islandsSupported) {
                val recommendationLabel = JLabel("💡 Upgrade to IntelliJ 2025.3 or later to use the Islands theme")
                recommendationLabel.font = Font(Font.DIALOG, Font.ITALIC, 11)
                recommendationLabel.foreground = JBColor(Color(224, 175, 104), Color(224, 175, 104))
                recommendationLabel.border = JBUI.Borders.emptyTop(10)
                infoPanel.add(recommendationLabel)
            }
            
        } catch (e: Exception) {
            val errorLabel = JLabel("Unable to detect IntelliJ version information")
            errorLabel.foreground = JBColor.RED
            infoPanel.add(errorLabel)
        }
        
        panel.add(infoPanel, BorderLayout.CENTER)
        return panel
    }
    
    private fun createThemeAvailabilityPanel(): JPanel {
        val panel = JPanel(BorderLayout())
        
        val titleLabel = JLabel("🎭 Available Themes")
        titleLabel.font = Font(Font.DIALOG, Font.BOLD, 14)
        titleLabel.foreground = JBColor(Color(255, 158, 100), Color(255, 158, 100))
        panel.add(titleLabel, BorderLayout.NORTH)
        
        val themesPanel = JPanel()
        themesPanel.layout = BoxLayout(themesPanel, BoxLayout.Y_AXIS)
        themesPanel.border = JBUI.Borders.emptyLeft(20)
        
        val availableThemes = ThemeCompatibilityChecker.getThemesForCurrentVersion()
        val islandsSupported = ThemeCompatibilityChecker.isIslandsThemeSupported()
        
        ThemeCompatibilityChecker.ALL_THEMES.forEach { theme ->
            val isAvailable = availableThemes.any { it.id == theme.id }
            val themeRow = createThemeRow(theme, isAvailable, islandsSupported)
            themesPanel.add(themeRow)
            themesPanel.add(Box.createVerticalStrut(8))
        }
        
        panel.add(themesPanel, BorderLayout.CENTER)
        return panel
    }
    
    private fun createThemeRow(
        theme: ThemeCompatibilityChecker.ThemeConfig,
        isAvailable: Boolean,
        islandsSupported: Boolean
    ): JPanel {
        val panel = JPanel(BorderLayout())
        
        val icon = if (isAvailable) "✅" else "❌"
        val nameLabel = JLabel("$icon ${theme.name}")
        nameLabel.font = Font(Font.DIALOG, Font.BOLD, 12)
        
        if (isAvailable) {
            nameLabel.foreground = JBColor(Color(158, 206, 106), Color(158, 206, 106))
        } else {
            nameLabel.foreground = JBColor.GRAY
        }
        
        panel.add(nameLabel, BorderLayout.NORTH)
        
        val detailsPanel = JPanel()
        detailsPanel.layout = BoxLayout(detailsPanel, BoxLayout.Y_AXIS)
        detailsPanel.border = JBUI.Borders.emptyLeft(25)
        
        val typeLabel = JLabel("Type: ${theme.variant.name}")
        typeLabel.font = Font(Font.DIALOG, Font.PLAIN, 11)
        detailsPanel.add(typeLabel)
        
        if (!isAvailable && theme.variant == ThemeCompatibilityChecker.ThemeVariant.ISLANDS) {
            val reasonLabel = JLabel("(Requires IntelliJ 2025.3+)")
            reasonLabel.font = Font(Font.DIALOG, Font.ITALIC, 10)
            reasonLabel.foreground = JBColor.GRAY
            detailsPanel.add(reasonLabel)
        }
        
        panel.add(detailsPanel, BorderLayout.CENTER)
        return panel
    }
    
    private fun createThemeDescriptionsPanel(): JPanel {
        val panel = JPanel(BorderLayout())
        
        val titleLabel = JLabel("📖 Theme Descriptions")
        titleLabel.font = Font(Font.DIALOG, Font.BOLD, 14)
        titleLabel.foreground = JBColor(Color(255, 158, 100), Color(255, 158, 100))
        panel.add(titleLabel, BorderLayout.NORTH)
        
        val descPanel = JPanel()
        descPanel.layout = BoxLayout(descPanel, BoxLayout.Y_AXIS)
        descPanel.border = JBUI.Borders.emptyLeft(20)
        
        val descriptions = listOf(
            Triple("TokyoDark", "The main theme", "Classic Tokyo Night color palette with balanced contrast"),
            Triple("TokyoDark Storm", "Enhanced variant", "Darker backgrounds for reduced eye strain"),
            Triple("TokyoDark Contrast", "Accessibility", "Higher contrast for improved readability"),
            Triple("Tokyo Dark Islands", "Modern UI", "Islands theme for IntelliJ 2025.3+ with rounded corners")
        )
        
        descriptions.forEach { (name, type, desc) ->
            val themeDesc = JPanel(BorderLayout())
            
            val nameTypeLabel = JLabel("• $name ($type)")
            nameTypeLabel.font = Font(Font.DIALOG, Font.BOLD, 11)
            nameTypeLabel.foreground = JBColor(Color(122, 162, 247), Color(122, 162, 247))
            themeDesc.add(nameTypeLabel, BorderLayout.NORTH)
            
            val descriptionLabel = JLabel("  $desc")
            descriptionLabel.font = Font(Font.DIALOG, Font.PLAIN, 11)
            descriptionLabel.foreground = JBColor.DARK_GRAY
            themeDesc.add(descriptionLabel, BorderLayout.CENTER)
            
            descPanel.add(themeDesc)
            descPanel.add(Box.createVerticalStrut(12))
        }
        
        panel.add(descPanel, BorderLayout.CENTER)
        return panel
    }
    
    private fun createInfoRow(label: String, value: String): JPanel {
        val panel = JPanel(BorderLayout())
        
        val labelComponent = JLabel(label)
        labelComponent.font = Font(Font.DIALOG, Font.BOLD, 12)
        labelComponent.foreground = JBColor.DARK_GRAY
        labelComponent.preferredSize = Dimension(140, labelComponent.preferredSize.height)
        panel.add(labelComponent, BorderLayout.WEST)
        
        val valueComponent = JLabel(value)
        valueComponent.font = Font(Font.DIALOG, Font.PLAIN, 12)
        valueComponent.foreground = JBColor(Color(201, 203, 255), Color(201, 203, 255))
        panel.add(valueComponent, BorderLayout.CENTER)
        
        return panel
    }
    
    override fun isModified(): Boolean = false
    
    @Throws(ConfigurationException::class)
    override fun apply() {
        // No settings to apply - this is an informational page
    }
    
    override fun reset() {
        // Nothing to reset
    }
    
    override fun disposeUIResources() {
        mainPanel = null
    }
}