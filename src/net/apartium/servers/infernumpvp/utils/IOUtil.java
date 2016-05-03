package net.apartium.servers.infernumpvp.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class IOUtil {
	
	public static Entry<File,FileConfiguration> handleYML(File parent, String path) {
		File f = new File(parent,path);
		FileConfiguration fc = YamlConfiguration.loadConfiguration(f);
		if(!f.exists()) {
			try {
				fc.save(f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new Entry<File, FileConfiguration>() {
			@Override public File getKey() {return f;}
			@Override public FileConfiguration getValue() {return fc;}
			@Override public FileConfiguration setValue(FileConfiguration arg0) {return fc;}
			
		};
	}
	
	public static File handleDir(File parent, String path) {
		File f = new File(parent,path);
		f.mkdir();
		return f;
	}
}
