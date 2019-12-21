package de.steuer.buildffa.commands;

import de.steuer.buildffa.BuildFFA;
import de.steuer.buildffa.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender cs, Command command, String label, String[] args) {
        if(!(cs instanceof Player)) {
            cs.sendMessage(BuildFFA.noplayer);
            return true;
        }
        Player p = (Player) cs;
        if(!p.hasPermission("system.setup")) {
            p.sendMessage(BuildFFA.noperms);
            return true;
        }
        if(args.length != 1) {
            p.sendMessage(BuildFFA.prefix + "§7Benutze §8-> §e/setup set<SPAWN|DEATHHEIGHT|SPAWNHEIGHT>");
            return true;
        }
        if(args[0].equalsIgnoreCase("setspawn")) {
            LocationManager.setSpawn(p.getLocation());
            p.sendMessage(BuildFFA.prefix + "§7Du hast den §aSPAWN §7erfolgreich gesetzt!");
        } else if(args[0].equalsIgnoreCase("setdeathheight")) {
            LocationManager.setDeathHeight(p.getLocation().getY());
            p.sendMessage(BuildFFA.prefix + "§7Du hast die §aDEATHHEIGHT §7erfolgreich gesetzt!");
        } else if(args[0].equalsIgnoreCase("setspawnheight")) {
            LocationManager.setSpawnHeight(p.getLocation().getY());
            p.sendMessage(BuildFFA.prefix + "§7Du hast die §aSPAWNHEIGHT §7erfolgreich gesetzt!");
        } else {
            p.sendMessage(BuildFFA.prefix + "§7Benutze §8-> §e/setup set<SPAWN|DEATHHEIGHT|SPAWNHEIGHT>");
        }


        return false;
    }
}
