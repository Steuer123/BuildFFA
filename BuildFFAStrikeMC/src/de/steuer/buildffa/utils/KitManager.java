package de.steuer.buildffa.utils;

import de.steuer.buildffa.listener.PlayerJoinListener;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class KitManager {

    public static void setRodKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, (new PlayerJoinListener().createEnchItem(Material.GOLD_SWORD, 1, 0, "§eSchwert", "§7Schärfe III", Enchantment.DAMAGE_ALL, 3)));
        p.getInventory().setItem(1, (new PlayerJoinListener().createEnchItem(Material.STICK, 1, 0, "§bStick", "§7Rückstoss I", Enchantment.KNOCKBACK, 1)));
        p.getInventory().setItem(2, (new PlayerJoinListener().createItem(Material.FISHING_ROD, 1, 0, "§cRod", "")));
        p.getInventory().setItem(7, (new PlayerJoinListener().createItem(Material.ENDER_PEARL, 1, 0, "§bEnderperle", "")));
        p.getInventory().setItem(8, (new PlayerJoinListener().createItem(Material.SANDSTONE, 64, 0, "§6Blöcke", "§7Verschwinden nach 5 Sekunden")));
        p.getInventory().setBoots((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_BOOTS, "§bSchuhe", Color.RED)));
        p.getInventory().setLeggings((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_LEGGINGS, "§bHose", Color.RED)));
        p.getInventory().setChestplate((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_CHESTPLATE, "§bBrustplatte", Color.RED)));
        p.getInventory().setHelmet((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_HELMET, "§bHelm", Color.RED)));
    }

    public static void setSnowballKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, (new PlayerJoinListener().createEnchItem(Material.GOLD_SWORD, 1, 0, "§eSchwert", "§7Schärfe III", Enchantment.DAMAGE_ALL, 3)));
        p.getInventory().setItem(1, (new PlayerJoinListener().createEnchItem(Material.STICK, 1, 0, "§bStick", "§7Rückstoss I", Enchantment.KNOCKBACK, 1)));
        p.getInventory().setItem(2, (new PlayerJoinListener().createItem(Material.SNOW_BALL, 16, 0, "§cSchneebälle", "")));
        p.getInventory().setItem(7, (new PlayerJoinListener().createItem(Material.ENDER_PEARL, 1, 0, "§bEnderperle", "")));
        p.getInventory().setItem(8, (new PlayerJoinListener().createItem(Material.SANDSTONE, 64, 0, "§6Blöcke", "§7Verschwinden nach 5 Sekunden")));
        p.getInventory().setBoots((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_BOOTS, "§bSchuhe", Color.RED)));
        p.getInventory().setLeggings((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_LEGGINGS, "§bHose", Color.RED)));
        p.getInventory().setChestplate((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_CHESTPLATE, "§bBrustplatte", Color.RED)));
        p.getInventory().setHelmet((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_HELMET, "§bHelm", Color.RED)));
    }

    public static void setSniperKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, (new PlayerJoinListener().createEnchItem(Material.GOLD_SWORD, 1, 0, "§eSchwert", "§7Schärfe III", Enchantment.DAMAGE_ALL, 3)));
        p.getInventory().setItem(1, (new PlayerJoinListener().createEnchItem(Material.STICK, 1, 0, "§bStick", "§7Rückstoss I", Enchantment.KNOCKBACK, 1)));
        p.getInventory().setItem(2, (new PlayerJoinListener().createEnchItem(Material.BOW, 1, 0, "§cSniper", "§7Punch II", Enchantment.ARROW_KNOCKBACK, 2)));
        p.getInventory().setItem(7, (new PlayerJoinListener().createItem(Material.ENDER_PEARL, 1, 0, "§bEnderperle", "")));
        p.getInventory().setItem(8, (new PlayerJoinListener().createItem(Material.SANDSTONE, 64, 0, "§6Blöcke", "§7Verschwinden nach 5 Sekunden")));
        p.getInventory().setItem(9, (new PlayerJoinListener().createItem(Material.ARROW, 16, 0, "§bPfeile", "")));
        p.getInventory().setBoots((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_BOOTS, "§bSchuhe", Color.RED)));
        p.getInventory().setLeggings((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_LEGGINGS, "§bHose", Color.RED)));
        p.getInventory().setChestplate((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_CHESTPLATE, "§bBrustplatte", Color.RED)));
        p.getInventory().setHelmet((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_HELMET, "§bHelm", Color.RED)));
    }

    public static void setTankKit(Player p) {
        p.getInventory().clear();
        p.getInventory().setItem(0, (new PlayerJoinListener().createEnchItem(Material.GOLD_SWORD, 1, 0, "§eSchwert", "§7Schärfe III", Enchantment.DAMAGE_ALL, 3)));
        p.getInventory().setItem(1, (new PlayerJoinListener().createEnchItem(Material.STICK, 1, 0, "§bStick", "§7Rückstoss I", Enchantment.KNOCKBACK, 1)));
        p.getInventory().setItem(7, (new PlayerJoinListener().createItem(Material.ENDER_PEARL, 1, 0, "§bEnderperle", "")));
        p.getInventory().setItem(8, (new PlayerJoinListener().createItem(Material.SANDSTONE, 64, 0, "§6Blöcke", "§7Verschwinden nach 5 Sekunden")));
        p.getInventory().setBoots((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_BOOTS, "§bSchuhe", Color.RED)));
        p.getInventory().setLeggings((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_LEGGINGS, "§bHose", Color.RED)));
        p.getInventory().setChestplate((new PlayerJoinListener().createEnchItem(Material.CHAINMAIL_CHESTPLATE, 1, 0, "§bBrustplatte", "§7Schutz II", Enchantment.PROTECTION_ENVIRONMENTAL, 2)));
        p.getInventory().setHelmet((new PlayerJoinListener().createLeatherArmor(Material.LEATHER_HELMET, "§bHelm", Color.RED)));
    }
}
