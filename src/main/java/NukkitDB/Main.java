package NukkitDB;

import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    private NukkitDB nukkitDB;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.nukkitDB = new NukkitDB();
        NukkitDB.createConnection(this);
    }

    @Override
    public void onDisable() {
        NukkitDB.closeConnection();
    }

    public NukkitDB get() {
        return nukkitDB;
    }
}
