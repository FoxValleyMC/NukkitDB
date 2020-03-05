package NukkitDB.Provider;

import NukkitDB.Main;
import cn.nukkit.utils.Config;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoDB {

    static MongoClient client = null;

    static Main plugin;

    public MongoDB(Main plugin) {
        MongoDB.plugin = plugin;
    }

    /**
     * Only this plugin should make connections to databases internally
     */
    public void connect() {
        try {
            Config config = plugin.getConfig();
            String connectionString = config.getString("MongoDB-uri");
            MongoClientURI clientURI = new MongoClientURI(connectionString);
            client = new MongoClient(clientURI);
        } catch (IllegalArgumentException e){
            plugin.getLogger().error("The connection string is invalid. Connection strings must start with either 'mongodb://' or 'mongodb+srv://");
        }
    }

    /**
     * Only this plugin should close connection to databases internally
     */
    public void disConnect() {
        if (client != null) client.close();
    }

}
