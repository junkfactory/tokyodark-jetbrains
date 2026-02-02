# Validation Checkpoints

## Purpose
This document defines comprehensive validation checkpoints for AI-generated IntelliJ themes, ensuring quality, accessibility, and compatibility. It provides systematic validation procedures and quality metrics.

## Validation Framework Overview

### Validation Categories
1. **Structural Validation**: File format and syntax
2. **Color Validation**: Color references and accessibility
3. **Component Validation**: UI component completeness
4. **Functional Validation**: Theme functionality testing
5. **Performance Validation**: Resource usage and rendering
6. **Compatibility Validation**: Cross-platform and version testing

### Validation Process Flow
```
Input Theme → Structural Validation → Color Validation → Component Validation
    ↓
Functional Validation → Performance Validation → Compatibility Validation
    ↓
Quality Score Generation → Issue Reporting → Auto-Fix Application
```

## Structural Validation

### JSON Schema Validation
```javascript
function validateThemeJSON(themeJSON) {
  const schema = {
    required: ['name', 'dark', 'author', 'editorScheme'],
    optional: ['parentTheme', 'colors', 'ui', 'icons', 'background'],
    types: {
      name: 'string',
      dark: 'boolean',
      author: 'string',
      editorScheme: 'string',
      colors: 'object',
      ui: 'object'
    }
  };
  
  return validateAgainstSchema(themeJSON, schema);
}
```

### XML Scheme Validation
```javascript
function validateEditorScheme(xmlContent) {
  const validation = {
    wellFormed: false,
    hasRequiredElements: false,
    validAttributes: false,
    validColors: false
  };
  
  try {
    const parser = new DOMParser();
    const doc = parser.parseFromString(xmlContent, 'application/xml');
    
    validation.wellFormed = !doc.getElementsByTagName('parsererror').length;
    validation.hasRequiredElements = validateRequiredElements(doc);
    validation.validAttributes = validateAttributeStructure(doc);
    validation.validColors = validateColorElements(doc);
    
  } catch (error) {
    validation.error = error.message;
  }
  
  return validation;
}
```

### Plugin XML Validation
```javascript
function validatePluginXML(pluginXML) {
  const checks = {
    hasValidStructure: false,
    hasRequiredDependencies: false,
    hasValidThemeProviders: false,
    hasValidIds: false
  };
  
  // Check for required plugin structure
  checks.hasValidStructure = /<idea-plugin>[\s\S]*<\/idea-plugin>/.test(pluginXML);
  
  // Check for platform dependency
  checks.hasRequiredDependencies = /<depends>com\.intellij\.modules\.platform<\/depends>/.test(pluginXML);
  
  // Check for valid theme provider format
  checks.hasValidThemeProviders = /<themeProvider[^>]*path="[^"]*"[^>]*\/>/.test(pluginXML);
  
  // Check for valid IDs
  checks.hasValidIds = /id="[^"]*\.[^"]*"/.test(pluginXML);
  
  return checks;
}
```

## Color Validation

### Color Reference Validation
```javascript
function validateColorReferences(themeJSON) {
  const validation = {
    undefinedReferences: [],
    unusedColors: [],
    circularReferences: [],
    invalidFormats: []
  };
  
  const colors = themeJSON.colors || {};
  const ui = themeJSON.ui || {};
  
  // Check for undefined color references
  const referencedColors = extractColorReferences(ui);
  for (const ref of referencedColors) {
    if (!colors[ref]) {
      validation.undefinedReferences.push(ref);
    }
  }
  
  // Check for unused color definitions
  for (const colorName of Object.keys(colors)) {
    if (!referencedColors.includes(colorName)) {
      validation.unusedColors.push(colorName);
    }
  }
  
  // Check for circular references
  validation.circularReferences = detectCircularReferences(colors);
  
  // Check for invalid color formats
  for (const [name, value] of Object.entries(colors)) {
    if (!isValidColorFormat(value)) {
      validation.invalidFormats.push({ name, value });
    }
  }
  
  return validation;
}
```

### Color Format Validation
```javascript
function isValidColorFormat(colorValue) {
  // Accept hex colors
  if (/^#[0-9A-Fa-f]{6}$/.test(colorValue)) return true;
  if (/^#[0-9A-Fa-f]{8}$/.test(colorValue)) return true;
  
  // Accept color references
  if (typeof colorValue === 'string' && /^[a-zA-Z][a-zA-Z0-9]*$/.test(colorValue)) return true;
  
  // Accept objects with OS-specific values
  if (typeof colorValue === 'object' && colorValue.os) return true;
  
  return false;
}
```

