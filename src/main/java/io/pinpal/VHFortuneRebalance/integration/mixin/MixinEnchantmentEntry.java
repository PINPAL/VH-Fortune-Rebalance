package io.pinpal.VHFortuneRebalance.integration.mixin;

import iskallia.vault.util.EnchantmentEntry;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

/**
 * Changes the {@link iskallia.vault.util.EnchantmentEntry} class used by the
 * Vault Enchanter.
 * <p>
 * Specifically, allows for it to enchant Fortune V.
 */
@Mixin(EnchantmentEntry.class)
public abstract class MixinEnchantmentEntry {
	@Shadow(remap = false)
	private int level;
	@Shadow(remap = false)
	private Enchantment enchantment;

	/**
	 * Sets the level of the Fortune enchantment to 5.
	 */
	@Inject(
	  method = "<init>",
	  at = @At("RETURN"),
	  remap = false
	)
	private void init$return(Enchantment enchantment, int level, CallbackInfo ci) {
		String enchantmentName = Objects.toString(this.enchantment.getRegistryName(), "");

		if (enchantmentName.equals("minecraft:fortune")) {
			this.level = 5;
		}
	}

	/**
	 * Ensures that Fortune V is a valid enchantment.
	 */
	@Inject(
	  method = "isValid()Z",
	  at = @At("RETURN"),
	  cancellable = true,
	  remap = false
	)
	private void isValid$return(CallbackInfoReturnable<Boolean> ci) {
		String registryName = Objects.toString(this.enchantment.getRegistryName(), "");

		if (registryName.equals("minecraft:fortune") && this.level <= 5) {
			ci.setReturnValue(true);
		}
	}
}
