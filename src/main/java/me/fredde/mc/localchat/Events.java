package me.fredde.mc.localchat;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class Events implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        int bound = Settings.RADIUS / 2;

        List<Entity> entities = event.getPlayer().getNearbyEntities(bound, bound, bound);
        String message = "<" + player.getName() + "> " + event.getMessage();

        for (Entity entity : entities) {
            if (entity instanceof Player) {
                player.sendMessage(message);
            }
        }
    }
}