### Accessibility Validation
```javascript
function validateAccessibility(themeJSON) {
  const validation = {
    contrastRatios: {},
    wcagCompliance: {},
    colorblindFriendly: false,
    overallScore: 0
  };
  
  const colors = themeJSON.colors || {};
  const ui = themeJSON.ui || {};
  
  // Test primary text contrast
  const primaryContrast = calculateContrastRatio(colors.fg, colors.bg);
  validation.contrastRatios.primary = primaryContrast;
  validation.wcagCompliance.primary = {
    AA: primaryContrast >= 4.5,
    AAA: primaryContrast >= 7.0
  };
  
  // Test selection contrast
  const selectionContrast = calculateContrastRatio(colors.fg, colors.selectionBackground);
  validation.contrastRatios.selection = selectionContrast;
  validation.wcagCompliance.selection = {
    AA: selectionContrast >= 4.5,
    AAA: selectionContrast >= 7.0
  };
  
  // Test button contrast
  if (ui.Button) {
    const buttonContrast = calculateContrastRatio(ui.Button.foreground, ui.Button.startBackground);
    validation.contrastRatios.button = buttonContrast;
    validation.wcagCompliance.button = {
      AA: buttonContrast >= 4.5,
      AAA: buttonContrast >= 7.0
    };
  }
  
  // Calculate overall accessibility score
  const scores = Object.values(validation.wcagCompliance)
    .flatMap(compliance => Object.values(compliance))
    .filter(Boolean);
  validation.overallScore = scores.length / (Object.keys(validation.contrastRatios).length * 2);
  
  return validation;
}
```

## Component Validation

### Required UI Components
```javascript
const REQUIRED_COMPONENTS = [
  '*',              // Wildcard defaults
  'Button',         // Button styling
  'Editor',         // Editor area
  'ToolWindow',     // Tool windows
  'EditorTabs',     // Editor tabs
  'StatusBar',      // Status bar
  'List',           // Lists and trees
  'Popup',          // Popups and dialogs
  'ComboBox',       // Combo boxes
  'TextField'       // Text fields
];

function validateUIComponents(themeJSON) {
  const validation = {
    presentComponents: [],
    missingComponents: [],
    incompleteComponents: [],
    componentQuality: {}
  };
  
  const ui = themeJSON.ui || {};
  
  // Check for required components
  for (const component of REQUIRED_COMPONENTS) {
    if (ui[component]) {
      validation.presentComponents.push(component);
      const quality = validateComponentQuality(ui[component], component);
      validation.componentQuality[component] = quality;
      
      if (quality.score < 0.7) {
        validation.incompleteComponents.push(component);
      }
    } else {
      validation.missingComponents.push(component);
    }
  }
  
  return validation;
}
```

### Component Quality Validation
```javascript
function validateComponentQuality(component, componentName) {
  const quality = {
    score: 0,
    issues: [],
    suggestions: []
  };
  
  // Check for required properties
  const requiredProps = getRequiredProperties(componentName);
  const presentProps = Object.keys(component);
  
  for (const prop of requiredProps) {
    if (presentProps.includes(prop)) {
      quality.score += 0.2;
    } else {
      quality.issues.push(`Missing required property: ${prop}`);
    }
  }
  
  // Check for proper color references
  const colorProps = getColorProperties(componentName);
  for (const prop of colorProps) {
    if (component[prop]) {
      if (isValidColorReference(component[prop])) {
        quality.score += 0.1;
      } else {
        quality.issues.push(`Invalid color reference in ${prop}`);
        quality.suggestions.push(`Use a valid color reference for ${prop}`);
      }
    }
  }
  
  return quality;
}
```

### Editor Scheme Validation
```javascript
function validateEditorScheme(xmlContent) {
  const validation = {
    hasRequiredColors: false,
    hasRequiredAttributes: false,
    languageSupport: {},
    consistency: {}
  };
  
  const doc = parseXML(xmlContent);
  
  // Validate colors section
  const colors = doc.getElementsByTagName('colors')[0];
  if (colors) {
    const requiredColors = ['CARET_COLOR', 'SELECTION_BACKGROUND', 'BACKGROUND'];
    validation.hasRequiredColors = requiredColors.every(color => 
      colors.querySelector(`option[name="${color}"]`)
    );
  }
  
  // Validate attributes section
  const attributes = doc.getElementsByTagName('attributes')[0];
  if (attributes) {
    const requiredAttributes = ['TEXT', 'DEFAULT_KEYWORD', 'DEFAULT_STRING'];
    validation.hasRequiredAttributes = requiredAttributes.every(attr => 
      attributes.querySelector(`option[name="${attr}"]`)
    );
  }
  
  return validation;
}
```

## Functional Validation

