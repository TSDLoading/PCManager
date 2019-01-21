package Interface;

public interface global {
	String Version = "v2.0";
	boolean Debug = false;
	
	String SettingsPath = System.getProperty("user.home") + "/PCManager/";
	String SettingsFile = "/config.properties";
	int Width = 900;
	int Height = 600;
	
	int WidthOffset = 17;
	int HeightOffset = 62;
	
	boolean IsResizable = true;
	
	String WindowTitle = "PCManager " + Version;
	
	String StandardStr = "Nothing";
	String FileSplitter = ";";
}
