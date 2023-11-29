package PoolGame.Config;

import PoolGame.Items.Pocket;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;
/** A config class that will contain the list of pockets configuration */
public class PocketsConfig implements Configurable{
    private ArrayList<PocketConfig> pockets;

    /**
     *
     * @param jSONObject of the pocket
     */
    public PocketsConfig(Object jSONObject) {
        this.parseJSON(jSONObject);
    }

    /**
     *
     * @param pockets of the table
     */
    public PocketsConfig(ArrayList<PocketConfig> pockets) {
        this.init(pockets);
    }

    private void init(ArrayList<PocketConfig> pockets) {this.pockets = pockets;}

    /**
     * Initialise a config for the pockets list using a JONObject
     * @param obj Either a JSONObject or JSONArray to be parsed and initialised
     * into the appropriate class.
     * @return the Config pockets
     */
    @Override
    public Configurable parseJSON(Object obj) {
        ArrayList<PocketConfig> list = new ArrayList<PocketConfig>();
        JSONArray json = (JSONArray) obj;

        for (Object p: json) {
            list.add(new PocketConfig(p));
        }
        this.init(list);
        return this;
    }

    /**
     *
     * @return the Arraylist of pocketconfig
     */
    public ArrayList<PocketConfig> getPocketConfigs() {return this.pockets;}


}






