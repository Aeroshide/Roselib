package com.aeroshide.rose_bush.api;

import net.minecraft.client.OptionInstance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Central registry for mods to add options to Vanilla screens without Mixin conflicts.
 */
public class RoselibVanillaSettings {

    private static final List<OptionInstance<?>> ACCESSIBILITY_OPTIONS = new ArrayList<>();

    /**
     * Registers a new option to be displayed in the Accessibility Settings screen.
     * Call this during your mod's Client Initialization.
     *
     * @param option The OptionInstance to add.
     */
    public static void addAccessibilityOption(OptionInstance<?> option) {
        ACCESSIBILITY_OPTIONS.add(option);
    }

    /**
     * Internal use only. Returns the list of registered options.
     */
    public static List<OptionInstance<?>> getAccessibilityOptions() {
        return Collections.unmodifiableList(ACCESSIBILITY_OPTIONS);
    }
}