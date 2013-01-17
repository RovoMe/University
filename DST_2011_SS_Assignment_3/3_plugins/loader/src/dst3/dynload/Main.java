package dst3.dynload;

import java.io.File;
import java.util.Scanner;

public class Main
{
	public static void main(String ... args)
	{
		IPluginExecutor pluginManager = PluginManager.getInstance();
		File pluginDir = new File(new File("").getAbsolutePath()+"/plugins");
		pluginManager.monitor(pluginDir);
		pluginManager.start();
		
		Scanner scanner = new Scanner(System.in);
		String command = "";
		System.out.println("Application started - type 'quit' to exit");
		while (!command.equals("quit"))
		{
			if (command.equals("list"))
			{
				System.out.println("Not yet implemented");
			}
			else if (command.startsWith("unload"))
			{
				String[] tokens = command.split(" ");
				if (tokens.length != 2)
				{
					System.err.println("Invalid call! Usage: 'unload plugin.name' where plugin.name is the name of the plugin to unload");
					System.out.print("app > ");
				}
				else
				{
					// usually only one plug-in per jar-file is used
					// so every jar-file is a unique plug-in
					// but for this lab a jar file may contain multiple
					// plug-ins which makes it hard to handle removing
					// therefore this was not implemented
//					pluginManager.stopMonitoring(tokens[1]);
					System.out.println("Not yet implemented");
//					System.out.println("Plugin unloaded: "+tokens[1]);
				}
			}
			else if (command.startsWith("load"))
			{
				String[] tokens = command.split(" ");
				if (tokens.length != 2 && tokens[1].startsWith("'"))
				{
					String name = tokens[1];
					int i;
					for (i=2; i<tokens.length && !tokens[i-1].endsWith("'"); i++)
					{
						name+=" "+tokens[i];
						System.out.println(name);
					}
					name = name.replaceAll("'", "");
					if (i == tokens.length)
						pluginManager.monitor(new File(name));
					else
					{
						System.err.println("Invalid call! Usage: 'load plugin' where plugin is the path and the name of the plugin to load");
						System.out.print("");
					}
				}
			}
			else if (command.equals("help"))
			{
				System.out.println("list - lists all currently loaded plugins");
				System.out.println("load 'plugin.jar' - tries to load a plugin. Example: load C:\\plugin.jar");
				System.out.println("unload plugin - unloads a loaded plugin. Example: unload dst3.dynload.PluginTestImpl");
			}
			System.out.print("");
			command = scanner.nextLine();
		}
		
		scanner.close();
		pluginManager.stop();
		System.out.println("Application exited");
	}
}
