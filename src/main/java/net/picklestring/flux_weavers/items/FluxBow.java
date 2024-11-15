package net.picklestring.flux_weavers.items;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.picklestring.flux_weavers.FluxWeavers;
import net.picklestring.flux_weavers.entities.FluxArrowEntity;
import net.picklestring.flux_weavers.registries.ItemComponentRegistry;
import net.picklestring.flux_weavers.utils.ListUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FluxBow extends BowItem {
	public FluxBow(Settings settings) {
		super(settings);
	}

	protected void shoot(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target) {
		projectile.setVelocity(shooter, shooter.getPitch(), shooter.getYaw() + yaw, 0.0F, speed, divergence);
		((FluxArrowEntity)projectile).spell = ListUtils.listToDefaultedList(projectile.getOwner().getWeaponStack().get(ItemComponentRegistry.SPELL), ItemStack.EMPTY);
	}

	protected ProjectileEntity createArrowEntity(World world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical) {
		PersistentProjectileEntity persistentProjectileEntity = new FluxArrowEntity(world, shooter, projectileStack, projectileStack);;
		if (critical) {
			persistentProjectileEntity.setCritical(true);
		}

		return persistentProjectileEntity;
	}

}
