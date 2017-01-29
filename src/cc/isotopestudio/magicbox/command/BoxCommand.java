package cc.isotopestudio.magicbox.command;
/*
 * Created by david on 2017/1/30.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.magicbox.data.Box;
import cc.isotopestudio.magicbox.util.S;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cc.isotopestudio.magicbox.MagicBox.data;
import static cc.isotopestudio.magicbox.MagicBox.plugin;

public class BoxCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("box")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(S.toPrefixRed("���ִ�е�����"));
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("box.admin")) {
                player.sendMessage(S.toPrefixRed("��û��Ȩ��"));
                return true;
            }

            if (args.length < 1) {
                sendHelpPage1(label, sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("add")) {
                if (args.length < 3) {
                    sendHelpPage1(label, sender);
                    return true;
                }
                int odd;
                try {
                    odd = Integer.parseInt(args[2]);
                    if (odd < 1) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    player.sendMessage(S.toPrefixRed("���ʲ���"));
                    return true;
                }
                Box.storeItem(args[1], player.getItemInHand(), odd);
                player.sendMessage(S.toPrefixGreen("�ɹ�"));
            }
            if (args[0].equalsIgnoreCase("give")) {
                if (args.length < 3) {
                    sendHelpPage1(label, sender);
                    return true;
                }
                Player receiver = Bukkit.getPlayer(args[1]);
                if (receiver == null) {
                    player.sendMessage(S.toPrefixRed("��Ҳ�����"));
                    return true;
                }
                Map<ItemStack, Integer> box = Box.getBox(args[2]);
                if (box.keySet().size() == 0) {
                    player.sendMessage(S.toPrefixRed("����������"));
                    return true;
                }

                List<ItemStack> items = new ArrayList<>(box.keySet());
                List<Integer> luckList = new ArrayList<>(box.values());

                int i;
                double sum = 0;
                double[] luckAcc = new double[luckList.size()];
                for (i = 0; i < luckList.size(); i++) {
                    double v = 1.0 / luckList.get(i);
                    luckAcc[i] = sum + v;
                    sum += v;
                }
                sum = luckAcc[luckList.size() - 1];
                double point = random(sum);
                i = 0;
                while (true) {
                    if (luckAcc[i] < point) {
                        i++;
                        continue;
                    }
                    break;
                }
                receiver.getInventory().addItem(items.get(i));
                player.sendMessage(S.toPrefixGreen("�ɹ�"));
            }
            if (args[0].equalsIgnoreCase("list")) {
                player.sendMessage(S.toPrefixYellow(data.getKeys(false).toString()));
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.onReload();
                player.sendMessage(S.toPrefixGreen("�ɹ�"));
                return true;
            }
            sendHelpPage1(label, sender);
            return true;
        }
        return false;
    }


    private void sendHelpPage1(String label, CommandSender sender) {
        sender.sendMessage(S.toPrefixGreen("�����˵�"));
        sender.sendMessage(S.toYellow("/" + label + " add <������> <����> - ���������Ʒ������"));
        sender.sendMessage(S.toYellow("/" + label + " give <���> <������> - ���������Ʒ"));
        sender.sendMessage(S.toYellow("/" + label + " list - �����б�"));
        sender.sendMessage(S.toYellow("/" + label + " reload - ����"));
    }

    private static double random(double max) {
        return Math.random() * max;
    }
}
