package NukkitDB;

import cn.nukkit.utils.Config;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

class NukkitDB {

    private static MongoClient client = null;

    static void createConnection(Main plugin) {
        try {
            Config config = plugin.getConfig();
            String databaseString = config.getString("database");
            MongoClientURI clientURI = new MongoClientURI(config.getString("uri"));
            client = new MongoClient(clientURI);
            plugin.getLogger().info("Connected to Mongodb instance...");
        }catch (IllegalArgumentException e) {
            plugin.getLogger().error("The connection string is invalid. Connection strings must start with either 'mongodb://' or 'mongodb+srv://");
        }
    }

    static void closeConnection() {
        if (client != null) client.close();
    }

    public static Document query(String value, String fieldName, String database, String collection){
        return getCollection(getDatabase(database), collection).find(Filters.eq(fieldName, value)).first();
    }

    private static MongoDatabase getDatabase(String database) {
        return client.getDatabase(database);
    }

    private static MongoCollection<Document> getCollection(MongoDatabase database, String collection) {
        return database.getCollection(collection);
    }
}
