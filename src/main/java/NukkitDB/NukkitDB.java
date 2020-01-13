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


    /**
     * @param database database name
     * @param collection collection name
     * @param querySearch value to search in database
     * @param field field in database to search
     * @return Document
     * */
    public static Document query(String querySearch, String field, String database, String collection) {
        return getCollection(getDatabase(database), collection).find(Filters.eq(field, querySearch)).first();
    }


    /**
     * @param document new document to create
     * @param database database name
     * @param collection collection name
     * */
    public static void insertDocument(Document document, String database, String collection) {
        getCollection(getDatabase(database), collection).insertOne(document);
    }


    /**
     * @apiNote update string value
     * @param querySearch value to search in database where to update
     * @param field field in database to search where to update
     * @param key key in document to update
     * @param value value to update in key
     * @param database database name
     * @param collection collection name
     * */
    public static void updateDocument(String querySearch, String field, String key, String value, String database, String collection) {
        getCollection(getDatabase(database), collection).updateOne(Filters.eq(field, querySearch), new Document("$set", new Document(key, value)));
    }


    /**
     * @apiNote update int value
     * @param querySearch value to search in database where to update
     * @param field field in database to search where to update
     * @param key key in document to update
     * @param value value to update in key
     * @param database database name
     * @param collection collection name
     * */
    public static void updateDocument(String querySearch, String field, String key, Integer value, String database, String collection) {
        getCollection(getDatabase(database), collection).updateOne(Filters.eq(field, querySearch), new Document("$set", new Document(key, value)));
    }


    private static MongoDatabase getDatabase(String database) {
        return client.getDatabase(database);
    }


    private static MongoCollection<Document> getCollection(MongoDatabase database, String collection) {
        return database.getCollection(collection);
    }
}
