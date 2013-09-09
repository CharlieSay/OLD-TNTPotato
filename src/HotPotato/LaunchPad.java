package HotPotato;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class LaunchPad implements Listener{
    
    @EventHandler
    public void LaunchPad(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location playerLoc = player.getLocation();
        int ID = playerLoc.getWorld().getBlockAt(playerLoc).getRelative(0, -1, 0).getTypeId();
        int plate = playerLoc.getWorld().getBlockAt(playerLoc).getTypeId();
            if (ID == 152){
                if (plate == 70){ 
                player.setVelocity(player.getLocation().getDirection().multiply(3));
                player.setVelocity(new Vector(player.getVelocity().getX(), 1.0D, player.getVelocity().getZ()));
                player.playSound(player.getLocation(), Sound.CREEPER_HISS, 10, 0);
                player.playEffect(player.getLocation(), Effect.SMOKE, 5);
                player.sendMessage("dallas is fag");
            }
        }
    
  }
}
