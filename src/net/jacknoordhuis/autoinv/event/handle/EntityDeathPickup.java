package net.jacknoordhuis.autoinv.event.handle;

import net.jacknoordhuis.autoinv.event.EventHandler;
import net.jacknoordhuis.autoinv.event.EventManager;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class EntityDeathPickup extends EventHandler {

	public EntityDeathPickup(EventManager manager) {
		super(manager);
	}

	@org.bukkit.event.EventHandler
	public void handleEntityDeath(EntityDeathEvent event) {
		Entity victim = event.getEntity();
		EntityDamageEvent cause = victim.getLastDamageCause();
		if(cause instanceof EntityDamageByEntityEvent) {
			Entity killer = ((EntityDamageByEntityEvent) cause).getDamager();
			if(killer instanceof InventoryHolder) {
				Inventory inv = ((InventoryHolder) killer).getInventory();
				ArrayList<ItemStack> toKeep = new ArrayList<ItemStack>();
				for(ItemStack drop : event.getDrops()) { // loop over all drops
					HashMap<Integer, ItemStack> leftover = inv.addItem(drop); // get the items that weren't added to the inventory
					for(Map.Entry<Integer, ItemStack> entry : leftover.entrySet()) { // loop over the remaining items that couldn't be added to the inv
						toKeep.add(entry.getValue());
					}
				}

				event.getDrops().clear(); // Clear all the current drops
				event.getDrops().addAll(toKeep); // Add all the drops back that couldn't be added to the inv
			}
		}
	}

}