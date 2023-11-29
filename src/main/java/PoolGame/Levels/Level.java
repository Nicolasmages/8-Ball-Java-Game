package PoolGame.Levels;

import PoolGame.ConfigReader;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * The level interface
 */
public interface Level {
    /**
     * set the path of the config level
     * @return the path of file
     */
    public String getPath();

    /**
     * Read the config file and error catching
     * @return the configreader of the given path
     */
    public default ConfigReader getState() {
        try {
            return new ConfigReader(this.getPath(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (ConfigReader.ConfigKeyMissingException e) {
            throw new RuntimeException(e);
        }
    }


}
