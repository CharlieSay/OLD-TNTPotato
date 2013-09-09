package HotPotato;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager extends JavaPlugin implements Listener{
    
    public static org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static Score seconds;
    public static Score maxplayers;
    public static Scoreboard board;
    public static Score explosioncountdown;
    
    public static void Creation(){
        board = manager.getNewScoreboard();
        Team main = board.registerNewTeam("Players");
        Objective objective = board.registerNewObjective("test", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Stats");
        seconds = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Lobby Timer:")); //Get a fake offline player
        maxplayers = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.GREEN + "Alive Players:"));
        explosioncountdown = objective.getScore(Bukkit.getOfflinePlayer(ChatColor.RED + "Explosion:"));
    }

}
