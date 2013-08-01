package com.stewsters.util


class MathUtils {

    private static Random random

    public static synchronized init() {
        if (!random)
            random = new Random()
    }

/**
 * @param low inclusive
 * @param high inclusive
 * @return
 */
    public static int getIntInRange(int low, int high) {
        if (low == high) return low
        if (!random) init()
        return random.nextInt(high + 1 - low) + low
    }

    public static int d(int high) {
        return getIntInRange(1, high)
    }


    public static float getFloatInRange(float low, float high) {
        if (low == high) return low
        if (!random) init()
        return (random.nextFloat() * (high - low)) + low
    }

    public static boolean getBoolean() {
        if (!random) init()
        return random.nextBoolean()
    }

    public static int manhattanDistance(int x1, int y1, int x2, int y2) {
        Math.abs(x1 - x2) + Math.abs(y1 - y2)
    }

    // Manhattan distance with diagonals
    public static int chebyshevDistance(int x1, int y1, int x2, int y2) {
        return Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2))
    }

    public static <E> E rand(ArrayList<E> source) {
        int id = getIntInRange(0, source.size() - 1)
        return source[id]
    }

    public static def randVal(def source) {
        int id = getIntInRange(0, source.size() - 1)
        return source[id]
    }

    public static int limit(int number, int low, int high) {
        return Math.max(low, Math.min(high, number))
    }

    public static float limit(float number, float low, float high) {
        return Math.max(low, Math.min(high, number))
    }

    public static double limit(double number, double low, double high) {
        return Math.max(low, Math.min(high, number))
    }

    public static double getGauss(double stdDeviation = 1) {
        if (!random) init()
        random.nextGaussian() * stdDeviation
    }


    public static String getChoice(Map choicesMap) {
        int totalChances = choicesMap.values().sum() //check that shit out, groovy ftw
        int dice = MathUtils.getIntInRange(0, totalChances)
        int runningTotal = 0
        for (def keyValue : choicesMap) {
            runningTotal += keyValue.value
            if (dice <= runningTotal)
                return keyValue.key
        }
        return null
    }
}
