package net.picklestring.flux_weavers.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.items.runes.RuneItem;
import net.picklestring.flux_weavers.registries.EntityRegistry;
import net.picklestring.flux_weavers.registries.ItemComponentRegistry;
import net.picklestring.flux_weavers.registries.ParticleRegistry;
import net.picklestring.flux_weavers.utils.CastingContext;
import net.picklestring.flux_weavers.utils.ListUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FluxArrowEntity extends PersistentProjectileEntity {
	public DefaultedList<ItemStack> spell = DefaultedList.ofSize(60, ItemStack.EMPTY);

	public FluxArrowEntity(EntityType<FluxArrowEntity> entityType, World world) {
		super(entityType, world);
	}

	public FluxArrowEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(EntityRegistry.FLUX_ARROW_ENTITY, owner, world, stack, shotFrom);
	}

	public FluxArrowEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
		super(EntityRegistry.FLUX_ARROW_ENTITY, x, y, z, world, stack, shotFrom);
	}

	public void tick() {
		super.tick();
		if (this.getWorld().isClient && !this.inGround) {
			this.getWorld().addParticle(ParticleRegistry.FLUX_STAR, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
		}

	}

	protected void onHit(LivingEntity target) {
		super.onHit(target);
		CastingContext context = new CastingContext();
		context.extraContext.put("Target", target);
		castSpell(context);
	}

	protected void onBlockHit(BlockHitResult blockHitResult) {
		super.onBlockHit(blockHitResult);
		CastingContext context = new CastingContext();
		context.extraContext.put("BlockPos", blockHitResult.getBlockPos());
		castSpell(context);
	}

	protected void castSpell(CastingContext context) {
		if (!(getOwner() instanceof PlayerEntity owner)) return;
        if (getWorld().isClient || owner.isSneaking()) return;

		context.extraContext.put("Arrow", this);
		CastingContext.cast(spell, (PlayerEntity)getOwner(), context);

		kill();
	}

	public void readCustomDataFromNbt(NbtCompound nbt) {
		super.readCustomDataFromNbt(nbt);
		if (nbt.contains("Spell")) {
			readSpell(nbt, spell, getWorld().getRegistryManager());
		}

	}

	public void writeCustomDataToNbt(NbtCompound nbt) {
		super.writeCustomDataToNbt(nbt);
		writeSpell(nbt, spell, true, getWorld().getRegistryManager());
	}

	public void readSpell(NbtCompound nbt, DefaultedList<ItemStack> stacks, RegistryWrapper.WrapperLookup registries) {
		NbtList nbtList = nbt.getList("Spell", 10);

		for(int i = 0; i < nbtList.size(); ++i) {
			NbtCompound nbtCompound = nbtList.getCompound(i);
			int j = nbtCompound.getByte("Slot") & 255;
			if (j >= 0 && j < stacks.size()) {
				stacks.set(j, (ItemStack)ItemStack.fromNbt(registries, nbtCompound).orElse(ItemStack.EMPTY));
			}
		}
	}

	public NbtCompound writeSpell(NbtCompound nbt, DefaultedList<ItemStack> stacks, boolean setIfEmpty, RegistryWrapper.WrapperLookup registries) {
		NbtList nbtList = new NbtList();

		for(int i = 0; i < stacks.size(); ++i) {
			ItemStack itemStack = (ItemStack)stacks.get(i);
			if (!itemStack.isEmpty()) {
				NbtCompound nbtCompound = new NbtCompound();
				nbtCompound.putByte("Slot", (byte)i);
				nbtList.add(itemStack.encode(registries, nbtCompound));
			}
		}

		if (!nbtList.isEmpty() || setIfEmpty) {
			nbt.put("Spell", nbtList);
		}

		return nbt;
	}

	protected ItemStack getDefaultItemStack() {
		return new ItemStack(Items.SPECTRAL_ARROW);
	}
}
