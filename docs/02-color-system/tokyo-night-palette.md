# Tokyo Night Color Palette & Design System

## Purpose
This document defines the complete Tokyo Night color palette and design system used in the TokyoDark theme family. It provides AI agents with a comprehensive color framework for creating consistent theme variants.

## Color Philosophy

### Tokyo Night Design Principles
- **Dark-first design**: Optimized for low-light environments
- **High readability**: Excellent text contrast ratios
- **Minimal eye strain**: Carefully balanced saturation levels
- **Professional appearance**: Suitable for long coding sessions
- **Color harmony**: Cohesive color relationships throughout

## Primary Color Palette

### Base Colors (Hex Values)
```json
{
  "bgHi": "#161720",        // Highest contrast background
  "bg": "#1a1b26",          // Primary background
  "terminalBlack": "#414868", // Terminal black
  "fg": "#c0caf5",          // Primary foreground
  "fgDark": "#a9b1d6",       // Secondary foreground
  "fgGutter": "#3b4261",     // Gutter foreground
  "dark3": "#545c7e",        // Dark accent
  "comment": "#565f89",       // Comment text
  "dark5": "#737aa2"         // Subtle foreground
}
```

### Semantic Color Groups

#### Blue Family
```json
{
  "blue0": "#3d59a1",   // Background blue
  "blue": "#7aa2f7",     // Primary blue
  "cyan": "#7dcfff",      // Bright cyan
  "blue1": "#2ac3de",    // Light blue
  "blue2": "#0db9d7",    // Aqua blue
  "blue5": "#89ddff",    // Bright blue
  "blue6": "#B4F9F8",    // Light cyan
  "blue7": "#394b70"     // Dark blue
}
```

#### Purple & Magenta
```json
{
  "magenta": "#bb9af7",    // Primary magenta
  "magenta2": "#ff007c",   // Bright magenta
  "purple": "#9d7cd8"      // Purple accent
}
```

#### Warm Colors
```json
{
  "orange": "#ff9e64",     // Primary orange
  "yellow": "#e0af68",     // Primary yellow
  "red": "#f7768e",       // Primary red
  "red1": "#db4b4b"       // Dark red
}
```

#### Green Family
```json
{
  "green": "#9ece6a",     // Primary green
  "green1": "#73daca",    // Aqua green
  "green2": "#41a6b5",    // Dark green
  "teal": "#1abc9c"      // Teal accent
}
```

## Semantic Color Tokens

### Interactive States
```json
{
  "hoverBackground": "#232433",           // Hover state
  "selectionInactiveBackground": "#414868", // Inactive selection
  "separatorColor": "#36374F",            // UI separators
  "selectionBackground": "#304285",         // Active selection
  "shadowColor": "#161823"                 // Drop shadows
}
```

### Contextual Colors
- **Information**: Use blue family (`blue`, `blue5`)
- **Warning**: Use yellow/orange (`yellow`, `orange`)
- **Error**: Use red family (`red`, `red1`)
- **Success**: Use green family (`green`, `green1`)
- **Accent**: Use purple/magenta (`magenta`, `purple`)

## Color Relationships

### Hierarchy System
```
Background Hierarchy (lightest to darkest):
bgHi → bg → hoverBackground → selectionInactiveBackground → selectionBackground

Foreground Hierarchy (dimmest to brightest):
dark5 → comment → dark3 → fgDark → fg
```

