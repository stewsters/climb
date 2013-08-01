package com.stewsters.climb.pathfinder


class AStarSearch {

    public static LinkedList<PathNode> getPath(PathNode start, PathNode goal) {

        //PriorityQueue
        Queue<PathNode> open = new LinkedList<>()
//        def open = []
        HashSet<PathNode> closed = new HashMap<>()
        HashMap<PathNode, PathNode> cameFrom = [:]

        HashMap<PathNode, Float> gScore = [:]
        HashMap<PathNode, Float> fScore = [:]

        open.push(start)
        while (!open.isEmpty()) {
            PathNode current = open.poll()

            if (current == goal) {
                return reconstructPath(cameFrom, goal)
            }

            closed.add(current)
            for (PathNode neighbor : current.getNeighbors() - null) {
                float tenativeGScore = gScore[current] ?: 0f + current.getDistance(neighbor)
                if (closed.contains(neighbor)) {
                    if (tenativeGScore >= gScore[neighbor])
                        continue
                }
                if (!open.contains(neighbor) || tenativeGScore < gScore[neighbor]) {
                    cameFrom[neighbor] = current
                    gScore[neighbor] = tenativeGScore
                    fScore[neighbor] = gScore[neighbor] + neighbor.getDistance(goal)
                    if (!open.contains(neighbor)) {
                        open.add(neighbor)
                    }
                }
            }
        }
        return null
    }

    private static LinkedList<PathNode> reconstructPath(LinkedHashMap cameFrom, PathNode currentNode) {
        if (cameFrom.containsKey(currentNode)) {
            return reconstructPath(cameFrom, cameFrom[currentNode])
        } else {
            LinkedList<PathNode> list = new LinkedList<>()
            list.push(currentNode)
            return list
        }

    }
}
