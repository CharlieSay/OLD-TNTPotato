package HotPotato;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnPoint {

    public static void ToyBox(final Player p) {
        World current = p.getWorld();
        double x = Double.parseDouble("35.93389");
        double y = Double.parseDouble("116");
        double z = Double.parseDouble("-84.23766");
        Location spawn = new Location(current, x, y, z);
        p.teleport(spawn);
        Bukkit.getScheduler().runTaskLater((Main.instance), new Runnable() {
            @Override
            public void run() {
                p.sendMessage(Main.gamename + "-----------------------------");
                p.sendMessage(Main.gamename + " Map Name : " + ChatColor.GREEN + " ToyBox");
                p.sendMessage(Main.gamename + " Author : " + ChatColor.GREEN + " Team Pedro");
                p.sendMessage(Main.gamename + "------------------------------");
            }
        }, 150);


    }

    public static void SchoolMap(final Player p) {
        World current = p.getWorld();
        double x = Double.parseDouble("48.86199");
        double y = Double.parseDouble("116");
        double z = Double.parseDouble("-12.42755");
        Location spawn = new Location(current, x, y, z);
        p.teleport(spawn);
        Bukkit.getScheduler().runTaskLater((Main.instance), new Runnable() {
            @Override
            public void run() {
                p.sendMessage(Main.gamename + "------------------------------");
                p.sendMessage(Main.gamename + " Map Name : " + ChatColor.GREEN + " School");
                p.sendMessage(Main.gamename + " Author : " + ChatColor.GREEN + " Team Pedro");
                p.sendMessage(Main.gamename + "------------------------------");
            }
        }, 150);

    }
}
