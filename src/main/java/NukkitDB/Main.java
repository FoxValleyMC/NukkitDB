package NukkitDB;

import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase {

    private NukkitDB nukkitDB;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.nukkitDB = new NukkitDB();
        nukkitDB.createConnection(this);
    }

    @Override
    public void onDisable() {
        nukkitDB.closeConnection();
    }

    public NukkitDB get() {
        return nukkitDB;
    }
}
