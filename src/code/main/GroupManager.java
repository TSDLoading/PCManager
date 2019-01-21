package code.main;

import java.util.ArrayList;

import code.source.Group;
import code.source.Profile;

public class GroupManager {
	GeneralManager manager;
	Engine engine;
	
	ProfileManager profilemgr;
//	GroupManager groupmgr;
	ServerManager servermgr;
	CommentManager commentmgr;
	
	ArrayList<Group> Grouplist;
	ArrayList<Integer> Groups;
	
	public GroupManager(GeneralManager parManager) {
		manager = parManager;
		engine = manager.getEngine();

		Grouplist = new ArrayList<Group>();
		Groups = new ArrayList<Integer>();
	}
	
	public void CallManagers() {
		profilemgr = manager.getProfileManager();
//		groupmgr = manager.getGroupManager();
		servermgr = manager.getServerManager();
		commentmgr = manager.getCommentManager();
	}
	
	public void AddProfileGroups(Profile parProfile) {
		for(int p = 0; p < parProfile.GetGrouplist().size(); p++) {
			if(!Groups.contains(parProfile.GetGrouplist().get(p).GetID())) {
				Grouplist.add(parProfile.GetGrouplist().get(p));
				Groups.add(parProfile.GetGrouplist().get(p).GetID());
				servermgr.AddGroupServers(parProfile.GetGrouplist().get(p));
			}
		}
	}
	
	
	//Delete
	public void deleteGroup(int parID) {
		if(Groups.contains(parID)) {
			profilemgr.deleteGroupFromProfile(Grouplist.get(Groups.indexOf(parID)).GetProfileID(), Grouplist.get(Groups.indexOf(parID)).GetID());
			Grouplist.remove(Groups.indexOf(parID));
			Groups.remove(Groups.indexOf(parID));
		}
	}
	
	public void deleteServerFromGroup(int parGroupID, int parServerID) {
		if(Groups.contains(parGroupID)) {
			Grouplist.get(Groups.indexOf(parGroupID)).removeServer(parServerID);
		}
	}

}
