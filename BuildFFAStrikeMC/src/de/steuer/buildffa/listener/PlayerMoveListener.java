package de.steuer.buildffa.listener;

import de.steuer.buildffa.utils.KitManager;
import de.steuer.buildffa.utils.LocationManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(KitListener.rod.contains(p)) {
            if(p.getLocation().getY() <= LocationManager.getSpawnHeight() && !p.getInventory().contains(Material.SANDSTONE)) {
                KitManager.setRodKit(p);
            }
        } else if(KitListener.tank.contains(p)) {
            if(p.getLocation().getY() <= LocationManager.getSpawnHeight() && !p.getInventory().contains(Material.SANDSTONE)) {
                KitManager.setTankKit(p);
            }
        } else if(KitListener.bow.contains(p)) {
            if(p.getLocation().getY() <= LocationManager.getSpawnHeight() && !p.getInventory().contains(Material.SANDSTONE)) {
                KitManager.setSniperKit(p);
            }
        } else if(KitListener.snowball.contains(p)) {
            if(p.getLocation().getY() <= LocationManager.getSpawnHeight() && !p.getInventory().contains(Material.SANDSTONE)) {
                KitManager.setSnowballKit(p);
            }
        } else {
            if(p.getLocation().getY() <= LocationManager.getSpawnHeight() && !p.getInventory().contains(Material.SANDSTONE)) {
                KitManager.setRodKit(p);
            }
        }
    }
}
