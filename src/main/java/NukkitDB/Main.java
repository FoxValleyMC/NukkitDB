package NukkitDB;

import NukkitDB.Provider.MongoDB;
import NukkitDB.Provider.Provider;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.List;
import java.util.Map;

public class Main extends PluginBase {

    MongoDB mongoDB;


    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        if (IsEnabled(Provider.MongoDB)) {
            mongoDB = new MongoDB(this);
            mongoDB.connect();
        }

        if (IsEnabled(Provider.MySQL)) {
            /// TODO - implement MySQL data provider
        }

    }

    @Override
    public void onDisable() {
        if (IsEnabled(Provider.MongoDB)) {
            mongoDB.disConnect();
        }

        if (IsEnabled(Provider.MySQL)) {
            /// TODO - implement MySQL data provider
        }
    }

    private boolean IsEnabled(Provider provider) {
        Config config = this.getConfig();

        switch (provider.name()) {
            case "MongoDB":
                return config.getBoolean("MongoDB");
            case "MySQL":
                return config.getBoolean("MySQL");
            default:
                return false;
        }

    }

}
