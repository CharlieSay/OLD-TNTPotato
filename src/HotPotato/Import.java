package HotPotato;

import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;


public class Import {

    public static void Start(Main pl){
        FileConfiguration config = pl.getConfig();
        Main.Spawn = stringToLocation(config.getString("TNT.Lobby"));
        
        Set<String> Maps = config.getConfigurationSection("TNT.Maps").getKeys(false);
        String[] Mapss = Maps.toArray(new String[0]);
        
        for(String m:Mapss){
            Location Spawn = stringToLocation(config.getString("TNT.Maps."+m+".Spawn"));
            String Name = config.getString("TNT.Maps."+m+".Name");
            String Creator = config.getString("TNT.Maps."+m+".Creator");
            PMap mp = new PMap(Spawn,Name,Creator);
            Main.Maps.add(mp);
        }
    }
        
    public static Location stringToLocation(String s) {
        String[] ls = s.split(",");
        World w = Bukkit.getWorld(ls[0]);
        double X = Double.parseDouble(ls[1]);
        double Y = Double.parseDouble(ls[2]);
        double Z = Double.parseDouble(ls[3]);
        float yaw = Float.parseFloat(ls[4]);
        float pitch = Float.parseFloat(ls[5]);

        return new Location(w, X, Y, Z,yaw,pitch);
    }
}