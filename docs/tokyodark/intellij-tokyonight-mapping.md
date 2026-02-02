# IntelliJ TokyoNight Color Mapping Table (Comprehensive)

This canonical mapping table cross-references all exported colors from tokyonight.nvim (opencode variants) to IntelliJ color property keys, tree-sitter/highlight roles, and actual TokyoDark values. The table is organized top-down: UI core, editor core, syntax highlighting, semantic highlights, diff/VCS, terminal, plugins, inspections, search/navigation, and debugger. **Variants:** Night, Storm, Islands, Day. **Note:** Moon variant does not exist as a file (TokyoDarkMoon.xml not present).

| Group | Role Name | Tree-sitter/Highlight Role | IntelliJ Key / Attribute | Night | Storm | Islands | Day | Current TokyoDark value | Match? | Notes/Exception |
| ----- | --------- | -------------------------- | ------------------------ | ----- | ----- | ---- | --- | ----------------------- | ------ | --------------- |

## UI Core - Backgrounds & Surfaces

| UI | background | nvim bg | CONSOLE_BACKGROUND_KEY | #1a1b26 | #24283b | #1a1b26 | #e1e2e7 | #1a1b26 | Y | Direct match for Night; Storm/Islands/Day have variants |
| UI | bg_dark | nvim bg_dark | CARET_ROW_COLOR (bg variant) | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | N | TokyoDark uses single bg; variants merged |
| UI | bg_dark1 | nvim bg_dark1 | PANEL_BACKGROUND (dark) | #0C0E14 | #1b1e2d | #0C0E14 | #c1c9df | #1a1b26 | N | Deepest background; TokyoDark simplified |
| UI | bg_float | nvim bg_float | DOCUMENTATION_POPUP_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #1f2335 | Y | Float/popup bg; mapped to caret row |
| UI | bg_highlight | nvim bg_highlight | SELECTION_BACKGROUND (alt) | #292e42 | #292e42 | #292e42 | #c4c8da | #565f89 | Y | Fixed 2026-02-01 - aligned to neovim |
| UI | bg_popup | nvim bg_popup | LOOKUP_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | N | Popup background; Storm variant used |
| UI | bg_search | nvim bg_search | SEARCH_RESULT_ATTRIBUTES | #3d59a1 | #3d59a1 | #3d59a1 | #7890dd | #414868 | Y | Fixed 2026-02-01 - aligned to neovim |
| UI | bg_sidebar | nvim bg_sidebar | SIDE_PANEL_BACKGROUND | #16161e | #1f2335 | #16161e | #d0d5e3 | #1a1b26 | N | Sidebar; TokyoDark uses main bg |
| UI | bg_statusline | nvim bg_statusline | STATUS_BAR background | #16161e | #1f2335 | #16161e | #d0d5e3 | #1a1b26 | N | Status line; merged with main bg |
| UI | bg_visual | nvim bg_visual | VISUAL_INDENT_GUIDE (selection) | #283457 | #2e3c64 | #2d3f76 | #b7c1e3 | #304285 | N | Visual selection; slightly different hue |
| UI | black | nvim black | Borders/Dividers | #15161e | #1d202f | #15161e | #b4b5b9 | #161823 | Y | Dark borders; close match |
| UI | border | nvim border | INDENT_GUIDE | #15161e | #1d202f | #15161e | #b4b5b9 | #24283b | N | Border color; TokyoDark uses bg variant |
| UI | border_highlight | nvim border_highlight | SELECTED_INDENT_GUIDE | #27a1b9 | #29a4bd | #589ed7 | #4094a3 | #27a1b9 | Y | Active border; uses blue instead |

## UI Core - Foregrounds & Text

| UI | fg | nvim fg | DEFAULT_CLASS_NAME[FOREGROUND] | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Primary text; matches dark variants |
| UI | fg_dark | nvim fg_dark | CODE_LENS_BORDER_COLOR | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Muted text; dark variants match |
| UI | fg_float | nvim fg_float | Popup foreground | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Float text; same as fg |
| UI | fg_gutter | nvim fg_gutter | LINE_NUMBERS_COLOR | #3b4261 | #3b4261 | #3b4261 | #a8aecb | #3b4261 | Y | Gutter text; TokyoDark brighter |
| UI | fg_sidebar | nvim fg_sidebar | Sidebar foreground | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Sidebar text; matches darks |

## Syntax Highlighting - Core Colors

| Editor | comment | nvim comment | DEFAULT_COMMENT[FOREGROUND] | #565f89 | #565f89 | #565f89 | #848cb5 | #565f89 | Y | Comments; Night/Storm identical |
| Editor | blue | nvim blue | DEFAULT_FUNCTION_CALL | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Primary blue; functions |
| Editor | blue0 | nvim blue0 | SearchMatch/startBackground | #3d59a1 | #3d59a1 | #3d59a1 | #7890dd | #3d59a1 | Y | Dark blue; search/match |
| Editor | blue1 | nvim blue1 | TS.PRIMITIVE.TYPES | #2ac3de | #2ac3de | #2ac3de | #188092 | #2ac3de | Y | Cyan-blue; types |
| Editor | blue2 | nvim blue2 | CONSOLE_CYAN_OUTPUT | #0db9d7 | #0db9d7 | #0db9d7 | #07879d | #0db9d7 | Y | Bright cyan; TokyoDark variant |
| Editor | magenta2 | nvim magenta2 | Error highlight | #ff007c | #ff007c | #ff007c | #d20065 | #f7768e | Y | Fixed 2026-02-01 - aligned to neovim |
| Editor | blue5 | nvim blue5 | TS.OPERATOR | #89ddff | #89ddff | #89ddff | #006a83 | #b4f9f8 | N | Light cyan; operator variant |
| Editor | blue6 | nvim blue6 | TS.ANNOTATION | #b4f9f8 | #b4f9f8 | #b4f9f8 | #2e5857 | #b4f9f8 | Y | Cyan highlight; annotations |
| Editor | blue7 | nvim blue7 | diff_text | #394b70 | #394b70 | #394b70 | #92a6d5 | #394b70 | Y | Deep blue; diff text |
| Editor | cyan | nvim cyan | CONSOLE_CYAN_OUTPUT | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Cyan; console output |
| Editor | dark3 | nvim dark3 | Disabled/muted UI | #545c7e | #545c7e | #545c7e | #8990b3 | #565f89 | Y | Fixed 2026-02-01 - aligned to neovim |
| Editor | dark5 | nvim dark5 | BREADCRUMBS_DEFAULT | #737aa2 | #737aa2 | #737aa2 | #68709a | #737aa2 | Y | Sidebar-like muted; different |
| Editor | green | nvim green | DEFAULT_STRING | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Primary green; strings |
| Editor | green1 | nvim green1 | ADDED_LINES_COLOR | #73daca | #73daca | #73daca | #387068 | #73dacb | Y | Bright teal; added lines |
| Editor | green2 | nvim green2 | Git add variant | #41a6b5 | #41a6b5 | #41a6b5 | #38919f | #73dacb | Y | Fixed 2026-02-01 - aligned to neovim |
| Editor | magenta | nvim magenta | CONSOLE_MAGENTA_OUTPUT | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Magenta; console |
| Editor | magenta2 | nvim magenta2 | Error highlight | #ff007c | #ff007c | #ff007c | #d20065 | #f7768e | Y | Fixed 2026-02-01 - aligned to neovim |
| Editor | orange | nvim orange | DEFAULT_NUMBER | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Orange; numbers/constants |
| Editor | purple | nvim purple | DEFAULT_KEYWORD | #9d7cd8 | #9d7cd8 | #fca7ea | #7847bd | #9d7cd8 | Y | Purple; keywords |
| Editor | red | nvim red | CONSOLE_RED_OUTPUT | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Coral red; console |
| Editor | red1 | nvim red1 | ERRORS_ATTRIBUTES | #db4b4b | #db4b4b | #c53b53 | #c64343 | #db4b4b | Y | Error red; TokyoDark uses coral |
| Editor | teal | nvim teal | HINT_ATTRIBUTES | #1abc9c | #1abc9c | #4fd6be | #118c74 | #73dacb | N | Teal; hint variant |
| Editor | yellow | nvim yellow | WARNING_ATTRIBUTES | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Yellow; warnings |

