# IntelliJ Theme Development Overview

## Purpose
This document provides a comprehensive overview of IntelliJ theme development, covering both traditional and Islands UI paradigms. It serves as the foundation for AI agents to understand the complete theme ecosystem.

## Theme Types

### Traditional Themes
- **Design**: Continuous flat surfaces with visible borders
- **Structure**: Hierarchical UI with connected components
- **Inheritance**: From `Darcula` (dark) or `IntelliJ Light` (light)
- **Versions**: Compatible with all IntelliJ Platform versions

### Islands Themes (2025.3+)
- **Design**: Component-based "islands" with visual separation
- **Structure**: Disconnected UI elements with rounded corners
- **Inheritance**: From `Islands Dark` or `Islands Light`
- **Requirements**: Requires IntelliJ Platform 2025.3 or later

## File Structure

```
theme-project/
├── src/main/resources/
│   ├── themes/
│   │   ├── ThemeName.theme.json      # UI theme description
│   │   └── ThemeNameIslands.theme.json # Islands variant
│   ├── colorSchemes/
│   │   ├── ThemeName.xml             # Editor color scheme
│   │   └── ThemeNameIslands.xml      # Islands editor scheme
│   └── META-INF/
│       └── plugin.xml                # Plugin configuration
└── build.gradle.kts                  # Build configuration
```

## Plugin Architecture

### Theme Registration
```xml
<!-- plugin.xml -->
<idea-plugin>
    <id>com.company.theme</id>
    <name>Theme Name</name>
    <depends>com.intellij.modules.platform</depends>
    <extensions defaultExtensionNs="com.intellij">
        <themeProvider id="com.company.theme" path="/themes/ThemeName.theme.json"/>
        <themeProvider id="com.company.theme-islands" path="/themes/ThemeNameIslands.theme.json"/>
    </extensions>
</idea-plugin>
```

### Theme Provider Properties
- **id**: Unique theme identifier
- **path**: Relative path to theme description file
- **Automatic registration**: Plugin system handles theme loading

## Development Environment

### Required Tools
1. **IntelliJ IDEA** with Plugin DevKit
2. **IntelliJ Platform SDK** (2025.3+ for Islands support)
3. **Gradle** for build management
4. **Java** (JDK 17+ recommended)

### Setup Process
1. Create new plugin project using IntelliJ IDEA
2. Configure Plugin DevKit dependencies
3. Create theme resource directories
4. Register themes in plugin.xml
5. Test with Run/Debug configuration

## Theme Components

### UI Theme (.theme.json)
Controls all non-editor UI elements:
- Buttons, panels, dialogs, popups
- Tool windows, status bar, menus
- Colors, borders, spacing, arcs
- Icon customizations

### Editor Scheme (.xml)
Controls editor-specific appearance:
- Syntax highlighting for all languages
- Text colors, backgrounds, effects
- Console colors and error highlighting
- Line numbers, gutters, separators

## Inheritance System

### Traditional Theme Inheritance
```json
{
  "parentTheme": "Darcula",
  "ui": {
    "Button.background": "#customColor" // Overrides parent
  }
}
```

### Islands Theme Inheritance
```json
{
  "parentTheme": "Islands Dark",
  "Islands": 1,
  "ui": {
    "Island.borderColor": "#customColor"
  }
}
```

## Variant Creation Strategy

### Base Theme Approach
1. **Create primary theme** (e.g., TokyoDark)
2. **Generate variants** through systematic color transformation:
   - **Storm**: Darker backgrounds, reduced contrast
   - **Contrast**: Enhanced contrast ratios
   - **Islands**: Component separation with transparency

### TokyoDark Reference Implementation
- **Primary**: `TokyoDark.theme.json` → `TokyoDark.xml`
- **Storm**: `TokyoDarkStorm.theme.json` → `TokyoDarkStorm.xml`
- **Contrast**: `TokyoDarkContrast.theme.json` → `TokyoDarkContrast.xml`
- **Islands**: `TokyoDarkIslands.theme.json` → `TokyoDarkIslands.xml`

## Distribution

### JetBrains Marketplace
- **Primary distribution channel**
- **Version compatibility** requirements
- **Quality validation** process

### Manual Installation
- **Plugin JAR** installation
- **Theme file** import (limited functionality)

## Version Compatibility

| Platform Version | Theme Support | Islands Support |
|------------------|----------------|-----------------|
| 2024.3 and below | Traditional | No |
| 2025.0 - 2025.2 | Traditional | Limited |
| 2025.3+ | Traditional | Full |

## Key Differences: Traditional vs Islands

| Aspect | Traditional | Islands |
|--------|-------------|----------|
| **Borders** | Visible between components | Transparent, separation through spacing |
| **Corners** | Sharp (arc: 5-7) | Rounded (arc: 10-20) |
| **Backgrounds** | Continuous surfaces | Separate "islands" |
| **Hierarchy** | Border-based | Spacing and color-based |
| **Visual Focus** | Border emphasis | Component separation |

## Best Practices

### Design Principles
- **Consistency**: Maintain visual harmony across components
- **Accessibility**: Meet WCAG 4.5:1 contrast ratios
- **Performance**: Minimal resource usage
- **Compatibility**: Support multiple IDE versions

### Development Workflow
1. **Start with inheritance** from base themes
2. **Define color palette** using semantic naming
3. **Implement UI components** systematically
4. **Create editor scheme** with comprehensive language support
5. **Test thoroughly** across different IDE products
6. **Validate accessibility** requirements
7. **Document theme variants** clearly

## Common Pitfalls

### Technical Issues
- **Missing inheritance**: Leads to incomplete themes
- **Color conflicts**: Inconsistent naming causes overrides
- **Border management**: Islands themes require transparent borders
- **Version compatibility**: Islands themes only work on 2025.3+

### Design Issues
- **Poor contrast**: Fails accessibility requirements
- **Inconsistent spacing**: Visual disharmony
- **Over-customization**: Breaks platform conventions
- **Missing variants**: Limited user choice

## Related Documentation
- [Color System & Design Tokens](../02-color-system/README.md)
- [File Architecture](../03-file-architecture/README.md)
- [Islands Implementation](../06-islands-themes/README.md)
- [AI Development Workflow](../04-ai-workflow/README.md)