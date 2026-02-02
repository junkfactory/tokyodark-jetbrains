# Islands UI Paradigm Implementation

## Purpose
This document provides comprehensive guidance for implementing Islands themes in IntelliJ, covering the new UI paradigm introduced in IntelliJ Platform 2025.3+. It explains the design philosophy, technical requirements, and implementation strategies.

## Islands Design Philosophy

### Core Concepts
- **Component Separation**: UI elements are visually distinct "islands"
- **Reduced Visual Clutter**: Eliminates continuous borders and chrome
- **Focus-Oriented**: Enhances code focus through visual hierarchy
- **Modern Aesthetics**: Aligns with contemporary design patterns

### Visual Characteristics
- **Rounded Corners**: Soft, rounded island boundaries (10-20px arc)
- **No Continuous Borders**: Sidebars and panels have transparent borders
- **Hierarchical Spacing**: Islands separated by defined gaps
- **Background Depth**: Layered backgrounds for visual hierarchy

## Technical Requirements

### Platform Compatibility
- **Minimum Version**: IntelliJ Platform 2025.3+
- **Plugin SDK**: 2025.3+ required for Islands support
- **Theme Registration**: Must inherit from Islands base themes
- **Backward Compatibility**: Not supported on older versions

### Required Metadata
```json
{
  "name": "My Islands Theme",
  "dark": true,
  "parentTheme": "Islands Dark",  // Required for Islands support
  "Islands": 1,                   // Explicit Islands flag
  "ui": {
    "Island.arc": 20,              // Rounded corner radius
    "Island.borderWidth": 5,        // Gap between islands
    "Island.borderColor": "#2c2e40"  // Island border color
  }
}
```

## Islands Color Keys

### Core Islands Properties
| Property | Type | Required | Description |
|----------|------|----------|-------------|
| `Islands` | number | ✅ | Islands flag (must be 1) |
| `Island.arc` | number | ✅ | Corner radius (2x actual radius) |
| `Island.borderWidth` | number | ✅ | Distance between islands |
| `Island.borderWidth.compact` | number | ❌ | Compact mode distance |
| `Island.borderColor` | color | ✅ | Island border color |
| `Island.inactiveAlpha` | number | ❌ | Inactive window transparency |

### Border Management Properties
```json
{
  "ui": {
    "StatusBar.borderColor": "#00000000",        // Transparent
    "ToolWindow.Stripe.borderColor": "#00000000", // Transparent
    "MainToolbar.borderColor": "#00000000",      // Transparent
    "Window.borderColor": "#00000000"            // Transparent
  }
}
```

## Background Color Hierarchy

### Dark Theme Requirements
- **MainWindow.background**: Must be LIGHTER than tool windows
- **ToolWindow.background**: Must be DARKER than main window
- **Editor.background**: Typically matches tool windows
- **Minimum Contrast**: 1.20:1 ratio between elements

### Example Color Hierarchy
```json
{
  "ui": {
    "*": {
      "background": "#1a1b26"           // Main window
    },
    "MainWindow.background": "#21222e",     // Lighter than main
    "ToolWindow.background": "#2c2e40",     // Darker than main
    "Editor.background": "#2c2e40",         // Match tool windows
    "StatusBar.background": "#2c2e40"       // Match tool windows
  }
}
```

### Light Theme Requirements
- **MainWindow.background**: Must be DARKER than tool windows
- **ToolWindow.background**: Must be LIGHTER than main window
- **Same contrast principles** apply to light themes

## Component Implementation

### Tool Windows
```json
{
  "ui": {
    "ToolWindow": {
      "background": "#2c2e40",
      "Header": {
        "background": "#2c2e40",
        "inactiveBackground": "#1f2335",
        "borderColor": "#00000000"  // Transparent
      },
      "Button": {
        "hoverBackground": "#364a59"
      }
    }
  }
}
```

### Editor Tabs
```json
{
  "ui": {
    "EditorTabs": {
      "background": "#1a1b26",
      "underlinedTabBackground": "#2c2e40",
      "underlinedBorderColor": "#e0af68",
      "inactiveUnderlinedTabBackground": "#1a1b26",
      "underlineHeight.compact": 1
    }
  }
}
```