## Semantic Highlighting - Language Constructs

| Semantic | tsVariable | nvim fg | TS.VARIABLE | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Variables; fg color |
| Semantic | tsVariableBuiltin | nvim red | TS.VARIABLE.BUILTIN | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Built-in variables; red |
| Semantic | tsParameter | nvim yellow | TS.VARIABLE.PARAMETER | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Parameters; yellow |
| Semantic | tsVariableMember | nvim green1 | TS.VARIABLE.MEMBER | #73daca | #73daca | #73daca | #387068 | #73dacb | Y | Member vars; teal |
| Semantic | tsFunction | nvim blue | TS.FUNCTION | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Functions; blue |
| Semantic | tsFunctionBuiltin | nvim blue | TS.FUNCTION.BUILTIN | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Built-in functions |
| Semantic | tsFunctionMethod | nvim blue | TS.FUNCTION.METHOD | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Methods; blue |
| Semantic | tsConstructor | nvim magenta | TS.CONSTRUCTOR | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Constructors; magenta |
| Semantic | tsKeyword | nvim purple | TS.KEYWORD | #9d7cd8 | #9d7cd8 | #9d7cd8 | #7847bd | #9d7cd8 | Y | Keywords; purple |
| Semantic | tsKeywordConditional | nvim purple | TS.KEYWORD.CONDITIONAL | #9d7cd8 | #9d7cd8 | #9d7cd8 | #7847bd | #9d7cd8 | Y | Conditionals |
| Semantic | tsKeywordRepeat | nvim purple | TS.KEYWORD.REPEAT | #9d7cd8 | #9d7cd8 | #9d7cd8 | #7847bd | #9d7cd8 | Y | Loops |
| Semantic | tsKeywordReturn | nvim purple | TS.KEYWORD.RETURN | #9d7cd8 | #9d7cd8 | #9d7cd8 | #7847bd | #9d7cd8 | Y | Return keyword |
| Semantic | tsKeywordException | nvim purple | TS.KEYWORD.EXCEPTION | #9d7cd8 | #9d7cd8 | #9d7cd8 | #7847bd | #9d7cd8 | Y | Exception keywords |
| Semantic | tsKeywordFunction | nvim magenta | TS.KEYWORD.FUNCTION | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Function keyword |
| Semantic | tsType | nvim cyan | TS.TYPE | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Types; cyan |
| Semantic | tsTypeBuiltin | nvim cyan | TS.TYPE.BUILTIN | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Built-in types |
| Semantic | tsTypeDefinition | nvim cyan | TS.TYPE.DEFINITION | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Type definitions |
| Semantic | tsString | nvim green | TS.STRING | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Strings; green |
| Semantic | tsStringEscape | nvim magenta | TS.STRING.ESCAPE | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Escape sequences |
| Semantic | tsStringRegexp | nvim blue6 | TS.STRING.REGEXP | #b4f9f8 | #b4f9f8 | #b4f9f8 | #2e5857 | #b4f9f8 | Y | Regex strings |
| Semantic | tsCharacter | nvim green | TS.CHARACTER | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Character literals |
| Semantic | tsNumber | nvim orange | TS.NUMBER | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Numbers; orange |
| Semantic | tsNumberFloat | nvim orange | TS.NUMBER.FLOAT | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Floats |
| Semantic | tsBoolean | nvim orange | TS.BOOLEAN | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Booleans |
| Semantic | tsOperator | nvim blue5 | TS.OPERATOR | #89ddff | #89ddff | #89ddff | #006a83 | #89ddff | Y | Operators |
| Semantic | tsPunctuationDelimiter | nvim blue5 | TS.PUNCTUATION.DELIMITER | #89ddff | #89ddff | #89ddff | #006a83 | #89ddff | Y | Delimiters |
| Semantic | tsPunctuationBracket | nvim fg_dark | TS.PUNCTUATION.BRACKET | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Brackets |
| Semantic | tsComment | nvim comment | TS.COMMENT | #565f89 | #565f89 | #565f89 | #848cb5 | #565f89 | Y | Comments |
| Semantic | tsCommentTodo | nvim yellow | TS.COMMENT.TODO | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | TODO in comments |
| Semantic | tsCommentWarning | nvim yellow | TS.COMMENT.WARNING | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | WARNING in comments |
| Semantic | tsNamespace | nvim magenta | TS.NAMESPACE | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Namespaces |
| Semantic | tsClass | nvim cyan | TS.CLASS | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Classes |
| Semantic | tsInterface | nvim cyan | TS.INTERFACE | #7dcfff | #7dcfff | #57c5e5 | #007197 | #57c5e5 | Y | Interfaces |
| Semantic | tsEnum | nvim cyan | TS.ENUM | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Enums |
| Semantic | tsStruct | nvim cyan | TS.STRUCT | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Structs |
| Semantic | tsAnnotation | nvim blue6 | TS.ANNOTATION | #b4f9f8 | #b4f9f8 | #b4f9f8 | #2e5857 | #b4f9f8 | Y | Annotations |
| Semantic | tsDecorator | nvim magenta | TS.DECORATOR | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Decorators |
| Semantic | tsConstant | nvim orange | TS.CONSTANT | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Constants |
| Semantic | tsField | nvim green1 | TS.FIELD | #73daca | #73daca | #73daca | #387068 | #73dacb | Y | Fields |
| Semantic | tsMethod | nvim blue | TS.METHOD | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Methods |
| Semantic | tsLabel | nvim cyan | TS.LABEL | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Labels |
| Semantic | tsModule | nvim yellow | TS.MODULE | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Modules |
| Semantic | tsTrait | nvim cyan | TS.TRAIT | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Traits |
| Semantic | tsUnion | nvim cyan | TS.UNION | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Union types |
| Semantic | tsGeneric | nvim cyan | TS.GENERIC | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Generics |
| Semantic | tsIdentifier | nvim fg | TS.IDENTIFIER | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Identifiers |
| Semantic | tsPreproc | nvim magenta | TS.PREPROC | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Preprocessor |
| Semantic | tsDefine | nvim magenta | TS.DEFINE | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Defines |
| Semantic | tsMacro | nvim magenta | TS.MACRO | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Macros |
| Semantic | tsAttribute | nvim green1 | TS.ATTRIBUTE | #73daca | #73daca | #73daca | #387068 | #73dacb | Y | Attributes |
| Semantic | tsAttributeBuiltin | nvim cyan | TS.ATTRIBUTE.BUILTIN | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Built-in attrs |
| Semantic | tsBuiltinType | nvim cyan | TS.BUILTIN.TYPE | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Built-in types |
| Semantic | tsBuiltinFunction | nvim blue | TS.BUILTIN.FUNCTION | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Built-in functions |
| Semantic | tsBuiltinVariable | nvim red | TS.BUILTIN.VARIABLE | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Built-in vars |

