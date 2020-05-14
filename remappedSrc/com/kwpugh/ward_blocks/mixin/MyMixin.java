package com.kwpugh.ward_blocks.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class MyMixin
{
	
	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(CallbackInfo ci)
	{
		// TBD
	}
}