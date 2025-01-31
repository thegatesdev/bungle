package io.github.thegatesdev.bungle.config;

import org.spongepowered.configurate.objectmapping.*;
import org.spongepowered.configurate.objectmapping.meta.*;

@ConfigSerializable
public final class BungleConfig {

    @Comment("Replenish the stack you are holding while placing blocks")
    public ReplenishConfig replenish = new ReplenishConfig();
}
