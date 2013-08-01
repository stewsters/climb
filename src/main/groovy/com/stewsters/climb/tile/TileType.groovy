package com.stewsters.climb.tile

import squidpony.squidcolor.SColor


public enum TileType {
    AIR(false, false, false, 0f, '.' as char, SColor.LIGHT_BLUE),
    WALL(true, false, true, 1f, '#' as char, SColor.LIGHT_GRAY),
    SAND(true, true, false, 1f, 'X' as char, SColor.LIGHT_YELLOW_DYE),
    BOUNDS(true, false, false, 1f, 'x' as char, SColor.DARK_GRAY)

    public Boolean isBlocked
    public Boolean isDiggable
    public Boolean isGrippable
    public float opacity
    public char glyph // null means see whats below
    public SColor color

    //gore is getting to be a new decor layer
    //public Boolean isExplored = false

    public TileType(Boolean isBlocked, Boolean isDiggable, Boolean isGrippable, float opacity, char glyph, SColor color) {
        this.isBlocked = isBlocked
        this.isDiggable = isDiggable
        this.isGrippable = isGrippable
        this.opacity = opacity
        this.glyph = glyph
        this.color = color
    }

}
