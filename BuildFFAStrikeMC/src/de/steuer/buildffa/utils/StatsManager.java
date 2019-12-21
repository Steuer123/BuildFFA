package de.steuer.buildffa.utils;

import de.steuer.buildffa.MySQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsManager {

    public static Double getKD(String uuid) throws SQLException {
        if(getDeaths(uuid) == 0) {
            return Double.valueOf(getDeaths(uuid));
        } else {
            double kd = Double.valueOf(getKills(uuid) / getDeaths(uuid));
            kd = Double.valueOf(Math.round(100.0D * kd) / 10.0D);
            return kd;
        }
    }

    public static int getKills(String uuid) throws SQLException {
        Integer i = Integer.valueOf(0);
        if(MySQL.isRegistered(uuid)) {
            try {
                PreparedStatement ps = MySQL.getStatement("SELECT * FROM BFFAStats WHERE UUID = '" + uuid + "'");
                ResultSet rs = ps.executeQuery();
                if ((rs.next()) && (Integer.valueOf(rs.getInt("KILLS")) == null)) {}
                i = Integer.valueOf(rs.getInt("KILLS"));
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            MySQL.createPlayer(uuid);
            getKills(uuid);
        }
        return i;
    }

    public static int getDeaths(String uuid) throws SQLException {
        Integer i = Integer.valueOf(0);
        if(MySQL.isRegistered(uuid)) {
            try {
                PreparedStatement ps = MySQL.getStatement("SELECT * FROM BFFAStats WHERE UUID = '" + uuid + "'");
                ResultSet rs = ps.executeQuery();
                if ((rs.next()) && (Integer.valueOf(rs.getInt("DEATHS")) == null)) {}
                i = Integer.valueOf(rs.getInt("DEATHS"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            MySQL.createPlayer(uuid);
            getKills(uuid);
        }
        return i;
    }

    public static void setKills(String uuid, Integer kills) throws SQLException {
        if (MySQL.isRegistered(uuid))
        {
            PreparedStatement ps = MySQL.getStatement("UPDATE BFFAStats SET KILLS= '" + kills + "' WHERE UUID= '" + uuid + "'");
            ps.executeUpdate();
        }
        else
        {
            MySQL.createPlayer(uuid);
            setKills(uuid, kills);
        }
    }

    public static void setDeaths(String uuid, Integer deaths) throws SQLException {
        if (MySQL.isRegistered(uuid))
        {
            PreparedStatement ps = MySQL.getStatement("UPDATE BFFAStats SET DEATHS= '" + deaths + "' WHERE UUID= '" + uuid + "'");
            ps.executeUpdate();
        }
        else
        {
            MySQL.createPlayer(uuid);
            setDeaths(uuid, deaths);
        }
    }

    public static void addKill(String uuid) throws SQLException {
        Integer i = Integer.valueOf(1);
        if (MySQL.isRegistered(uuid))
        {
            setKills(uuid, Integer.valueOf(getKills(uuid) + i.intValue()));
        }
        else
        {
            MySQL.createPlayer(uuid);
            addKill(uuid);
        }
    }

    public static void addDeath(String uuid) throws SQLException {
        Integer i = Integer.valueOf(1);
        if (MySQL.isRegistered(uuid)) {
            setDeaths(uuid, Integer.valueOf(getDeaths(uuid) + i.intValue()));
        } else {
            MySQL.createPlayer(uuid);
            addDeath(uuid);
        }
    }
}
