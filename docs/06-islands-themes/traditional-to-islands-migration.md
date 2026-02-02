# Traditional to Islands Migration Guide

## Purpose
This document provides a comprehensive guide for migrating existing traditional IntelliJ themes to the new Islands UI paradigm introduced in IntelliJ Platform 2025.3+. It covers step-by-step migration, technical requirements, and best practices.

## Migration Overview

### Why Migrate to Islands?
- **Future-Proof**: Islands is the default UI for new IntelliJ versions
- **Modern Design**: Aligns with contemporary UI patterns
- **Better User Experience**: Reduced visual clutter, improved focus
- **Market Demand**: Users increasingly prefer Islands themes

### Migration Challenges
- **Paradigm Shift**: Requires different design thinking
- **Border Management**: Transparent borders vs visible borders
- **Color Hierarchy**: New background relationships required
- **Version Compatibility**: Only supports 2025.3+

## Migration Decision Framework

### When to Migrate
✅ **Migrate if:**
- Theme is actively maintained
- User base requests modern UI
- Plan to support latest IntelliJ features
- Want to future-proof theme

❌ **Keep Traditional if:**
- Targeting older IntelliJ versions (pre-2025.3)
- Minimal maintenance resources
- User base prefers traditional UI
- Theme complexity makes migration high-risk

### Migration Strategy Options

#### 1. Parallel Development
- Keep traditional theme for legacy support
- Develop Islands variant separately
- Maintain both versions
- **Pros**: Maximum compatibility, gradual transition
- **Cons**: Double maintenance, resource intensive

#### 2. Complete Migration
- Replace traditional theme with Islands variant
- Update plugin metadata
- Drop legacy support
- **Pros**: Single codebase, focused maintenance
- **Cons**: Breaks compatibility, user retraining

#### 3. Hybrid Approach
- Extend traditional theme with Islands support
- Use version detection for conditional styling
- Gradually deprecate traditional features
- **Pros**: Smooth transition, single codebase
- **Cons**: Complex conditional logic, testing overhead

## Technical Migration Steps

### Step 1: Prepare Development Environment

#### Update IntelliJ Platform SDK
```kotlin
// build.gradle.kts
intellij {
    version.set("2025.3")
    type.set("IU")  // Ultimate Edition for full Islands support
}
```

#### Update Plugin Metadata
```xml
<!-- plugin.xml -->
<idea-plugin>
    <depends>com.intellij.modules.platform</depends>
    <!-- Add minimum since-build for Islands support -->
    <idea-version since-build="253.3177.56"/>
</idea-plugin>
```

### Step 2: Update Theme Structure

#### Change Parent Theme
```json
// Traditional
{
  "parentTheme": "Darcula",
  "dark": true
}

// Islands
{
  "parentTheme": "Islands Dark",
  "dark": true,
  "Islands": 1
}
```

#### Add Islands Metadata
```json
{
  "name": "My Theme Islands",
  "parentTheme": "Islands Dark",
  "dark": true,
  "Islands": 1,
  "ui": {
    "Island.arc": 20,
    "Island.borderWidth": 5,
    "Island.borderColor": "#2c2e40"
  }
}
```

### Step 3: Border Management Migration

#### Remove Visible Borders
```json
// Traditional (remove these)
{
  "ui": {
    "StatusBar.borderColor": "#2c2e40",
    "ToolWindow.Stripe.borderColor": "#2c2e40",
    "MainToolbar.borderColor": "#2c2e40"
  }
}

// Islands (add these)
{
  "ui": {
    "StatusBar.borderColor": "#00000000",
    "ToolWindow.Stripe.borderColor": "#00000000",
    "MainToolbar.borderColor": "#00000000"
  }
}
```

#### Border Migration Checklist
- [ ] StatusBar borderColor set to transparent
- [ ] ToolWindow.Stripe borderColor set to transparent
- [ ] MainToolbar borderColor set to transparent
- [ ] Window borderColor set to transparent
- [ ] Remove all continuous borders between components

### Step 4: Background Hierarchy Adjustment

#### Dark Theme Background Hierarchy
```json
{
  "ui": {
    "*": {
      "background": "#1a1b26"           // Main window background
    },
    "MainWindow.background": "#21222e",     // Lighter than main
    "ToolWindow.background": "#2c2e40",     // Darker than main
    "Editor.background": "#2c2e40",         // Match tool windows
    "StatusBar.background": "#2c2e40"       // Match tool windows
  }
}
```

#### Light Theme Background Hierarchy
```json
{
  "ui": {
    "*": {
      "background": "#fafafa"           // Main window background
    },
    "MainWindow.background": "#f0f0f0",     // Darker than main
    "ToolWindow.background": "#ffffff",     // Lighter than main
    "Editor.background": "#ffffff",         // Match tool windows
    "StatusBar.background": "#ffffff"       // Match tool windows
  }
}
```

