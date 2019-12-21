package de.steuer.buildffa.utils;

import de.steuer.buildffa.BuildFFA;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class LocationManager {

    private static FileConfiguration cfg = BuildFFA.getInstance().getConfig();

    public static void setSpawn(Location loc) {
        String path = "Location.spawn.";
        cfg.set(path + "World", loc.getWorld().getName());
        cfg.set(path + "X", loc.getX());
        cfg.set(path + "Y", loc.getY());
        cfg.set(path + "Z", loc.getZ());
        cfg.set(path + "Yaw", loc.getYaw());
        cfg.set(path + "Pitch", loc.getPitch());
        BuildFFA.getInstance().saveConfig();
    }

    public static Location getSpawn() {
        return new Location(Bukkit.getWorld(cfg.getString("Location.spawn.World")), cfg.getDouble("Location.spawn.X"), cfg.getDouble("Location.spawn.Y"), cfg.getDouble("Location.spawn.Z"), (float) cfg.getDouble("Location.spawn.Y"), (float) cfg.getDouble("Location.spawn.Pitch"));
    }

    public static void setSpawnHeight(double y) {
        cfg.set("Spawnheight", y);
        BuildFFA.getInstance().saveConfig();
    }

    public static void setDeathHeight(double y) {
        cfg.set("Deathheight", y);
        BuildFFA.getInstance().saveConfig();
    }

    public static double getSpawnHeight() {
        return cfg.getDouble("Spawnheight");
    }

    public static double getDeathHeight() {
        return cfg.getDouble("Deathheight");
    }
}
