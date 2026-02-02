# Theme JSON Structure (.theme.json)

## Purpose
This document provides a comprehensive breakdown of the IntelliJ theme JSON structure, explaining all components, properties, and best practices for AI agents creating theme files.

## File Overview

The `.theme.json` file controls all non-editor UI elements in IntelliJ themes. It defines colors, component styling, and visual properties for the entire IDE interface.

## Basic Structure

### Required Properties
```json
{
  "name": "Theme Name",
  "dark": true,
  "author": "Author Name <email@example.com>",
  "editorScheme": "/path/to/editor-scheme.xml"
}
```

### Optional Properties
```json
{
  "parentTheme": "Darcula",           // Inheritance from parent theme
  "colors": { ... },                  // Named color definitions
  "ui": { ... },                     // UI component styling
  "icons": { ... },                  // Icon customizations
  "background": { ... }               // Background images
}
```

## Complete Property Reference

### Theme Metadata

| Property | Type | Required | Description |
|----------|------|----------|-------------|
| `name` | string | ✅ | Display name in theme settings |
| `dark` | boolean | ✅ | Theme type (true=dark, false=light) |
| `author` | string | ✅ | Theme author information |
| `editorScheme` | string | ✅ | Path to XML editor scheme file |
| `parentTheme` | string | ❌ | Parent theme for inheritance |
| `nameKey` | string | ❌ | I18n key for theme name |

### Colors Section

Defines reusable color tokens referenced throughout the UI section.

```json
{
  "colors": {
    "primaryColor": "#7aa2f7",
    "backgroundColor": "#1a1b26",
    "foregroundColor": "#c0caf5",
    "hoverState": "#232433",
    "selectionColor": "#304285"
  }
}
```

#### Color Naming Conventions
- **Semantic names**: `primaryColor`, not `blue1`
- **CamelCase**: All names use camelCase
- **Purpose-specific**: `hoverBackground`, `errorForeground`
- **Hierarchical**: `bg`, `bgHi`, `bgHover`

### UI Section

Controls all UI component styling. Uses hierarchical structure for component organization.

#### Wildcard Section (`*`)
Defines default values for all components unless overridden:

```json
{
  "ui": {
    "*": {
      "background": "bg",
      "foreground": "fg",
      "selectionBackground": "selectionColor",
      "selectionForeground": "fg",
      "borderColor": "borderColor",
      "focusColor": "focusColor",
      "disabledForeground": "disabledColor",
      "disabledBackground": "disabledBg"
    }
  }
}
```

#### Wildcard Properties

| Property | Purpose | Example |
|----------|---------|---------|
| `background` | Default background | `"background": "bg"` |
| `foreground` | Default text color | `"foreground": "fg"` |
| `selectionBackground` | Selection highlight | `"selectionBackground": "#304285"` |
| `selectionForeground` | Selected text | `"selectionForeground": "#ffffff"` |
| `borderColor` | Component borders | `"borderColor": "#2c2e40"` |
| `focusColor` | Focus indicators | `"focusColor": "#7aa2f7"` |
| `disabledForeground` | Disabled text | `"disabledForeground": "#565f89"` |
| `disabledBackground` | Disabled backgrounds | `"disabledBackground": "#1f2335"` |

### Component Sections

#### Button Component
```json
{
  "ui": {
    "Button": {
      "foreground": "fg",
      "startBackground": "primaryColor",
      "endBackground": "primaryColor",
      "startBorderColor": "borderColor",
      "endBorderColor": "borderColor",
      "focusedBorderColor": "focusColor",
      "hoverBackground": "hoverState",
      "pressedBackground": "hoverState",
      "disabledBorderColor": "disabledColor",
      "arc": 6,
      "shadowColor": "shadowColor",
      "default": {
        "foreground": "bg",
        "startBackground": "primaryColor",
        "endBackground": "primaryColor"
      }
    }
  }
}
```

#### Button Properties

| Property | Type | Description |
|----------|------|-------------|
| `foreground` | color | Text color |
| `startBackground` | color | Gradient start (or solid) |
| `endBackground` | color | Gradient end (or solid) |
| `startBorderColor` | color | Border gradient start |
| `endBorderColor` | color | Border gradient end |
| `focusedBorderColor` | color | Focus state border |
| `hoverBackground` | color | Hover state background |
| `pressedBackground` | color | Pressed state background |
| `arc` | number | Corner radius |
| `shadowColor` | color | Drop shadow color |
| `default` | object | Default button variant |

#### Editor Component
```json
{
  "ui": {
    "Editor": {
      "background": "bgHi",
      "foreground": "fg",
      "caretForeground": "fg",
      "selectionBackground": "selectionColor",
      "selectionForeground": "fg",
      "lineHighlight": "lineHighlightColor",
      "gutterBackground": "gutterBg"
    }
  }
}
```

#### Tool Window Component
```json
{
  "ui": {
    "ToolWindow": {
      "background": "toolWindowBg",
      "Header": {
        "background": "headerBg",
        "inactiveBackground": "headerInactiveBg",
        "borderColor": "borderColor"
      },
      "HeaderTab": {
        "underlineColor": "primaryColor",
        "inactiveUnderlineColor": "inactiveColor",
        "hoverBackground": "hoverState"
      }
    }
  }
}
```

