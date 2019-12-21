package de.steuer.buildffa.commands;

import de.steuer.buildffa.BuildFFA;
import de.steuer.buildffa.utils.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class StatsCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if(!(cs instanceof Player)) {
            cs.sendMessage(BuildFFA.noplayer);
            return true;
        }
        Player p = (Player) cs;
        if(args.length == 0) {
            p.sendMessage("§e§l§m----------§aBuildFFA§e§l§m----------");
            p.sendMessage(" ");
            p.sendMessage("§6Name §8-> §c" + p.getName());
            try {
                p.sendMessage("§6Kills §8| §6Tode §8-> §c" + StatsManager.getKills(p.getUniqueId().toString()) + "§8 | §c" + StatsManager.getDeaths(p.getUniqueId().toString()));
                p.sendMessage("§6KD §8-> §c" + StatsManager.getKD(p.getUniqueId().toString()));
                p.sendMessage("  ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(args.length == 1) {
            Player t = Bukkit.getPlayer(args[0]);
            if(t == null) {
                p.sendMessage(BuildFFA.notfound);
                return true;
            }
            p.sendMessage("§e§l§m----------§aBuildFFA§e§l§m----------");
            p.sendMessage(" ");
            p.sendMessage("§6Name §8-> §c" + t.getName());
            try {
                p.sendMessage("§6Kills §8| §6Tode §8-> §c" + StatsManager.getKills(t.getUniqueId().toString()) + "§8 | §c" + StatsManager.getDeaths(t.getUniqueId().toString()));
                p.sendMessage("§6KD §8-> §c" + StatsManager.getKD(t.getUniqueId().toString()));
                p.sendMessage("  ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            p.sendMessage(BuildFFA.prefix + "§7Benutze §8-> §e/stats §8|| §e/stats <SPIELER>");
        }


        return false;
    }
}
