package dev.gigafyde.apollo.commands.music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.gigafyde.apollo.core.MusicManager;
import dev.gigafyde.apollo.core.TrackScheduler;
import dev.gigafyde.apollo.core.command.Command;
import dev.gigafyde.apollo.core.command.CommandEvent;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

public class Queue extends Command {
    public Queue() {
        this.name = "queue";
        this.triggers = new String[]{"q", "queue"};
    }

    public void execute(CommandEvent event) {
        TextChannel channel = event.getTextChannel();
        MusicManager musicManager = event.getClient().getMusicManager();
        TrackScheduler scheduler = musicManager.getScheduler(event.getGuild());
        if (scheduler.getQueue().isEmpty()) {
            channel.sendMessage("Queue is currently empty").queue();
            return;
        }
        List<AudioTrack> queuedTracks = new ArrayList<>(scheduler.getQueue());
        if (scheduler.getPlayer().isPaused()) {
            return;
        }
        if (scheduler.getPlayer().getPlayingTrack().getInfo().uri.isEmpty()) {
            return;
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(event.getSelfMember().getColor());
        eb.setTitle("Currently playing:");
        eb.addField(scheduler.getPlayer().getPlayingTrack().getInfo().title, scheduler.getPlayer().getPlayingTrack().getInfo().uri, false);
        eb.addBlankField(false);

        int page = 1;
        try {
            page = Integer.parseInt(event.getArgument());
        } catch (NumberFormatException ignored) {
        }
        if (page < 1) page = 1;
        int maxPages = (queuedTracks.size() + 10 - 1) / 4;
        if (page > maxPages) page = maxPages;
        int lowerLimit = (page - 1) * 10;
        int higherLimit = lowerLimit + 10;
        if (higherLimit > queuedTracks.size()) higherLimit = queuedTracks.size();
        for (int i = lowerLimit; i < higherLimit; i++) {
            eb.addField(String.format("`[%d]` %s", i + 1, queuedTracks.get(i).getInfo().title), queuedTracks.get(i).getInfo().uri, false);
        }
        eb.setFooter("Page " + page + " of " + maxPages, null);
        channel.sendMessage(eb.build()).queue();
    }
}
