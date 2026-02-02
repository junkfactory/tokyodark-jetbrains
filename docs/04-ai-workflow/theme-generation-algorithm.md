# Theme Generation Algorithm

## Purpose
This document provides a systematic algorithm for AI agents to generate complete IntelliJ themes, including both traditional and Islands variants. It covers the complete workflow from color palette selection to file generation.

## Algorithm Overview

### Input Requirements
1. **Theme Name**: Human-readable theme name
2. **Theme Type**: Traditional or Islands
3. **Color Mode**: Light or dark
4. **Base Palette**: Primary color selection
5. **Variants**: Optional variant types (Storm, Contrast)

### Output Generation
1. **Theme JSON file**: UI component styling
2. **Editor XML file**: Syntax highlighting scheme
3. **Plugin XML**: Theme registration
4. **Variant files**: Additional theme variants

## Core Algorithm Steps

### Step 1: Color Palette Generation

#### Input Analysis
```javascript
function analyzeInput(themeConfig) {
  return {
    themeName: themeConfig.name,
    isDark: themeConfig.dark,
    isIslands: themeConfig.type === "islands",
    baseColors: extractBaseColors(themeConfig.palette),
    variants: themeConfig.variants || []
  };
}
```

#### Color Token Generation
```javascript
function generateColorTokens(input) {
  const basePalette = input.isDark ? DARK_PALETTE : LIGHT_PALETTE;
  const customColors = mergePalettes(basePalette, input.baseColors);
  
  return {
    // Semantic colors
    bg: calculateBackground(customColors, input.isDark),
    fg: calculateForeground(customColors, input.isDark),
    
    // Hierarchy colors
    bgHi: adjustBrightness(customColors.bg, input.isDark ? 10 : -10),
    hoverBackground: adjustBrightness(customColors.bg, input.isDark ? 15 : -15),
    selectionBackground: generateSelectionColor(customColors),
    
    // Functional colors
    primaryColor: customColors.primary || extractPrimary(customColors),
    accentColor: customColors.accent || extractAccent(customColors),
    
    // State colors
    errorColor: generateStateColor(customColors, 'error'),
    warningColor: generateStateColor(customColors, 'warning'),
    successColor: generateStateColor(customColors, 'success')
  };
}
```

#### Color Validation
```javascript
function validateColorTokens(tokens) {
  const validation = {
    contrastRatios: {},
    colorHarmony: false,
    accessibility: false
  };
  
  // Check primary contrast ratios
  validation.contrastRatios.primary = calculateContrastRatio(tokens.fg, tokens.bg);
  validation.contrastRatios.hover = calculateContrastRatio(tokens.fg, tokens.hoverBackground);
  
  // Validate WCAG compliance
  validation.accessibility = Object.values(validation.contrastRatios)
    .every(ratio => ratio >= 4.5);
  
  // Check color harmony
  validation.colorHarmony = validateColorHarmony(tokens);
  
  return validation;
}
```

### Step 2: Theme JSON Structure Generation

#### Base Theme Structure
```javascript
function generateThemeStructure(input, colorTokens) {
  const structure = {
    name: input.themeName,
    dark: input.isDark,
    author: "AI Theme Generator",
    editorScheme: `/${input.themeName}.xml`,
    colors: colorTokens
  };
  
  // Add Islands-specific properties
  if (input.isIslands) {
    structure.parentTheme = input.isDark ? "Islands Dark" : "Islands Light";
    structure.Islands = 1;
  } else {
    structure.parentTheme = input.isDark ? "Darcula" : "IntelliJ Light";
  }
  
  return structure;
}
```

#### UI Component Generation
```javascript
function generateUIComponents(colorTokens, input) {
  const ui = {
    "*": generateWildcardSection(colorTokens),
    "Button": generateButtonStyling(colorTokens),
    "Editor": generateEditorStyling(colorTokens, input),
    "ToolWindow": generateToolWindowStyling(colorTokens, input),
    "EditorTabs": generateEditorTabsStyling(colorTokens, input),
    "StatusBar": generateStatusBarStyling(colorTokens, input)
  };
  
  // Add Islands-specific components
  if (input.isIslands) {
    Object.assign(ui, generateIslandsComponents(colorTokens));
  }
  
  return ui;
}
```

#### Wildcard Section Generation
```javascript
function generateWildcardSection(tokens) {
  return {
    background: tokens.bg,
    foreground: tokens.fg,
    selectionBackground: tokens.selectionBackground,
    selectionForeground: tokens.fg,
    selectionInactiveBackground: tokens.hoverBackground,
    selectionInactiveForeground: tokens.fg,
    hoverBackground: tokens.hoverBackground,
    inactiveBackground: tokens.bgHi,
    disabledBackground: tokens.bgHi,
    disabledForeground: adjustBrightness(tokens.fg, -40),
    borderColor: tokens.bgHi,
    separatorColor: adjustBrightness(tokens.bg, 15),
    focusColor: tokens.primaryColor,
    caretForeground: tokens.fg
  };
}
```

