package HotPotato;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import uk.co.shadycast.shadycontroller.Objects.SStatus;
import uk.co.shadycast.shadycontroller.ShadyController;

public class Main extends JavaPlugin {

    public static String gamename = "" + ChatColor.WHITE + "[" + ChatColor.AQUA + "Hot Potato" + ChatColor.WHITE + "]" + ChatColor.AQUA;
    public static Main instance;
    public static List<String> TNTHolder = new ArrayList();
    public static List<String> playingplayers = new ArrayList();
    public static List<String> DeadPlayers = new ArrayList();
    public static int mapNumber;

    @Override
    public void onEnable() {
        Random rand = new Random();
        mapNumber = 1 - rand.nextInt(2)*2;
        ScoreboardManager.Creation();
        instance = this;
        Bukkit.getLogger().log(Level.OFF, "Enabled");
        Countdowns.lobbycountdown();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new MainListener(), this);
        pm.registerEvents(new TNTTransfer(), this);
        pm.registerEvents(new LaunchPad(), this);
        ShadyController.getThisServer().setStatus(SStatus.Join);
    }
}