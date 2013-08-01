package com.stewsters.climb.worldBuilder

import com.stewsters.climb.tile.TileType
import com.stewsters.climb.tile.World

//http://trystans.blogspot.com/2011/08/roguelike-tutorial-03-scrolling-through.html
public class RandomCaveBuilder {
    private int width;
    private int height;
    private int depth;
    private TileType[][][] tiles = null;

    public RandomCaveBuilder(int width, int height, int depth) {
        this.width = width
        this.height = height
        this.depth = depth
        this.tiles = new TileType[width][height][depth]
    }

    public World build() {
        return new World(tiles);
    }

    public RandomCaveBuilder makeCaves() {
        return randomizeTiles().smooth(4);
    }

    private RandomCaveBuilder randomizeTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < depth; z++) {
                    tiles[x][y][z] = Math.random() < 0.5 ? TileType.AIR : TileType.WALL;
                }
            }
        }
        return this;
    }

    private RandomCaveBuilder smooth(int times) {
        TileType[][][] tiles2 = new TileType[width][height][depth];
        for (int time = 0; time < times; time++) {

            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    for (int z = 0; z < depth; z++) {
                        int floors = 0;
                        int rocks = 0;

                        for (int ox = -1; ox < 2; ox++) {
                            for (int oy = -1; oy < 2; oy++) {
                                for (int oz = -1; oz < 2; oz++) {

                                    if (x + ox < 0 || x + ox >= width || y + oy < 0 || y + oy >= height || z + oz < 0 || z + oz >= depth)
                                        continue;

                                    if (tiles[x + ox][y + oy][z + oz] == TileType.AIR)
                                        floors++;
                                    else
                                        rocks++;
                                }
                            }
                        }
                        tiles2[x][y][z] = (floors >= rocks) ? TileType.AIR : TileType.WALL;
                    }
                }
            }
            tiles = tiles2;
        }
        return this;

    }
}