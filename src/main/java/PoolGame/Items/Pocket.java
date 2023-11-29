package PoolGame.Items;

import PoolGame.Drawable;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** The pocket of a pool table */
public class Pocket implements Drawable {
    
    /** The radius of the pocket */
    public final static double RADIUS = Ball.RADIUS + 5;
    /** The colour of the pocket */
    protected Color colour = Color.BLACK;
    /** The JavaFX shape of the pocket */
    protected Circle shape;

    /**
     * Initialise the pool table pocket with the provided value
     * @param posX The x coordinate position of the pocket
     * @param posY The y coordinate position of the pocket
     */
    public Pocket(double posX, double posY) {
        this.shape = new Circle(posX, posY, RADIUS, this.colour);
    }

    /**
     * Check if a point is in the pocket bounds
     * @param point A point to check.
     * @return True if the point is in the pocket bounds, false otherwise
     */
    public boolean isInPocket(Point2D point) {
        return this.shape.getBoundsInLocal().contains(point);
    }

    /**
     *
     * @return the node of the shape
     */
    public Node getNode() {
        return this.shape;
    }

    /**
     *
     * @param groupChildren The list of `Node` obtained from the JavaFX Group.
     */
    public void addToGroup(ObservableList<Node> groupChildren) {
        groupChildren.add(this.shape);
    }

    /**
     *
     * @param radius of the ball
     */
    public void setRadius(double radius) {
        this.shape.setRadius(radius);
    }



}
