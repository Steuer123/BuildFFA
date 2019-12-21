package de.steuer.buildffa.listener;

import de.steuer.buildffa.utils.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.SQLException;

public class StatsInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) throws SQLException {
        Player p = e.getPlayer();
        if(e.getItem().getType() == Material.SKULL_ITEM) {
            if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eStats §8│ §7Rechtsklick")) {
                Inventory inv = Bukkit.createInventory(null, 27, "§eStats §8| §a" + p.getName());

                ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta im = (SkullMeta) i.getItemMeta();
                im.setOwner(p.getName());
                im.setDisplayName("§eStats");
                i.setItemMeta(im);

                for(int ife = 0; ife < inv.getSize(); ife++) {
                    inv.setItem(ife, (new PlayerJoinListener().createItem(Material.STAINED_GLASS_PANE, 1, 7, " ", " ")));
                }
                inv.setItem(11, (new PlayerJoinListener().createItem(Material.GOLD_SWORD, 1, 0, "§cKills §8| §cTode", "§c" + StatsManager.getKills(p.getUniqueId().toString()) + "§8 | §c" + StatsManager.getDeaths(p.getUniqueId().toString()))));
                inv.setItem(13, i);
                inv.setItem(15, (new PlayerJoinListener().createItem(Material.GOLD_NUGGET, 1, 0, "§cKD", "§c" + StatsManager.getKD(p.getUniqueId().toString()))));
                p.openInventory(inv);
                p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
            }
        }
    }

}
