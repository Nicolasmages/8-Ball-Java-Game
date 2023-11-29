package PoolGame.Observer;

/**
 * the interface of the cheat publisher
 */
public interface CheatPublisher {
    /**
     * Notify the subscriber and change their state
     * @param colour the colour of the ball user wants to remove
     */
    public void notifyCheat(String colour);
}
