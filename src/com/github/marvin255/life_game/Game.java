package com.github.marvin255.life_game;

import java.util.List;

class Game {
    private final World world;
    private final RuleSet ruleSet;
    private final WorldPrinter worldPrinter;
    private final int tickTime;

    public Game(World world, RuleSet ruleSet, WorldPrinter worldPrinter, int tickTime) {
        this.world = world;
        this.ruleSet = ruleSet;
        this.worldPrinter = worldPrinter;
        this.tickTime = tickTime;
    }

    public void run(List<WorldCoordinate> defaultWorldData) throws InterruptedException {
        this.world.clear();
        for (WorldCoordinate coordinate : defaultWorldData) {
            this.world.setCellStatus(coordinate, true);
        }

        this.worldPrinter.print(this.world);
        boolean canContinue;
        do {
            Thread.sleep(this.tickTime);
            canContinue = this.ruleSet.updateWorld(this.world);
            this.worldPrinter.print(this.world);
        } while (canContinue);
    }
}