#### Islands-Specific Properties
```json
{
  "ui": {
    "Island.borderColor": "#2c2e40",
    "Island.arc": 20,
    "Island.borderWidth": 5,
    "Island.borderWidth.compact": 4,
    "Island.inactiveAlpha": 0.44
  }
}
```

### Icons Section

Customizes icon colors and mappings:

```json
{
  "icons": {
    "ColorPalette": {
      "Actions.Blue": "#7AA2F7",
      "Actions.Green": "#9ECE6A",
      "Actions.Orange": "#FF9E64",
      "Actions.Purple": "#9D7CD8",
      "Actions.Red": "#F7768E",
      "Actions.Yellow": "#E0AF68",
      "Objects.Blue": "#7AA2F7",
      "Objects.Green": "#9ECE6A",
      "Objects.Red": "#F7768E"
    },
    "/path/to/icon.svg": "/custom/path/icon.svg"
  }
}
```

### Background Section

Defines background images and patterns:

```json
{
  "background": {
    "image": "/path/to/image.png",
    "opacity": 0.1,
    "position": "bottom-right",
    "repeat": "no-repeat"
  }
}
```

## Property Value Types

### Color References
- **Hex values**: `"#7aa2f7"`
- **Named colors**: `"primaryColor"` (references colors section)
- **OS-specific**: `{"os.default": "#color", "os.windows": "#winColor"}`

### Numbers and Measurements
- **Arc radius**: `"arc": 6` (pixel radius)
- **Insets**: `"insets": "top,right,bottom,left"`
- **Sizes**: `"minimumSize": "width,height"`

### Boolean Values
```json
{
  "paintShadow": true,
  "opaque": false
}
```

## Inheritance System

### Parent Theme Inheritance
Child themes inherit all parent properties and can override specific values:

```json
{
  "parentTheme": "Darcula",
  "ui": {
    "Button": {
      "startBackground": "#customColor"  // Only overrides this property
    }
  }
}
```

### Wildcard Inheritance
Components inherit from wildcard section unless explicitly overridden:

```json
{
  "ui": {
    "*": {
      "background": "bg",
      "borderColor": "#defaultBorder"
    },
    "Button": {
      "background": "buttonBg",  // Overrides wildcard
      // borderColor inherited from wildcard
    }
  }
}
```

## Platform-Specific Customization

### OS Detection
```json
{
  "ui": {
    "*": {
      "background": {
        "os.default": "#defaultColor",
        "os.windows": "#winColor",
        "os.mac": "#macColor",
        "os.linux": "#linuxColor"
      }
    }
  }
}
```

### Compact Mode Support
```json
{
  "ui": {
    "Component": {
      "insets": "4,8,4,8",
      "insets.compact": "2,4,2,4"
    }
  }
}
```

## Validation Rules

### Required Properties Check
```json
{
  "required": ["name", "dark", "author", "editorScheme"],
  "optional": ["parentTheme", "colors", "ui", "icons", "background"]
}
```

### Color Reference Validation
- All referenced colors must exist in `colors` section
- Hex values must be valid (#RRGGBB or #AARRGGBB)
- Named colors must be defined before use

### Component Structure Validation
- Component names must match IntelliJ UI components
- Property names must be valid for component type
- Value types must match property expectations

## Best Practices

### Organization
1. **Group related components**: Buttons, editors, tool windows
2. **Use consistent indentation**: 2 spaces recommended
3. **Maintain alphabetical order** for properties within components
4. **Add comments** for complex customizations

### Performance
1. **Minimize color definitions**: Reuse named colors
2. **Avoid unnecessary overrides**: Inherit when possible
3. **Limit OS-specific code**: Only when necessary
4. **Optimize icon paths**: Use relative paths

### Maintenance
1. **Document color meanings**: Add comments for semantic colors
2. **Version control changes**: Track theme evolution
3. **Test across platforms**: Windows, macOS, Linux
4. **Validate theme files**: Use theme validation tools

## Common Patterns

### Complete Theme Structure
```json
{
  "name": "My Theme",
  "dark": true,
  "author": "Developer <dev@example.com>",
  "editorScheme": "/themes/MyTheme.xml",
  "parentTheme": "Darcula",
  "colors": {
    // Named colors
  },
  "ui": {
    "*": {
      // Wildcard defaults
    },
    "Button": {
      // Button styling
    },
    "Editor": {
      // Editor styling
    }
  },
  "icons": {
    // Icon customizations
  }
}
```

### Islands Theme Structure
```json
{
  "name": "My Islands Theme",
  "dark": true,
  "parentTheme": "Islands Dark",
  "ui": {
    "Island.borderColor": "#2c2e40",
    "Island.arc": 20,
    "Island.borderWidth": 5,
    "*": {
      "background": "#1a1b26",
      "foreground": "#c0caf5"
    }
  }
}
```

## Related Documentation
- [Editor XML Scheme Structure](./editor-xml-scheme.md)
- [Plugin Configuration](./plugin-configuration.md)
- [Islands Implementation](../06-islands-themes/islands-implementation.md)
- [Theme Generation Algorithm](../04-ai-workflow/theme-generation-algorithm.md)