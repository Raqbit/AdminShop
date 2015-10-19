package io.github.hsyyid.adminshop.cmdexecutors;

import io.github.hsyyid.adminshop.AdminShop;
import io.github.hsyyid.adminshop.utils.ShopItem;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.source.CommandBlockSource;
import org.spongepowered.api.util.command.source.ConsoleSource;
import org.spongepowered.api.util.command.spec.CommandExecutor;

public class SetItemShopExecutor implements CommandExecutor
{
	public CommandResult execute(CommandSource src, CommandContext ctx) throws CommandException
	{
		String itemID = ctx.<String>getOne("item ID").get();
		if (src instanceof Player)
		{
			Player player = (Player) src;
			ShopItem item = new ShopItem(player, itemID);
			AdminShop.items.add(item);
			player.sendMessage(Texts.of(TextColors.DARK_RED, "[AdminShop]: ", TextColors.GOLD, "Right click an AdminShop sign!"));
		}
		else if (src instanceof ConsoleSource)
		{
			src.sendMessage(Texts.of(TextColors.DARK_RED, "Error! ", TextColors.RED, "Must be an in-game player to use /setitem!"));
		}
		else if (src instanceof CommandBlockSource)
		{
			src.sendMessage(Texts.of(TextColors.DARK_RED, "Error! ", TextColors.RED, "Must be an in-game player to use /setitem!"));
		}
		return CommandResult.success();
	}
}