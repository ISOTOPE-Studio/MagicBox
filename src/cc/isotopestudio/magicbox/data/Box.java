package cc.isotopestudio.magicbox.data;
/*
 * Created by david on 2017/1/30.
 * Copyright ISOTOPE Studio
 */

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import static cc.isotopestudio.magicbox.MagicBox.data;

public class Box {

    public static void storeItem(String box, ItemStack item, int odd) {
        ConfigurationSection config = getConfig(box);
        int i = 0;
        for (String keyS : config.getKeys(false)) {
            int key = Integer.parseInt(keyS);
            if (key > i) i = key;
        }
        config.set((++i) + ".item", item);
        config.set(i + ".odd", odd);
        data.save();
    }

    public static Map<ItemStack, Integer> getBox(String box) {
        ConfigurationSection config = getConfig(box);
        Map<ItemStack, Integer> result = new HashMap<>();
        for (String keyS : config.getKeys(false)) {
            result.put(config.getItemStack(keyS + ".item"), config.getInt(keyS + ".odd"));
        }
        return result;
    }

    private static ConfigurationSection getConfig(String box) {
        return data.isSet(box + ".1") ? data.getConfigurationSection(box) : data.createSection(box);
    }

}
