package de.steuer.buildffa.listener;

import de.steuer.buildffa.BuildFFA;
import de.steuer.buildffa.listener.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class KitListener implements Listener {

    public static List<Player> rod = new ArrayList<>();
    public static List<Player> bow = new ArrayList<>();
    public static List<Player> snowball = new ArrayList<>();
    public static List<Player> tank = new ArrayList<>();

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        try {
            if(p.getItemInHand() == null || p.getItemInHand().equals(Material.AIR)) return;
            if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                if(e.getItem().getType() == Material.CHEST) {
                    if(e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eKits §8│ §7Rechtsklick")) {
                        Inventory inv = Bukkit.createInventory(null, 27, "§eKits");
                        for(int i = 0; i < inv.getSize(); i++) {
                            inv.setItem(i, (new PlayerJoinListener().createItem(Material.STAINED_GLASS_PANE, 1, 7, " ", "")));
                        }
                        inv.setItem(10, (new PlayerJoinListener().createItem(Material.FISHING_ROD, 1, 0, "§8⇢ §3Rod", "§8✦ §7§l1x Angel")));
                        inv.setItem(12, (new PlayerJoinListener().createEnchItem(Material.BOW, 1, 0, "§8⇢ §3Sniper", "§8✦ §7§lKnockback Bogen", Enchantment.ARROW_KNOCKBACK, 2)));
                        inv.setItem(14, (new PlayerJoinListener().createItem(Material.SNOW_BALL, 1, 0, "§8⇢ §3Scheemann", "§8✦ §7§l16x Scheebälle")));
                        inv.setItem(16, (new PlayerJoinListener().createEnchItem(Material.CHAINMAIL_CHESTPLATE, 1, 0, "§8⇢ §3Tank", "§8✦ §7Protection 2", Enchantment.PROTECTION_ENVIRONMENTAL, 2)));

                        p.playSound(p.getLocation(), Sound.NOTE_BASS, 1, 1);
                        p.openInventory(inv);
                    }
                }
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onRodClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if(e.getCurrentItem().getType() == Material.FISHING_ROD) {
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8⇢ §3Rod")) {
                p.playSound(p.getLocation(), Sound.BURP, 1, 1);
                if(bow.contains(p)) {
                    bow.remove(p);
                } else if(tank.contains(p)) {
                    tank.remove(p);
                } else if(snowball.contains(p)) {
                    snowball.remove(p);
                } else if(rod.contains(p)) {
                    p.closeInventory();
                    p.sendMessage(BuildFFA.prefix + "§7Du hast bereits das §eROD §7Kit ausgewählt!");
                    return;
                }
                rod.add(p);
                p.closeInventory();
                p.sendMessage(BuildFFA.prefix + "§7Du hast das §eROD §7Kit ausgewählt!");
            }
        }
    }

    @EventHandler
    public void onBowClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if(e.getCurrentItem().getType() == Material.BOW) {
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8⇢ §3Sniper")) {
                p.playSound(p.getLocation(), Sound.BURP, 1, 1);
                if(tank.contains(p)) {
                    tank.remove(p);
                } else if(rod.contains(p)) {
                    rod.remove(p);
                } else if(snowball.contains(p)) {
                    snowball.remove(p);
                } else if(bow.contains(p)) {
                    p.closeInventory();
                    p.sendMessage(BuildFFA.prefix + "§7Du hast bereits das §eSNIPER §7Kit ausgewählt!");
                    return;
                }
                bow.add(p);
                p.closeInventory();
                p.sendMessage(BuildFFA.prefix + "§7Du hast das §eSNIPER §7Kit ausgewählt!");
            }
        }
    }

    @EventHandler
    public void onSnowClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if(e.getCurrentItem().getType() == Material.BOW) {
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8⇢ §3Schneemann")) {
                p.playSound(p.getLocation(), Sound.BURP, 1, 1);
                if(bow.contains(p)) {
                    bow.remove(p);
                } else if(rod.contains(p)) {
                    rod.remove(p);
                } else if(tank.contains(p)) {
                    tank.remove(p);
                } else if(snowball.contains(p)) {
                    p.closeInventory();
                    p.sendMessage(BuildFFA.prefix + "§7Du hast bereits das §eSCHNEEMANN §7Kit ausgewählt!");
                    return;
                }
                snowball.add(p);
                p.closeInventory();
                p.sendMessage(BuildFFA.prefix + "§7Du hast das §eSCHNEEMANN §7Kit ausgewählt!");
            }
        }
    }

    @EventHandler
    public void onTankClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
        if(e.getCurrentItem().getType() == Material.CHAINMAIL_CHESTPLATE) {
            if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8⇢ §3Tank")) {
                p.playSound(p.getLocation(), Sound.BURP, 1, 1);
                if(bow.contains(p)) {
                    bow.remove(p);
                } else if(rod.contains(p)) {
                    rod.remove(p);
                } else if(snowball.contains(p)) {
                    snowball.remove(p);
                } else if(tank.contains(p)) {
                    p.closeInventory();
                    p.sendMessage(BuildFFA.prefix + "§7Du hast bereits das §eTANK §7Kit ausgewählt!");
                    return;
                }
                tank.add(p);
                p.closeInventory();
                p.sendMessage(BuildFFA.prefix + "§7Du hast das §eTANK §7Kit ausgewählt!");
            }
        }
    }
}
