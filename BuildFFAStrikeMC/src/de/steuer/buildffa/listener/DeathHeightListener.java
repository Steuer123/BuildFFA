package de.steuer.buildffa.listener;

import de.steuer.buildffa.BuildFFA;
import de.steuer.buildffa.utils.LocationManager;
import de.steuer.buildffa.utils.StatsManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.SQLException;

public class DeathHeightListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) throws SQLException {
        Player p = e.getPlayer();
        Player k = p.getKiller();
        double y = p.getLocation().getY();
        if(y < LocationManager.getDeathHeight()) {
            p.setHealth(20);
            StatsManager.addDeath(p.getUniqueId().toString());
            p.playSound(p.getLocation(), Sound.IRONGOLEM_HIT, 1, 1);
            p.teleport(LocationManager.getSpawn());
            if(k != null && k != p) {
                StatsManager.addKill(k.getUniqueId().toString());
                k.sendMessage(BuildFFA.prefix + "§7Du hast §e" + p.getName() + "§7 getötet!");
                k.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 3, true), true);

                ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta meta = (SkullMeta) i.getItemMeta();
                meta.setOwner(p.getName());
                meta.setDisplayName("§eStats §8│ §7Rechtsklick");
                i.setItemMeta(meta);

                p.setFoodLevel(20);

                p.getInventory().clear();
                p.getInventory().setArmorContents(null);
                p.getInventory().setItem(3, i);
                p.getInventory().setItem(5,(new PlayerJoinListener().createItem(Material.CHEST, 1, 0, "§eKits §8│ §7Rechtsklick", " ")));

                p.sendMessage(BuildFFA.prefix + "§7Du wurdest von §e"+  k.getName() + "§7 getötet!");
            } else {
                p.sendMessage(BuildFFA.prefix + "§7Du bist gestorben!");

                ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta meta = (SkullMeta) i.getItemMeta();
                meta.setOwner(p.getName());
                meta.setDisplayName("§eStats §8│ §7Rechtsklick");
                i.setItemMeta(meta);

                p.getInventory().clear();
                p.getInventory().setArmorContents(null);
                p.getInventory().setItem(3, i);
                p.getInventory().setItem(5,(new PlayerJoinListener().createItem(Material.CHEST, 1, 0, "§eKits §8│ §7Rechtsklick", " ")));
            }
        }
    }
}
