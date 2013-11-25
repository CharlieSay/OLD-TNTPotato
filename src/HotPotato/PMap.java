package HotPotato;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;


public class PMap {
    private Location spawn;
    private String Name;
    private String Creator;
    
    public PMap(Location Spawn,String Name,String Creator)
    {this.spawn = Spawn; this.Name = Name; this.Creator = Creator;}
    
    public Location getSpawn(){return this.spawn;}
    public String getName(){return this.Name;}
    public String getCreator(){return this.Creator;}
    
    public void tellMap(){
                Bukkit.broadcastMessage(Main.gamename + "-----------------------------");
                Bukkit.broadcastMessage(Main.gamename + " Map Name : " + ChatColor.GREEN + getName());
                Bukkit.broadcastMessage(Main.gamename + " Author : " + ChatColor.GREEN + getCreator());
                Bukkit.broadcastMessage(Main.gamename + "------------------------------");
    }
    public void tellMap(Player p){
                p.sendMessage(Main.gamename + "-----------------------------");
                p.sendMessage(Main.gamename + " Map Name : " + ChatColor.GREEN + getName());
                p.sendMessage(Main.gamename + " Author : " + ChatColor.GREEN + getCreator());
                p.sendMessage(Main.gamename + "------------------------------");
    }
    

}