### Theme Loading Validation
```javascript
function validateThemeLoading(themeFiles) {
  const validation = {
    loadsSuccessfully: false,
    appearsInSettings: false,
    appliesCorrectly: false,
    errors: []
  };
  
  // Simulate theme loading
  try {
    const theme = loadThemeFromFiles(themeFiles);
    validation.loadsSuccessfully = true;
    
    // Check if theme appears in settings
    if (isThemeInSettings(theme.name)) {
      validation.appearsInSettings = true;
      
      // Test theme application
      if (applyThemeAndValidate(theme)) {
        validation.appliesCorrectly = true;
      }
    }
  } catch (error) {
    validation.errors.push(error.message);
  }
  
  return validation;
}
```

### Visual Regression Testing
```javascript
function validateVisualRegression(theme, baselineTheme) {
  const validation = {
    visualDifferences: [],
    regressions: [],
    improvements: [],
    similarity: 0
  };
  
  // Compare visual elements
  const components = ['Button', 'Editor', 'ToolWindow', 'StatusBar'];
  
  for (const component of components) {
    const diff = compareComponentVisuals(theme, baselineTheme, component);
    
    if (diff.significant) {
      if (diff.quality < 0) {
        validation.regressions.push({ component, issue: diff.issue });
      } else {
        validation.improvements.push({ component, improvement: diff.improvement });
      }
    }
    
    validation.visualDifferences.push(diff);
  }
  
  // Calculate overall similarity score
  validation.similarity = calculateVisualSimilarity(validation.visualDifferences);
  
  return validation;
}
```

## Performance Validation

### Resource Usage Validation
```javascript
function validatePerformance(theme) {
  const validation = {
    colorCount: 0,
    componentComplexity: 0,
    memoryUsage: 0,
    renderingPerformance: 0,
    optimization: []
  };
  
  // Count unique colors
  validation.colorCount = countUniqueColors(theme);
  if (validation.colorCount > 50) {
    validation.optimization.push('Consider reducing color count for better performance');
  }
  
  // Calculate component complexity
  validation.componentComplexity = calculateComponentComplexity(theme);
  if (validation.componentComplexity > 100) {
    validation.optimization.push('Simplify component structure for better performance');
  }
  
  // Estimate memory usage
  validation.memoryUsage = estimateMemoryUsage(theme);
  if (validation.memoryUsage > 10) { // MB
    validation.optimization.push('Large theme size may impact memory usage');
  }
  
  return validation;
}
```

### Rendering Performance Testing
```javascript
function validateRenderingPerformance(theme) {
  const validation = {
    fps: 0,
    renderingTime: 0,
    gpuAcceleration: false,
    bottlenecks: []
  };
  
  // Simulate rendering tests
  const tests = [
    'editorScrolling',
    'tabSwitching',
    'menuOpening',
    'dialogDisplay'
  ];
  
  for (const test of tests) {
    const result = simulateRenderingTest(theme, test);
    validation[test] = result;
    
    if (result.fps < 60) {
      validation.bottlenecks.push(`${test}: Low FPS (${result.fps})`);
    }
  }
  
  return validation;
}
```

## Compatibility Validation

### Cross-Platform Validation
```javascript
function validateCrossPlatform(theme) {
  const platforms = ['windows', 'mac', 'linux'];
  const validation = {
    tested: [],
    issues: {},
    compatibilityScore: 0
  };
  
  for (const platform of platforms) {
    const result = testThemeOnPlatform(theme, platform);
    validation.tested.push(platform);
    
    if (result.issues.length > 0) {
      validation.issues[platform] = result.issues;
    }
  }
  
  // Calculate compatibility score
  const totalIssues = Object.values(validation.issues).reduce((sum, issues) => sum + issues.length, 0);
  validation.compatibilityScore = Math.max(0, 1 - (totalIssues / (platforms.length * 5)));
  
  return validation;
}
```

### Version Compatibility Validation
```javascript
function validateVersionCompatibility(theme) {
  const validation = {
    minimumVersion: null,
    supportedVersions: [],
    deprecatedFeatures: [],
    compatibilityIssues: []
  };
  
  // Check for Islands-specific features
  if (theme.Islands || theme.parentTheme?.includes('Islands')) {
    validation.minimumVersion = '2025.3';
    validation.supportedVersions = ['2025.3', '2025.4', '2026.1'];
    
    // Check for deprecated features
    if (hasDeprecatedIslandsFeatures(theme)) {
      validation.deprecatedFeatures.push('Using deprecated Islands configuration');
    }
  } else {
    validation.minimumVersion = '2024.3';
    validation.supportedVersions = ['2024.3', '2025.0', '2025.1', '2025.2', '2025.3', '2026.1'];
  }
  
  return validation;
}
```

## Quality Score Calculation

