package code.main;

import java.util.ArrayList;

import code.source.Comment;
import code.source.Group;
import code.source.Profile;
import code.source.Server;
import code.source.Settings;

public class ProfileManager {
	GeneralManager manager;
	Engine engine;
	Settings settings;
	
	Profile CurrentProfile;
	ArrayList<Profile> Profilelist;
	ArrayList<Integer> Profiles;
	
//	ProfileManager profilemgr;
	GroupManager groupmgr;
	ServerManager servermgr;
	CommentManager commentmgr;
	
	public ProfileManager(GeneralManager parManager) {
		manager = parManager;
		engine = manager.getEngine();
		settings = engine.getSettings();
		
		Profilelist = new ArrayList<Profile>();
		Profiles = new ArrayList<Integer>();
		
		CurrentProfile = null;
	}
	
	public void CallManagers() {
//		profilemgr = manager.getProfileManager();
		groupmgr = manager.getGroupManager();
		servermgr = manager.getServerManager();
		commentmgr = manager.getCommentManager();
	}
	
	public void AddProfile(Profile parProfile) {
		if(!Profiles.contains(parProfile.getID())) {
			Profilelist.add(parProfile);
			Profiles.add(parProfile.getID());
			groupmgr.AddProfileGroups(parProfile);
		}
	}
	
	
	
	
	
	

	
	public int GetNextProfileID() {
		int max = 0;
		for(int x = 0; x < ProfileList.size(); x++) {
			int cur = ProfileList.get(x).getID();
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	//Search Group in Current Profile, if not found go on to GetGroupFromAll
	public Group GetGroupFromID(int parID) {
		if(CurrentProfile.GetGroups().contains(parID)) {
			return CurrentProfile.GetGrouplist().get(CurrentProfile.GetGroups().indexOf(parID));
		}else {
			return GetGroupFromAll(parID);
		}
	}
	
	//if Group not found in Current Profile, then search in every Profile
	public Group GetGroupFromAll(int parID) {
		for(int p = 0; p < ProfileList.size(); p++) {
			Profile CurProfile = ProfileList.get(p);
			
			if (CurProfile.GetGroups().contains(parID)){
				return CurProfile.GetGrouplist().get(CurProfile.GetGroups().indexOf(parID));
			}
		}
		return null;
	}
	
	public Server GetServerFromID(int parID) {
		for(int p = 0; p < ProfileList.size(); p++) {
			Profile CurProfile = ProfileList.get(p);
			if(CurProfile.GetServerFromID(parID) != null) return CurProfile.GetServerFromID(parID);
		}
		return null;
	}
	
	public int GetNextGroupID() {
		int max = 0;
		for(int p = 0; p < ProfileList.size(); p++) {
			int cur = ProfileList.get(p).GetHighestGroupID();
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	public int GetNextServerID() {
		int max = 0;
		for(int p = 0; p < ProfileList.size(); p++) {
			int cur = ProfileList.get(p).GetHighestServerID();
			if(cur >= max) {
				max = cur;
			}
		}
		
		return max + 1;
	}
	
	public ArrayList<Server> GetCurrentServerlist(){
		if(CurrentProfile != null) {
			if(CurrentProfile.getCurrentGroup() != null) {
				return CurrentProfile.getCurrentGroup().GetServerList();
			}
		}
		return null;
	}
	
	public ArrayList<Comment> GetCurrentCommentlist(){
		if(CurrentProfile != null) {
			if(CurrentProfile.getCurrentGroup() != null) {
				if(CurrentProfile.getCurrentGroup().GetCurrentServer() != null) {
					return CurrentProfile.getCurrentGroup().GetCurrentServer().GetCommentList();
				}
			}
		}
		return null;
	}
	
	public void ChooseProfile() {
		if(CurrentProfile == null) {
			if(ProfileList.size() > 0) {
				CurrentProfile = ProfileList.get(0);
			}else {
				ProfileList.add(new Profile(0,"Standard", true));
				CurrentProfile = ProfileList.get(0);
			}
		}
	}
	
	public ArrayList<Integer> GetCurrentServers(){
		return CurrentProfile.getCurrentGroup().GetServers();
	}
	
	public ArrayList<Group> GetCurrentGrouplist(){
		if(CurrentProfile != null) return CurrentProfile.GetGrouplist();
		return null;
	}
	
	public ArrayList<Integer> GetCurrentGroups(){
		return CurrentProfile.GetGroups();
	}

	public Profile getCurrentProfile() {
		return CurrentProfile;
	}
	
	public void setCurrentProfile(int parProfileID) {
		if(Profiles.contains(parProfileID)){
			setCurrentProfile(ProfileList.get(Profiles.indexOf(parProfileID)));
		}
	}
	
	public void setCurrentProfile(Profile currentProfile) {
		engine.debug("Setting Current Profile: " + currentProfile.getID() + " " + currentProfile.getCaption());
		CurrentProfile = currentProfile;
	}

	public ArrayList<Profile> getProfileList() {
		return Profilelist;
	}
	
	
	//Delete
	public void deleteProfile(int parID) {
		if(Profiles.contains(parID)) {
			Profilelist.remove(Profiles.indexOf(parID));
			Profiles.remove(Profiles.indexOf(parID));
		}
	}

	public void deleteGroupFromProfile(int parProfileID, int parGroupID) {
		if(Profiles.contains(parProfileID)) {
			Profilelist.get(Profiles.indexOf(parProfileID)).removeGroup(parGroupID);
		}
		
	}
}
