/*
    Glow Sheep
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/Fleecifer
    License: GNU LGPLv3
*/

package com.mclegoman.glowsheep.common.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;

public class GlowComponent {
	private final SynchedEntityData dataTracker;
	private final EntityDataAccessor<Boolean> glow;

	public GlowComponent(SynchedEntityData dataTracker, EntityDataAccessor<Boolean> glow) {
		this.dataTracker = dataTracker;
		this.glow = glow;
	}
	public void writeNbt(CompoundTag nbt) {
		nbt.putBoolean("Glow", this.isGlow());
	}
	public void readNbt(CompoundTag nbt) {
		this.setGlow(nbt.getBoolean("Glow"));
	}
	public void setGlow(boolean value) {
		this.dataTracker.set(this.glow, value);
	}
	public boolean isGlow() {
		return this.dataTracker.get(this.glow);
	}
}

