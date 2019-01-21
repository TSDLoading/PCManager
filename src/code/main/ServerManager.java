package code.main;

import java.util.ArrayList;

import code.source.Group;
import code.source.Server;

public class ServerManager {
	GeneralManager manager;
	Engine engine;
	
	ProfileManager profilemgr;
	GroupManager groupmgr;
//	ServerManager servermgr;
	CommentManager commentmgr;
	
	ArrayList<Server> Serverlist;
	ArrayList<Integer> Servers;
	
	public ServerManager(GeneralManager parManager) {
		manager = parManager;
		engine = manager.getEngine();
		
		Serverlist = new ArrayList<Server>();
		Servers = new ArrayList<Integer>();

	}
	
	public void CallManagers() {
		profilemgr = manager.getProfileManager();
		groupmgr = manager.getGroupManager();
//		servermgr = manager.getServerManager();
		commentmgr = manager.getCommentManager();
	}
	
	public void AddGroupServers(Group parGroup) {
		for(int g = 0; g < parGroup.GetServerList().size(); g++) {
			if(!Servers.contains(parGroup.GetServerList().get(g).getID())) {
				Serverlist.add(parGroup.GetServerList().get(g));
				Servers.add(parGroup.GetServerList().get(g).getID());
				commentmgr.AddServerComments(parGroup.GetServerList().get(g));
			}
		}
	}
	
	
	//Delete
	public void deleteServer(int parID) {
		if(Servers.contains(parID)) {
			groupmgr.deleteServerFromGroup(Serverlist.get(Servers.indexOf(parID)).getGroupID(), Serverlist.get(Servers.indexOf(parID)).getID());
			Serverlist.remove(Servers.indexOf(parID));
			Servers.remove(Servers.indexOf(parID));
		}
	}
	
	public void deleteCommentForServer(int parServerID, int parCommentID) {
		if(Servers.contains(parServerID)) {
			Serverlist.get(Servers.indexOf(parServerID)).deleteComment(parCommentID);
		}
	}
}
