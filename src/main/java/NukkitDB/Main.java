package NukkitDB;

import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        NukkitDB.createConnection(this);
    }

    @Override
    public void onDisable() {
        NukkitDB.closeConnection();
    }
}