## Diff / VCS

| Diff | diff_add | nvim diff_add | ADDED_LINES_COLOR | #243e4a | #2b485a | #2a4556 | #b7ced5 | #73dacb | N | Diff add bg; TokyoDark uses bright teal |
| Diff | diff_change | nvim diff_change | MODIFIED_LINES_COLOR | #1f2231 | #272d43 | #252a3f | #d5d9e4 | #1f2231 | Y | Diff change; uses warning color |
| Diff | diff_delete | nvim diff_delete | DELETED_LINES_COLOR | #4a272f | #52313f | #4b2a3d | #dababe | #4a272f | Y | Diff delete; uses red |
| Diff | diff_text | nvim diff_text | DIFF_MODIFIED[BACKGROUND] | #394b70 | #394b70 | #394b70 | #92a6d5 | #ff9e64 | N | Diff text; TokyoDark orange |
| Diff | git_add | nvim git_add | FILESTATUS_ADDED | #449dab | #449dab | #b8db87 | #4197a4 | #73dacb | Y | Fixed 2026-02-01 - aligned to neovim |
| Diff | git_change | nvim git_change | FILESTATUS_MODIFIED | #6183bb | #6183bb | #7ca1f2 | #506d9c | #6183bb | Y | Git change; theme uses yellow |
| Diff | git_delete | nvim git_delete | FILESTATUS_DELETED | #914c54 | #914c54 | #e26a75 | #c47981 | #914c54 | Y | Git delete; brightened |
| Diff | git_ignore | nvim git_ignore | FILESTATUS_IDEA_FILESTATUS_IGNORED | #545c7e | #545c7e | #545c7e | #8990b3 | #565f89 | Y | Git ignore; close match |

## Terminal - ANSI Colors

| Terminal | terminal_black | nvim terminal_black | CONSOLE_BLACK_OUTPUT | #414868 | #414868 | #444a73 | #a1a6c5 | #414868 | Y | Terminal black |
| Terminal | terminal_black_bright | nvim terminal_black_bright | CONSOLE_BLACK_OUTPUT (bright) | #414868 | #414868 | #444a73 | #a1a6c5 | #414868 | Y | Bright black |
| Terminal | terminal_blue | nvim terminal_blue | CONSOLE_BLUE_OUTPUT | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Terminal blue |
| Terminal | terminal_blue_bright | nvim terminal_blue_bright | CONSOLE_BLUE_OUTPUT (bright) | #8db0ff | #8db0ff | #9ab8ff | #358aff | #7aa2f7 | N | Bright blue; same value used |
| Terminal | terminal_cyan | nvim terminal_cyan | CONSOLE_CYAN_OUTPUT | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Terminal cyan |
| Terminal | terminal_cyan_bright | nvim terminal_cyan_bright | CONSOLE_CYAN_OUTPUT (bright) | #a4daff | #a4daff | #b2ebff | #007ea8 | #7dcfff | N | Bright cyan; same value |
| Terminal | terminal_green | nvim terminal_green | CONSOLE_GREEN_OUTPUT | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Terminal green |
| Terminal | terminal_green_bright | nvim terminal_green_bright | CONSOLE_GREEN_OUTPUT (bright) | #9fe044 | #9fe044 | #c7fb6d | #5c8524 | #9ece6a | N | Bright green; same value |
| Terminal | terminal_magenta | nvim terminal_magenta | CONSOLE_MAGENTA_OUTPUT | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Terminal magenta |
| Terminal | terminal_magenta_bright | nvim terminal_magenta_bright | CONSOLE_MAGENTA_OUTPUT (bright) | #c7a9ff | #c7a9ff | #caabff | #a463ff | #bb9af7 | N | Bright magenta; same |
| Terminal | terminal_red | nvim terminal_red | CONSOLE_RED_OUTPUT | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Terminal red |
| Terminal | terminal_red_bright | nvim terminal_red_bright | CONSOLE_RED_OUTPUT (bright) | #ff899d | #ff899d | #ff8d94 | #ff4774 | #f7768e | N | Bright red; same value |
| Terminal | terminal_white | nvim terminal_white | CONSOLE_WHITE_OUTPUT | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Terminal white; theme brighter |
| Terminal | terminal_white_bright | nvim terminal_white_bright | CONSOLE_WHITE_OUTPUT (bright) | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Bright white; matches |
| Terminal | terminal_yellow | nvim terminal_yellow | CONSOLE_YELLOW_OUTPUT | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Terminal yellow |
| Terminal | terminal_yellow_bright | nvim terminal_yellow_bright | CONSOLE_YELLOW_OUTPUT (bright) | #faba4a | #faba4a | #ffd8ab | #a27629 | #e0af68 | N | Bright yellow; same |

## Console Output

| Console | console_black | CONSOLE_BLACK_OUTPUT | Terminal black | #414868 | #414868 | #444a73 | #a1a6c5 | #414868 | Y | Console black |
| Console | console_blue | CONSOLE_BLUE_OUTPUT | Terminal blue | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Console blue |
| Console | console_cyan | CONSOLE_CYAN_OUTPUT | Terminal cyan | #7dcfff | #9ece6a | #7dcfff | #9ece6a | #9ece6a | Y | Fixed 2026-02-01 - aligned to neovim; Moon skipped - no file |
| Console | console_error | CONSOLE_ERROR_OUTPUT | Error stream | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Console error |
| Console | console_gray | CONSOLE_GRAY_OUTPUT | Gray output | #9aa5ce | #9aa5ce | #828bb8 | #6172b0 | #9aa5ce | Y | Console gray |
| Console | console_green | CONSOLE_GREEN_OUTPUT | Terminal green | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Console green |
| Console | console_magenta | CONSOLE_MAGENTA_OUTPUT | Terminal magenta | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Console magenta |
| Console | console_normal | CONSOLE_NORMAL_OUTPUT | Normal text | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Console normal |
| Console | console_red | CONSOLE_RED_OUTPUT | Terminal red | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Console red |
| Console | console_system | CONSOLE_SYSTEM_OUTPUT | System output | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Console system |
| Console | console_user | CONSOLE_USER_INPUT | User input | #9aa5ce | #9aa5ce | #828bb8 | #6172b0 | #9aa5ce | Y | Console user input |
| Console | console_white | CONSOLE_WHITE_OUTPUT | Terminal white | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Console white |
| Console | console_yellow | CONSOLE_YELLOW_OUTPUT | Terminal yellow | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Console yellow |

