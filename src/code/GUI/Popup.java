package code.GUI;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import code.main.Engine;
import code.source.Group;
import code.source.Server;
import code.source.Settings;

public class Popup extends PopupMenu implements PopupMenuListener, ActionListener{
	private static final long serialVersionUID = 1L;
	
	Window window;
	Engine engine;
	Settings settings;
	
	MenuItem miDebug;
	MenuItem miBeenden;
	MenuItem miSettings;
	
	Menu menuProfile;
	
	ArrayList<Menu> Groupmenus;
	ArrayList<ArrayList<MenuItem>> Serveritems;
	
	public Popup(Window parWindow) {
		window = parWindow;
		engine = window.getEngine();
		settings = engine.getSettings();
		
		engine.debug("Init Popup");
				
		UpdatePopup();
	}
	
	public void UpdateMenuItems() {
		Groupmenus = new ArrayList<Menu>();
		
		menuProfile = new Menu("Profile");
		
		if(engine.GetGrouplist() != null) {
			for(int x = 0; x < engine.GetGrouplist().size();x++) {
				
				Group CurrentGroup = engine.GetGrouplist().get(x);
				Groupmenus.add(new Menu(CurrentGroup.GetCaption()));
				
				ArrayList<Server> CurrentServerList = CurrentGroup.getListAsArrayList();
				
				for(int y = 0; y < CurrentServerList.size(); y++) {
					MenuItem mi = new MenuItem(CurrentServerList.get(y).getCaption());
					mi.setActionCommand("Server;" + String.valueOf(CurrentServerList.get(y).getID()));
					mi.addActionListener(this);
					Groupmenus.get(x).add(mi);
				}
			}
		}
		
		if(engine.GetProfileList() != null) {
			for(int p = 0; p < engine.GetProfileList().size(); p++) {
				MenuItem mi = new MenuItem(engine.GetProfileList().get(p).getCaption());
				mi.setActionCommand("Profile;" + String.valueOf(engine.GetProfileList().get(p).getID()));
				mi.addActionListener(this);
				menuProfile.add(mi);
			}
		}
			
		miDebug = new MenuItem("Debug");
		miDebug.setActionCommand("Debug");
		miDebug.addActionListener(this);
		
		miSettings = new MenuItem("Einstellungen");
		miSettings.setActionCommand("Settings");
		miSettings.addActionListener(this);
		
		miBeenden = new MenuItem("Beenden");
		miBeenden.setActionCommand("Exit");
		miBeenden.addActionListener(this);
	}
	
	public void UpdatePopup() {
		removeAll();
		
		engine.debug("Jetzt");
		UpdateMenuItems();
		
		for(int x = 0; x < Groupmenus.size(); x++) {
			add(Groupmenus.get(x));
		}
		
		if(Boolean.parseBoolean(settings.Get("Debug"))) {
			addSeparator();
			add(miDebug);
		}
		
		addSeparator();
		add(menuProfile);
		add(miSettings);
		add(miBeenden);	
		
	}

	@Override
	public void popupMenuCanceled(PopupMenuEvent e) {		
	}

	@Override
	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {		
	}

	@Override
	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
		UpdatePopup();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getActionCommand() == "Exit") {
			System.exit(0);
		}else if(ae.getActionCommand() == "Settings") {
			window.Maximize();
		}else if(ae.getActionCommand() == "Debug"){
			engine.GetDebugEngine().Maximize();
		}else {
			String[] cmd = ae.getActionCommand().split(";");
			
			switch(cmd[0]) {
			case "Profile":
				engine.SetCurrentProfile(Integer.parseInt(cmd[1]));
				break;
			case "Server":
				Server CurrentServer = engine.GetServerFromID(Integer.parseInt(cmd[1]));
				CurrentServer.connect();
				break;
			}
		}
		
	}

}
