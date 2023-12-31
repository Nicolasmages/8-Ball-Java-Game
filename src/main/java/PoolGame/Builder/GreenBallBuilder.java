package PoolGame.Builder;

import PoolGame.Items.Ball;
import PoolGame.Strategy.BallPocketStrategy;

/** Builder for green ball */
public class GreenBallBuilder extends BlueBallBuilder{

    private Ball ball;
    private Ball.BallType ballType = null;
    private BallPocketStrategy action = null;

    /** Initialise the builder and start a new build */
    public GreenBallBuilder() {
        this.reset();
    }

    /**
     * Initialise a builder with the ball type and action for the new build
     * @param type The ball type the builder will build
     * @param action The action that the ball have when it falls into a pocket
     */
    public GreenBallBuilder(Ball.BallType type, BallPocketStrategy action) {
        this.ballType = type;
        this.action = action;
        this.reset();
    }

    public void reset() {
        this.ball = new Ball();
        this.ball.setColour("green");
        if (ballType != null) {
            this.ball.setBallType(this.ballType);
        }
        if (this.action != null) {
            this.ball.setPocketAction(this.action);
        }
    }

    public void setXPos(double xPos) {
        this.ball.setInitialXPos(xPos);
    }

    public void setYPos(double yPos) {
        this.ball.setInitialYPos(yPos);
    }

    public void setXVel(double xVel) {
        this.ball.setInitialXVel(xVel);
    }

    public void setYVel(double yVel) {
        this.ball.setInitialYVel(yVel);
    }

    public void setMass(double mass) {
        this.ball.setMass(mass);
    }

    public void setBallType(Ball.BallType type) {
        this.ballType = type;
        this.ball.setBallType(type);
    }

    public void setPocketAction(BallPocketStrategy action) {
        this.action = action;
        this.ball.setPocketAction(action);
    }

    public Ball finaliseBuild() {
        Ball ball = this.ball;
        this.reset();
        return ball;
    }

    @Override
    public void setBallScore() {
        this.ball.setScore(3);
    }
}