### Step 5: Component Styling Updates

#### Update Button Styling
```json
// Traditional
{
  "Button": {
    "arc": 6,
    "borderColor": "#2c2e40"
  }
}

// Islands
{
  "Button": {
    "arc": 8,
    "background": "#2c2e40",
    "borderColor": "#00000000"  // Transparent
  }
}
```

#### Update Tool Window Styling
```json
// Islands-specific
{
  "ToolWindow": {
    "background": "#2c2e40",
    "Header": {
      "background": "#2c2e40",
      "inactiveBackground": "#1f2335",
      "borderColor": "#00000000"  // Important: transparent
    }
  }
}
```

#### Update Editor Tabs
```json
// Islands tabs
{
  "EditorTabs": {
    "background": "#1a1b26",
    "underlinedTabBackground": "#2c2e40",
    "underlinedBorderColor": "#e0af68",
    "inactiveUnderlinedTabBackground": "#1a1b26",
    "underlineHeight.compact": 1
  }
}
```

### Step 6: Arc Radius Standardization

#### Standard Arc Values
```json
{
  "ui": {
    "Island.arc": 20,              // Main islands (10px actual)
    "Button.arc": 8,               // Buttons (4px actual)
    "ComboBox.arc": 8,            // Combo boxes (4px actual)
    "TextField.arc": 4,           // Text fields (2px actual)
    "Popup.arc": 12                // Popups (6px actual)
  }
}
```

#### Arc Migration Table
| Component | Traditional Arc | Islands Arc | Change |
|----------|-----------------|-------------|---------|
| Buttons | 6 | 8 | +33% |
| Combo Boxes | 6 | 8 | +33% |
| Text Fields | 0 | 4 | +4px |
| Popups | 6 | 12 | +100% |
| Main Islands | N/A | 20 | New |

### Step 7: Update Plugin Registration

#### Add Islands Theme Provider
```xml
<!-- plugin.xml -->
<extensions defaultExtensionNs="com.intellij">
    <!-- Keep traditional theme -->
    <themeProvider id="com.company.theme" path="/themes/MyTheme.theme.json"/>
    
    <!-- Add Islands variant -->
    <themeProvider id="com.company.theme-islands" path="/themes/MyThemeIslands.theme.json"/>
</extensions>
```

#### Version-Specific Registration
```xml
<!-- Conditional registration for version support -->
<idea-plugin>
    <!-- Traditional theme for older versions -->
    <depends config="2025.2">com.intellij.modules.platform</depends>
    
    <!-- Islands theme for newer versions -->
    <depends config="2025.3+">com.intellij.modules.platform</depends>
    
    <extensions defaultExtensionNs="com.intellij">
        <themeProvider id="com.company.theme" path="/themes/MyTheme.theme.json"/>
        <themeProvider id="com.company.theme-islands" path="/themes/MyThemeIslands.theme.json" 
                       config="2025.3+"/>
    </extensions>
</idea-plugin>
```

## Migration Script

### Automated Migration Script
```javascript
function migrateTraditionalToIslands(traditionalTheme) {
  const islandsTheme = JSON.parse(JSON.stringify(traditionalTheme));
  
  // Step 1: Update metadata
  islandsTheme.parentTheme = traditionalTheme.dark ? "Islands Dark" : "Islands Light";
  islandsTheme.Islands = 1;
  
  // Step 2: Add Islands properties
  if (!islandsTheme.ui) islandsTheme.ui = {};
  islandsTheme.ui.Island = {
    arc: 20,
    borderWidth: 5,
    borderColor: islandsTheme.colors?.hoverBackground || "#2c2e40"
  };
  
  // Step 3: Make borders transparent
  makeBordersTransparent(islandsTheme);
  
  // Step 4: Adjust background hierarchy
  adjustBackgroundHierarchy(islandsTheme);
  
  // Step 5: Update component styling
  updateComponentStyling(islandsTheme);
  
  // Step 6: Update arc values
  updateArcValues(islandsTheme);
  
  return islandsTheme;
}

function makeBordersTransparent(theme) {
  const bordersToTransparent = [
    "StatusBar.borderColor",
    "ToolWindow.Stripe.borderColor", 
    "MainToolbar.borderColor",
    "Window.borderColor"
  ];
  
  for (const border of bordersToTransparent) {
    if (theme.ui && hasProperty(theme.ui, border)) {
      setProperty(theme.ui, border, "#00000000");
    }
  }
}
```

## Testing Migration

### Visual Testing Checklist
- [ ] Islands appear visually separated
- [ ] No visible borders between sidebars
- [ ] Rounded corners consistent (10px for islands)
- [ ] Background hierarchy creates depth
- [ ] Focus states remain clear
- [ ] Hover states work correctly
- [ ] Selection highlights visible

