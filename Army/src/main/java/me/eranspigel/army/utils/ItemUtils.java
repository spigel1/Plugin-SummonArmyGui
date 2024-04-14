package me.eranspigel.army.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
public class ItemUtils {

        public static ItemStack createItem(Material material, String displayName, String lore) {
            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(displayName);
            if (lore != null) {
                meta.setLore(Collections.singletonList(lore));
            }
            item.setItemMeta(meta);
            return item;
        }
    }


