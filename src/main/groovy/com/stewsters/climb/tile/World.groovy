package com.stewsters.climb.tile

import com.stewsters.climb.creature.Creature
import com.stewsters.util.RenderConfig
import com.stewsters.util.Point3i
import squidpony.squidcolor.SColor
import squidpony.squidcolor.SColorFactory

public class World {
    private TileType[][][] tiles
    private TileNode[][][] nodes
    public List<Creature> creatures;

    public int width
    public int height
    public int depth

    public World(TileType[][][] tiles) {
        this.tiles = tiles
        this.width = tiles.length
        this.height = tiles[0].length
        this.depth = tiles[0][0].length

        this.nodes = new TileNode[width][height][depth]
        width.times { x ->
            height.times { y ->
                depth.times { z ->
                    nodes[x][y][z] = new TileNode(this, x, y, z)
                }
            }
        }

        creatures = []
    }

    public TileType tile(int x, int y, int z) {
        if (x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
            return TileType.BOUNDS
        else
            return tiles[x][y][z]
    }

    public Creature creature(int x, int y, int z) {
        for (Creature c : creatures) {
            if (c.x == x && c.y == y && c.z == z)
                return c;
        }
        return null;
    }

    public char glyph(int x, int y, int z) {

        if (tile(x, y, z).isBlocked) {
            return tile(x, y, z).glyph
        } else if (tile(x, y, z - 1).isBlocked) { //floor
            return '+' as char
        } else if (tile(x, y, z - 2).isBlocked) {
            return '.' as char
        } else if (tile(x, y, z - 3).isBlocked) {
            return '.' as char
        } else {
            return ' ' as char
        }
    }

    private float getTint(double radius) {
        return (float) (0f + RenderConfig.lightTintPercentage * radius);//adjust tint based on distance
    }

    public SColor color(int x, int y, int z) {

        for (int i = 0; i < 4; i++) {
            if (tile(x, y, z - i).isBlocked) {
                return SColorFactory.blend(tile(x, y, z - i).color, SColor.BLACK, getTint(i));
            }
        }

        return SColor.BLACK
    }

    public void dig(int x, int y, int z) {
        if (tile(x, y, z).isDiggable)
            tiles[x][y][z] = TileType.AIR;
    }

    public void update() {
        List<Creature> toUpdate = new ArrayList<Creature>(creatures);
        for (Creature creature : toUpdate) {
            creature.update();
        }
    }

    public void addAtEmptyLocation(Creature creature) {
        int x = -1;
        int y = -1;
        int z = -1;

        while (tile(x, y, z).isBlocked || this.creature(x, y, z) != null) {
            x = (int) (Math.random() * width)
            y = (int) (Math.random() * height)
            z = (int) (Math.random() * depth)
        }

        creature.x = x;
        creature.y = y;
        creature.z = z;
        creatures.add(creature);
    }

    public void remove(Creature other) {
        creatures.remove(other);
    }

    public TileNode getNode(Point3i point) {
        if (point.x < 0 || point.x >= width || point.y < 0 || point.y >= height || point.z < 0 || point.z >= depth)
            return null
        else
            return nodes[point.x][point.y][point.z]
    }

    public List<Node> getNode(List<Point3i> points) {
        List<Node> output = new ArrayList<Node>()
        points.each {
            output << getNode(it)
        }
        return output
    }
}