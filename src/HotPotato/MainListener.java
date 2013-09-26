package HotPotato;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class MainListener
  implements Listener
{
    public static int one;
    public static int two;
    public static int three;
    public static int four;
    
  @EventHandler
  public void playerjoin(PlayerJoinEvent e)
  {
    Player p = e.getPlayer();
    String pname = e.getPlayer().getName();
    p.setLevel(0);
    Main.playingplayers.add(pname);
    p.setScoreboard(ScoreboardManager.board);
    p.setExp(0.0F);
    
        for(PotionEffect effect : p.getActivePotionEffects())
        {
            p.removePotionEffect(effect.getType());
        }
    p.getInventory().clear();
    p.getInventory().setBoots(null);
    p.getInventory().setChestplate(null);
    p.getInventory().setLeggings(null);
    p.getInventory().setHelmet(null);
    World current = p.getWorld();
//    ////////////////////////////////////////////////////////////////////////
//     if (one < four){
//    double x = Double.parseDouble("-34.232");
//    double y = Double.parseDouble("70.00");
//    double z = Double.parseDouble("-92.77895");
//    Location Nuketown1 = new Location(current, x, y, z);
//    p.teleport(Nuketown1);    
//    one++;
//     }
//    ////////////////////////////////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////
//     else if (two < one){
//    double x2 = Double.parseDouble("-57.05");
//    double y2 = Double.parseDouble("64");
//    double z2 = Double.parseDouble("-138");
//    Location Nuketown2 = new Location(current, x2, y2, z2);
//    p.teleport(Nuketown2);    
//    two++;
//     }
//    ////////////////////////////////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////
//     else if (three < two){
//    double x3 = Double.parseDouble("105");
//    double y3 = Double.parseDouble("64");
//    double z3 = Double.parseDouble("-138");
//    Location Nuketown3 = new Location(current, x3, y3, z3);
//    p.teleport(Nuketown3);    
//    three++;
//    ////////////////////////////////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////
//    }else{
    double x4 = Double.parseDouble("-11.91096");
    double y4 = Double.parseDouble("41");
   double z4 = Double.parseDouble("63.37651");
   Location Nuketown4 = new Location(current, x4, y4, z4);
    p.teleport(Nuketown4);    
//    four++;
//    }
    ////////////////////////////////////////////////////////////////////////
    p.setGameMode(GameMode.ADVENTURE);
    if (Countdowns.lobbycountdown == 0) {
        e.setJoinMessage(null);
      p.sendMessage(Main.gamename + " game has started, so you can spectate!");
      p.setGameMode(GameMode.CREATIVE);
      Main.playingplayers.remove(pname);
      for (Player hide : Bukkit.getOnlinePlayers())
        hide.hidePlayer(p);
    }
    else if (Countdowns.lobbycountdown > 0) {
      e.setJoinMessage(Main.gamename + " " + pname + " has joined the game");    
    }
    ScoreboardManager.maxplayers.setScore(Main.playingplayers.size());
  }

  @EventHandler
  public void playerleave(PlayerQuitEvent e) {
    Player p = e.getPlayer();
    String pname = p.getName();
    e.setQuitMessage(null);
    Main.playingplayers.remove(pname);
    Main.TNTHolder.remove(pname);
    Main.DeadPlayers.remove(pname);
    if (Main.TNTHolder.isEmpty()) {
        if (Countdowns.gamecountdown == 0){
      Random ran = new Random();
      String TNTHolderName = Main.playingplayers.get(ran.nextInt(Main.playingplayers.size()));
      Player TNTHolder = Bukkit.getPlayer(TNTHolderName);
      Bukkit.broadcastMessage(Main.gamename + pname + " left the game!");
      Bukkit.broadcastMessage(Main.gamename + TNTHolderName + " is the new potato!");
      Bukkit.broadcastMessage(Main.gamename + " But dont worry, the timer has reset!");
      Bukkit.getScheduler().cancelTask(Countdowns.battytask);
      Countdowns.explosioncountdowntimer = 60;
      Countdowns.explosioncountdown();
        }
     }
  }

  @EventHandler
  public void noblockbreak(BlockBreakEvent e) {
    Player p = e.getPlayer();
    e.setCancelled(true);
  }

  @EventHandler
  public void ggsneak(PlayerToggleSneakEvent e){
      e.setCancelled(true);
  }
  
  @EventHandler
  public void blockplace(BlockPlaceEvent e) {
    e.setCancelled(true);
    if (e.getBlock().getTypeId() == 46) {
      ItemStack TNT = new ItemStack(Material.TNT, 1);
      e.getPlayer().getInventory().setItem(0, TNT);
    }
  }

  @EventHandler
  public void nodrop(InventoryClickEvent e) {
    e.setCancelled(true);
  }

  @EventHandler
  public void playerrespawn(PlayerRespawnEvent e)
  {
    Player p = e.getPlayer();
    if (Countdowns.lobbycountdown == 0)
    {
      p.sendMessage(Main.gamename + " unlucky on dying! Spectate the game now!");
      for (Player hide : Bukkit.getOnlinePlayers()) {
        hide.hidePlayer(p);
      }
    World current = p.getWorld();
    double x = Double.parseDouble("-2");
    double y = Double.parseDouble("2");
    double z = Double.parseDouble("-0");
    Location HotPotatoSpawn = new Location(current, x, y, z);
    p.teleport(HotPotatoSpawn);        
      p.setGameMode(GameMode.CREATIVE);
    }if(Main.playingplayers.size() == 1){
      for (Player hide : Bukkit.getOnlinePlayers()) {
        hide.showPlayer(p);
      }        
    }
  }
  
  @EventHandler
  public void DeathNo(EntityDamageEvent e){
      e.setCancelled(true);
  }
  
  @EventHandler
  public void playerdeath(PlayerDeathEvent e){
      e.getDrops().remove(new ItemStack(Material.TNT,1));
      e.setDeathMessage(null);
  }
  
  @EventHandler
  public void NOdrop(PlayerDropItemEvent e){
      e.setCancelled(true);
  }

  @EventHandler
  public void onPing(ServerListPingEvent event) {
      event.setMotd(ChatColor.RED + "HotPotato UK-2" + ChatColor.RESET + ChatColor.GREEN + "                                                    <<-- Join for Hot Potato!");
  }  
  
  
}