### Buttons and Controls
```json
{
  "ui": {
    "Button": {
      "background": "#2c2e40",
      "foreground": "#a9b1d6",
      "arc": 8,                        // Rounded corners
      "hoverBackground": "#364a59",
      "pressedBackground": "#405164"
    },
    "ComboBox": {
      "background": "#2c2e40",
      "ArrowButton": {
        "background": "#2c2e40"
      }
    }
  }
}
```

## Arc Radius Guidelines

### Recommended Values
| Component | Arc Range | Recommended |
|----------|------------|-------------|
| Islands | 16-24 | 20 (10px actual) |
| Buttons | 6-12 | 8 (4px actual) |
| ComboBox | 6-12 | 8 (4px actual) |
| Tabs | 16-24 | 20 (10px actual) |
| Dialogs | 12-20 | 16 (8px actual) |

### Arc Calculation
```json
{
  "arc": 20,        // 10px actual radius
  "arc": 8,         // 4px actual radius
  "arc": 6,         // 3px actual radius
  "arc": 4          // 2px actual radius
}
```

## Border Management Strategy

### Transparent Borders (Recommended)
```json
{
  "ui": {
    "Component": {
      "borderColor": "#00000000"  // Fully transparent
    },
    "Borders": {
      "color": "#00000000",       // Default transparent
      "ContrastBorderColor": "#2c2e40"  // Subtle contrast when needed
    }
  }
}
```

### Visual Separation Alternatives
- **Background differences**: Use color hierarchy
- **Spacing**: Leverage `Island.borderWidth`
- **Shadows**: Apply subtle drop shadows
- **Focus states**: Use focus colors for definition

## Variant Creation

### Islands Dark Theme
```json
{
  "name": "My Islands Dark",
  "dark": true,
  "parentTheme": "Islands Dark",
  "Islands": 1,
  "ui": {
    "Island.arc": 20,
    "Island.borderWidth": 5,
    "Island.borderColor": "#2c2e40",
    "*": {
      "background": "#1a1b26",
      "foreground": "#a9b1d6"
    },
    "MainWindow.background": "#21222e",
    "ToolWindow.background": "#2c2e40"
  }
}
```

### Islands Light Theme
```json
{
  "name": "My Islands Light",
  "dark": false,
  "parentTheme": "Islands Light",
  "Islands": 1,
  "ui": {
    "Island.arc": 20,
    "Island.borderWidth": 5,
    "Island.borderColor": "#e1e5e8",
    "*": {
      "background": "#fafafa",
      "foreground": "#2b2b2b"
    },
    "MainWindow.background": "#f0f0f0",
    "ToolWindow.background": "#ffffff"
  }
}
```

## Migration from Traditional Themes

### Step-by-Step Migration
1. **Update parentTheme**:
   ```json
   "parentTheme": "Islands Dark"  // Instead of "Darcula"
   ```

2. **Add Islands metadata**:
   ```json
   "Islands": 1,
   "Island.arc": 20,
   "Island.borderWidth": 5
   ```

3. **Make borders transparent**:
   ```json
   "StatusBar.borderColor": "#00000000",
   "ToolWindow.Stripe.borderColor": "#00000000"
   ```

4. **Adjust background hierarchy**:
   ```json
   "MainWindow.background": "#21222e",  // Lighter than tool windows
   "ToolWindow.background": "#2c2e40"   // Darker than main
   ```

5. **Update corners and spacing**:
   ```json
   "Button.arc": 8,
   "ComboBox.arc": 8
   ```

### Common Migration Issues
- **Missing Islands flag**: Theme won't render as Islands
- **Visible borders**: Creates visual clutter
- **Wrong background hierarchy**: Poor visual separation
- **Incorrect arc values**: Inconsistent rounded corners

## Testing and Validation

### Visual Testing Checklist
- [ ] Islands appear visually separated
- [ ] No visible borders between sidebars
- [ ] Rounded corners consistent
- [ ] Background hierarchy visible
- [ ] Focus states clear
- [ ] Hover states functional

