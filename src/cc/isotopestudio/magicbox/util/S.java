package cc.isotopestudio.magicbox.util;

import org.bukkit.ChatColor;

import static cc.isotopestudio.magicbox.MagicBox.prefix;

/*
 * Created by Mars on 5/15/2016.
 * Copyright ISOTOPE Studio
 */
public class S {

    public static String toYellow(String s) {
        return String.valueOf(ChatColor.YELLOW) + s + ChatColor.RESET;
    }

    public static String toPrefixRed(String s) {
        return prefix + ChatColor.RED + s + ChatColor.RESET;
    }

    public static String toPrefixGreen(String s) {
        return prefix + ChatColor.GREEN + s + ChatColor.RESET;
    }

    public static String toPrefixYellow(String s) {
        return prefix + ChatColor.YELLOW + s + ChatColor.RESET;
    }

}
