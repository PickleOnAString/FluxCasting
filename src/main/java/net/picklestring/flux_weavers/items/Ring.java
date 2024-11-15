package net.picklestring.flux_weavers.items;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class Ring extends TrinketItem {
	public Ring(Settings settings) {
		super(settings);
	}

	@Override
	public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier SlotIdentifier) {
		var modifiers = super.getModifiers(stack, slot, entity, SlotIdentifier);
		// +10% movement speed
		modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(SlotIdentifier, 0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE));
		// If the player has access to ring slots, this will give them an extra one
		SlotAttributes.addSlotModifier(modifiers, "hand/ring", SlotIdentifier, 1, EntityAttributeModifier.Operation.ADD_VALUE);
		return modifiers;
	}
}
