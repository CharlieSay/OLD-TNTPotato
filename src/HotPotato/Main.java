package HotPotato;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
  public static String gamename = "" + ChatColor.WHITE + "[" + ChatColor.AQUA + "Hot Potato" + ChatColor.WHITE + "]" + ChatColor.AQUA;
  public static Main instance;
  public static List<String> TNTHolder = new ArrayList();
  public static List<String> playingplayers = new ArrayList();
  public static List<String> DeadPlayers = new ArrayList();
    @Override
  public void onEnable()
  {
    ScoreboardManager.Creation();
    instance = this;
    Bukkit.getLogger().log(Level.OFF, "Enabled");
    Countdowns.lobbycountdown();
    Bukkit.getPluginManager().registerEvents(new MainListener(), this);
    Bukkit.getPluginManager().registerEvents(new TNTTransfer(), this);
    Bukkit.getPluginManager().registerEvents(new LaunchPad(), this);
  }
   @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    Player p = (Player)sender;
    if ((commandLabel.equalsIgnoreCase("stats")) || (commandLabel.equalsIgnoreCase("stat"))) {
      p.sendMessage(gamename + "No stats.");
    }
    return false;
  }
}