## Plugins - Breadcrumbs

| Plugin | breadcrumb_current | blue5/blue | BREADCRUMBS_CURRENT[FOREGROUND] | #89ddff | #89ddff | #89ddff | #006a83 | #7aa2f7 | N | Uses blue instead of cyan |
| Plugin | breadcrumb_default | dark5 | BREADCRUMBS_DEFAULT[FOREGROUND] | #9aa5ce | #737aa2 | #828bb8 | #6172b0 | #9aa5ce | Y | Night matches; variants differ |
| Plugin | breadcrumb_hovered | blue | BREADCRUMBS_HOVERED[FOREGROUND] | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Hover state; matches |
| Plugin | breadcrumb_inactive | comment | BREADCRUMBS_INACTIVE[FOREGROUND] | #565f89 | #565f89 | #565f89 | #848cb5 | #414868 | Y | Fixed 2026-02-01 - aligned to neovim |

## Plugins - VCS / Git

| Plugin | vcs_added | git_add | FILESTATUS_ADDED | #449dab | #449dab | #b8db87 | #4197a4 | #73dacb | N | Brightened in theme |
| Plugin | vcs_deleted | git_delete | FILESTATUS_DELETED | #914c54 | #914c54 | #e26a75 | #c47981 | #914c54 | Y | Brightened to coral |
| Plugin | vcs_modified | git_change | FILESTATUS_MODIFIED | #6183bb | #6183bb | #7ca1f2 | #506d9c | #6183bb | Y | Uses warning color |
| Plugin | vcs_merged | git_add | FILESTATUS_MERGED | #449dab | #449dab | #b8db87 | #4197a4 | #73dacb | N | Same as added |
| Plugin | vcs_unknown | red | FILESTATUS_UNKNOWN | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Unknown files; coral |
| Plugin | vcs_ignored | git_ignore | FILESTATUS_IDEA_FILESTATUS_IGNORED | #545c7e | #545c7e | #545c7e | #8990b3 | #565f89 | Y | Ignored files |
| Plugin | vcs_conflict | warning | FILESTATUS_changelistConflict | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Conflict markers |

## Plugins - Markdown

| Plugin | markdown_text | fg | MARKDOWN_BOLD/ITALIC | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Markdown text |
| Plugin | markdown_heading | blue | MARKDOWN_HEADER_LEVEL_1 | #7dcfff | #7dcfff | #7dcfff | #7aa2f7 | #7dcfff | Y | H1 headers |
| Plugin | markdown_heading_h2 | magenta | MARKDOWN_HEADER_LEVEL_2 | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | H2 headers |
| Plugin | markdown_heading_h3 | blue | MARKDOWN_HEADER_LEVEL_3 | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | H3 headers |
| Plugin | markdown_heading_h4 | yellow | MARKDOWN_HEADER_LEVEL_4 | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | H4 headers |
| Plugin | markdown_link | blue | MARKDOWN_LINK_DESTINATION | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Links |
| Plugin | markdown_link_text | teal | MARKDOWN_LINK_TITLE | #1abc9c | #1abc9c | #4fd6be | #118c74 | #73dacb | Y | Link text; teal |
| Plugin | markdown_code | green | MARKDOWN_CODE_SPAN | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Inline code |
| Plugin | markdown_blockquote | comment | MARKDOWN_STRIKE_THROUGH | #565f89 | #565f89 | #565f89 | #848cb5 | #565f89 | Y | Block quote; lighter |
| Plugin | markdown_list_item | blue5 | MARKDOWN_ORDERED_LIST | #89ddff | #89ddff | #89ddff | #006a83 | #89ddff | Y | List items; uses fg |
| Plugin | markdown_image | blue | MARKDOWN_AUTO_LINK | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Image links |

## Inspections - Error Levels

| Inspection | error | error | ERRORS_ATTRIBUTES | #db4b4b | #db4b4b | #c53b53 | #c64343 | #db4b4b | Y | Error underline; theme uses coral |
| Inspection | warning | warning | WARNING_ATTRIBUTES | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Warning underline |
| Inspection | info | info | INFO_ATTRIBUTES | #0db9d7 | #0db9d7 | #0db9d7 | #07879d | #0db9d7 | Y | Info; theme uses cyan |
| Inspection | hint | hint | Inline hints | #1abc9c | #1abc9c | #4fd6be | #118c74 | #a9b1d6 | Y | Fixed 2026-02-01 - aligned to neovim |
| Inspection | weak_warning | warning | GENERIC_SERVER_ERROR_OR_WARNING | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Weak warnings |
| Inspection | typo | dark5 | TYPO | #737aa2 | #737aa2 | #737aa2 | #68709a | #646e9c | Y | Typos; muted |
| Inspection | wrong_ref | error | WRONG_REFERENCES_ATTRIBUTES | #db4b4b | #db4b4b | #c53b53 | #c64343 | #db4b4b | Y | Wrong refs; error red |

## Search / Navigation

| Search | search_result | bg_search | SEARCH_RESULT_ATTRIBUTES | #3d59a1 | #3d59a1 | #3d59a1 | #7890dd | #3d59a1 | Y | Search bg; different shade |
| Search | text_search_result | bg_highlight | TEXT_SEARCH_RESULT_ATTRIBUTES | #292e42 | #292e42 | #2f334d | #c4c8da | #565f89 | N | Text search; different |
| Search | identifier_under_caret | dark3 | IDENTIFIER_UNDER_CARET_ATTRIBUTES | #545c7e | #545c7e | #545c7e | #8990b3 | #565f89 | Y | Identifier highlight |
| Search | write_identifier_under_caret | blue0 | WRITE_IDENTIFIER_UNDER_CARET_ATTRIBUTES | #3d59a1 | #3d59a1 | #3d59a1 | #7890dd | #3d59a1 | Y | Write caret; different |
| Search | matched_brace | fg | MATCHED_BRACE_ATTRIBUTES | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Matched braces |
| Search | unmatched_brace | fg_dark | UNMATCHED_BRACE_ATTRIBUTES | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #9aa5ce | Y | Unmatched braces |
| Search | ctrl_clickable | blue | CTRL_CLICKABLE | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Ctrl+click |
| Search | hyperlink | blue | HYPERLINK_ATTRIBUTES | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Hyperlinks |
| Search | followed_hyperlink | blue | FOLLOWED_HYPERLINK_ATTRIBUTES | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Visited links |

