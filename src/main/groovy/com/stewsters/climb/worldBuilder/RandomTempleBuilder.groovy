package com.stewsters.climb.worldBuilder

import com.stewsters.climb.tile.TileType
import com.stewsters.climb.tile.World
import com.stewsters.util.RectPrism

class RandomTempleBuilder {

    private int width;
    private int height;
    private int depth;
    private TileType[][][] tiles = null;


    public RandomTempleBuilder(int width, int height, int depth) {
        this.width = width
        this.height = height
        this.depth = depth
        this.tiles = new TileType[width][height][depth]
    }

    public World build() {
        return new World(tiles);
    }

    public RandomTempleBuilder horizontalFillBelow(TileType tile, int maxZ = 1) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < maxZ; z++) {
                    tiles[x][y][z] = tile
                }
            }
        }
        return this
    }

    public RandomTempleBuilder generateStructure(int historicGroundLevel) {

        setRectPrism(new RectPrism(5, 5, 20, 10, 10, 4), TileType.AIR, TileType.WALL)
        setRectPrism(new RectPrism(5, 16, 20, 10, 10, 3), TileType.AIR, TileType.WALL)

        setPillar(25, 25, 20, 25, TileType.SAND)
        setPillar(25, 27, 20, 25, TileType.SAND)
        return this
    }

    private setPillar(int x, int y, int zLow, int zHigh, TileType fill) {
        (zLow..zHigh).each { z ->
            tiles[x][y][z] = fill
        }
    }

    private setRectPrism(RectPrism prism, TileType fill, TileType border = null) {
        for (int x = prism.x1; x <= prism.x2; x++) {
            for (int y = prism.y1; y <= prism.y2; y++) {
                for (int z = prism.z1; z <= prism.z2; z++) {

                    if (border && (x == prism.x1 || x == prism.x2 ||
                            y == prism.y1 || y == prism.y2 ||
                            z == prism.z1 || z == prism.z2)) {
                        tiles[x][y][z] = border
                    } else {
                        tiles[x][y][z] = fill
                    }
                }
            }
        }
    }

}