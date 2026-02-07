#!/bin/bash

# =============================================================================
# TokyoDark Quick Scale Script
# =============================================================================
# Description: Quick scaling for tokyodark theme images to match tokyonight dimensions
# Usage: ./scale_to_tokyonight.sh IMAGE_FILE
# =============================================================================

set -euo pipefail

# Target dimensions (matching tokyonight_dark.png)
TARGET_WIDTH=1504
TARGET_HEIGHT=1665

# Script directory
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
SCALE_SCRIPT="$SCRIPT_DIR/scale_theme_image.sh"

# Colors
GREEN='\033[0;32m'
BLUE='\033[0;34m'
NC='\033[0m'

show_usage() {
    cat << EOF
Quick TokyoDark Theme Scaling

Usage: $(basename "$0") IMAGE_FILE

Scales an image to match tokyonight_dark.png dimensions (1504x1665).

Arguments:
    IMAGE_FILE    Path to the image file to scale

Example:
    $(basename "$0") static/tokyodark_islands.png
    $(basename "$0") ../static/tokyodark_contrast.png
EOF
}

# Check arguments
if [[ $# -ne 1 ]] || [[ "$1" == "-h" ]] || [[ "$1" == "--help" ]]; then
    show_usage
    exit 1
fi

IMAGE_FILE="$1"

# Check if the main scaling script exists
if [[ ! -f "$SCALE_SCRIPT" ]]; then
    echo "❌ Error: scale_theme_image.sh not found in $SCRIPT_DIR"
    exit 1
fi

echo "🎯 Quick TokyoDark Scaling"
echo "========================"
echo "📁 Image: $IMAGE_FILE"
echo "📏 Target: ${TARGET_WIDTH}x${TARGET_HEIGHT} (matching tokyonight_dark.png)"
echo

# Execute the main scaling script
exec "$SCALE_SCRIPT" -b backups -q 100 -f Lanczos "$IMAGE_FILE" "$TARGET_WIDTH" "$TARGET_HEIGHT"