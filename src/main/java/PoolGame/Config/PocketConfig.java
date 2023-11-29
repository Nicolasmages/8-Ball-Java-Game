package PoolGame.Config;

import org.json.simple.JSONObject;

/** A config class that will contain the pocket configuration */
public class PocketConfig implements Configurable{
    private PositionConfig position;
    private double radius;

    /**
     *
     * @param obj to process the obj and obtain the information of the pocket
     */
    public PocketConfig(Object obj) {
        this.parseJSON(obj);
    }

    /**
     *
     * @param posConf the position config
     * @param radius the radius of the pocket
     */
    public PocketConfig(PositionConfig posConf, double radius) {
        this.init(posConf, radius);
    }

    /**
     * Initialise the pocket with given config attribute
     * @param posCof
     * @param radius
     */
    private void init(PositionConfig posCof, Double radius) {
        this.position = posCof;
        this.radius = radius;
    }


    @Override
    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        PositionConfig positionConfig = new PositionConfig(json.get("position"));
        double radius = (double) json.get("radius");
        this.init(positionConfig, radius);
        return null;
    }

    /**
     *
     * @return the radius in the config object
     */
    public double getRadius() {
        return radius;
    }

    /**
     *
     * @return the position config from the config object
     */
    public PositionConfig getPosition() {
        return position;
    }
}
