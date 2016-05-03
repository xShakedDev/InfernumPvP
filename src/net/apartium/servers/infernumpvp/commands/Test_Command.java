package net.apartium.servers.infernumpvp.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.apartium.servers.infernumpvp.InfernumPvP;
import net.apartium.servers.infernumpvp.utils.AnvilGUI;
import net.apartium.servers.infernumpvp.utils.AnvilGUI.AnvilClickEventHandler;
import net.apartium.servers.infernumpvp.utils.ChatBuilder;

public class Test_Command implements CommandExecutor {
	private static InfernumPvP m = InfernumPvP.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			if (label.equalsIgnoreCase("test")) {
				sender.sendMessage(m.ADMINISTRATION + ChatBuilder.build("TEST Command :)"));
				AnvilGUI gui = new AnvilGUI((Player) sender);
				AnvilClickEventHandler e = new AnvilClickEventHandler() {
					@Override
					public void onAnvilClick(AnvilGUI.AnvilClickEvent event) {
						if (event.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
							event.setWillClose(true);
							event.setWillDestroy(true);
							sender.sendMessage(event.getName());
						} else {
							event.setWillClose(false);
							event.setWillDestroy(false);
						}
					}
				};
				gui.setHandler(e);

				gui.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, new ItemStack(Material.NAME_TAG));

				gui.open();
			}
		}
		return false;
	}

}
