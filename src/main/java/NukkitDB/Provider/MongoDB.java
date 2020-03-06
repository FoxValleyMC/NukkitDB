package NukkitDB.Provider;

import NukkitDB.Main;
import cn.nukkit.utils.Config;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.lang.Nullable;
import org.bson.BsonRegularExpression;
import org.bson.BsonUndefined;
import org.bson.Document;
import org.bson.types.*;

import java.util.*;

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
     * Only this plugin should close the connection to databases internally
     */
    public void disConnect() {
        if (client != null) client.close();
    }

    /**
     * returns a database object
     * @return | MongoDatabase
     */
    public static MongoDatabase getDatabase() {
        return client.getDatabase(plugin.getConfig().getString("MongoDB-database"));
    }

    /**
     *
     * @param collection | collection string name
     * @return MongoCollection<org.bson.Document>
     */
    public static MongoCollection<Document> getCollection(String collection) {
        return getDatabase().getCollection(collection);
    }

    /**
     * returns all documents from a given collection.
     * Documents are converted into "key/value" pairs and stored in a single list.
     * @param collection | collection object to retrieve data
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getAllFromCollection(MongoCollection<Document> collection) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> objectMap = new HashMap<>();
        FindIterable<Document> documents = collection.find();
        for (Map<String, Object> document : documents) {
            mapList.add(document);
        }
        return mapList;
    }

    /**
     * returns a org.bson.Document converted into "key/value" pairs from provided "key/value" search terms.
     * @param collection | collection object of database to search
     * @param key | key to search
     * @param value | value to search
     * @return Map<String, Object>
     */
    public static Map<String, Object> getDocument(MongoCollection<Document> collection, String key, String value) {
        Document document = collection.find(Filters.eq(key, value)).first();
        Map<String, Object> documentMap = new HashMap<>();
        if (document != null) {
            for (Document.Entry<String, Object> entry : document.entrySet()) {
                documentMap.put(entry.getKey(), entry.getValue());
            }
            return documentMap;
        } else {
            return null;
        }
    }

    /**
     * Inserts one new document into the given collection.
     * cast all data values to type "Object".
     * @param documentMap | map with "key/value" pairs to insert
     * @param collection | collection object to insert into
     */
    public static void insertOne(Map<String, Object> documentMap, MongoCollection<Document> collection) {
        Document document = new Document();
        for (Map.Entry<String, Object> entry : documentMap.entrySet()) {
            document.append(entry.getKey(), entry.getValue());
        }
        collection.insertOne(document);
    }

    /**
     * updates "keyToUpdate" from document found from "keyToSearch"
     * @param collection | collection object in database to update
     * @param keyToSearch | document search key
     * @param value | old value
     * @param keyToUpdate | key to update
     * @param newValue | new value
     */
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, ArrayList<Object> newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, Binary newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, boolean newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, Code newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, Date newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, Decimal128 newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, double newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, int newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, long newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, MaxKey newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, MinKey newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, Object newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, ObjectId newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, BsonRegularExpression newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, String newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, Symbol newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, BSONTimestamp newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }
    public static void updateOne(MongoCollection<Document> collection, String keyToSearch, String value, String keyToUpdate, BsonUndefined newValue) {
        collection.updateOne(
                Filters.eq(keyToSearch, value),
                new Document("$set", new Document(keyToUpdate, newValue))
        );
    }

}