### Technical Validation
```json
{
  "validation": {
    "hasIslandsFlag": true,
    "hasParentTheme": true,
    "bordersTransparent": true,
    "backgroundHierarchyValid": true,
    "arcValuesReasonable": true
  }
}
```

### Platform Testing
- **Windows**: Check rendering at different DPIs
- **macOS**: Verify native window chrome integration
- **Linux**: Test with various desktop environments
- **Cross-IDE**: Validate with different JetBrains products

## Best Practices

### Design Guidelines
1. **Maintain visual balance**: Islands should feel grounded
2. **Consistent rounding**: Use arc values consistently
3. **Adequate spacing**: Islands shouldn't touch
4. **Hierarchical colors**: Backgrounds should create depth
5. **Subtle borders**: Only use when necessary for definition

### Performance Considerations
1. **Minimize transparency**: Overuse can impact performance
2. **Optimize arc rendering**: Simple radii perform better
3. **Limit color variations**: Fewer colors = better performance
4. **Test with large projects**: Ensure scaling works well

### Accessibility
1. **Maintain contrast**: Islands shouldn't reduce readability
2. **Focus indicators**: Clear focus states essential
3. **Color independence**: Don't rely solely on color for information
4. **High contrast support**: Consider variants for accessibility

## Debugging Common Issues

### Islands Not Rendering
```json
// Problem
{
  "parentTheme": "Darcula",  // Wrong parent
  "dark": true
}

// Solution
{
  "parentTheme": "Islands Dark",  // Correct parent
  "Islands": 1,                 // Required flag
  "dark": true
}
```

### Visible Borders
```json
// Problem
{
  "ui": {
    "StatusBar.borderColor": "#2c2e40"  // Visible border
  }
}

// Solution
{
  "ui": {
    "StatusBar.borderColor": "#00000000"  // Transparent
  }
}
```

### Poor Visual Separation
```json
// Problem
{
  "ui": {
    "*": {
      "background": "#2c2e40"           // Same everywhere
    }
  }
}

// Solution
{
  "ui": {
    "*": {
      "background": "#1a1b26"           // Main window
    },
    "MainWindow.background": "#21222e",     // Lighter
    "ToolWindow.background": "#2c2e40"     // Darker
  }
}
```

## Advanced Features

### Platform-Specific Adjustments
```json
{
  "ui": {
    "Island.arc": {
      "os.default": 20,
      "os.mac": 18,          // Slightly smaller on macOS
      "os.windows": 22       // Slightly larger on Windows
    }
  }
}
```

### Dynamic Island Configuration
```json
{
  "ui": {
    "Island.borderWidth": 5,
    "Island.borderWidth.compact": 3,  // Smaller in compact mode
    "Island.inactiveAlpha": 0.44       // Dim inactive windows
  }
}
```

## Reference Implementation

### Complete Islands Dark Theme
```json
{
  "name": "Reference Islands Dark",
  "dark": true,
  "parentTheme": "Islands Dark",
  "author": "AI Agent",
  "Islands": 1,
  "ui": {
    "Island.arc": 20,
    "Island.borderWidth": 5,
    "Island.borderColor": "#2c2e40",
    "*": {
      "background": "#1a1b26",
      "foreground": "#a9b1d6",
      "selectionBackground": "#414868"
    },
    "MainWindow.background": "#21222e",
    "ToolWindow.background": "#2c2e40",
    "Editor.background": "#2c2e40",
    "StatusBar.borderColor": "#00000000",
    "ToolWindow.Stripe.borderColor": "#00000000",
    "MainToolbar.borderColor": "#00000000",
    "Button": {
      "background": "#2c2e40",
      "arc": 8,
      "hoverBackground": "#364a59"
    },
    "EditorTabs": {
      "underlinedTabBackground": "#2c2e40",
      "underlinedBorderColor": "#e0af68"
    }
  }
}
```

## Related Documentation
- [Theme Development Overview](../01-foundation/theme-development-overview.md)
- [Theme JSON Structure](../03-file-architecture/theme-json-structure.md)
- [Traditional to Islands Migration](../06-islands-themes/traditional-to-islands-migration.md)
- [Theme Generation Algorithm](../04-ai-workflow/theme-generation-algorithm.md)