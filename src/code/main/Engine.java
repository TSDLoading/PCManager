package code.main;

import java.util.ArrayList;

import Interface.global;
import code.GUI.GUI;
import code.GUI.Window;
import code.IO.IO;
import code.IO.Log;
import code.source.Comment;
import code.source.Group;
import code.source.Profile;
import code.source.Server;
import code.source.Settings;

/*
 * Engine just puts requests through to the right Manager
 * 
 * 
 */

public class Engine implements global{
	
	static Settings settings;
	static Window window;
	
	GeneralManager manager;
	
	DebugEngine debugengine;
	
	GUI gui;
	IO io;
	Log log;
	
	public Engine() {
		settings = new Settings(this);
		log = new Log(this);
		gui = new GUI();
		
		manager = new GeneralManager(this);
				
		io = new IO(this);
		
		io.LoadLists();
		if(profilemgr.getCurrentProfile() == null){
			profilemgr.ChooseProfile();
		}
		
		debugengine = new DebugEngine(this);
		
		window = new Window(this, settings);
		
//		UpdateLists();
	}
	
	public void ClearLog() {
		if(confirm("Möchten Sie das Log löschen?")) {
			log.clear();
		}
	}
	
	public void UpdateLists() {
		io.SaveLists();
		io.LoadLists();
	}
	
	public ArrayList<Profile> GetProfileList(){
		return profilemgr.getProfileList();
	}
	
	public void SetCurrentProfile(int profileID) {
		profilemgr.setCurrentProfile(profileID);
	}
	
	public void CreateProfile(String parName) {
		profilemgr.addProfile(profilemgr.GetNextProfileID(), parName, false);
		UpdateLists();
	}

	
	public Server GetServerFromID(int parID) {
		return(profilemgr.GetServerFromID(parID));
	}
	
	public int GetNextProfileID() {
		return profilemgr.GetNextProfileID();
	}
	
	public int GetNextGroupID() {
		return profilemgr.GetNextGroupID();
	}

	
	public int GetNextServerID() {
		return profilemgr.GetNextServerID();
	}
	
	public String[] GatherGroups() {
		String[] GroupCaps = new String[GetGrouplist().size()];
		for(int x = 0; x < GetGrouplist().size(); x++) {
			GroupCaps[x] = GetGrouplist().get(x).GetCaption();
		}
		return GroupCaps;
	}
	
	public ArrayList<Integer> GetGroups(){
		return profilemgr.GetCurrentGroups();
	}
	
	public ArrayList<Group> GetGrouplist(){
		return profilemgr.GetCurrentGrouplist();
	}
	
	public ArrayList<Integer> GetServers(){
		return profilemgr.GetCurrentServers();
	}
	
	public ArrayList<Server> GetServerList(){
		return profilemgr.GetCurrentServerlist();
	}
	
	public ArrayList<Comment> GetCommentList(){
		return profilemgr.GetCurrentCommentlist();
	}

	public void addGroup(String parName) {
		addGroup(GetNextGroupID(), parName);
	}
	
	public void addGroup(int parID, String parName) {
		profilemgr.addGroup(parID, parName);
		UpdateLists();
	}
	
	public void deleteGroup(int parID) {
		profilemgr.DeleteGroup(parID);
	}
	
	public void AddServer(Server parServer) {
		if(profilemgr.getCurrentProfile().getCurrentGroup() != null) {
			
			debug("Profile: " + profilemgr.getCurrentProfile().getID() + " - " + profilemgr.getCurrentProfile().getCaption());
			debug("Gruppe: " + profilemgr.getCurrentProfile().getCurrentGroup().GetID() + " - " + profilemgr.getCurrentProfile().getCurrentGroup().GetCaption());
			
			profilemgr.addServer(
					parServer.getID(), 			//ID
					parServer.getURL(),			//URL
					parServer.getCaption(),		//Caption
					parServer.getGroup(),		//Group
					parServer.getVPN(),			//VPN
					parServer.getUser(),		//User
					parServer.isFullscreen(),	//Fullscreen
					parServer.isMultimon(),		//Multimon
					parServer.isSpan(),			//Span
					parServer.isAdmin(),		//Admin
					parServer.isPublic(),		//Public
					parServer.getWidth(),		//Width
					parServer.getHeight());		//Height
			
		}else {
			log("Please choose a group");
		}
	}
	

	
	public void deleteServer(int parID) {
		profilemgr.DeleteServer(parID);
	}
	
	public void SetCurrent(Group CurGroup, Server CurServer) {
		profilemgr.getCurrentProfile().SetCurrentGroup(CurGroup);
		if(profilemgr.getCurrentProfile().getCurrentGroup() != null) {
			profilemgr.getCurrentProfile().getCurrentGroup().SetCurrentServer(CurServer);
		}
	}
	
	
	
	
	
	
	//Debugging and GUI outputs
	public void log(String message) {
		log.LogMessage(message);
	}
	
	public void log(int message) {
		log.LogMessage(String.valueOf(message));
	}
	
	public void log(Object o) {
		log.LogMessage(o.toString());
	}
	
	public void debug(String message) {
		if(Boolean.valueOf(settings.Get("Debug")))log.LogMessage(message);
	}
	
	public void debug(int message) {
		if(Boolean.valueOf(settings.Get("Debug")))log.LogMessage(String.valueOf(message));
	}
	
	public void debug(Object o) {
		if(Boolean.valueOf(settings.Get("Debug")))log.LogMessage(o.toString());
	}

	public boolean confirm(String qst) {
		return gui.confirm(qst);
	}

	public void message(String msg) {
		gui.message(msg);
	}
	
	public String input(String qst) {
		return gui.input(qst);
	}
	
	public Settings getSettings() {
		return settings;
	}
	
	
	
	
	//Managers
	public DebugEngine getDebugEngine() {
		return debugengine;
	}
	
	public GeneralManager getGeneralManager() {
		return manager;
	}
	
}
