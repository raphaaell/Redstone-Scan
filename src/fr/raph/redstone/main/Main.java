package fr.raph.redstone.main;

import org.bukkit.plugin.java.JavaPlugin;

import fr.raph.redstone.cmd.Commands;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		getCommand("RedstoneScan").setExecutor(new Commands(this));
	}

}
