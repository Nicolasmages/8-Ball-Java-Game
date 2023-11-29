package PoolGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import PoolGame.Builder.BallBuilderDirector;
import PoolGame.Config.BallConfig;
import PoolGame.Items.Ball;
import PoolGame.Items.PoolTable;
import PoolGame.Items.StopWatch;
import PoolGame.Observer.CheatPublisher;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/** The game class that runs the game */
public class Game implements CheatPublisher {
    private PoolTable table;
    private boolean shownWonText;
    private final Text winText = new Text(50, 50, "Win and Bye");

    /*
    Added Features
     */
    private StopWatch stopWatch;
    private Text timeText;

    private Text scoreText;

    private int totalGameScore = 0;

//    private final Text cheatTest = new Text("Cheat Activated");




    /**
     * Initialise the game with the provided config
     * @param config The config parser to load the config from
     */
    public Game(ConfigReader config) {
        this.setup(config);
    }

    /**
     * Make a hard copy of Game object
     * @param game the hard copy of the game
     */
    public Game (Game game) {
        this.table = new PoolTable(game.getPoolTable());
        this.totalGameScore = game.getTotalGameScore();
        this.stopWatch = new StopWatch(game.getStopWatch());
        this.setTimeText();
        this.setScoreText();
        this.setText(game);
        this.shownWonText = game.isShownWonText();
    }

    private void setup(ConfigReader config) {

        this.table = new PoolTable(config.getConfig().getTableConfig());
        List<BallConfig> ballsConf = config.getConfig().getBallsConfig().getBallConfigs();
        List<Ball> balls = new ArrayList<>();
        BallBuilderDirector builder = new BallBuilderDirector();
        builder.registerDefault();
        for (BallConfig ballConf: ballsConf) {
            Ball ball = builder.construct(ballConf);
            if (ball == null) {
                System.err.println("WARNING: Unknown ball, skipping...");
            } else {
                balls.add(ball);
            }
        }
        this.setWinText();
        this.table.setupBalls(balls);

        this.stopWatch = new StopWatch();

        this.totalGameScore = 0;
        this.setTimeText();


        this.setScoreText();
    }

    /**
     * Get the window dimension in the x-axis
     * @return The x-axis size of the window dimension
     */
    public double getWindowDimX() {
        return this.table.getDimX();
    }

    /**
     * Get the window dimension in the y-axis
     * @return The y-axis size of the window dimension
     */
    public double getWindowDimY() {
        return this.table.getDimY();
    }

    /**
     * Get the pool table associated with the game
     * @return The pool table instance of the game
     */
    public PoolTable getPoolTable() {
        return this.table;
    }

    /** Add all drawable object to the JavaFX group
     * @param root The JavaFX `Group` instance
    */
    public void addDrawables(Group root) {
        ObservableList<Node> groupChildren = root.getChildren();
        table.addToGroup(groupChildren);
        groupChildren.add(this.winText);

        groupChildren.add(this.timeText);
        this.timeText.setMouseTransparent(true);
        groupChildren.add(this.scoreText);
        this.scoreText.setMouseTransparent(true);

    }

    /** Reset the game */
    public void reset() {
        this.winText.setVisible(false);
        this.shownWonText = false;
        this.table.reset();
        this.totalGameScore = 0;
    }

    /** Code to execute every tick. */
    public void tick() {
        if (table.hasWon() && !this.shownWonText) {
            System.out.println(this.winText.getText());
            this.winText.setVisible(true);
            this.shownWonText = true;
            this.stopWatch.won();
            this.timeText.setVisible(false);
        }

        this.stopWatch.tick();
        timeText.setText(this.stopWatch.getTime());
        scoreText.setText(Integer.toString(totalGameScore));


        table.checkPocket(this);
        table.handleCollision();
        this.table.applyFrictionToBalls();
        for (Ball ball : this.table.getBalls()) {
            ball.move();
        }
    }

    /**
     * Update the game score
     * @param score the score of the ball
     */
    public void scoreUpdate(int score) {
        this.totalGameScore += score;
    }

    /**
     * @return the total score of the game
     */
    public int getTotalGameScore() {
        return totalGameScore;
    }

    /**
     *
     * @return the StopWatch of the game
     */
    public StopWatch getStopWatch() {
        return this.stopWatch;
    }

    /**
     *
     * @param table the table that game want to make hard copy of
     */
    public void setTable(PoolTable table) {
        this.table = new PoolTable(table);
    }

    /**
     *
     * @param totalGameScore set the new score that is given
     */
    public void setTotalGameScore(int totalGameScore) {
        this.totalGameScore = totalGameScore;
    }

    /**
     *
     * @param stopWatch set the stopwatch that is given
     */
    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = new StopWatch(stopWatch);
    }


    /**
     * set the time text in the game
     */
    public void setTimeText() {
        timeText = new Text();
        timeText.setVisible(true);
        timeText.setX(table.getDimX() / 10 );
        timeText.setY(table.getDimY() / 2);
        timeText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        timeText.setStrokeWidth(3);
        timeText.setFill(this.table.getColour());
        timeText.setStroke(Color.BLACK );
    }

    /**
     * set the score text in game
     */
    public void setScoreText() {
        this.scoreText = new Text(Integer.toString(this.totalGameScore));
        scoreText.setX((table.getDimX() / 5) * 4 );
        scoreText.setY(table.getDimY() / 2 );
        scoreText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        scoreText.setStrokeWidth(3);
        scoreText.setFill(this.table.getColour());
        scoreText.setStroke(Color.BLACK );
    }

    /**
     * Helper function for setting the win text in game
     */
    public void setWinText() {
        this.shownWonText = false;
        this.winText.setVisible(false);
        this.winText.setX(table.getDimX() / 10);
        this.winText.setY(table.getDimY()/2);
        this.winText.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        this.winText.setStrokeWidth(3);
        this.winText.setFill(this.table.getColour());
        this.winText.setStroke(Color.BLACK );
    }

    /**
     *
     * @return if the won text is shown or not
     */
    public boolean isShownWonText() {
        return shownWonText;
    }

    /**
     * Set the text in game used in hardcopy
     * @param game the hard copy of game
     */
    public void setText(Game game) {

        this.setWinText();
        this.winText.setVisible(game.isShownWonText());
        this.shownWonText = game.isShownWonText();

        this.setScoreText();
        this.setTimeText();
        this.timeText.setVisible(!game.isShownWonText());

    }

    public void notifyCheat(String colour) {
        List<Ball> balls = this.getPoolTable().getBalls();
        Iterator<Ball> iter = balls.iterator();
        Ball x;
        while (iter.hasNext()) {
            x = iter.next();
            if (x.getColour().equals(Color.valueOf(colour))) {
                if (!x.isDisabled()) {
                    x.updateCheatState();
                    this.scoreUpdate(x.getScore());
                    iter.remove();
                }
            }
        }
    }

}