### Functional Testing Checklist
- [ ] Theme loads successfully in 2025.3+
- [ ] All UI components styled correctly
- [ ] Editor highlighting works
- [ ] Dialogs and popups render properly
- [ ] Tool windows function correctly
- [ ] Status bar displays properly

### Compatibility Testing Checklist
- [ ] Works on Windows (different DPIs)
- [ ] Works on macOS (native integration)
- [ ] Works on Linux (various DEs)
- [ ] Compatible with different JetBrains IDEs
- [ ] Performance acceptable
- [ ] Memory usage reasonable

## Common Migration Issues and Solutions

### Issue 1: Theme Not Loading
**Problem**: Islands theme not appearing in settings
**Cause**: Missing Islands metadata or incorrect parent theme
**Solution**:
```json
{
  "parentTheme": "Islands Dark",  // Must be Islands theme
  "Islands": 1                   // Must be present
}
```

### Issue 2: Visible Borders Still Present
**Problem**: Borders between components still visible
**Cause**: Border colors not set to transparent
**Solution**:
```json
{
  "ui": {
    "StatusBar.borderColor": "#00000000",
    "ToolWindow.Stripe.borderColor": "#00000000"
  }
}
```

### Issue 3: Poor Visual Separation
**Problem**: Islands don't appear separated
**Cause**: Background hierarchy incorrect
**Solution**:
```json
{
  "ui": {
    "MainWindow.background": "#21222e",  // Lighter
    "ToolWindow.background": "#2c2e40"   // Darker
  }
}
```

### Issue 4: Inconsistent Rounded Corners
**Problem**: Some components still have sharp corners
**Cause**: Arc values not updated consistently
**Solution**:
```json
{
  "ui": {
    "Button.arc": 8,
    "ComboBox.arc": 8,
    "Island.arc": 20
  }
}
```

## Best Practices for Migration

### Design Principles
1. **Maintain Visual Identity**: Keep theme's core visual character
2. **Respect Islands Philosophy**: Embrace component separation
3. **Test Thoroughly**: Validate across platforms and IDEs
4. **Document Changes**: Track migration decisions
5. **Gather User Feedback**: Collect impressions during transition

### Technical Best Practices
1. **Use Transparent Borders**: Eliminate continuous borders
2. **Implement Proper Hierarchy**: Differentiate backgrounds
3. **Standardize Arc Values**: Use consistent rounding
4. **Preserve Color Palette**: Maintain theme color identity
5. **Test Performance**: Ensure no regression in performance

### Migration Process Best Practices
1. **Start with Copy**: Duplicate traditional theme first
2. **Incremental Changes**: Apply changes step by step
3. **Test Each Step**: Validate after each major change
4. **Maintain Backward Compatibility**: Support older versions if needed
5. **Document Everything**: Keep detailed migration notes

## Post-Migration Optimization

### Performance Optimization
```json
{
  "optimizations": {
    "reduceColorCount": "Minimize unique colors",
    "simplifyComponents": "Remove unnecessary overrides",
    "optimizeImages": "Use SVG instead of raster images"
  }
}
```

### User Experience Enhancements
```json
{
  "enhancements": {
    "improveContrast": "Enhance text readability",
    "addTransitions": "Smooth hover animations",
    "customizeFocus": "Better focus indicators",
    "optimizeSpacing": "Comfortable component spacing"
  }
}
```

## Rollback Strategy

### Rollback Triggers
- User feedback strongly negative
- Critical functionality broken
- Performance regression significant
- Compatibility issues widespread

### Rollback Process
1. **Revert traditional theme**: Restore original theme files
2. **Update plugin metadata**: Remove Islands registration
3. **Communicate to users**: Explain rollback reasons
4. **Plan re-migration**: Address issues for future attempt
5. **Maintain version control**: Keep Islands branch for future work

## Maintenance Considerations

### Dual Maintenance Mode
- Separate development branches for traditional and Islands
- Shared color tokens where possible
- Automated testing for both variants
- Clear documentation of differences

### Migration Timeline
- **Phase 1** (0-2 months): Parallel development
- **Phase 2** (2-4 months): Beta testing
- **Phase 3** (4-6 months): Full migration
- **Phase 4** (6+ months): Traditional deprecation

## Related Documentation
- [Islands UI Paradigm](../06-islands-themes/islands-ui-paradigm.md)
- [Islands Implementation](../06-islands-themes/islands-implementation.md)
- [Theme Generation Algorithm](../04-ai-workflow/theme-generation-algorithm.md)
- [Validation Checkpoints](../05-quality-assurance/validation-checkpoints.md)