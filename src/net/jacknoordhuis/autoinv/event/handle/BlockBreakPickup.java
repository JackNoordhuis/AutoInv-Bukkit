package net.jacknoordhuis.autoinv.event.handle;

import net.jacknoordhuis.autoinv.event.EventHandler;
import net.jacknoordhuis.autoinv.event.EventManager;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakPickup extends EventHandler {

	public BlockBreakPickup(EventManager manager) {
		super(manager);
	}

	@org.bukkit.event.EventHandler
	public void handleBlockBreak(BlockBreakEvent event) {
		for(ItemStack item : event.getBlock().getDrops()) {
			event.getPlayer().getInventory().addItem(item);
		}

		event.setDropItems(false);
	}

}
