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
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        String message = "<" + player.getName() + "> " + event.getMessage();

        for (Player local : getLocalPlayers(player.getLocation())) {
            local.sendMessage(message);
        }
    }

    private List<Player> getLocalPlayers(Location location) {
        List<Player> locals = new ArrayList<>();

        for (Player player : Bukkit.getOnlinePlayers()) {
            double distance = player.getLocation().distance(location);

            if (distance < Settings.RADIUS) {
                locals.add(player);
            }
        }

        return locals;
    }
}
