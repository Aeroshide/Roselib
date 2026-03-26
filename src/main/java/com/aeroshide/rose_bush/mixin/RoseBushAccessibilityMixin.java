package com.aeroshide.rose_bush.mixin;

import com.aeroshide.rose_bush.api.RoselibVanillaSettings;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.screens.options.AccessibilityOptionsScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.List;

@Mixin(value = AccessibilityOptionsScreen.class, priority = 900) // please open an issue if this causes compat and be technically verbose!
public class RoseBushAccessibilityMixin {

    @Inject(method = "options", at = @At("RETURN"), cancellable = true)
    private static void injectRoseBushOptions(Options options, CallbackInfoReturnable<OptionInstance<?>[]> cir) {
        OptionInstance<?>[] vanillaOptions = cir.getReturnValue();
        if (vanillaOptions == null) {
            vanillaOptions = new OptionInstance<?>[0];
        }

        List<OptionInstance<?>> modOptions = RoselibVanillaSettings.getAccessibilityOptions();

        if (modOptions.isEmpty()) {
            return;
        }

        OptionInstance<?>[] combinedOptions = Arrays.copyOf(vanillaOptions, vanillaOptions.length + modOptions.size());

        for (int i = 0; i < modOptions.size(); i++) {
            combinedOptions[vanillaOptions.length + i] = modOptions.get(i);
        }

        cir.setReturnValue(combinedOptions);
    }
}