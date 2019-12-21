package de.steuer.buildffa.listener;

import de.steuer.buildffa.BuildFFA;
import de.steuer.buildffa.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() != GameMode.CREATIVE) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.CREATIVE) return;
        final Block block = e.getBlockPlaced();
        if(block.getType() == Material.SANDSTONE) {
            if(p.getLocation().getY() < LocationManager.getSpawnHeight()) {
                p.getInventory().setItem(8, (new PlayerJoinListener().createItem(Material.SANDSTONE, 64, 0, "§6Blöcke", "§7Verschwinden nach 5 Sekunden")));
                Bukkit.getScheduler().runTaskLater(BuildFFA.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        block.setType(Material.RED_SANDSTONE);

                        Bukkit.getScheduler().runTaskLater(BuildFFA.getInstance(), new Runnable() {
                            @Override
                            public void run() {
                                block.setType(Material.AIR);
                            }
                        }, 20*3);
                    }
                }, 30);
            } else {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(p.getGameMode() == GameMode.CREATIVE) return;
        if(e.getInventory() != p.getInventory()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getEntity();
        if(p.getLocation().getY() > LocationManager.getSpawnHeight()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Player p = (Player) e.getEntity();
        if(e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        } else if(e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            if(p.getLocation().getY() > LocationManager.getSpawnHeight()) {
                e.setCancelled(true);
            }
        } else if(p.getLocation().getY() > LocationManager.getSpawnHeight()) {
            e.setCancelled(true);
        }
    }
}