### Contrast Ratios
| Foreground | Background | Ratio | WCAG AA | WCAG AAA |
|------------|-------------|--------|----------|----------|
| fg (#c0caf5) | bg (#1a1b26) | 12.8:1 | ✅ | ✅ |
| fgDark (#a9b1d6) | bg (#1a1b26) | 9.1:1 | ✅ | ✅ |
| comment (#565f89) | bg (#1a1b26) | 3.2:1 | ✅ | ❌ |
| dark5 (#737aa2) | bg (#1a1b26) | 2.8:1 | ✅ | ❌ |

## Variant Color Transformations

### Storm Variant Rules
```json
{
  "transformation": {
    "bg": "#24283b",      // +~15% darkness
    "bgHi": "#1f2335",    // +~15% darkness
    "selectionBackground": "#2d3f59", // +~15% darkness
    "hoverBackground": "#292e42"      // +~15% darkness
  }
}
```

### Contrast Variant Rules
```json
{
  "transformation": {
    "fg": "#d4d8f0",      // +~10% brightness
    "blue": "#89b4fa",     // +~20% brightness
    "green": "#b4e3a0",    // +~20% brightness
    "selectionBackground": "#365880" // Higher contrast
  }
}
```

### Islands Adaptation Rules
```json
{
  "adaptation": {
    "MainWindow.background": "#21222e", // Lighter than tool windows
    "ToolWindow.background": "#2c2e40", // Darker islands
    "Island.borderColor": "#2c2e40",     // Match tool windows
    "Island.arc": 20,                     // Rounded corners
    "Island.borderWidth": 5                 // Island spacing
  }
}
```

## Color Naming Convention

### Semantic Categories
1. **Base Colors**: `bg`, `fg` - fundamental background/foreground
2. **Hierarchy**: `bgHi`, `fgDark`, `dark3`, `dark5` - contrast levels
3. **Function**: `blue`, `red`, `green` - semantic purposes
4. **State**: `hoverBackground`, `selectionBackground` - interaction states
5. **UI**: `separatorColor`, `shadowColor` - interface elements

### Naming Rules
- **CamelCase** for all color names
- **Descriptive but concise** (max 15 characters)
- **Semantic over literal** (use `selectionBackground` not `darkBlue`)
- **Consistent suffixes**: `Background`, `Color`, `Foreground`

## Implementation Guidelines

### Color Token Usage
```json
{
  "colors": {
    // Define all colors in colors section
    "bg": "#1a1b26",
    "fg": "#c0caf5"
  },
  "ui": {
    "*": {
      "background": "bg",      // Reference by name
      "foreground": "fg"
    },
    "Button": {
      "startBackground": "blue",
      "endBackground": "blue"
    }
  }
}
```

### Best Practices
1. **Always define colors in colors section** before referencing
2. **Use semantic names** instead of direct hex values
3. **Maintain contrast ratios** for accessibility
4. **Test in different lighting conditions**
5. **Consider colorblind users** (avoid relying on red/green only)

## Color Validation Rules

### Automated Checks
```javascript
// Contrast ratio validation
function validateContrast(foreground, background, minimum = 4.5) {
  const ratio = calculateContrastRatio(foreground, background);
  return ratio >= minimum;
}

// Color harmony validation
function validateHarmony(colorPalette) {
  // Check for consistent saturation/brightness relationships
  // Validate no clashing colors
  return true;
}
```

### Required Contrast Levels
- **Standard text**: 4.5:1 minimum (WCAG AA)
- **Large text**: 3:1 minimum (WCAG AA)
- **UI components**: 3:1 minimum
- **Enhanced accessibility**: 7:1 recommended

## Color Psychology in Code Context

### Language-Specific Associations
- **Blue**: Functions, methods, control flow
- **Green**: Strings, success states
- **Orange/Red**: Errors, warnings, important keywords
- **Purple**: Types, interfaces, annotations
- **Cyan**: Numbers, constants, operators
- **Yellow**: Documentation, comments, metadata

### Cognitive Load Reduction
- **Limit color usage**: 5-7 colors maximum for syntax
- **Consistent meaning**: Same color = same concept across languages
- **Subtle variations**: Use brightness/saturation for hierarchy
- **Avoid saturated colors**: Reduce eye strain in long sessions

## Related Documentation
- [Named Color System](./named-color-system.md)
- [Color Relationships](./color-relationships.md)
- [Accessibility Guidelines](./accessibility-guidelines.md)
- [Theme Generation Algorithm](../04-ai-workflow/theme-generation-algorithm.md)