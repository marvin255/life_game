package com.github.marvin255.life_game;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    public void run(List<WorldCoordinate> defaultWorldData) {
        this.prepareWorld(defaultWorldData);

        this.worldPrinter.print(this.world);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(
            () -> {
                boolean canContinue = this.ruleSet.updateWorld(this.world);
                this.worldPrinter.print(this.world);
                if (!canContinue) {
                    executor.shutdown();
                }
            },
            0,
            this.tickTime,
            TimeUnit.MILLISECONDS
        );
    }

    private void prepareWorld(List<WorldCoordinate> defaultWorldData) {
        this.world.clear();
        for (WorldCoordinate coordinate : defaultWorldData) {
            this.world.setCellStatus(coordinate, true);
        }
    }
}
