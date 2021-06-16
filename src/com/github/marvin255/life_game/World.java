package com.github.marvin255.life_game;

import java.util.Arrays;

public class World {
    private final int width;
    private final int height;
    private final boolean[] cells;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new boolean[width * height];
        this.clear();
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean getCellStatus(WorldCoordinate coordinate) {
        int index = this.convertCoordinateToIndex(coordinate);
        return this.cells[index];
    }

    public void setCellStatus(WorldCoordinate coordinate, boolean status) {
        int index = this.convertCoordinateToIndex(coordinate);
        this.cells[index] = status;
    }

    public void clear() {
        Arrays.fill(this.cells, 0, this.width * this.height, false);
    }

    private int convertCoordinateToIndex(WorldCoordinate coordinate) {
        int normX = this.normalizeCoordinateValue(coordinate.getX(), this.width);
        int normY = this.normalizeCoordinateValue(coordinate.getY(), this.height);
        return normX + normY * this.width;
    }

    private int normalizeCoordinateValue(int value, int max) {
        if (value >= max) {
            return value % max;
        } else if (value < 0 && Math.abs(value) < max) {
            return max - Math.abs(value);
        } else if (value < 0 && Math.abs(value) >= max) {
            return max - (Math.abs(value) % max);
        } else {
            return value;
        }
    }
}
