package dev.gigafyde.apollo.commands;

/*
 Created by GigaFyde
 https://github.com/GigaFyde
 */

import dev.gigafyde.apollo.commands.basic.Ping;
import dev.gigafyde.apollo.commands.music.Clear;
import dev.gigafyde.apollo.commands.music.Connect;
import dev.gigafyde.apollo.commands.music.Disconnect;
import dev.gigafyde.apollo.commands.music.Grab;
import dev.gigafyde.apollo.commands.music.NowPlaying;
import dev.gigafyde.apollo.commands.music.Pause;
import dev.gigafyde.apollo.commands.music.Play;
import dev.gigafyde.apollo.commands.music.Queue;
import dev.gigafyde.apollo.commands.music.Remove;
import dev.gigafyde.apollo.commands.music.Restart;
import dev.gigafyde.apollo.commands.music.Resume;
import dev.gigafyde.apollo.commands.music.Seek;
import dev.gigafyde.apollo.commands.music.Shuffle;
import dev.gigafyde.apollo.commands.music.Skip;
import dev.gigafyde.apollo.commands.music.Volume;
import dev.gigafyde.apollo.commands.secret.Eval;
import dev.gigafyde.apollo.core.Client;
import dev.gigafyde.apollo.core.command.CommandRegistry;

public class CommandList extends CommandRegistry {
    public CommandList(Client client) {
        CommandRegistry registry = client.getCommandRegistry();
        //basic commands
        registry.addCommand(new Ping());

        //music commands
        registry.addCommand(new Play());
        registry.addCommand(new Queue());
        registry.addCommand(new Pause());
        registry.addCommand(new Resume());
        registry.addCommand(new Skip());
        registry.addCommand(new Shuffle());
        registry.addCommand(new Restart());
        registry.addCommand(new Connect());
        registry.addCommand(new Disconnect());
        registry.addCommand(new Volume());
        registry.addCommand(new Grab());
        registry.addCommand(new NowPlaying());
        registry.addCommand(new Seek());
        registry.addCommand(new Remove());
        registry.addCommand(new Clear());

        //secret commands
        registry.addCommand(new Eval());
    }
}
