package me.cutzuu.zuusTweaks.listeners;

import com.destroystokyo.paper.event.block.AnvilDamagedEvent;
import io.papermc.paper.event.entity.EntityDamageItemEvent;
import me.cutzuu.zuusTweaks.main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class features implements Listener
{
    @EventHandler
    public void onMinecartSpawn(VehicleCreateEvent e)
    {
        if (main.Global.configFasterMinecarts && e.getVehicle() instanceof Minecart cart) cart.setMaxSpeed(1.0);
    }

    @EventHandler
    public void onMinecartEnter(VehicleEnterEvent e)
    {
        if (main.Global.configFasterMinecarts && e.getVehicle() instanceof Minecart cart) cart.setMaxSpeed(1.0);
    }

    // Unbreakable Anvil - Auto Repair
    @EventHandler
    public void godAnvil(AnvilDamagedEvent e)
    {
        if (main.Global.configUnbreakableAnvils)
        {
            e.setDamageState(AnvilDamagedEvent.DamageState.FULL);
            e.setCancelled(true);
        }
    }

    // Unbreakable Items for Players
    @EventHandler
    public void godItem1(PlayerItemDamageEvent e)
    {
        if (main.Global.configUnbreakableItems) e.setCancelled(true);
    }

    // Unbreakable Items for Creatures
    @EventHandler
    public void godItem2(EntityDamageItemEvent e)
    {
        if (main.Global.configUnbreakableItems) e.setCancelled(true);
    }

    // Limited Trees - Destroys any saplings dropped.
    @EventHandler
    public void onItemSpawn(ItemSpawnEvent e)
    {
        if (main.Global.configLimitedTrees)
        {
            if (saplings.contains(e.getEntity().getItemStack().getType())) e.getEntity().remove();
        }
    }

    public static List<Material> saplings = List.of(
            Material.OAK_SAPLING,
            Material.DARK_OAK_SAPLING,
            Material.BIRCH_SAPLING,
            Material.JUNGLE_SAPLING,
            Material.ACACIA_SAPLING,
            Material.CHERRY_SAPLING,
            Material.PALE_OAK_SAPLING,
            Material.MANGROVE_PROPAGULE,
            Material.SPRUCE_SAPLING);

    // Insta Kill Wither
    @EventHandler
    public void badWither(EntitySpawnEvent e)
    {
        if (main.Global.configInstaKillWither)
        {
            Entity entity = e.getEntity();
            Location location = entity.getLocation();
            World world = location.getWorld();

            if (entity.getType() == EntityType.WITHER)
            {
                Bukkit.getScheduler().runTaskLater((Plugin) this, () ->
                {
                    entity.remove();
                    world.dropItem(location.toCenterLocation(), ItemStack.of(Material.NETHER_STAR));
                    world.playSound(location.toCenterLocation(), Sound.ENTITY_WITHER_DEATH, 0.2f, 1.0f);
                    world.spawnEntity(location.toCenterLocation(), EntityType.FIREWORK_ROCKET);

                }, 10L);
            }
        }
    }

    // Droppable EnderChest - Destroys the blocks dropped from breaking an EnderChest.
    @EventHandler
    public void itemDrop(BlockDropItemEvent e)
    {
        if (main.Global.configDropEnderChest)
        {
            if (e.getBlock().getType() == Material.ENDER_CHEST) e.getItems().clear();
        }
    }

    // Droppable EnderChest - Once an EnderChest is broken, it will drop an EnderChest.
    @EventHandler
    public void eChest(BlockBreakEvent e)
    {
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) return;
        if (main.Global.configDropEnderChest)
        {
            Block block = e.getBlock();
            Location location = block.getLocation();
            World world = location.getWorld();

            if (block.getType() == Material.ENDER_CHEST)
            {
                block.setType(Material.AIR);
                world.dropItem(location.toCenterLocation(), ItemStack.of(Material.ENDER_CHEST));
                world.spawnParticle(Particle.FLAME, location.toCenterLocation(), 10);
                world.playSound(location.toCenterLocation(),Sound.BLOCK_ENCHANTMENT_TABLE_USE, 0.6f, 1.0f);
            }
        }
    }
}