#### Button Component Generation
```javascript
function generateButtonStyling(tokens) {
  return {
    foreground: tokens.fg,
    startBackground: tokens.hoverBackground,
    endBackground: tokens.hoverBackground,
    startBorderColor: tokens.hoverBackground,
    endBorderColor: tokens.hoverBackground,
    focusedBorderColor: tokens.primaryColor,
    hoverBackground: adjustBrightness(tokens.hoverBackground, 10),
    pressedBackground: adjustBrightness(tokens.hoverBackground, -10),
    disabledBorderColor: tokens.bgHi,
    arc: 6,
    shadowColor: adjustBrightness(tokens.bg, -20),
    default: {
      foreground: tokens.bg,
      startBackground: tokens.primaryColor,
      endBackground: tokens.primaryColor,
      startBorderColor: adjustBrightness(tokens.primaryColor, -20),
      endBorderColor: adjustBrightness(tokens.primaryColor, -20),
      focusColor: tokens.fg,
      focusedBorderColor: tokens.fg
    }
  };
}
```

#### Islands Components Generation
```javascript
function generateIslandsComponents(tokens) {
  return {
    Island: {
      arc: 20,                                    // 10px actual radius
      borderWidth: 5,                               // Gap between islands
      borderColor: tokens.hoverBackground,             // Match tool windows
      borderWidth: {
        compact: 4                                 // Smaller in compact mode
      },
      inactiveAlpha: 0.44                           // Dim inactive windows
    },
    MainWindow: {
      background: adjustBrightness(tokens.bg, 10)     // Lighter than tool windows
    },
    ToolWindow: {
      background: adjustBrightness(tokens.bg, -10)    // Darker islands
    }
  };
}
```

### Step 3: Editor Scheme Generation

#### XML Structure Creation
```javascript
function generateEditorScheme(input, colorTokens) {
  const scheme = {
    name: input.themeName,
    version: 142,
    parent_scheme: input.isIslands ? "Islands Dark" : "Darcula",
    colors: generateEditorColors(colorTokens),
    attributes: generateTextAttributes(colorTokens)
  };
  
  return scheme;
}
```

#### Editor Colors Generation
```javascript
function generateEditorColors(tokens) {
  return {
    CARET_COLOR: tokens.fg,
    CARET_ROW_COLOR: adjustBrightness(tokens.bg, 5),
    SELECTION_BACKGROUND: tokens.selectionBackground,
    CONSOLE_BACKGROUND_KEY: tokens.bg,
    GUTTER_BACKGROUND: tokens.bg,
    LINE_NUMBERS_COLOR: adjustBrightness(tokens.fg, -40),
    INDENT_GUIDE: adjustBrightness(tokens.bg, 10),
    WHITESPACES: adjustBrightness(tokens.bg, 8),
    SEPARATOR_ABOVE_COLOR: adjustBrightness(tokens.bg, 10),
    SEPARATOR_BELOW_COLOR: adjustBrightness(tokens.bg, 10)
  };
}
```

#### Text Attributes Generation
```javascript
function generateTextAttributes(tokens) {
  const attributes = {};
  
  // Base text styling
  attributes.TEXT = {
    value: {
      FOREGROUND: tokens.fg,
      BACKGROUND: tokens.bg
    }
  };
  
  // Syntax highlighting (language-agnostic)
  attributes.DEFAULT_KEYWORD = generateKeywordStyle(tokens);
  attributes.DEFAULT_STRING = generateStringStyle(tokens);
  attributes.DEFAULT_NUMBER = generateNumberStyle(tokens);
  attributes.DEFAULT_COMMENT = generateCommentStyle(tokens);
  attributes.DEFAULT_FUNCTION_CALL = generateFunctionStyle(tokens);
  attributes.DEFAULT_FUNCTION_DECLARATION = generateFunctionDeclStyle(tokens);
  attributes.DEFAULT_CLASS_NAME = generateClassStyle(tokens);
  
  // Error and warning states
  attributes.ERRORS_ATTRIBUTES = generateErrorStyle(tokens);
  attributes.WARNING_ATTRIBUTES = generateWarningStyle(tokens);
  
  return attributes;
}
```

### Step 4: Variant Generation

