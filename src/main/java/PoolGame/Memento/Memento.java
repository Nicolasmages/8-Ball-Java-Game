package PoolGame.Memento;

import PoolGame.Game;

/**
 * the memento of the game state
 */
public class Memento {
    private Game snapshot;

    /**
     * Save the game state as snapshot
     * @param snapshot save the game as snapshot in the memento
     */
    public Memento(Game snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * return the snapshot stat as game
     * @return Game return the save state
     */
    public Game getSnapshot() {
        return this.snapshot;
    }
}
