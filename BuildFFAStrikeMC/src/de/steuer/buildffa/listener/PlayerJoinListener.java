package de.steuer.buildffa.listener;

import de.steuer.buildffa.BuildFFA;
import de.steuer.buildffa.MySQL;
import de.steuer.buildffa.utils.LocationManager;
import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.SQLException;
import java.util.Arrays;

public class PlayerJoinListener implements Listener {

    public ItemStack createEnchItem(Material material, int anzahl, int shortid, String name, String lore, Enchantment ench, int lvl) {
        ItemStack i = new ItemStack(material, anzahl, (short) shortid);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(new String[] { lore }));
        im.spigot().setUnbreakable(true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(im);
        i.addUnsafeEnchantment(ench, lvl);
        return i;
    }

    public ItemStack createItem(Material material, int anzahl, int shortid, String name, String lore) {
        ItemStack i = new ItemStack(material, anzahl, (short) shortid);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(new String[] { lore }));
        im.spigot().setUnbreakable(true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(im);
        return i;
    }

    public ItemStack createLeatherArmor(Material material, String name, Color color) {
        ItemStack i = new ItemStack(material, 1);
        LeatherArmorMeta im = (LeatherArmorMeta) i.getItemMeta();
        im.setColor(color);
        im.setDisplayName(name);
        im.spigot().setUnbreakable(true);
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        i.setItemMeta(im);
        return i;
    }

    @EventHandler
    @Deprecated
    public void onJoin(PlayerJoinEvent e) throws SQLException {
        Player p = e.getPlayer();
        if(!MySQL.isRegistered(p.getUniqueId().toString())) {
            MySQL.createPlayer(p.getUniqueId().toString());
        }

        ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) i.getItemMeta();
        meta.setOwner(p.getName());
        meta.setDisplayName("§eStats §8│ §7Rechtsklick");
        i.setItemMeta(meta);

        p.setFoodLevel(20);
        e.setJoinMessage(BuildFFA.prefix + "§8» §a" + p.getName() + "§7 hat das Spiel betreten!");
        p.getInventory().clear();
        p.getInventory().setArmorContents(null);
        p.getInventory().setItem(3, i);
        p.getInventory().setItem(5, createItem(Material.CHEST, 1, 0, "§eKits §8│ §7Rechtsklick", " "));

        p.teleport(LocationManager.getSpawn());
        p.playEffect(p.getLocation().add(0.0D, 1.0D, 0.0D), Effect.ENDER_SIGNAL, 20);

    }
}
