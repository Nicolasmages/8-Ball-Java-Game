package PoolGame.Factory;

import PoolGame.Config.Configurable;
import PoolGame.Config.PocketConfig;
import PoolGame.Config.PocketsConfig;
import PoolGame.Config.TableConfig;
import PoolGame.Items.Pocket;
import org.json.simple.JSONObject;

import java.util.ArrayList;

/** A config factory that will handle the `Pockets` section of the config */
public class PocketsConfigFactory implements ConfigFactory {


    @Override
    public Configurable make(Object jSONObject) {
        jSONObject = ((JSONObject)jSONObject).get("pockets");
        return new PocketsConfig(jSONObject);
    }
}
