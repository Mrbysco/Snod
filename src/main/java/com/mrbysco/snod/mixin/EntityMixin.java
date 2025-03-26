package com.mrbysco.snod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.mrbysco.snod.SnodMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class EntityMixin {
	@ModifyReturnValue(method = "getName()Lnet/minecraft/network/chat/Component;", at = @At("RETURN"))
	public Component getName(Component original) {
		return SnodMod.getModifiedComponent(original);
	}
}
