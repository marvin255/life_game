package com.github.marvin255.life_game;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        World world = new World(20, 20);
        WorldPrinter printer = new WorldPrinter();
        RuleSet ruleSet = new RuleSet();
        Game game = new Game(world, ruleSet, printer, 1000);

        ArrayList<WorldCoordinate> defaultWorldData = new ArrayList<>();
        defaultWorldData.add(new WorldCoordinate(6, 5));
        defaultWorldData.add(new WorldCoordinate(7, 6));
        defaultWorldData.add(new WorldCoordinate(5, 7));
        defaultWorldData.add(new WorldCoordinate(6, 7));
        defaultWorldData.add(new WorldCoordinate(7, 7));

        game.run(defaultWorldData);
    }
}
