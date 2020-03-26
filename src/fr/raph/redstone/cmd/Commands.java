package fr.raph.redstone.cmd;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.raph.redstone.main.Main;
import net.md_5.bungee.api.ChatColor;

public class Commands implements CommandExecutor {
	
	private Main plugin;
	private int nbRedstoneBlock;
	private int nbPiston;
	private int nbStickyPiston;
	private int nbRedstoneTorch;
	private int nbRedstone;
	private int nbRepeater;
	private String prefixErreur = ChatColor.DARK_RED + "ScanErreur : ";
	private String prefixScan = ChatColor.DARK_RED + "Scan : ";
	

	public Commands(Main main) {
		this.plugin = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		if(sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("RedstoneScan")) {
				
				if(!p.hasPermission("scan.RedstoneScan")) {
					p.sendMessage(prefixErreur + ChatColor.RED +"Vous n'avez pas les permissions nécessaire pour exécuter cette commande !");
					return false;
				}
				Player player;
				
				if(args.length == 0) {
					player = p;
				}else {
				
					try {
						Bukkit.getPlayerExact(args[0].toString()).isOnline();
					} catch (Exception e) {
						p.sendMessage(prefixErreur + ChatColor.RED + "Le joueur n'est pas connecté ou n'exsite pas !");
						return false;
					}
					
					player = Bukkit.getPlayerExact(args[0].toString());
				}
				
				Chunk c = player.getWorld().getChunkAt(p.getLocation());
				
				for(int x=0; x < 16; x++) {
					for(int y=1;y < 255;y++) {
						for(int z=0;z < 16;z++) {
							Block b = c.getBlock(x, y, z);

							Material m = b.getType();
							
							if(m == Material.REDSTONE_BLOCK) {
								nbRedstoneBlock++;
							}
							if(m == Material.PISTON) {
								nbPiston++;
							}
							if(m == Material.STICKY_PISTON) {
								nbStickyPiston++;
							}
							if(m == Material.REDSTONE_TORCH || m == Material.REDSTONE_WALL_TORCH) {
								nbRedstoneTorch++;
							}
							if(m == Material.REDSTONE_WIRE) {
								nbRedstone++;
							}
							if(m == Material.REPEATER) {
								nbRepeater++;
							}
							
						}
					}
				}
				
				if(args.length == 0) {
					p.sendMessage(prefixScan + ChatColor.AQUA + "Dans ce chunk, il y a " + ChatColor.RED + nbRedstoneBlock + ChatColor.AQUA + " bloc de redstone, " + ChatColor.RED + nbPiston + ChatColor.AQUA + 
								" piston, " + ChatColor.RED + nbStickyPiston + ChatColor.AQUA + " piston collant, " + ChatColor.RED + nbRedstoneTorch + ChatColor.AQUA + " torche de redstone, " + ChatColor.RED + 
								nbRedstone + ChatColor.AQUA + " poudre de redstone, " + ChatColor.RED + nbRepeater + ChatColor.AQUA + " repeater !"); 
				}else {
					p.sendMessage(prefixScan + ChatColor.AQUA + "Dans le chunk, où se trouve " + ChatColor.GREEN + player.getName() + ChatColor.AQUA + " il y a " + ChatColor.RED + nbRedstoneBlock + 
							ChatColor.AQUA + " bloc de redstone, " + ChatColor.RED + nbPiston + ChatColor.AQUA + " piston, " + ChatColor.RED + nbStickyPiston + ChatColor.AQUA + " piston collant, " +
							ChatColor.RED + nbRedstoneTorch + ChatColor.AQUA + " torche de redstone, " + ChatColor.RED + nbRedstone + ChatColor.AQUA + " poudre de redstone !"); 
					
				}
				
				
				
				nbRedstoneBlock = 0;
				nbPiston = 0;
				nbStickyPiston = 0;
				nbRedstoneTorch = 0;
				nbRedstone = 0; 
				nbRepeater = 0;
				
				
			}
			
			
		}
		
		
		return false;
	}

}