#### Storm Variant Algorithm
```javascript
function generateStormVariant(baseTheme) {
  return {
    name: `${baseTheme.name} Storm`,
    ...baseTheme,
    colors: {
      ...baseTheme.colors,
      bg: adjustBrightness(baseTheme.colors.bg, -15),
      bgHi: adjustBrightness(baseTheme.colors.bgHi, -15),
      hoverBackground: adjustBrightness(baseTheme.colors.hoverBackground, -15),
      selectionBackground: adjustBrightness(baseTheme.colors.selectionBackground, -10)
    }
  };
}
```

#### Contrast Variant Algorithm
```javascript
function generateContrastVariant(baseTheme) {
  return {
    name: `${baseTheme.name} Contrast`,
    ...baseTheme,
    colors: {
      ...baseTheme.colors,
      fg: adjustBrightness(baseTheme.colors.fg, 10),
      primaryColor: adjustBrightness(baseTheme.colors.primaryColor, 20),
      selectionBackground: adjustBrightness(baseTheme.colors.selectionBackground, 15),
      hoverBackground: adjustBrightness(baseTheme.colors.hoverBackground, 10)
    }
  };
}
```

#### Islands Variant Algorithm
```javascript
function generateIslandsVariant(baseTheme) {
  return {
    name: `${baseTheme.name} Islands`,
    dark: baseTheme.dark,
    parentTheme: baseTheme.dark ? "Islands Dark" : "Islands Light",
    colors: baseTheme.colors,
    ui: {
      ...baseTheme.ui,
      ...generateIslandsComponents(baseTheme.colors)
    }
  };
}
```

## Complete Generation Algorithm

### Master Generation Function
```javascript
function generateIntelliJTheme(themeConfig) {
  // Step 1: Analyze input
  const input = analyzeInput(themeConfig);
  
  // Step 2: Generate color tokens
  let colorTokens = generateColorTokens(input);
  
  // Step 3: Validate colors
  const validation = validateColorTokens(colorTokens);
  if (!validation.accessibility) {
    throw new Error('Color tokens fail accessibility requirements');
  }
  
  // Step 4: Generate base theme structure
  const themeStructure = generateThemeStructure(input, colorTokens);
  
  // Step 5: Generate UI components
  themeStructure.ui = generateUIComponents(colorTokens, input);
  
  // Step 6: Generate editor scheme
  const editorScheme = generateEditorScheme(input, colorTokens);
  
  // Step 7: Generate variants
  const variants = {};
  for (const variantType of input.variants) {
    switch (variantType) {
      case 'storm':
        variants.storm = generateStormVariant(themeStructure);
        break;
      case 'contrast':
        variants.contrast = generateContrastVariant(themeStructure);
        break;
      case 'islands':
        variants.islands = generateIslandsVariant(themeStructure);
        break;
    }
  }
  
  // Step 8: Assemble final output
  return {
    baseTheme: themeStructure,
    editorScheme: editorScheme,
    variants: variants,
    validation: validation
  };
}
```

## File Output Generation

### Theme JSON File Generation
```javascript
function generateThemeJSON(theme) {
  const jsonString = JSON.stringify(theme, null, 2);
  return {
    filename: `${theme.name}.theme.json`,
    content: jsonString,
    path: `/themes/${theme.name}.theme.json`
  };
}
```

### Editor XML File Generation
```javascript
function generateEditorXML(scheme) {
  let xml = `<?xml version="1.0"?>\n`;
  xml += `<scheme name="${scheme.name}" parent_scheme="${scheme.parent_scheme}" version="${scheme.version}">\n`;
  
  // Generate colors section
  xml += generateXMLColors(scheme.colors);
  
  // Generate attributes section
  xml += generateXMLAttributes(scheme.attributes);
  
  xml += `</scheme>\n`;
  
  return {
    filename: `${scheme.name}.xml`,
    content: xml,
    path: `/colorSchemes/${scheme.name}.xml`
  };
}
```

### Plugin XML Registration
```javascript
function generatePluginXML(themes) {
  let xml = `<?xml version="1.0"?>\n<idea-plugin>\n`;
  xml += `  <id>com.generated.themes</id>\n`;
  xml += `  <name>Generated Themes</name>\n`;
  xml += `  <author>AI Theme Generator</author>\n`;
  xml += `  <depends>com.intellij.modules.platform</depends>\n`;
  xml += `  <extensions defaultExtensionNs="com.intellij">\n`;
  
  // Register base theme
  xml += `    <themeProvider id="com.generated.${themes.baseTheme.name}" path="${themes.baseTheme.name}.theme.json"/>\n`;
  
  // Register variants
  for (const [variantName, variant] of Object.entries(themes.variants)) {
    xml += `    <themeProvider id="com.generated.${variant.name}" path="${variant.name}.theme.json"/>\n`;
  }
  
  xml += `  </extensions>\n</idea-plugin>\n`;
  
  return {
    filename: 'plugin.xml',
    content: xml,
    path: '/META-INF/plugin.xml'
  };
}
```