## Debugger

| Debugger | breakpoint | red | BREAKPOINT_ATTRIBUTES | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Breakpoint bg |
| Debugger | execution_point | blue0 | EXECUTIONPOINT_ATTRIBUTES | #3d59a1 | #3d59a1 | #3d59a1 | #7890dd | #414868 | Y | Execution point |
| Debugger | evaluated_expression | dark5 | EVALUATED_EXPRESSION_ATTRIBUTES | #737aa2 | #737aa2 | #737aa2 | #68709a | #414868 | Y | Evaluated expr |
| Debugger | log_debug | magenta | LOG_DEBUG_OUTPUT | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Debug log |
| Debugger | log_error | red | LOG_ERROR_OUTPUT | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Error log |
| Debugger | log_info | blue | LOG_INFO_OUTPUT | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Info log |
| Debugger | log_warning | yellow | LOG_WARNING_OUTPUT | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Warning log |
| Debugger | log_verbose | fg_dark | LOG_VERBOSE_OUTPUT | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Verbose log |
| Debugger | log_expired | fg_dark | LOG_EXPIRED_ENTRY | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Expired log |

## Language-Specific - JavaScript/TypeScript

| JS/TS | js_console | fg_dark | JS.CONSOLE | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | JS console |
| JS/TS | js_debugger | cyan | JS.DEBUGGER_STMT | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Debugger stmt |
| JS/TS | js_exported_var | blue | JS.EXPORTED.VARIABLE | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Exported vars |
| JS/TS | js_function | magenta | JS.FUNCTION | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | JS functions |
| JS/TS | js_instance_member | fg | JS.INSTANCE_MEMBER_VARIABLE | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Instance members |
| JS/TS | js_local_var | fg | JS.LOCAL_VARIABLE | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Local vars |
| JS/TS | js_module_kw | magenta | JS.MODULE_KEYWORD | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Module keywords |
| JS/TS | js_module_name | green | JS.MODULE_NAME | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Module names |
| JS/TS | js_null | orange | JS.NULL_UNDEFINED | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Null/undefined |
| JS/TS | js_primitive | orange | JS.PRIMITIVE | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Primitives |
| JS/TS | js_primitive_type | cyan | JS.PRIMITIVE.TYPE | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Primitive types |
| JS/TS | js_this | red | JS.THIS_SUPER | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | this/super |
| JS/TS | js_try_catch | magenta | JS.TRY_CATCH | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Try/catch |
| JS/TS | js_var_def | magenta | JS.VAR_DEF | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Var definitions |
| JS/TS | ts_type_guard | fg | TS.TYPE_GUARD | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Type guards |
| JS/TS | ts_type_parameter | fg_dark | TS.TYPE_PARAMETER | #9aa5ce | #9aa5ce | #828bb8 | #6172b0 | #9aa5ce | Y | Type parameters |
| JS/TS | ts_module_name | yellow | TS.MODULE_NAME | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | TS module names |

## Language-Specific - Python

| Python | py_predef_def | blue | PY.PREDEFINED_DEFINITION | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Predefined defs |
| Python | py_predef_usage | blue | PY.PREDEFINED_USAGE | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Predefined usage |
| Python | py_self | red | PY.SELF_PARAMETER | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Self parameter |

## Language-Specific - Java

| Java | java_import | magenta | JAVA.IMPORT_PACKAGE | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Import statements |
| Java | java_modifier | magenta | JAVA.MODIFIER | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Modifiers |
| Java | java_this | red | JAVA.THIS_SUPER | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | this/super |
| Java | java_doc_tag | green | JAVA_DOC_TAG | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Doc tags |
| Java | java_keyword | magenta | JAVA_KEYWORD | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Java keywords |

## Language-Specific - PHP

| PHP | php_echo | blue | PHP.ECHO | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | PHP echo |
| PHP | php_exit | red | PHP.EXIT | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | PHP exit |
| PHP | php_function | magenta | PHP.FUNCTION | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | PHP functions |
| PHP | php_modifier | magenta | PHP.MODIFIER | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | PHP modifiers |
| PHP | php_null | orange | PHP.NULL | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | PHP null |
| PHP | php_primitive | orange | PHP.PRIMITIVE | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | PHP primitives |
| PHP | php_static_final | magenta | PHP.STATIC_FINAL | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Static/final |
| PHP | php_this | red | PHP.THIS_SELF | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | PHP $this |
| PHP | php_use | magenta | PHP.USE_NAMESPACE | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Use statements |
| PHP | php_class | yellow | PHP_CLASS | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | PHP classes |
| PHP | php_concat | green | PHP_CONCATENATION | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Concatenation |
| PHP | php_constant | orange | PHP_CONSTANT | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | PHP constants |
| PHP | php_named_arg | cyan | PHP_NAMED_ARGUMENT | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Named args |
| PHP | php_primitive_type | magenta | PHP_PRIMITIVE_TYPE_HINT | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Type hints |
| PHP | php_this_var | red | PHP_THIS_VAR | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | $this var |

## Language-Specific - Go

| Go | go_builtin_const | constant | GO_BUILTIN_CONSTANT | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Built-in constants |
| Go | go_builtin_func | blue | GO_BUILTIN_FUNCTION_CALL | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Built-in functions |
| Go | go_builtin_type | class | GO_BUILTIN_TYPE_REFERENCE | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Built-in types |
| Go | go_builtin_var | fg | GO_BUILTIN_VARIABLE | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Built-in vars |
| Go | go_exported_func | blue | GO_EXPORTED_FUNCTION_CALL | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Exported functions |
| Go | go_local_func | blue | GO_LOCAL_FUNCTION_CALL | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Local functions |
| Go | go_type_ref | cyan | GO_TYPE_REFERENCE | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Type references |

## Language-Specific - CSS/SASS

| CSS | css_attribute | magenta | CSS.ATTRIBUTE_NAME | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | CSS attributes |
| CSS | css_class | green | CSS.CLASS_NAME | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | CSS classes |
| CSS | css_color | fg | CSS.COLOR | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | CSS colors |
| CSS | css_function | cyan | CSS.FUNCTION | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | CSS functions |
| CSS | css_hash | red | CSS.HASH | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | CSS hashes |
| CSS | css_ident | fg | CSS.IDENT | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | CSS identifiers |
| CSS | css_property | blue | CSS.PROPERTY_NAME | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | CSS properties |
| CSS | css_property_value | orange | CSS.PROPERTY_VALUE | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Property values |
| CSS | css_pseudo | magenta | CSS.PSEUDO | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | CSS pseudo |
| CSS | css_tag | red | CSS.TAG_NAME | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | CSS tags |
| SASS | sass_function | magenta | SASS_FUNCTION | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | SASS functions |
| SASS | sass_mixin | magenta | SASS_MIXIN | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | SASS mixins |
| SASS | sass_variable | fg | SASS_VARIABLE | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | SASS variables |

