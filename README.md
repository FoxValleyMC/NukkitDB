<img src="https://i.imgur.com/RIWMp43.png" alt="icon" width="100px" />

# Important!
This plugin requires MongoDB java drivers which conveniently has been provided within the `/libs` directory ***OF THIS REPO***

# Installation
 - Drop into plugins folder
 - Start and stop your server again
 - Edit newly generated config.yml
 ```yaml
uri: "YOUR_ URI_STRING"
```

# Api Usage
 - create two `new` properties within your `config.yml`
 - It is recommended to create an additional class within your plugin to manage all NukkitDB methods
 - All of NukkitDB methods are static as shown below in `newClass.java`!
```yaml
# config.yml
# ----------
database: "databaseName"
collection: "collectionName"
```
```java
// Main.java
// ----------

public class Main extends PluginBase {

    private static Main instance;
    
    @override
    public void onEnable() {

        Main.instance = this;

        // try to get NukkitDB plugin in your main class 'onEnable' method
        try {
            this.getServer().getPluginManager().getPlugin("NukkitDB");
        } catch (NullPointerException e) {
            this.getServer().getPluginManager().disablePlugin(this);
        }

    }

    // plugins main instance
    public static Main getInstance() {
        return instance;
    }   
}
```
```java
// NewClass.java (i.e.) DatabaseHandler.java
// -----------------------------------------

public class DatabaseHandler {
     
    // query document from database
    public static Map<String, Object> query() {
        String database = Main.getInstance().getConfig().getString("database");
        String collection = Main.getInstance().getConfig().getString("collection");
        return NukkitDB.query(key, fieldName, database, collection);
    }

    // inserts a new document to database
    public static void insertDocument(Map<String, Object> objectMap) {
        NukkitDB.insertDocument(objectMap, plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
    }

    // updates a document where field is equal to query and sets key to value
    // key is another field within the found document being search via the field property
    // set key to a string value
    public static updateDocument(String query, String field, String key, String value) {
        NukkitDB.updateDocument(query, field, key, value, Main.getInstance().getConfig().getString("database"), Main.getInstance().getConfig().getString("collection"));
    }

    // updates a document where field is equal to query and sets key to value
    // key is another field within the found document being search via the field property
    // set key to an integer value
    public static updateDocument(String query, String field, String key, Integer value) {
        NukkitDB.updateDocument(query, field, key, value, Main.getInstance().getConfig().getString("database"), Main.getInstance().getConfig().getString("collection"));
    }
}
```
You can create new documents to insert into database as follows:
```java
import java.util.HashMap;public class YourClass {

    public void yourMethod(Player player) {

        String uuid = player.getUniqueId().toString();
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("uuid", uuid);
        DatabaseHandler.insertDocument(objectMap);
        // you can add as many fields as you want with the '.put()' operator
        // .put("field", value);        

    }
}
```

# credits [![HitCount](http://hits.dwyl.io/TimelessMC/NukkitDB.svg)](http://hits.dwyl.io/TimelessMC/NukkitDB)
Icon made by <a href="https://www.flaticon.com/authors/good-ware" title="Good Ware">Good Ware</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>


