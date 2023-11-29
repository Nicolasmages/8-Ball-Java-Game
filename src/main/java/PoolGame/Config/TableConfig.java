package PoolGame.Config;

import PoolGame.Factory.ConfigFactory;
import PoolGame.Factory.PocketsConfigFactory;
import org.json.simple.JSONObject;

/** A config class for the table configuration */
public class TableConfig implements Configurable {
    private String colour;
    private double friction;
    private SizeConfig size;
    private PocketsConfig pocketsConfig;

    /**
     * Initialise a config for table using a JSONObject
     * @param obj A JSONObject containing required keys for table
     */
    public TableConfig(Object obj) {
        this.parseJSON(obj);
    }

    /**
     * Initialise a config for velocity using the provided values
     * @param colour The colour of the table as String
     * @param friction The friction of the table
     * @param sizeConf A size config instance for the size of the table
     * @param pockets the config instance of pockets
     */
    public TableConfig(String colour, double friction, SizeConfig sizeConf,
                       PocketsConfig pockets) {
        this.init(colour, friction, sizeConf, pockets);
    }

    private void init(String colour, double friction, SizeConfig sizeConf, PocketsConfig pockets) {
        if (!ConfigChecker.colourChecker(colour)) {
            throw new IllegalArgumentException(String.format("\"%s\" is not a valid colour.", colour));
        } else if (friction <= 0) {
            throw new IllegalArgumentException("Table friction must be at least 0.");
        } else if (friction >= 1) {
            throw new IllegalArgumentException("Table friction must be smaller than 1.");
        }
        this.colour = colour;
        this.friction = friction;
        this.size = sizeConf;
        this.pocketsConfig = pockets;
    }

    public Configurable parseJSON(Object obj) {
        JSONObject json = (JSONObject) obj;
        String colour = (String)json.get("colour");
        double friction = (double)json.get("friction");
        SizeConfig szConf = new SizeConfig(json.get("size"));
        ConfigFactory pocketConfigFactory = new PocketsConfigFactory();
        PocketsConfig pockets = (PocketsConfig) pocketConfigFactory.make(obj);
        this.init(colour, friction, szConf, pockets);
        return this;
    }



    /**
     * Get the colour of the table as defined in the config.
     * @return The colour of the table
     */
    public String getColour() {
        return this.colour;
    }

    /**
     * Get the friction of the table as defined in the config.
     * @return The friction of the table
     */
    public double getFriction() {
        return this.friction;
    }

    /**
     * Get the table size config instance.
     * @return The table size config instance
     */
    public SizeConfig getSizeConfig() {
        return this.size;
    }

    /**
     *
     * @return the pocketConfig from teh table config
     */
    public PocketsConfig getPocketsConfig() {
        return pocketsConfig;
    }
}
