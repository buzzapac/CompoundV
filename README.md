package com.compoundv;

import com.compoundv.commands.CompoundVCommand;
import com.compoundv.listeners.PlayerStateListener;
import com.compoundv.listeners.PotionListener;
import com.compoundv.listeners.PowerInteractListener;
import com.compoundv.tasks.PowerEffectTask;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Compound V Plugin — main entry point.
 *
 * <p>Introduces two custom drinkable potions to Minecraft:
 * <ul>
 *   <li><b>Temp V</b>      (green) — 20 minutes of a random superpower</li>
 *   <li><b>Compound V</b>  (blue)  — permanent superpower (one not yet possessed)</li>
 * </ul>
 *
 * <p>Compatible with <b>PaperMC 26.1.2</b> / Minecraft latest Java edition.
 */
public class CompoundVPlugin extends JavaPlugin {

    private PowerManager powerManager;

    @Override
    public void onEnable() {
        powerManager = new PowerManager(this);

        // ── Listeners ─────────────────────────────────────────────────────────
        getServer().getPluginManager().registerEvents(
                new PotionListener(this, powerManager), this);
        getServer().getPluginManager().registerEvents(
                new PowerInteractListener(this, powerManager), this);
        getServer().getPluginManager().registerEvents(
                new PlayerStateListener(this, powerManager), this);

        // ── Commands ──────────────────────────────────────────────────────────
        CompoundVCommand cmd = new CompoundVCommand(this, powerManager);
        PluginCommand pluginCmd = getCommand("compoundv");
        if (pluginCmd != null) {
            pluginCmd.setExecutor(cmd);
            pluginCmd.setTabCompleter(cmd);
        }

        // ── Repeating task (every 20 ticks = 1 second) ───────────────────────
        new PowerEffectTask(this, powerManager).runTaskTimer(this, 20L, 20L);

        getLogger().info("╔══════════════════════════════════╗");
        getLogger().info("║   Compound V Plugin  v1.0.0      ║");
        getLogger().info("║   PaperMC 26.1.2  —  Enabled!    ║");
        getLogger().info("╚══════════════════════════════════╝");
    }

    @Override
    public void onDisable() {
        getLogger().info("[CompoundV] Plugin disabled.");
    }

    public PowerManager getPowerManager() {
        return powerManager;
    }
}
