package NukkitDB;

import cn.nukkit.utils.Config;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

class NukkitDB {
    private static MongoClient client = null;
    private static MongoDatabase database = null;

    static void createConnection(Main plugin) {
        try {
            Config config = plugin.getConfig();
            String databaseString = config.getString("database");
            MongoClientURI clientURI = new MongoClientURI(config.getString("uri"));
            client = new MongoClient(clientURI);
            database = client.getDatabase(databaseString);
            plugin.getLogger().info("Connected to Mongodb instance...");
        }catch (IllegalArgumentException e) {
            plugin.getLogger().error("The connection string is invalid. Connection strings must start with either 'mongodb://' or 'mongodb+srv://");
        }
    }

    static void closeConnection() {
        if (client != null) client.close();
    }
}
