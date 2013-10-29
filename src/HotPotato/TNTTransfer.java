package HotPotato;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TNTTransfer
        implements Listener {

    public static String tntname;
    private String victimname;
    public static int Cooldowntask;
    public static List<Player> Cooldown = new ArrayList();

    @EventHandler
    public void playerDamagePlayer(EntityDamageByEntityEvent event) {
        if ((event.getDamager() instanceof Player)) {
            event.setCancelled(true);
        }
        final Player damager = (Player) event.getDamager();
        tntname = damager.getName();
        final Player victim = (Player) event.getEntity();
        victimname = victim.getName();
        ItemStack is = damager.getItemInHand();
        ////////////////////////////////////////
        if (Cooldown.contains(victim) && (Cooldown.contains(damager))) {
            damager.sendMessage(Main.gamename + " You just hit them find someone else!");
        } else {
            ////////////////////////////////////////
            if (is.getTypeId() == 0) {
                event.setCancelled(true);
            } else if (is.getTypeId() == 46) {
                damager.removePotionEffect(PotionEffectType.SPEED);
                Main.TNTHolder.remove(tntname);
                Main.TNTHolder.add(victimname);
                Cooldown.add(victim);
                Cooldown.add(damager);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Main.instance), new Runnable() {
                    @Override
                    public void run() {
                        Cooldown.remove(victim);
                        Cooldown.remove(damager);
                    }
                }, 60);

                ItemStack TNT = new ItemStack(Material.TNT, 1);
                ItemStack Headtnt = new ItemStack(Material.TNT, 1);
                ItemMeta tntmeta = Headtnt.getItemMeta();
                tntmeta.setDisplayName(ChatColor.RED + "Hot Potato");
                tntmeta.addEnchant(Enchantment.KNOCKBACK, 2, true);
                TNT.setItemMeta(tntmeta);
                damager.getInventory().remove(is);
                damager.getInventory().setHelmet(null);
                victim.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2));
                victim.playSound(victim.getLocation(), Sound.FUSE, 10, 0);
                Bukkit.broadcastMessage(Main.gamename + " " + victimname + " is now the TNT! Run from them");
                Countdowns.gamecountdown = Countdowns.gamecountdown + 5;
                victim.getInventory().setItem(0, TNT);
                victim.getInventory().setHelmet(Headtnt);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void notntdrop(PlayerInteractEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void notntpickup(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }
}