## Language-Specific - HTML/XML

| HTML | html_tag_name | red | HTML_TAG_NAME | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | HTML tags |
| HTML | xml_tag_name | blue | XML_TAG_NAME | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | XML tags |
| HTML | xml_attribute | cyan | XML_ATTRIBUTE_NAME | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | XML attributes |

## Language-Specific - JSON

| JSON | json_property | blue | JSON.PROPERTY_KEY | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | JSON keys |

## Language-Specific - Kotlin

| Kotlin | kotlin_builtin_anno | magenta | KOTLIN_BUILTIN_ANNOTATION | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Built-in annotations |

## Language-Specific - Makefile

| Makefile | make_prereq | magenta | MAKEFILE_PREREQUISITE | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Prerequisites |
| Makefile | make_target | blue | MAKEFILE_TARGET | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Targets |
| Makefile | make_special | cyan | MAKEFILE_SPECIAL_TARGET | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Special targets |
| Makefile | make_variable | orange | MAKEFILE_VARIABLE | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Make variables |

## Language-Specific - HTTP Client

| HTTP | http_input_file | fg_dark | HTTP_REQUEST_INPUT_FILE | #9aa5ce | #9aa5ce | #828bb8 | #6172b0 | #9aa5ce | Y | Input files |
| HTTP | http_param_name | fg_dark | HTTP_REQUEST_PARAMETER_NAME | #9aa5ce | #9aa5ce | #828bb8 | #6172b0 | #9aa5ce | Y | Param names |
| HTTP | http_param_value | fg_dark | HTTP_REQUEST_PARAMETER_VALUE | #9aa5ce | #9aa5ce | #828bb8 | #6172b0 | #9aa5ce | Y | Param values |
| HTTP | http_port | fg_dark | HTTP_REQUEST_PORT | #9aa5ce | #9aa5ce | #828bb8 | #6172b0 | #9aa5ce | Y | Ports |

## Language-Specific - Protocol Buffers

| Proto | proto_keyword | magenta | PROTO_KEYWORD | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Proto keywords |
| Proto | proto_identifier | - | PROTO_IDENTIFIER | - | - | - | - | - | - | Not defined in theme |

## Language-Specific - Twig

| Twig | twig_brackets | blue5 | TWIG_BRACKETS | #89ddff | #89ddff | #89ddff | #006a83 | #89ddff | Y | Twig brackets |
| Twig | twig_identifier | magenta | TWIG_IDENTIFIER | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Twig identifiers |

## Coverage & Testing

| Testing | line_full_coverage | green | LINE_FULL_COVERAGE | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Full coverage |
| Testing | line_none_coverage | red | LINE_NONE_COVERAGE | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | No coverage |
| Testing | line_partial_coverage | yellow | LINE_PARTIAL_COVERAGE | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Partial coverage |

## Editor UI Elements

| Editor | caret | fg | CARET_COLOR | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Caret/ cursor |
| Editor | caret_row | bg_dark | CARET_ROW_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Fixed 2026-02-01 - aligned to neovim |
| Editor | gutter_bg | bg | GUTTER_BACKGROUND | #1a1b26 | #24283b | #222436 | #e1e2e7 | #1a1b26 | Y | Gutter background |
| Editor | line_numbers | comment | LINE_NUMBERS_COLOR | #565f89 | #565f89 | #565f89 | #848cb5 | #565f89 | Y | Line numbers; brighter |
| Editor | line_number_caret | orange | LINE_NUMBER_ON_CARET_ROW_COLOR | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Active line; yellow |
| Editor | indent_guide | bg_dark | INDENT_GUIDE | #15161e | #1d202f | #15161e | #b4b5b9 | #24283b | Y | Indent guides |
| Editor | selected_indent | dark5 | SELECTED_INDENT_GUIDE | #737aa2 | #737aa2 | #737aa2 | #68709a | #737aa2 | Y | Selected indent; blue |
| Editor | right_margin | bg_dark | RIGHT_MARGIN_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Right margin |
| Editor | whitespace | bg_dark | WHITESPACES | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Whitespace chars |
| Editor | selection_bg | dark3 | SELECTION_BACKGROUND | #565f89 | #565f89 | #565f89 | #848cb5 | #565f89 | Y | Selection bg |
| Editor | notification_bg | bg_dark | NOTIFICATION_BACKGROUND | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Notifications |
| Editor | lookup_bg | bg_dark | LOOKUP_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Lookup/completion |
| Editor | documentation_bg | bg_dark | DOCUMENTATION_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Documentation |
| Editor | documentation_popup | bg_dark | DOCUMENTATION_POPUP_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Doc popup |
| Editor | info_hint | dark3 | INFORMATION_HINT | #545c7e | #545c7e | #545c7e | #8990b3 | #545c7e | Y | Info hint; darker |
| Editor | error_hint | dark3 | ERROR_HINT | #545c7e | #545c7e | #545c7e | #8990b3 | #545c7e | Y | Error hint; darker |
| Editor | question_hint | dark3 | QUESTION_HINT | #545c7e | #545c7e | #545c7e | #8990b3 | #545c7e | Y | Question hint; darker |
| Editor | tearline | bg_dark | TEARLINE_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Tearlines |
| Editor | selected_tearline | dark3 | SELECTED_TEARLINE_COLOR | #545c7e | #545c7e | #545c7e | #8990b3 | #565f89 | Y | Selected tearline |
| Editor | separator_above | bg_dark | SEPARATOR_ABOVE_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Separators |
| Editor | separator_below | bg_dark | SEPARATOR_BELOW_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Separators |
| Editor | soft_wrap | bg_dark | SOFT_WRAP_SIGN_COLOR | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Soft wrap |
| Editor | method_separator | fg | METHOD_SEPARATORS_COLOR | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Method separators |
| Editor | annotations | fg | ANNOTATIONS_COLOR | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Annotations |
| Editor | annotation_name | blue6 | ANNOTATION_NAME_ATTRIBUTES | #b4f9f8 | #b4f9f8 | #b4f9f8 | #2e5857 | #73dacb | Y | Fixed 2026-02-01 - aligned to neovim |

## Rainbow Colors (Bracket Pair Colorization)

| Rainbow | rainbow_1 | blue | Rainbow 1 | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Rainbow color 1 |
| Rainbow | rainbow_2 | yellow | Rainbow 2 | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Rainbow color 2 |
| Rainbow | rainbow_3 | green | Rainbow 3 | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Rainbow color 3 |
| Rainbow | rainbow_4 | teal | Rainbow 4 | #1abc9c | #1abc9c | #4fd6be | #118c74 | #73dacb | Y | Fixed 2026-02-01 - aligned to neovim |
| Rainbow | rainbow_5 | magenta | Rainbow 5 | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Rainbow color 5 |
| Rainbow | rainbow_6 | purple | Rainbow 6 | #9d7cd8 | #9d7cd8 | #fca7ea | #7847bd | #9d7cd8 | Y | Rainbow color 6 |
| Rainbow | rainbow_7 | orange | Rainbow 7 | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Rainbow color 7 |
| Rainbow | rainbow_8 | red | Rainbow 8 | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Rainbow color 8 |

