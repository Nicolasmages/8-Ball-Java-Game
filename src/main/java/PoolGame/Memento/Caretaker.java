package PoolGame.Memento;

import javafx.scene.Scene;

import java.util.ArrayList;

/**
 * The care take of memento of game state
 */
public class Caretaker {
    private ArrayList<Memento> mementoArrayList = new ArrayList<Memento>();
    private int size = 0;

    /**
     * Add game state as memento and add it to the arraylist
     * @param state the game current state
     */
    public void add(Memento state) {
        if (size == 1) {
            mementoArrayList.remove(0);
            System.out.println("Previous save has been removed!");
            size -= 1;
        }
        mementoArrayList.add(state);
        this.size += 1;
    }

    /**
     * @return the memento game state store in the arraylist
     */
    public Memento get() {

        if (mementoArrayList.size() == 0) {
            return null;
        } else {
            if (this.size == 1) {
                return mementoArrayList.get(0);
            }
        }

        return null;
    }
}
