#!/bin/bash

# =============================================================================
# TokyoDark Theme Image Scaling Script
# =============================================================================
# Description: Scales theme images to match target dimensions with high quality
# Author: Generated for tokyodark-jetbrains project
# Version: 1.0
# =============================================================================

set -euo pipefail  # Strict error handling

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Default values
SOURCE_IMAGE=""
TARGET_WIDTH=""
TARGET_HEIGHT=""
BACKUP_DIR="backups"
QUALITY=100
FILTER="Lanczos"
FORCE=false

# Functions
log_info() {
    echo -e "${BLUE}ℹ️  INFO:${NC} $1"
}

log_success() {
    echo -e "${GREEN}✅ SUCCESS:${NC} $1"
}

log_warning() {
    echo -e "${YELLOW}⚠️  WARNING:${NC} $1"
}

log_error() {
    echo -e "${RED}❌ ERROR:${NC} $1"
}

show_usage() {
    cat << EOF
Usage: $(basename "$0") [OPTIONS] SOURCE_IMAGE TARGET_WIDTH TARGET_HEIGHT

DESCRIPTION:
    Scales an image to specified dimensions using high-quality ImageMagick processing.
    Creates automatic backups and validates the output.

ARGUMENTS:
    SOURCE_IMAGE    Path to the source image file
    TARGET_WIDTH    Target width in pixels
    TARGET_HEIGHT   Target height in pixels

OPTIONS:
    -b, --backup-dir DIR     Backup directory (default: backups)
    -q, --quality NUM        JPEG quality (1-100, default: 100)
    -f, --filter FILTER      Resize filter (Lanczos, Mitchell, Catrom, default: Lanczos)
    -F, --force              Force overwrite existing backup
    -h, --help              Show this help message

EXAMPLES:
    # Scale to match tokyonight_dark.png dimensions
    $(basename "$0") static/tokyodark_islands.png 1504 1665

    # Custom backup location and quality
    $(basename "$0") -b /tmp/backups -q 90 image.png 800 600

    # Use different filter
    $(basename "$0") -f Mitchell photo.png 1024 768

REQUIREMENTS:
    - ImageMagick (magick command)
    - File read/write permissions
EOF
}

validate_requirements() {
    # Check if ImageMagick is available
    if ! command -v magick &> /dev/null; then
        log_error "ImageMagick not found. Please install ImageMagick first."
        log_info "Install with: brew install imagemagick (macOS)"
        exit 1
    fi

    # Check if source image exists
    if [[ ! -f "$SOURCE_IMAGE" ]]; then
        log_error "Source image '$SOURCE_IMAGE' not found."
        exit 1
    fi

    # Validate dimensions
    if ! [[ "$TARGET_WIDTH" =~ ^[0-9]+$ ]] || ! [[ "$TARGET_HEIGHT" =~ ^[0-9]+$ ]]; then
        log_error "Width and height must be positive integers."
        exit 1
    fi

    # Validate quality
    if ! [[ "$QUALITY" =~ ^[0-9]+$ ]] || [[ "$QUALITY" -lt 1 ]] || [[ "$QUALITY" -gt 100 ]]; then
        log_error "Quality must be between 1 and 100."
        exit 1
    fi
}

get_image_info() {
    local image_file="$1"
    
    if ! info=$(file "$image_file" 2>/dev/null); then
        log_error "Cannot read image file: $image_file"
        exit 1
    fi
    
    echo "$info"
}

create_backup() {
    local source="$1"
    local backup_name
    backup_name="${source##*/}"
    backup_name="${backup_name%.*}_backup_$(date +%Y%m%d_%H%M%S).${source##*.}"
    local backup_path="$BACKUP_DIR/$backup_name"
    
    # Create backup directory if it doesn't exist
    mkdir -p "$BACKUP_DIR"
    
    if [[ -f "$backup_path" ]] && [[ "$FORCE" != "true" ]]; then
        log_error "Backup file already exists: $backup_path"
        log_error "Use -F/--force to overwrite or remove the existing backup."
        exit 1
    fi
    
    log_info "Creating backup: $backup_path"
    cp "$source" "$backup_path"
    
    if [[ $? -eq 0 ]]; then
        log_success "Backup created successfully"
        echo "$backup_path"
    else
        log_error "Failed to create backup"
        exit 1
    fi
}

scale_image() {
    local source="$1"
    local target="$2"
    local width="$3"
    local height="$4"
    
    log_info "Scaling image: ${width}x${height} using $FILTER filter"
    
    # Use ImageMagick v7 syntax with force dimensions
    if magick "$source" -resize "${width}x${height}!" -filter "$FILTER" -quality "$QUALITY" "$target"; then
        log_success "Image scaled successfully"
        return 0
    else
        log_error "Failed to scale image"
        return 1
    fi
}

