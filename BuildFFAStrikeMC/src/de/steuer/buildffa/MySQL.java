package de.steuer.buildffa;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;

public class MySQL {

    private static FileConfiguration cfg = BuildFFA.getInstance().getConfig();
    public static String host = cfg.getString("MySQL.host");
    public static String user = cfg.getString("MySQL.username");
    public static String password = cfg.getString("MySQL.password");
    public static String database = cfg.getString("MySQL.database");
    public static String port = cfg.getString("MySQL.port");

    private static Connection con;

    public static boolean isConnected() {
        return con != null;
    }

    public static void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, user, password);
                Bukkit.getConsoleSender().sendMessage("§aMySQL Connected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createTable() {
        try {
            PreparedStatement ps = getStatement("CREATE TABLE IF NOT EXISTS BFFAStats (UUID VARCHAR(100), KILLS VARCHAR(100), DEATHS VARCHAR(100))");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        if(isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage("§cMYSQL CLOSED");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement getStatement(String sql) {
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isRegistered(String uuid) {
        try {
            PreparedStatement ps = getStatement("SELECT * FROM BFFAStats WHERE UUID= '" + uuid +"'");
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getString("UUID") != null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid) throws SQLException {
        if(!isRegistered(uuid)) {
            PreparedStatement ps = getStatement("INSERT INTO BFFAStats (UUID, KILLS, DEATHS) VALUES (?, ?, ?)");
            ps.setString(1, uuid);
            ps.setString(2, "0");
            ps.setString(3, "0");
            ps.executeUpdate();
        }
    }
}