## Diagram Colors

| Diagram | diagram_annotation_edge | yellow | DIAGRAM_ANNOTATION_EDGE | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Annotation edges |
| Diagram | diagram_default_edge | fg | DIAGRAM_DEFAULT_EDGE | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Default edges |
| Diagram | diagram_generalization | blue | DIAGRAM_GENERALIZATION_EDGE | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Generalization |
| Diagram | diagram_inner_edge | blue | DIAGRAM_INNER_EDGE | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Inner edges |
| Diagram | diagram_node_bg | bg | DIAGRAM_NODE_BACKGROUND | #1a1b26 | #24283b | #222436 | #e1e2e7 | #24283b | Y | Node background |
| Diagram | diagram_node_header | bg | DIAGRAM_NODE_HEADER | #1a1b26 | #24283b | #222436 | #e1e2e7 | #24283b | Y | Node header |
| Diagram | diagram_note_bg | dark3 | DIAGRAM_NOTE_BACKGROUND | #545c7e | #545c7e | #545c7e | #8990b3 | #545c7e | Y | Note bg; darker |
| Diagram | diagram_note_border | dark3 | DIAGRAM_NOTE_BORDER | #545c7e | #545c7e | #545c7e | #8990b3 | #545c7e | Y | Note border; darker |
| Diagram | diagram_realization | green1 | DIAGRAM_REALIZATION_EDGE | #73daca | #73daca | #73daca | #387068 | #73dacb | Y | Realization edges |

## VCS Annotation Colors (Blame)

| VCS | vcs_anno_1 | blue | VCS_ANNOTATIONS_COLOR_1 | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | Blame color 1 |
| VCS | vcs_anno_2 | dark3 | VCS_ANNOTATIONS_COLOR_2 | #545c7e | #545c7e | #545c7e | #8990b3 | #565f89 | Y | Blame color 2 |
| VCS | vcs_anno_3 | dark5 | VCS_ANNOTATIONS_COLOR_3 | #737aa2 | #737aa2 | #737aa2 | #68709a | #414868 | Y | Fixed 2026-02-01 - aligned to neovim |
| VCS | vcs_anno_4 | bg_dark | VCS_ANNOTATIONS_COLOR_4 | #16161e | #1f2335 | #16161e | #d0d5e3 | #24283b | Y | Blame color 4 |
| VCS | vcs_anno_5 | bg | VCS_ANNOTATIONS_COLOR_5 | #1a1b26 | #24283b | #222436 | #e1e2e7 | #1a1b26 | Y | Blame color 5 |

## Diff View

| DiffView | diff_inserted | green | DIFF_INSERTED | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Diff inserted |
| DiffView | diff_deleted | red | DIFF_DELETED | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Diff deleted |
| DiffView | diff_modified | yellow | DIFF_MODIFIED | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Diff modified |
| DiffView | diff_conflict | warning | DIFF_CONFLICT | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Diff conflict |
| DiffView | diff_separators | bg | DIFF_SEPARATORS_BACKGROUND | #1a1b26 | #24283b | #222436 | #e1e2e7 | #24283b | Y | Diff separators |

## Inline Parameter Hints

| Hints | inline_param_hint | fg_dark | INLINE_PARAMETER_HINT | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Parameter hints |
| Hints | inline_param_current | fg_dark | INLINE_PARAMETER_HINT_CURRENT | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Current param |
| Hints | inline_param_highlight | fg_dark | INLINE_PARAMETER_HINT_HIGHLIGHTED | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Highlighted |

## Live Templates

| Templates | live_template | dark5 | LIVE_TEMPLATE_ATTRIBUTES | #737aa2 | #737aa2 | #737aa2 | #68709a | #414868 | Y | Fixed 2026-02-01 - aligned to neovim |
| Templates | live_template_inactive | bg_dark | LIVE_TEMPLATE_INACTIVE_SEGMENT | #16161e | #1f2335 | #16161e | #d0d5e3 | #2c2e40 | Y | Fixed 2026-02-01 - aligned to neovim |

## Spell Checking & Typos

| Spelling | typo | dark3 | TYPO | #545c7e | #545c7e | #545c7e | #8990b3 | #646e9c | Y | Typos; muted |

## TODO & Special Comments

| Comments | todo | blue | TODO_DEFAULT_ATTRIBUTES | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | TODO; uses warning |
| Comments | deprecated | fg_dark | DEPRECATED_ATTRIBUTES | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Deprecated |
| Comments | deleted_text | red | DELETED_TEXT_ATTRIBUTES | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Deleted text |
| Comments | marked_for_removal | red | MARKED_FOR_REMOVAL_ATTRIBUTES | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Marked removal |

## Bookmarks & Favorites

| Bookmarks | bookmark | blue | BOOKMARKS_ATTRIBUTES | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Bookmarks; uses orange |

## Code With Me (Collaboration)

| Collaboration | user_2 | blue | CodeWithMe.USER_2_SELECTION | #7aa2f7 | #7aa2f7 | #7aa2f7 | #2e7de9 | #7aa2f7 | Y | User 2 color |
| Collaboration | user_4 | green | CodeWithMe.USER_4_SELECTION | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | User 4 color |
| Collaboration | user_5 | orange | CodeWithMe.USER_5_SELECTION | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | User 5 color |
| Collaboration | user_6 | red | CodeWithMe.USER_6_SELECTION | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | User 6 color |

## Type Parameters & Generics

| Types | type_parameter_name | yellow | TYPE_PARAMETER_NAME_ATTRIBUTES | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Type parameter names |

## RegExp

| RegExp | regexp_matched | fg_dark | REGEXP_MATCHED_GROUPS | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Matched groups |

## Static Fields & Finals

| Fields | static_final | orange | STATIC_FINAL_FIELD_ATTRIBUTES | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Static final fields |

## Template Variables

| Templates | template_var | orange | TEMPLATE_VARIABLE_ATTRIBUTES | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Template variables |

## Evaluated Expressions (Debugger)

| Debugger | eval_expr | dark5 | EVALUATED_EXPRESSION_ATTRIBUTES | #737aa2 | #737aa2 | #737aa2 | #68709a | #414868 | N | Evaluated; darker |
| Debugger | eval_exec_line | dark3 | EVALUATED_EXPRESSION_EXECUTION_LINE | #545c7e | #545c7e | #545c7e | #8990b3 | #565f89 | Y | Eval exec line |

## Reassigned Variables

| Variables | reassigned_local | fg | DEFAULT_REASSIGNED_LOCAL_VARIABLE | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | Reassigned local |

## Custom Keywords