validate_output() {
    local target="$1"
    local expected_width="$2"
    local expected_height="$3"
    
    local info
    info=$(get_image_info "$target")
    
    # Extract dimensions from file command output
    # Format: filename: PNG image data, WIDTH x HEIGHT, ...
    if [[ "$info" =~ ([0-9]+)[[:space:]]+x[[:space:]]+([0-9]+) ]]; then
        local actual_width="${BASH_REMATCH[1]}"
        local actual_height="${BASH_REMATCH[2]}"
        
        if [[ "$actual_width" -eq "$expected_width" ]] && [[ "$actual_height" -eq "$expected_height" ]]; then
            log_success "Output validation passed: ${actual_width}x${actual_height}"
            return 0
        else
            log_error "Output validation failed: Expected ${expected_width}x${expected_height}, got ${actual_width}x${actual_height}"
            return 1
        fi
    else
        log_error "Cannot validate output dimensions"
        log_error "File output: '$info'"
        return 1
    fi
}

show_summary() {
    local source="$1"
    local target="$2"
    local backup="$3"
    
    echo
    echo "📊 OPERATION SUMMARY"
    echo "===================="
    
    local source_info
    local target_info
    local backup_info
    source_info=$(get_image_info "$source")
    target_info=$(get_image_info "$target")
    backup_info=$(get_image_info "$backup" 2>/dev/null || echo "Backup file not accessible")
    
    echo "📁 Source: $source"
    echo "   $source_info"
    echo
    echo "📁 Target: $target"
    echo "   $target_info"
    echo
    echo "💾 Backup: $backup"
    echo "   $backup_info"
    echo
    echo "✅ Operation completed successfully!"
}

# Parse command line arguments
parse_args() {
    while [[ $# -gt 0 ]]; do
        case $1 in
            -h|--help)
                show_usage
                exit 0
                ;;
            -b|--backup-dir)
                BACKUP_DIR="$2"
                shift 2
                ;;
            -q|--quality)
                QUALITY="$2"
                shift 2
                ;;
            -f|--filter)
                FILTER="$2"
                shift 2
                ;;
            -F|--force)
                FORCE=true
                shift
                ;;
            -*)
                log_error "Unknown option: $1"
                show_usage
                exit 1
                ;;
            *)
                if [[ -z "$SOURCE_IMAGE" ]]; then
                    SOURCE_IMAGE="$1"
                elif [[ -z "$TARGET_WIDTH" ]]; then
                    TARGET_WIDTH="$1"
                elif [[ -z "$TARGET_HEIGHT" ]]; then
                    TARGET_HEIGHT="$1"
                else
                    log_error "Too many arguments"
                    show_usage
                    exit 1
                fi
                shift
                ;;
        esac
    done

    # Validate required arguments
    if [[ -z "$SOURCE_IMAGE" || -z "$TARGET_WIDTH" || -z "$TARGET_HEIGHT" ]]; then
        log_error "Missing required arguments"
        show_usage
        exit 1
    fi
}

# Main execution
main() {
    echo "🎨 TokyoDark Image Scaling Tool"
    echo "================================"
    echo
    
    parse_args "$@"
    
    log_info "Configuration:"
    log_info "  Source: $SOURCE_IMAGE"
    log_info "  Target: ${TARGET_WIDTH}x${TARGET_HEIGHT}"
    log_info "  Quality: $QUALITY"
    log_info "  Filter: $FILTER"
    log_info "  Backup dir: $BACKUP_DIR"
    echo
    
    validate_requirements
    
    # Get source image info
    local source_info
    source_info=$(get_image_info "$SOURCE_IMAGE")
    log_info "Source image: $source_info"
    
    # Create backup
    local backup_file
    backup_file=$(create_backup "$SOURCE_IMAGE")
    
    # Create temporary scaled file
    local temp_file="${SOURCE_IMAGE%.*}_scaled_tmp.${SOURCE_IMAGE##*.}"
    
    # Scale the image
    if scale_image "$SOURCE_IMAGE" "$temp_file" "$TARGET_WIDTH" "$TARGET_HEIGHT"; then
        # Validate output
        if validate_output "$temp_file" "$TARGET_WIDTH" "$TARGET_HEIGHT"; then
            # Replace original
            mv "$temp_file" "$SOURCE_IMAGE"
            log_success "Original file replaced with scaled version"
            
            # Show summary
            show_summary "$SOURCE_IMAGE" "$SOURCE_IMAGE" "$backup_file"
        else
            log_error "Output validation failed, keeping original file"
            rm -f "$temp_file"
            exit 1
        fi
    else
        log_error "Scaling failed, keeping original file"
        rm -f "$temp_file"
        exit 1
    fi
}

# Run main function with all arguments
main "$@"