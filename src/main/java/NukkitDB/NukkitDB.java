package NukkitDB;

import cn.nukkit.utils.Config;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class NukkitDB {

    private static MongoClient client = null;

    void createConnection(Main plugin) {
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

    void closeConnection() {
        if (client != null) client.close();
    }

    public Document query(String value, String fieldName, String database, String collection){
        return getCollection(getDatabase(database), collection).find(Filters.eq(fieldName, value)).first();
    }

    public void insertDocument(Document document,String database, String collection) {
        getCollection(getDatabase(database), collection).insertOne(document);
    }

    public void updateDocument(String term, String query, String key, Integer value, String database, String collection) {
        getCollection(getDatabase(database), collection).updateOne(Filters.eq(term, query), new Document("$set", new Document(key, value)));
    }

    private MongoDatabase getDatabase(String database) {
        return client.getDatabase(database);
    }

    private MongoCollection<Document> getCollection(MongoDatabase database, String collection) {
        return database.getCollection(collection);
    }
}
