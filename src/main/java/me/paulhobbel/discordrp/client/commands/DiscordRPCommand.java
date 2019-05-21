package me.paulhobbel.discordrp.client.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.server.command.CommandTreeBase;

public class DiscordRPCommand extends CommandTreeBase {
    public DiscordRPCommand() {
        addSubcommand(new AcceptJoinCommand());
        addSubcommand(new DenyJoinCommand());
    }

    @Override
    public String getName() {
        return "discordrp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "discordrp.commands.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
