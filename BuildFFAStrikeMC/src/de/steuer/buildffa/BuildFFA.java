package de.steuer.buildffa;

import de.steuer.buildffa.commands.SetupCMD;
import de.steuer.buildffa.commands.StatsCMD;
import de.steuer.buildffa.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildFFA extends JavaPlugin {

    private static BuildFFA instance;
    public static String prefix = "§6StrikeMC.de §8| §r";
    public static String noperms = prefix + "§7Dazu hast du keine Rechte!";
    public static String notfound = prefix + "§7Dieser Spieler wurde nicht gefunden!";
    public static String noplayer = prefix + "§7Du musst ein Spieler sein!";

    @Override
    public void onEnable() {
        instance = this;

        MySQL.connect();
        MySQL.createTable();

        loadConfig();
        registerEvents();

        this.getCommand("setup").setExecutor(new SetupCMD());
        this.getCommand("stats").setExecutor(new StatsCMD());

        startTime();

        Bukkit.getConsoleSender().sendMessage(prefix + "§7Das Plugin wurde gestartet!");
    }

    @Override
    public void onDisable() {
        for(Player all : Bukkit.getOnlinePlayers()) {
            Bukkit.dispatchCommand(all, "/hub");
        }
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new DeathHeightListener(), this);
        pm.registerEvents(new KitListener(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new PlayerMoveListener(), this);
        pm.registerEvents(new PlayerQuitListener(), this);
        pm.registerEvents(new ProtectionListener(), this);
        pm.registerEvents(new StatsInteractListener(), this);
    }

    public void startTime() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run() {

                try {

                    World world = Bukkit.getWorld("world");
                    world.setTime(1000);

                } catch (NullPointerException | IllegalArgumentException ignored) {

                }

            }
        }, 20L, 20*60);
    }

    public static BuildFFA getInstance() {
        return instance;
    }
}
