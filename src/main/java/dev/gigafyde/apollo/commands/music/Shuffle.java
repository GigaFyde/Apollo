package dev.gigafyde.apollo.commands.music;

import dev.gigafyde.apollo.core.command.Command;
import dev.gigafyde.apollo.core.command.CommandEvent;

public class Shuffle extends Command {
    public Shuffle() {
        this.name = "shuffle";
        this.description = "Shuffle's the current queue";
        this.triggers = new String[]{"shuffle"};
        this.guildOnly = true;
    }

    public void execute(CommandEvent event) {
        try {
            event.getClient().getMusicManager().getScheduler(event.getGuild()).shuffleQueue();
            event.getTrigger().reply("Shuffled!").mentionRepliedUser(false).queue();
        } catch (Exception e) {
            event.getTrigger().reply("Failed to shuffle! error encountered was: " + e.getMessage()).queue();
        }
    }
}
