package me.cutzuu.zuusTweaks;

import me.cutzuu.zuusTweaks.listeners.features;
import me.cutzuu.zuusTweaks.listeners.server_spawn_stuff;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class main extends JavaPlugin implements Listener
 {
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new features(), this);
        this.getServer().getPluginManager().registerEvents(new server_spawn_stuff(), this);
        saveDefaultConfig();
        loadConfiguration();

        (new BukkitRunnable()
        {
            public void run()
            {
                if (Global.configSpeedBoost)
                {
                    // 0.2 is normal speed.
                    // 0.6 is a good spot. Any higher and it's busted for the player.
                    for(Player p : Bukkit.getOnlinePlayers())
                    {
                        int cord = Global.configServerSpawnSize;
                        World.Environment environment = p.getWorld().getEnvironment();
                        World world = p.getWorld();
                        if (Global.spawnWorldList.contains(world.getName()))
                        {
                            if (environment == World.Environment.NORMAL)
                            {
                                if (p.getX() > -cord && p.getX() < cord)
                                {
                                    if (p.getZ() > -cord && p.getZ() < cord)
                                    {
                                        if (p.getGameMode() != GameMode.SURVIVAL) p.setWalkSpeed(0.2F);
                                        else if (p.isDead()) p.setWalkSpeed(0.2F);
                                        else p.setWalkSpeed(0.6F);
                                    }
                                    else p.setWalkSpeed(0.2F);
                                }
                                else p.setWalkSpeed(0.2F);
                            }
                            else p.setWalkSpeed(0.2F);
                        }
                        else p.setWalkSpeed(0.2F);
                    }
                }
            }
        }).runTaskTimer(this, 0L, 60L);
    }

    public static class Global
    {
        public static boolean configDropEnderChest;
        public static boolean configInstaKillWither;
        public static boolean configUnbreakableAnvils;
        public static boolean configUnbreakableItems;
        public static boolean configLimitedTrees;
        public static int configAntiLavaCastHeight;
        public static int configServerSpawnSize;
        public static boolean configBlockLiquidFlow;
        public static boolean configBlockManipulation;
        public static boolean configSpeedBoost;
        public static boolean configBlockExplosions;
        public static boolean configBlockPlayerDamage;
        public static boolean configBlockPlayerHunger;
        public static boolean configBlockHurtingMobs;
        public static boolean configBlockHurtingNamedMobs;
        public static boolean configBlockMonsterAgro;

        public static Set<String> spawnWorldList;
    }

    private void loadConfiguration()
    {
        Global.configDropEnderChest = this.getConfig().getBoolean("DroppableEnderChest");
        Global.configInstaKillWither = this.getConfig().getBoolean("InstaKillWither");
        Global.configUnbreakableAnvils = this.getConfig().getBoolean("UnbreakableAnvils");
        Global.configUnbreakableItems = this.getConfig().getBoolean("UnbreakableItems");
        Global.configLimitedTrees = this.getConfig().getBoolean("LimitedTrees");
        Global.configAntiLavaCastHeight = this.getConfig().getInt("AntiLavaCasting");
        // server spawn stuff
        Global.configServerSpawnSize = this.getConfig().getInt("ServerSpawnSize");
        Global.configBlockLiquidFlow = this.getConfig().getBoolean("BlockLiquidFlow");
        Global.configBlockManipulation = this.getConfig().getBoolean("BlockManipulation");
        Global.configSpeedBoost = this.getConfig().getBoolean("SpeedBoost");
        Global.configBlockExplosions = this.getConfig().getBoolean("BlockExplosions");
        Global.configBlockPlayerDamage = this.getConfig().getBoolean("BlockPlayerDamage");
        Global.configBlockPlayerHunger = this.getConfig().getBoolean("BlockPlayerHunger");
        Global.configBlockHurtingMobs = this.getConfig().getBoolean("BlockHurtingMobs");
        Global.configBlockHurtingNamedMobs = this.getConfig().getBoolean("BlockHurtingNamedMobs");
        Global.configBlockMonsterAgro = this.getConfig().getBoolean("BlockMonsterAgro");

        loadWorldLists();
    }

    private void loadWorldLists()
    {
        Set<String> listedWorlds = new HashSet<>();
        FileConfiguration config = getConfig();
        List<String> worldList = config.getStringList("WorldNames");

        for (String WorldName : worldList)
        {
            try
            {
                listedWorlds.add((WorldName));
            } catch (IllegalArgumentException e)
            {
                getLogger().warning("Invalid World Name in config: " + WorldName);
            }
        }
        Global.spawnWorldList = listedWorlds;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args)
    {
        if (args.length == 0)
        {
            sender.sendMessage("§eUsage: §6/ztweaks reload");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload"))
        {
            if (sender.hasPermission("zuustweaks.reload"))
            {
                reloadConfig();
                loadConfiguration();

                sender.sendMessage("§7[§6ZuusTweaks§7] §aConfig reloaded.");
            } else {
                sender.sendMessage("§cYou don't have permission to do that.");
            }
            return true;
        }
        sender.sendMessage("§cUnknown subcommand.");
        return true;
    }

    @Override
    public void onDisable()
    {
        // Plugin shutdown logic
    }
}
