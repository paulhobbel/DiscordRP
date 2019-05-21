package me.paulhobbel.discordrp.client.commands;

import me.paulhobbel.discordrp.api.rpc.DiscordRPC;
import me.paulhobbel.discordrp.api.rpc.DiscordReply;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.server.command.TextComponentHelper;

public class AcceptJoinCommand extends CommandBase {
    @Override
    public String getName() {
        return "accept";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "discordrp.commands.accept_join.usage";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length < 1) {
            throw new WrongUsageException(getUsage(sender));
        }

        // TODO: Check if argument is correct
        DiscordRPC.Respond(args[0], DiscordReply.YES);

        ITextComponent accepted = TextComponentHelper.createComponentTranslation(sender, "discordrp.server.request_accepted");
        sender.sendMessage(accepted);
    }
}
