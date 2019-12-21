package de.steuer.buildffa.listener;

import de.steuer.buildffa.BuildFFA;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        if(KitListener.rod.contains(p)) {
            KitListener.rod.remove(p);
        } else if(KitListener.tank.contains(p)) {
            KitListener.tank.remove(p);
        } else if(KitListener.snowball.contains(p)) {
            KitListener.snowball.remove(p);
        } else if(KitListener.bow.contains(p)) {
            KitListener.bow.remove(p);
        }

        e.setQuitMessage(BuildFFA.prefix + "§8« §c" + p.getName() + "§7 hat das Spiel betreten!");
    }
}
