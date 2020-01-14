[![HitCount](http://hits.dwyl.io/TimelessMC/NukkitDB.svg)](http://hits.dwyl.io/TimelessMC/NukkitDB)

<img src="https://i.imgur.com/RIWMp43.png" alt="icon" width="100px" />

# Important!
This plugin requires MongoDB java drivers which conveniently has been provided within the `/libs` directory ***OF THIS REPO***

# Installation
<img src="https://imgur.com/QmG58Yz.png" alt="image"/>

- Create a `libs` directory in your server root. (i.e) `C://users/yourname/desktop/nukkitX-server/libs`... It should be alongside your `run.bat` file  

- place `bson-3.11.0.jar`, `mongodb-driver-3.11.0.jar`, and `mongodb-driver-core-3.11.0.jar` inside the `/libs` you just created

- Edit your `run.bat` and write:
```batch
java -cp YOUR_NUKKIT_JAR.jar;libs/* cn.nukkit.Nukkit
```
This command will load the mongoDB java drivers into the servers main package

# Api Usage
 - create two `new` properties within your `config.yml`
 - It is recommended to create an additional class within your plugin to manage all NukkitDB methods
 - All of NukkitDB methods are static as shown below in `newClass.java`!
```yaml
# config.yml
# ----------

database: databaseName
collection: collectionName
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
    public static Document query() {
        String database = Main.getInstance().getConfig().getString("database");
        String collection = Main.getInstance().getConfig().getString("collection");
        return NukkitDB.query(key, fieldName, database, collection);
    }

    // inserts a new document to database
    public static void insertDocument(Document document) {
        NukkitDB.insertDocument(document, plugin.getConfig().getString("database"), plugin.getConfig().getString("collection"));
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
public class YourClass {

    public void yourMethod(Player player) {

        String uuid = player.getUniqueId().toString();
        Document document = new Document();
        document.append("uuid", uuid);
        DatabaseHandler.insertDocument(document);
        // you can add as many fields as you want with the '.append()' operator
        // .append("field", value);        

    }
}
```

# credits
Icon made by <a href="https://www.flaticon.com/authors/good-ware" title="Good Ware">Good Ware</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>