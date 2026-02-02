package com.junkfactory.tokyodark

import com.intellij.openapi.application.ApplicationInfo

/**
 * Utility class for comparing IntelliJ build numbers.
 * Build numbers follow the format: YYYY.R.BUILD.PATCH (e.g., "253.3177.56")
 * 
 * Islands theme support starts from build 253.0 (IntelliJ 2025.3)
 */
object VersionUtils {
    
    /**
     * Minimum build number for Islands theme support
     */
    const val ISLANDS_MINIMUM_BUILD = "253.0"
    
    /**
     * Gets the current IntelliJ build number
     * @return Build number string (e.g., "253.3177.56")
     */
    fun getCurrentBuild(): String {
        val rawBuild = ApplicationInfo.getInstance().build.asString()
        // Extract just the numeric part from build strings like "IU-253.30387.90"
        return extractBuildNumber(rawBuild)
    }
    
    /**
     * Extracts the numeric build number from a build string.
     * Handles formats like "253.3177.56", "IU-253.30387.90", "IC-242.1234.5"
     * @param buildString Raw build string from ApplicationInfo
     * @return Clean build number (e.g., "253.30387.90")
     */
    fun extractBuildNumber(buildString: String): String {
        // Remove any product code prefix (e.g., "IU-", "IC-", "RM-", etc.)
        val productCodePattern = "^[A-Z]+-".toRegex()
        return buildString.replace(productCodePattern, "")
    }
    
    /**
     * Compares two version strings in dot notation
     * @param version1 First version string (e.g., "253.3177.56")
     * @param version2 Second version string (e.g., "253.0")
     * @return Positive if version1 > version2, negative if version1 < version2, 0 if equal
     */
    fun compareVersions(version1: String, version2: String): Int {
        val parts1 = version1.split(".").map { it.toIntOrNull() ?: 0 }
        val parts2 = version2.split(".").map { it.toIntOrNull() ?: 0 }
        
        val maxLength = maxOf(parts1.size, parts2.size)
        
        for (i in 0 until maxLength) {
            val num1 = parts1.getOrElse(i) { 0 }
            val num2 = parts2.getOrElse(i) { 0 }
            
            if (num1 != num2) {
                return num1 - num2
            }
        }
        
        return 0
    }
    
    /**
     * Checks if current build supports Islands themes
     * @return true if Islands themes are supported (build >= 253.0)
     */
    fun isIslandsSupported(): Boolean {
        val currentBuild = getCurrentBuild()
        return compareVersions(currentBuild, ISLANDS_MINIMUM_BUILD) >= 0
    }
    
    /**
     * Checks if current build is at least the specified version
     * @param minimumBuild Minimum build required (e.g., "253.0")
     * @return true if current build >= minimumBuild
     */
    fun isAtLeast(minimumBuild: String): Boolean {
        val currentBuild = getCurrentBuild()
        return compareVersions(currentBuild, minimumBuild) >= 0
    }
    
    /**
     * Gets current IntelliJ version for display
     * @return Full version string
     */
    fun getFullVersion(): String {
        return ApplicationInfo.getInstance().fullVersion
    }
}