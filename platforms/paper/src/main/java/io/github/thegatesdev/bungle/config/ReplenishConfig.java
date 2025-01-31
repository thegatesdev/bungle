package io.github.thegatesdev.bungle.config;

import org.spongepowered.configurate.objectmapping.*;
import org.spongepowered.configurate.objectmapping.meta.*;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal"})
@ConfigSerializable
public final class ReplenishConfig {

    @Comment("Enable or disable the replenish feature")
    public boolean enabled = false;
    @Comment("When enabled, only players with the permission will have the replenish feature")
    public boolean requirePermission = true;

    @Comment("The stack size at which the plugin will start trying to replenish items")
    public int replenishThreshold = 50;
}
