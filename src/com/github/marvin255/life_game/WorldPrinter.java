package com.github.marvin255.life_game;

public class WorldPrinter {
    public void print(World world) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        String delimiter = " ";
        String liveCell = "o";
        String deadCell = "-";

        for (int y = 0; y < world.getHeight(); y++) {
            StringBuilder line = new StringBuilder();
            for (int x = 0; x < world.getWidth(); x++) {
                WorldCoordinate coordinate = new WorldCoordinate(x, y);
                boolean status = world.getCellStatus(coordinate);
                line.append(delimiter).append(status ? liveCell : deadCell).append(delimiter);
            }
            System.out.println(line);
        }
    }
}
