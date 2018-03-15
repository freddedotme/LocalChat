package me.fredde.mc.localchat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class Events implements Listener {
    private List<Player> locals;

    Events() {
        locals = new ArrayList<>();
    }

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        String message = String.format(event.getFormat(), player.getName(), event.getMessage());

        player.sendMessage(message);

        for (Player local : getLocalPlayers(player.getLocation())) {
            local.sendMessage(message);
        }

        locals.clear();
    }

    private List<Player> getLocalPlayers(Location location) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            double distance = player.getLocation().distance(location);

            if (distance < Settings.RADIUS) {
                locals.add(player);
            }
        }

        return locals;
    }
}
