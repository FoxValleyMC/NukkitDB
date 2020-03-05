package NukkitDB.Provider;

import java.util.HashMap;
import java.util.Map;

public enum Provider {
    MongoDB(1),
    MySQL(2);

    int value;
    static Map<Integer, Provider> map = new HashMap<>();

    Provider(int value) {
        this.value = value;
    }

    static {
        for (Provider provider : Provider.values()) {
           map.put(provider.value, provider);
        }
    }

    public static Provider valueOf(int provider) {
        return  (Provider) map.get(provider);
    }

    public int getValue() {
        return value;
    }

}