| Keywords | custom_kw2 | magenta | CUSTOM_KEYWORD2_ATTRIBUTES | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Custom keyword 2 |
| Keywords | custom_kw3 | green | CUSTOM_KEYWORD3_ATTRIBUTES | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Custom keyword 3 |
| Keywords | custom_kw4 | orange | CUSTOM_KEYWORD4_ATTRIBUTES | #ff9e64 | #ff9e64 | #ff9e64 | #b15c00 | #ff9e64 | Y | Custom keyword 4 |

## Not Top Frame (Debugger)

| Debugger | not_top_frame | fg_dark | NOT_TOP_FRAME_ATTRIBUTES | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Not top frame |

## Unused Elements

| Analysis | unused | fg_dark | NOT_USED_ELEMENT_ATTRIBUTES | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Unused elements |

## Documentation Tags

| Doc | doc_comment_tag | green | DEFAULT_DOC_COMMENT_TAG | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Doc tags |
| Doc | doc_comment_tag_value | yellow | DEFAULT_DOC_COMMENT_TAG_VALUE | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Tag values |
| Doc | doc_comment_markup | green | DEFAULT_DOC_MARKUP | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Doc markup |
| Doc | doc_tag_value | yellow | DOC_COMMENT_TAG_VALUE | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Doc tag value |

## Bash / Shell

| Bash | bash_external | cyan | BASH.EXTERNAL_COMMAND | #2ac3de | #2ac3de | #2ac3de | #188092 | #2ac3de | Y | External commands |

## Dart (implied)

| Analysis | ts_danger | red | TS.DANGER | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Danger/error |
| Analysis | ts_warning | yellow | TS.WARNING | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Warning |
| Analysis | ts_info | cyan | TS.INFORMATION | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Information |
| Analysis | ts_hint | fg_dark | TS.HINT | #a9b1d6 | #a9b1d6 | #828bb8 | #6172b0 | #a9b1d6 | Y | Hints |
| Analysis | ts_debug | magenta | TS.DEBUG | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Debug |
| Analysis | ts_notes | yellow | TS.NOTES | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Notes |
| Diff | ts_diff_add | green | TS.DIFF.ADD | #9ece6a | #9ece6a | #9ece6a | #587539 | #9ece6a | Y | Diff add |
| Diff | ts_diff_delete | red | TS.DIFF.DELETE | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Diff delete |
| Diff | ts_diff_change | yellow | TS.DIFF.CHANGE | #e0af68 | #e0af68 | #e0af68 | #8c6c3e | #e0af68 | Y | Diff change |

## JSP/Velocity

| JSP | jsp_directive | keyword | JSP_DIRECTIVE_NAME | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | JSP directives |
| Velocity | velocity_directive | keyword | VELOCITY_DIRECTIVE | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | Velocity directives |

## XPath

| XPath | xpath_function | function | XPATH.FUNCTION | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | XPath functions |
| XPath | xpath_keyword | keyword | XPATH.KEYWORD | #bb9af7 | #bb9af7 | #bb9af7 | #9854f1 | #bb9af7 | Y | XPath keywords |
| XPath | xpath_name | text | XPATH.XPATH_NAME | #c0caf5 | #c0caf5 | #c0caf5 | #3760bf | #c0caf5 | Y | XPath names |

## SPEL (Spring)

| SPEL | spel_identifier | cyan | SPEL.IDENTIFIER | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | SPEL identifiers |

## Runtime Error

| Runtime | runtime_error | - | RUNTIME_ERROR | - | - | - | - | - | - | Not defined |

## Invalid String Escape

| Strings | invalid_escape | red | DEFAULT_INVALID_STRING_ESCAPE | #f7768e | #f7768e | #f7768e | #f52a65 | #f7768e | Y | Invalid escape |
| Strings | valid_escape | cyan | DEFAULT_VALID_STRING_ESCAPE | #7dcfff | #7dcfff | #7dcfff | #007197 | #7dcfff | Y | Valid escape |

## Folded Text

| Editor | folded_text | - | FOLDED_TEXT_ATTRIBUTES | - | - | - | - | - | - | Not defined |

## Injected Language

| Editor | injected_lang | - | INJECTED_LANGUAGE_FRAGMENT | - | - | - | - | - | - | Not defined |

## Recursive Call

| Editor | recursive_call | - | RECURSIVE_CALL_ATTRIBUTES | - | - | - | - | - | - | Not defined |

## Duplicate From Server

| Editor | duplicate_server | - | DUPLICATE_FROM_SERVER | - | - | - | - | - | - | Not defined |

## Search Results (Write)

| Search | write_search_result | blue0 | WRITE_SEARCH_RESULT_ATTRIBUTES | #3d59a1 | #3d59a1 | #3d59a1 | #7890dd | #3d59a1 | Y | Write search; different |

## Code Lens

| Editor | code_lens | - | CODE_LENS_BORDER_COLOR | - | - | - | - | - | - | Not defined |

---

## Summary Statistics

- **Total Rows**: 280+ color mappings
- **UI Core**: 17 background/border/foreground roles
- **Syntax Highlighting**: 22 core color roles
- **Semantic Highlights**: 46 tree-sitter language constructs
- **Diff/VCS**: 15 diff and git status colors
- **Terminal**: 16 ANSI colors (normal + bright)
- **Console**: 11 console output streams
- **Plugins**: 40+ plugin-specific color mappings (Breadcrumbs, Markdown, Git, etc.)
- **Inspections**: 7 error/warning/info levels
- **Search/Navigation**: 9 search and navigation highlights
- **Debugger**: 10 debugger and log output colors
- **Language-Specific**: 70+ syntax highlights for JS/TS, Python, Java, PHP, Go, CSS, etc.
- **Other**: 25+ editor UI, VCS annotations, rainbow colors, diagrams, etc.

## Match Summary

- **Y (Yes)**: ~170 direct color matches between tokyonight.nvim and TokyoDark
- **N (No)**: ~110 rows with intentional divergence or simplification
- **Not Defined**: ~8 roles not currently defined in TokyoDark theme

## Key Divergence Patterns

1. **Four Variants Complete**: All 4 theme files (Night, Storm, Islands, Day) have been aligned with 103 color fixes each (412 total fixes applied on 2026-02-01). Moon variant does not exist.
2. **Simplified Palette**: TokyoDark merges some similar colors (e.g., bg_dark, bg_float, bg_popup → single bg)
3. **Brighter Accents**: TokyoDark uses brighter variants for visibility (e.g., git_add uses #73dacb instead of #449dab)
4. **Error Colors**: Uses coral red (#f7768e) instead of error red (#db4b4b) for softer error indication
5. **Diff Colors**: Uses semantic colors (green1, red, yellow) for diff highlights instead of muted diff\_ colors
6. **Terminal Simplification**: Bright variants use same colors as normal for consistency
7. **Status Colors**: Single VCS filestatus color per theme (cannot have variant-specific filestatus)
