package me.cutzuu.zuusTweaks.listeners;

import me.cutzuu.zuusTweaks.main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.damage.DamageType;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class server_spawn_stuff implements Listener
{
    // Can place item frames
    // TNT can break spawn blocks



    // Anti-Liquid Flow at Spawn
    @EventHandler
    public void noFlow(BlockFromToEvent e)
    {
        if (main.Global.configBlockLiquidFlow)
        {
            Location location = e.getBlock().getLocation();
            World world = e.getBlock().getWorld();
            if (main.Global.spawnWorldList.contains(world.getName()))
            {
                int cord = main.Global.configServerSpawnSize + 5;
                if (location.getX() > -cord && location.getX() < cord)
                {
                    if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                }
            }
        }
    }

    // Anti-Place at Spawn
    @EventHandler
    public void noPlace(BlockPlaceEvent e)
    {
        if (!e.getPlayer().hasPermission("zuustweaks.bypass"))
        {
            if (main.Global.configBlockManipulation)
            {
                Location location = e.getBlock().getLocation();
                World world = e.getBlock().getWorld();
                if (main.Global.spawnWorldList.contains(world.getName()))
                {
                    int cord = main.Global.configServerSpawnSize;
                    if (location.getX() > -cord && location.getX() < cord)
                    {
                        if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                    }
                }
            }

        }
    }

    // Cant Prime TNT at Spawn
    @EventHandler
    public void noTNTPriming(BlockIgniteEvent e)
    {
        if (main.Global.configBlockExplosions)
        {
            Location location = e.getBlock().getLocation();
            World world = e.getBlock().getWorld();
            if (main.Global.spawnWorldList.contains(world.getName()))
            {
                int cord = main.Global.configServerSpawnSize;
                if (location.getX() > -cord && location.getX() < cord)
                {
                    if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                }
            }
        }
    }

    // Snow Cant Melt, Turtle Eggs cant Break, Fire cant Fade or Spread at Spawn
    @EventHandler
    public void noFading(BlockFadeEvent e)
    {
        if (main.Global.configBlockManipulation)
        {
            Location location = e.getBlock().getLocation();
            World world = e.getBlock().getWorld();
            if (main.Global.spawnWorldList.contains(world.getName()))
            {
                int cord = main.Global.configServerSpawnSize;
                if (location.getX() > -cord && location.getX() < cord)
                {
                    if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                }
            }
        }
    }

    // Anti-Break at Spawn
    @EventHandler
    public void noBreak(PlayerInteractEvent e)
    {
        if (!e.getPlayer().hasPermission("zuustweaks.bypass"))
        {
            if (main.Global.configBlockManipulation)
            {
                Location location = e.getPlayer().getLocation();
                World world = e.getPlayer().getWorld();
                if (main.Global.spawnWorldList.contains(world.getName()))
                {
                    int cord = main.Global.configServerSpawnSize;
                    if (location.getX() > -cord && location.getX() < cord)
                    {
                        if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                    }
                }
            }
        }
    }

    // Anti-Break at Spawn
    @EventHandler
    public void noBreak(BlockBreakEvent e)
    {
        if (!e.getPlayer().hasPermission("zuustweaks.bypass"))
        {
            if (main.Global.configBlockManipulation)
            {
                Location location = e.getBlock().getLocation();
                World world = e.getBlock().getWorld();
                if (main.Global.spawnWorldList.contains(world.getName()))
                {
                    int cord = main.Global.configServerSpawnSize;
                    if (location.getX() > -cord && location.getX() < cord)
                    {
                        if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                    }
                }
            }
        }
    }

    //Prevents things like Item Frames or Paintings from destruction if they're in the ProtectedEntities List.
    @EventHandler
    public void hangingEntityCheck(HangingBreakEvent e)
    {
        if (e.getCause() == HangingBreakEvent.RemoveCause.ENTITY)
        {
            if (main.Global.configBlockManipulation)
            {
                Location location = e.getEntity().getLocation();
                World world = e.getEntity().getWorld();
                if (main.Global.spawnWorldList.contains(world.getName()))
                {
                    int cord = main.Global.configServerSpawnSize;
                    if (location.getX() > -cord && location.getX() < cord)
                    {
                        if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e)
    {
        if (main.Global.configBlockExplosions)
        {
            Location location = e.getEntity().getLocation();
            World world = e.getEntity().getWorld();
            if (main.Global.spawnWorldList.contains(world.getName()))
            {
                int cord = main.Global.configServerSpawnSize;
                if (location.getX() > -cord && location.getX() < cord)
                {
                    if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void blockBoom(BlockExplodeEvent e)
    {
        if (main.Global.configBlockExplosions)
        {
            Location location = e.getBlock().getLocation();
            World world = e.getBlock().getWorld();
            if (main.Global.spawnWorldList.contains(world.getName()))
            {
                int cord = main.Global.configServerSpawnSize;
                if (location.getX() > -cord && location.getX() < cord)
                {
                    if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void blockHunger(EntityExhaustionEvent e)
    {
        if (main.Global.configBlockPlayerHunger)
        {
            Location location = e.getEntity().getLocation();
            World world = e.getEntity().getWorld();
            if (main.Global.spawnWorldList.contains(world.getName()))
            {
                int cord = main.Global.configServerSpawnSize;
                if (location.getX() > -cord && location.getX() < cord)
                {
                    if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void blockMobTargeting(EntityTargetEvent e)
    {
        if (main.Global.configBlockMonsterAgro)
        {
            Location location = e.getEntity().getLocation();
            World world = e.getEntity().getWorld();
            if (main.Global.spawnWorldList.contains(world.getName()))
            {
                int cord = main.Global.configServerSpawnSize;
                if (location.getX() > -cord && location.getX() < cord)
                {
                    if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void entityPain(EntityDamageEvent e)
    {
        Location location = e.getEntity().getLocation();
        int cord = main.Global.configServerSpawnSize;
        if (location.getX() > -cord && location.getX() < cord)
        {
            if (location.getZ() > -cord && location.getZ() < cord)
            {
                if (e.getDamageSource() == DamageType.PLAYER_ATTACK)
                {
                    if(e.getDamageSource().getCausingEntity() instanceof Player player && !player.hasPermission("zuustweaks.bypass"))
                    {
                        if (e.getEntity() instanceof Player && main.Global.configBlockPlayerDamage) cancelTheEvent(e);
                        else if (e.getEntity() instanceof Creature)
                        {
                            if (e.getEntity().customName() != null && main.Global.configBlockHurtingNamedMobs) cancelTheEvent(e);
                            else if (e.getEntity().customName() == null && main.Global.configBlockHurtingMobs) cancelTheEvent(e);
                        }
                    }
                }
                else
                {
                    if (e.getEntity() instanceof Player && main.Global.configBlockPlayerDamage) cancelTheEvent(e);
                    else if (e.getEntity() instanceof Creature)
                    {
                        if (e.getEntity().customName() != null && main.Global.configBlockHurtingNamedMobs) cancelTheEvent(e);
                        else if (e.getEntity().customName() == null && main.Global.configBlockHurtingMobs) cancelTheEvent(e);
                    }
                }
            }
        }
    }

    private static void cancelTheEvent(EntityDamageEvent e)
    {
        Location location = e.getEntity().getLocation();
        World world = e.getEntity().getWorld();
        if (main.Global.spawnWorldList.contains(world.getName()))
        {
            int cord = main.Global.configServerSpawnSize;
            if (location.getX() > -cord && location.getX() < cord)
            {
                if (location.getZ() > -cord && location.getZ() < cord) e.setCancelled(true);
            }
        }
    }
}