## Validation and Quality Assurance

### Automated Validation Rules
```javascript
function validateGeneratedTheme(themeOutput) {
  const validation = {
    structure: {},
    colors: {},
    accessibility: {},
    completeness: {}
  };
  
  // Validate JSON structure
  validation.structure.json = validateJSONStructure(themeOutput.baseTheme);
  
  // Validate XML structure
  validation.structure.xml = validateXMLStructure(themeOutput.editorScheme);
  
  // Validate color references
  validation.colors.references = validateColorReferences(themeOutput.baseTheme);
  
  // Validate accessibility
  validation.accessibility.contrast = validateContrastRatios(themeOutput.baseTheme);
  validation.accessibility.wcag = validateWCAGCompliance(themeOutput.baseTheme);
  
  // Validate completeness
  validation.completeness.components = validateRequiredComponents(themeOutput.baseTheme);
  validation.completeness.attributes = validateRequiredAttributes(themeOutput.editorScheme);
  
  return validation;
}
```

### Quality Metrics
```javascript
function calculateQualityMetrics(themeOutput) {
  return {
    colorConsistency: calculateColorConsistency(themeOutput.baseTheme),
    accessibility: calculateAccessibilityScore(themeOutput.baseTheme),
    performance: calculatePerformanceImpact(themeOutput.baseTheme),
    maintainability: calculateMaintainabilityScore(themeOutput.baseTheme),
    visualHarmony: calculateVisualHarmonyScore(themeOutput.baseTheme)
  };
}
```

## Error Handling and Recovery

### Common Generation Errors
```javascript
const errorHandlers = {
  'INVALID_COLORS': (error) => {
    return {
      fix: 'Suggest alternative color palette',
      autoFix: generateAlternativePalette(error.colors)
    };
  },
  
  'LOW_CONTRAST': (error) => {
    return {
      fix: 'Adjust color brightness for accessibility',
      autoFix: enhanceContrast(error.colors)
    };
  },
  
  'MISSING_COMPONENTS': (error) => {
    return {
      fix: 'Add missing UI component definitions',
      autoFix: addMissingComponents(error.theme)
    };
  }
};
```

### Auto-Fix Mechanisms
```javascript
function autoFixValidationIssues(validation, themeOutput) {
  const fixes = [];
  
  if (!validation.accessibility.wcag) {
    fixes.push(enhanceColorContrast(themeOutput.baseTheme));
  }
  
  if (!validation.completeness.components) {
    fixes.push(addMissingComponents(themeOutput.baseTheme));
  }
  
  return fixes;
}
```

## Performance Optimization

### Color Calculation Optimization
```javascript
// Cache color calculations
const colorCache = new Map();

function calculateColor(baseColor, adjustment) {
  const cacheKey = `${baseColor}-${adjustment}`;
  if (colorCache.has(cacheKey)) {
    return colorCache.get(cacheKey);
  }
  
  const result = performColorCalculation(baseColor, adjustment);
  colorCache.set(cacheKey, result);
  return result;
}
```

### File Generation Optimization
```javascript
// Stream XML generation for large schemes
function generateXMLStream(attributes) {
  const stream = new ReadableStream({
    read() {
      // Generate attributes in chunks
      return { done: false, value: nextAttributeChunk() };
    }
  });
  
  return stream;
}
```

## Usage Examples

### Basic Theme Generation
```javascript
const config = {
  name: "MyTheme",
  type: "islands",
  dark: true,
  palette: {
    primary: "#7aa2f7",
    accent: "#f7768e"
  },
  variants: ["storm", "contrast"]
};

const themeOutput = generateIntelliJTheme(config);
```

### Tokyo-Style Theme Generation
```javascript
const tokyoConfig = {
  name: "TokyoDark",
  type: "traditional",
  dark: true,
  palette: TOKYO_NIGHT_PALETTE,
  variants: ["storm", "contrast", "islands"]
};

const tokyoTheme = generateIntelliJTheme(tokyoConfig);
```

## Related Documentation
- [Tokyo Night Palette](../02-color-system/tokyo-night-palette.md)
- [Theme JSON Structure](../03-file-architecture/theme-json-structure.md)
- [Islands Implementation](../06-islands-themes/islands-implementation.md)
- [Validation Checkpoints](../05-quality-assurance/validation-checkpoints.md)