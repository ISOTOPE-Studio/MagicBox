package cc.isotopestudio.magicbox;

import cc.isotopestudio.magicbox.command.BoxCommand;
import cc.isotopestudio.magicbox.util.PluginFile;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MagicBox extends JavaPlugin {

    private static final String pluginName = "MagicBox";
    public static final String prefix = (new StringBuilder()).append(ChatColor.GOLD).append(ChatColor.BOLD).append("[")
            .append("MagicBox").append("]").append(ChatColor.RED).toString();

    public static MagicBox plugin;

    public static PluginFile data;

    @Override
    public void onEnable() {
        plugin = this;
        data = new PluginFile(this, "data.yml");

        this.getCommand("box").setExecutor(new BoxCommand());


        getLogger().info(pluginName + "成功加载!");
        getLogger().info(pluginName + "由ISOTOPE Studio制作!");
        getLogger().info("http://isotopestudio.cc");
    }

    public void onReload() {
        data.reload();
    }

    @Override
    public void onDisable() {
        getLogger().info(pluginName + "成功卸载!");
    }

}
