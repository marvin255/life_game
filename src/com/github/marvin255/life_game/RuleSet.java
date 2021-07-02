package com.github.marvin255.life_game;

import java.util.ArrayList;

class RuleSet {
    public boolean updateWorld(World world) {
        boolean hasLiveCells = false;
        ArrayList<WorldCoordinate> setTrue = new ArrayList<>();
        ArrayList<WorldCoordinate> setFalse = new ArrayList<>();

        for (int x = 0; x < world.getWidth(); x++) {
            for (int y = 0; y < world.getHeight(); y++) {
                WorldCoordinate coordinate = new WorldCoordinate(x, y);
                boolean currentStatus = world.getCellStatus(coordinate);
                boolean newStatus = this.calculateNewCellStatus(coordinate, world);
                if (!hasLiveCells && newStatus) {
                    hasLiveCells = true;
                }
                if (newStatus == currentStatus) {
                    continue;
                }
                if (newStatus) {
                    setTrue.add(coordinate);
                } else {
                    setFalse.add(coordinate);
                }
            }
        }

        for (WorldCoordinate setTrueCoordinate : setTrue) {
            world.setCellStatus(setTrueCoordinate, true);
        }

        for (WorldCoordinate setFalseCoordinate : setFalse) {
            world.setCellStatus(setFalseCoordinate, false);
        }

        return hasLiveCells;
    }

    private boolean calculateNewCellStatus(WorldCoordinate coordinate, World world) {
        boolean isAlive = world.getCellStatus(coordinate);

        WorldCoordinate[] neighbours = {
                new WorldCoordinate(coordinate.getX() - 1, coordinate.getY() - 1),
                new WorldCoordinate(coordinate.getX() - 1, coordinate.getY()),
                new WorldCoordinate(coordinate.getX() - 1, coordinate.getY() + 1),
                new WorldCoordinate(coordinate.getX(), coordinate.getY() - 1),
                new WorldCoordinate(coordinate.getX(), coordinate.getY() + 1),
                new WorldCoordinate(coordinate.getX() + 1, coordinate.getY() - 1),
                new WorldCoordinate(coordinate.getX() + 1, coordinate.getY()),
                new WorldCoordinate(coordinate.getX() + 1, coordinate.getY() + 1),
        };

        int liveNeighbours = 0;
        for (WorldCoordinate neighbour : neighbours) {
            if (world.getCellStatus(neighbour)) {
                liveNeighbours++;
            }
        }

        return (!isAlive && liveNeighbours == 3) || (isAlive && liveNeighbours > 1 && liveNeighbours < 4);
    }
}
