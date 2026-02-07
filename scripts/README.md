# TokyoDark Image Scaling Scripts

This directory contains bash scripts for scaling theme images in the tokyodark-jetbrains project.

## Scripts Overview

### 🎯 `scale_to_tokyonight.sh` (Quick Use)

Quick scaling script to match `tokyonight_dark.png` dimensions (1504×1665).

```bash
# Scale an image to match tokyonight_dark.png
./scale_to_tokyonight.sh static/tokyodark_islands.png

# Scale other theme images
./scale_to_tokyonight.sh static/tokyodark_contrast.png
```

### 🎨 `scale_theme_image.sh` (Advanced)

Flexible image scaling script with customizable options.

```bash
# Basic usage
./scale_theme_image.sh source.png 1504 1665

# With custom options
./scale_theme_image.sh -b custom_backups -q 90 -f Mitchell photo.png 800 600

# Force overwrite existing backup
./scale_theme_image.sh -F image.png 1024 768
```

## Features

- ✅ **Automatic Backups**: Creates timestamped backups before scaling
- ✅ **High Quality**: Uses ImageMagick Lanczos filter for premium results
- ✅ **Validation**: Verifies output dimensions match targets
- ✅ **Safe Operations**: Preserves originals if scaling fails
- ✅ **Flexible Options**: Custom quality, filters, and backup locations
- ✅ **Error Handling**: Comprehensive validation and error reporting

## Requirements

- ImageMagick (`magick` command)
- macOS/Linux with Bash shell

### Install ImageMagick (macOS)

```bash
brew install imagemagick
```

## Usage Examples

### Scale TokyoDark Islands

```bash
./scripts/scale_to_tokyonight.sh static/tokyodark_islands.png
```

### Scale with Custom Settings

```bash
./scripts/scale_theme_image.sh \
  -b project_backups \
  -q 95 \
  -f Mitchell \
  static/tokyodark_contrast.png \
  1200 \
  1000
```

### Batch Processing (multiple images)

```bash
# Scale multiple tokyodark theme images
for img in static/tokyodark_*.png; do
  ./scripts/scale_to_tokyonight.sh "$img"
done
```

## Command Options

### `scale_theme_image.sh`

- `-b, --backup-dir DIR` - Custom backup directory (default: backups)
- `-q, --quality NUM` - Image quality 1-100 (default: 100)
- `-f, --filter FILTER` - Resize filter: Lanczos, Mitchell, Catrom (default: Lanczos)
- `-F, --force` - Force overwrite existing backups
- `-h, --help` - Show help and exit

