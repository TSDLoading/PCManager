package code.main;

import java.util.ArrayList;

import code.source.Comment;
import code.source.Group;
import code.source.Profile;
import code.source.Server;

public class GeneralManager {
	Engine engine;
	
	ProfileManager profilemgr;
	GroupManager groupmgr;
	ServerManager servermgr;
	CommentManager commentmgr;
	
	ArrayList<Profile> Profilelist;
	ArrayList<Group> Grouplist;
	ArrayList<Server> Serverlist;
	ArrayList<Comment> Commentlist;
	
	public GeneralManager(Engine parEngine) {
		engine = parEngine;
		
		profilemgr = new ProfileManager(this);
		groupmgr = new GroupManager(this);
		servermgr = new ServerManager(this);
		commentmgr = new CommentManager(this);
		
		profilemgr.CallManagers();
		groupmgr.CallManagers();
		servermgr.CallManagers();
		commentmgr.CallManagers();
	}
	

	
	//Profiles
	public void addProfile(String[] parProfile) {
		switch(parProfile.length) {
		case 2:
			loadProfile(parProfile[0], parProfile[1]);
			break;
		case 3:
			loadProfile(parProfile[0], parProfile[1], parProfile[2]);
			break;
		default:
			break;
		}
	}
	
	public void loadProfile(String parID, String parName) {
		
		addProfile(Integer.parseInt(parID), parName, false);
	}
	
	public void loadProfile(String parID, String parName, String parDefault) {
		addProfile(Integer.parseInt(parID), parName, Boolean.valueOf(parDefault));
	}
	
	public void addProfile(int parID, String parName, boolean parDefault) {
		Profile tmpProfile = new Profile(parID, parName, parDefault);
		profilemgr.AddProfile(tmpProfile);
	}
	
	public void addGroup(String[] parGroup) {
		switch(parGroup.length) {
		case 3:
			loadGroup(parGroup[0], parGroup[1], parGroup[2]);
		}
	}
	
	public void loadGroup(String parID, String parName, String parProfile) {
		addGroup(Integer.parseInt(parID), parName, Integer.parseInt(parProfile));
	}
	
	public void addGroup(int parID, String parName) {
		addGroup(parID, parName, Profilelist.get(0).getID());
	}
	
	public void addGroup(int parID, String parName, int parProfile) {
		Group tmpGroup = new Group(parID, parName, parProfile);
		Grouplist.add(tmpGroup);
	}
	
	public void addServer(String[] parServer) {
		switch(parServer.length) {
		case 13:
			loadServer(parServer[0],
					parServer[1],
					parServer[2],
					parServer[3],
					parServer[4],
					parServer[5],
					parServer[6],
					parServer[7],
					parServer[8],
					parServer[9],
					parServer[10],
					parServer[11],
					parServer[12]);
			break;
		default:
			break;
		}
	}
	
	public void loadServer(String parID, String parURL, String parCaption, String parGroup, String parVPN, String parUser, String parFullscreen, String parMultimon, String parSpan, String parAdmin, String parPublic, String parWidth, String parHeight) {
		addServer(
				Integer.parseInt(parID), 		//ID
				parURL,							//URL
				parCaption,						//Caption
				Integer.parseInt(parGroup),		//Group
				parVPN,							//VPN
				parUser,						//User
				Boolean.valueOf(parFullscreen),	//Fullscreen
				Boolean.valueOf(parMultimon),	//Multimon
				Boolean.valueOf(parSpan),		//Span
				Boolean.valueOf(parAdmin),		//Admin
				Boolean.valueOf(parPublic),		//Public
				Integer.parseInt(parWidth),		//Width
				Integer.parseInt(parHeight)		//Height
				);
	}
	
	public void addServer(int parID, String parURL, String parCaption, int parGroup, String parVPN, String parUser, boolean parFullscreen, boolean parMultimon, boolean parSpan, boolean parAdmin, boolean parPublic, int parWidth, int parHeight) {
		Server tmpServer = new Server(
				engine,
				parID, 			//ID
				parURL,			//URL
				parCaption,		//Caption
				parGroup,		//Group
				parVPN,			//VPN
				parUser,		//User
				parFullscreen,	//Fullscreen
				parMultimon,	//Multimon
				parSpan,		//Span
				parAdmin,		//Admin
				parPublic,		//Public
				parWidth,		//Width
				parHeight		//Height
				);
		
		Serverlist.add(tmpServer);
		
	}
	
	public void addComment(String [] parComment) {
		switch(parComment.length) {
		case 3:
			loadComment(Integer.parseInt(parComment[0]), Integer.parseInt(parComment[1]), Integer.parseInt(parComment[2]), parComment[3]);
		}
	}
	
	public void loadComment(int parID, int parServer, int parLine, String parContent) {
		Comment tmpComment = new Comment(parID, parServer, parLine, parContent);
		Commentlist.add(tmpComment);
	}
	
	public void DeleteGroup(int parID) {
		groupmgr.deleteGroup(parID);
	}
	
	public void DeleteServer(int parID) {
		servermgr.deleteServer(parID);
	}
	
	
	//Needed once after loading lists into temp ArrayLists
	//Maybe changing it to collecting objects to right path on loading runtime
	public void PushLists() {
		
		for(int g = 0; g < Grouplist.size(); g++) {
			for(int p = 0; p < Profilelist.size(); p++) {
				if(Profilelist.get(p).getID() == Grouplist.get(g).GetID()) {
					Profilelist.get(p).add(Grouplist.get(g));
				}
			}
		}
		
		for(int s = 0; s < Serverlist.size(); s++) {
			for(int p = 0; p < Profilelist.size(); p++) {
				for(int g = 0; g < Profilelist.get(p).GetGrouplist().size(); g++) {
					if(Profilelist.get(p).GetGrouplist().get(g).GetID() == Serverlist.get(s).getGroupID()) {
						Profilelist.get(p).GetGrouplist().get(g).add(Serverlist.get(s));
					}
				}
			}
		}
		
		
		for(int c = 0; c < Commentlist.size(); c++) {
			for(int p = 0; p < Profilelist.size(); p++) {
				for(int g = 0; g < Profilelist.get(p).GetGrouplist().size(); g++) {
					for(int s = 0; s < Profilelist.get(p).GetGrouplist().get(g).GetServerList().size(); s++) {
						if(Profilelist.get(p).GetGrouplist().get(g).GetServerList().get(s).getID() == Commentlist.get(c).getID()) {
							Profilelist.get(p).GetGrouplist().get(g).GetServerList().get(s).addComment(Commentlist.get(c));
						}
					}
				}
			}
		}
		
		for(int p = 0; p < Profilelist.size(); p++) {
			profilemgr.AddProfile(Profilelist.get(p));
		}
	}
	
	//getter & setter
	
	public Engine getEngine() {
		return engine;
	}
	
	public ProfileManager getProfileManager() {
		return profilemgr;
	}
	
	public GroupManager getGroupManager() {
		return groupmgr;
	}
	
	public ServerManager getServerManager() {
		return servermgr;
	}
	
	public CommentManager getCommentManager() {
		return commentmgr;
	}

}
