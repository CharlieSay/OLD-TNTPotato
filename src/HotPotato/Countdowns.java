package HotPotato;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Countdowns
{
  public static int lobbycountdown = 35;
  public static int gamecountdown = 11;
  public static List<String> playerlist = new ArrayList();
  public static String TNTName;
  public static int explosioncountdowntimer = 46;
  public static int cooldowntask;
  public static int explosionname;
  public static int cooldown;
  public static int lobbytask;
  public static int gametask;
  public static int battytask;

  public static void lobbycountdown()
  {
    lobbytask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
    {
      @Override
      public void run() {
        Countdowns.lobbycountdown -= 1;
        Player[] players = Bukkit.getServer().getOnlinePlayers();
        for (Player p : players){
            p.setFoodLevel(20);
            int seconds = lobbycountdown;
            ScoreboardManager.seconds.setScore(seconds);  
      }
        if (Countdowns.lobbycountdown < 6) {
          for (Player p : players)
            p.playSound(p.getLocation(), Sound.CLICK, 20.0F, 0.0F);
         if (Main.playingplayers.size() < 3){
              Bukkit.getScheduler().cancelTask(Countdowns.lobbytask);
              Bukkit.broadcastMessage(Main.gamename + "There was not enough people to start the game! Timer restarted!");
              lobbycountdown = 35;
              Countdowns.lobbycountdown();
          }
        }
        if (Countdowns.lobbycountdown == 0) {
          Countdowns.gamecountdown();
          Bukkit.getScheduler().cancelTask(Countdowns.lobbytask);
        }
      }
    }
    , 0L, 20L);
  }

  public static void gamecountdown()
  {
    gametask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
    {
         @Override
      public void run()
      {
        Countdowns.gamecountdown -= 1;
        int seconds = gamecountdown;
        ScoreboardManager.seconds.setScore(seconds);
        Player[] players = Bukkit.getServer().getOnlinePlayers();
        Bukkit.broadcastMessage(Main.gamename + " " + Countdowns.gamecountdown + " seconds left.");
        for (Player p : players) {
          p.setFoodLevel(20);
          p.playSound(p.getLocation(), Sound.ORB_PICKUP, 0.0F, 20.0F);
        }
        if (Countdowns.gamecountdown == 0) {
          Random r = new Random();
          String TNTStartername = Main.playingplayers.get(r.nextInt(Main.playingplayers.size()));
          Player TNTStarter = Bukkit.getPlayer(TNTStartername);
          Countdowns.TNTName = TNTStarter.getName();
          Main.TNTHolder.add(Countdowns.TNTName);
          ItemStack TNT = new ItemStack(Material.TNT, 1);
          ItemStack Headtnt = new ItemStack(Material.TNT, 1);
          ItemMeta tntmeta = Headtnt.getItemMeta();
          tntmeta.setDisplayName(ChatColor.RED  +"Hot Potato");
          tntmeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
          TNT.setItemMeta(tntmeta); 
          TNTStarter.getInventory().setHelmet(Headtnt);
          TNTStarter.getInventory().setItem(0, TNT);
          TNTStarter.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
          Bukkit.getScheduler().cancelTask(Countdowns.gametask);
          Bukkit.broadcastMessage(Main.gamename + ChatColor.GOLD + " " + Countdowns.TNTName + ChatColor.AQUA + " has the tnt first! Run!");
          Countdowns.gamecountdown = 0;
          Countdowns.explosioncountdown();
        }
      }
    }
    , 0L, 20L);
  }

  public static void explosioncountdown()
  {
    explosionname = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable()
    {
      @Override
      public void run() {
        String TNTPlayername = Main.TNTHolder.get(0);
        Player e = Bukkit.getPlayer(TNTPlayername);
        String ename = e.getName();
        Countdowns.explosioncountdowntimer -= 1;
        Player[] players = Bukkit.getServer().getOnlinePlayers();
        for (Player p : players) {
          p.setLevel(Countdowns.explosioncountdowntimer);
          p.setFoodLevel(20);
          ScoreboardManager.explosioncountdown.setScore(explosioncountdowntimer);
        }
        if (Countdowns.explosioncountdowntimer == 0) {
          Main.playingplayers.remove(ename);
          Main.TNTHolder.remove(ename);
          Main.DeadPlayers.add(ename);
          Main.playingplayers.remove(ename);
          Player[] players1 = Bukkit.getServer().getOnlinePlayers();
          for (Player p : players1) {
            p.playSound(p.getLocation(), Sound.EXPLODE, 20.0F, 0.0F);
          }

            e.getInventory().clear();
            e.setHealth(0.0D);
            Bukkit.broadcastMessage(Main.gamename + " BOOM Another one bites the dust");

            Random ran = new Random();
            String TNTHoldername = Main.playingplayers.get(ran.nextInt(Main.playingplayers.size()));
            Player TNTHolder = Bukkit.getPlayer(TNTHoldername);
            Main.TNTHolder.add(TNTHoldername);
            ItemStack TNT = new ItemStack(Material.TNT, 1);
            ItemStack Headtnt = new ItemStack(Material.TNT, 1);
            ItemMeta tntmeta = Headtnt.getItemMeta();
            tntmeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
            tntmeta.setDisplayName(ChatColor.RED + "Hot Potato");
            TNT.setItemMeta(tntmeta);
            TNTHolder.getInventory().setHelmet(Headtnt);
            TNTHolder.getInventory().setItem(0, TNT);
            ///////////////////////////////////////////////////////////////////////////////////////////////
            Bukkit.broadcastMessage(Main.gamename + " The new potato is " + TNTHoldername + " run away!");
            TNTHolder.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
            Bukkit.getScheduler().cancelTask(Countdowns.explosionname);
            ScoreboardManager.maxplayers.setScore(Main.playingplayers.size());
            Countdowns.explosioncountdowntimer = 46;
            Countdowns.explosioncountdown();
            if (Main.playingplayers.size() == 1) {
            for (Player other : Bukkit.getOnlinePlayers()) {
                Countdowns.showAll(other);
                    }
            String winnername = Main.TNTHolder.get(0);
            Player winner = Bukkit.getPlayer(winnername);
            winner.setGameMode(GameMode.CREATIVE);
            winner.getInventory().clear();
            winner.getInventory().setHelmet(null);
            Bukkit.broadcastMessage(Main.gamename + " The game has finished " + winnername + " has won!");
            Bukkit.getScheduler().cancelAllTasks();
          }
        }
      }
    }
    , 0L, 20L);
  }
  public static void showAll(Player player){
        for (Player other : Bukkit.getOnlinePlayers()) {
                  player.showPlayer(other);
             }
      }
}