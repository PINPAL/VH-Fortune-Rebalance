package io.pinpal.VHFortuneRebalance.mixin;

import iskallia.vault.config.Config;
import iskallia.vault.config.ExpertisesGUIConfig;
import iskallia.vault.config.entry.SkillStyle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;

/**
 * Changes the {@link iskallia.vault.config.ExpertisesGUIConfig} class for
 * managing the configuration for the Expertises GUI.
 *
 * Specifically, it removes the Fortunate expertise.
 */
@Mixin(ExpertisesGUIConfig.class)
public abstract class MixinExpertisesGuiConfig extends Config {
	@Shadow(remap = false)
	private HashMap<String, SkillStyle> styles;

	/**
	 * Removes the Fortunate expertise on loading the configuration
	 * file.
	 */
	@Override
	protected void onLoad(Config oldConfigInstance) {
		super.onLoad(oldConfigInstance);
		this.styles.remove("Fortunate");
	}

	@Shadow(remap = false)
	@Override
	public String getName() {
		throw new UnsupportedOperationException("Unimplemented method 'getName'");
	}

	@Shadow(remap = false)
	@Override
	protected void reset() {
		throw new UnsupportedOperationException("Unimplemented method 'reset'");
	}
}
