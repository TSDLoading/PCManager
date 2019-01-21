package code.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import code.main.Engine;
import code.source.Server;

public class ServerListModel extends DefaultListModel<String>{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	
	ArrayList<Server> Serverlist;
	
	public ServerListModel(Engine parEngine) {
		engine = parEngine;
		Serverlist = new ArrayList<Server>();
		
		UpdateContent();
	}
	
	public Server getServerAt(int index) {
		if(index <= Serverlist.size() && index >= 0) {
			return Serverlist.get(index);
		}else {
			return null;
		}
	}
	
	public void UpdateContent() {
		this.removeAllElements();
		Serverlist.removeAll(Serverlist);
		
		if(engine.GetServerList() != null) {
			for(int s = 0; s < engine.GetServerList().size(); s++) {
				this.addElement(engine.GetServerList().get(s).getCaption());
				Serverlist.add(engine.GetServerList().get(s));
				
				engine.debug("ServerListModel adding Server " + engine.GetServerList().get(s).getID() + " " + engine.GetServerList().get(s).getCaption());
			}
		}
	}

}