### Comprehensive Quality Metrics
```javascript
function calculateQualityScore(validations) {
  const weights = {
    structural: 0.15,
    color: 0.20,
    component: 0.20,
    functional: 0.20,
    performance: 0.15,
    compatibility: 0.10
  };
  
  const scores = {
    structural: calculateStructuralScore(validations.structural),
    color: calculateColorScore(validations.color),
    component: calculateComponentScore(validations.component),
    functional: calculateFunctionalScore(validations.functional),
    performance: calculatePerformanceScore(validations.performance),
    compatibility: calculateCompatibilityScore(validations.compatibility)
  };
  
  const totalScore = Object.entries(weights)
    .reduce((total, [category, weight]) => {
      return total + (scores[category] * weight);
    }, 0);
  
  return {
    totalScore,
    categoryScores: scores,
    weights,
    grade: getQualityGrade(totalScore)
  };
}
```

### Quality Grading System
```javascript
function getQualityGrade(score) {
  if (score >= 0.95) return 'A+';
  if (score >= 0.90) return 'A';
  if (score >= 0.85) return 'B+';
  if (score >= 0.80) return 'B';
  if (score >= 0.70) return 'C';
  if (score >= 0.60) return 'D';
  return 'F';
}
```

## Auto-Fix Mechanisms

### Color Auto-Fix
```javascript
function autoFixColorIssues(validation, theme) {
  const fixes = [];
  
  // Fix undefined color references
  for (const ref of validation.undefinedReferences) {
    const fix = generateColorForReference(ref);
    theme.colors[ref] = fix.color;
    fixes.push(`Added missing color: ${ref} = ${fix.color}`);
  }
  
  // Fix low contrast
  if (!validation.accessibility.wcagCompliance.primary) {
    const fix = enhanceContrast(theme.colors.fg, theme.colors.bg);
    theme.colors.fg = fix.foreground;
    theme.colors.bg = fix.background;
    fixes.push('Enhanced primary text contrast for WCAG compliance');
  }
  
  return fixes;
}
```

### Structure Auto-Fix
```javascript
function autoFixStructuralIssues(validation, theme) {
  const fixes = [];
  
  // Add missing required properties
  if (validation.missingComponents.length > 0) {
    for (const component of validation.missingComponents) {
      const defaultProps = generateDefaultComponentProperties(component);
      theme.ui[component] = defaultProps;
      fixes.push(`Added default properties for ${component}`);
    }
  }
  
  // Fix invalid color formats
  for (const { name, value } of validation.invalidFormats) {
    const fixedValue = fixColorFormat(value);
    theme.colors[name] = fixedValue;
    fixes.push(`Fixed color format for ${name}`);
  }
  
  return fixes;
}
```

## Validation Report Generation

### Comprehensive Report Format
```javascript
function generateValidationReport(validations, qualityScore) {
  return {
    summary: {
      overallScore: qualityScore.totalScore,
      grade: qualityScore.grade,
      issuesCount: countTotalIssues(validations),
      fixableIssues: countFixableIssues(validations)
    },
    
    categories: {
      structural: validations.structural,
      color: validations.color,
      component: validations.component,
      functional: validations.functional,
      performance: validations.performance,
      compatibility: validations.compatibility
    },
    
    recommendations: generateRecommendations(validations),
    
    autoFixes: generateAutoFixes(validations)
  };
}
```

## Integration with Generation Pipeline

### Validation Integration Points
```javascript
function integrateValidationWithGeneration(generationResult) {
  // Run validation after generation
  const validation = runComprehensiveValidation(generationResult);
  
  // Apply auto-fixes if possible
  const autoFixes = applyAutoFixes(validation, generationResult);
  
  // Re-validate after fixes
  const finalValidation = runComprehensiveValidation(generationResult);
  
  return {
    originalTheme: generationResult,
    initialValidation: validation,
    autoFixes: autoFixes,
    finalValidation: finalValidation,
    qualityScore: calculateQualityScore(finalValidation)
  };
}
```

## Continuous Validation

### Validation Monitoring
```javascript
function setupContinuousValidation(theme) {
  const monitor = {
    checks: [],
    metrics: {},
    alerts: []
  };
  
  // Periodic validation checks
  setInterval(() => {
    const validation = runQuickValidation(theme);
    monitor.checks.push(validation);
    
    if (validation.issues.length > 0) {
      monitor.alerts.push({
        timestamp: new Date(),
        issues: validation.issues
      });
    }
  }, 300000); // Every 5 minutes
  
  return monitor;
}
```

## Related Documentation
- [Theme Generation Algorithm](./theme-generation-algorithm.md)
- [Accessibility Guidelines](../02-color-system/accessibility-guidelines.md)
- [Cross-Platform Testing](./cross-ide-compatibility.md)
- [Performance Optimization](./performance-optimization.md)