package com.stewsters.climb.tile

import com.stewsters.climb.pathfinder.PathNode
import com.stewsters.util.Point3i

public class TileNode implements PathNode {

    World world
    Point3i point


    public TileNode(World world, int x, int y, int z) {
        this.world = world
        point = new Point3i(x, y, z)
    }

    @Override
    public float getDistance(PathNode other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y) + Math.abs(z - other.z)
    }

    @Override
    public List<PathNode> getNeighbors() {
        return world.getNode(point.vonNeumannNeighborhood3d())
    }

    @Override
    def getX() {
        return point.x
    }

    @Override
    def getY() {
        return point.y
    }

    @Override
    def getZ() {
        return point.z
    }

}
