package com.mrbysco.snod;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

import java.util.List;

@Mod(SnodMod.MOD_ID)
public class SnodMod {
	public static final String MOD_ID = "snod";
	public static final Logger LOGGER = LogUtils.getLogger();

	public SnodMod(IEventBus eventBus, Dist dist, ModContainer container) {

	}

	public static Component getModifiedComponent(Component original) {
		MutableComponent copy = original.copy();
		List<Component> componentList = copy.getSiblings();
		if (componentList.isEmpty()) {
			String originalName = original.getString();
			return Component.literal(modifyName(originalName)).withStyle(copy.getStyle());
		} else {
			MutableComponent newName = Component.empty();
			for (int i = 0; i < componentList.size(); i++) {
				Component component = componentList.get(i);
				if (i == 0) {
					String raw = component.getString();
					newName.append(modifyName(raw));
				} else {
					newName.append(component);
				}
			}

			return newName;
		}
	}

	private static String modifyName(String name) {
		String vowels = "AEIOUaeiou";
		for (int i = 0; i < name.length(); i++) {
			if (vowels.indexOf(name.charAt(i)) != -1) {
				return "Sn" + Character.toLowerCase(name.charAt(i)) + name.substring(i + 1);
			}
		}
		return name;
	}
}
