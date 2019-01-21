package code.IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import Interface.global;
import code.main.Engine;
import code.main.GeneralManager;
import code.source.Comment;
import code.source.Group;
import code.source.Profile;
import code.source.Server;
import code.source.Settings;

public class IO {
	Engine engine;
	Settings settings;
	
	GeneralManager manager;
	
	File Profilefile;
	File Serverfile;
	File Groupfile;	
	File Commentfile;
	
	ArrayList<Profile> Profilelist;
	ArrayList<Group> Grouplist;
	ArrayList<Server> Serverlist;
	ArrayList<Comment> Commentlist;
	
	public IO(Engine parEngine) {
		engine = parEngine;
		settings = engine.getSettings();
		manager = engine.getGeneralManager();
	}
	


	public void LoadLists() {
		try {
			Profilefile = new File(settings.Get("Profilelist"));
			Groupfile = new File(settings.Get("Grouplist"));
			Serverfile = new File(settings.Get("Serverlist"));
			Commentfile = new File(settings.Get("Commentlist"));
			
			if(!Profilefile.exists()) {
				Profilefile.createNewFile();
			}
			
			if(!Groupfile.exists()) {
				Groupfile.createNewFile();
			}
			
			if(!Serverfile.exists()) {
				Serverfile.createNewFile();
			}
			
			if(!Commentfile.exists()) {
				Commentfile.createNewFile();
			}
			
			FileReader pfr = new FileReader(Profilefile);
			FileReader gfr = new FileReader(Groupfile);
			FileReader sfr = new FileReader(Serverfile);
			FileReader cfr = new FileReader(Commentfile);
			
			BufferedReader pbr = new BufferedReader(pfr);
			BufferedReader gbr = new BufferedReader(gfr);
			BufferedReader sbr = new BufferedReader(sfr);
			BufferedReader cbr = new BufferedReader(cfr);
			
			//Profiles
			String line = "";
			
			while((line = pbr.readLine()) != null) {
				engine.debug(line);
				manager.addProfile(line.split(settings.getProperty("FileSplitter")));
			}
			
			if (manager.getProfileManager().getProfileList().size() == 0){
				engine.debug("No profiles in list. Creating Standard profile");
				manager.addProfile(0,"Standard",true);
			}
			
			//Groups
			line = "";

			while((line = gbr.readLine()) != null) {
				engine.debug(line);
				manager.addGroup(line.split(settings.getProperty("FileSplitter")));
			}
			
			//Servers
			line = "";
			
			while((line = sbr.readLine()) != null) {
				engine.debug(line);
				manager.addServer(line.split(settings.getProperty("FileSplitter")));
			}
			
			//Comments
			line = "";
			
			while((line = cbr.readLine()) != null) {
				engine.debug(line);
				manager.addComment(line.split(settings.getProperty("FileSplitter")));
			}
			
			manager.PushLists();
			
			pbr.close();
			gbr.close();
			sbr.close();
			cbr.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			engine.log("Loading Serverlist failed. Please try again or check console");
			settings.RestoreDefaults();
			engine.message("Critical error. Settings restored. Please reboot " + global.WindowTitle);
		}
	}
	
	public void SaveLists() {
		try {
			Profilefile = new File(settings.Get("Profilelist"));
			Groupfile = new File(settings.Get("Grouplist"));
			Serverfile = new File(settings.Get("Serverlist"));
			Commentfile = new File(settings.Get("Commentlist"));
			
			if(!Profilefile.exists()) {
				Profilefile.createNewFile();
			}
			
			if(!Groupfile.exists()) {
				Groupfile.createNewFile();
			}
			
			if(!Serverfile.exists()) {
				Serverfile.createNewFile();
			}
			
			if(!Commentfile.exists()) {
				Commentfile.createNewFile();
			}
			
			FileWriter pfw = new FileWriter(Profilefile);
			FileWriter gfw = new FileWriter(Groupfile);
			FileWriter sfw = new FileWriter(Serverfile);
			FileWriter cfw = new FileWriter(Commentfile);
			
			BufferedWriter pbw = new BufferedWriter(pfw);
			BufferedWriter gbw = new BufferedWriter(gfw);
			BufferedWriter sbw = new BufferedWriter(sfw);
			BufferedWriter cbw = new BufferedWriter(cfw);
			
			for(int p = 0; p < manager.getProfileManager().getProfileList().size(); p++) {
				engine.debug("Saving Profile: " + AppendProfile(manager.getProfileManager().getProfileList().get(p)));
				Profile CurProfile = manager.getProfileManager().getProfileList().get(p);
				
				pbw.write(AppendProfile(CurProfile));
				pbw.newLine();
				
				for(int g = 0; g < CurProfile.GetGrouplist().size(); g++) {
					Group CurGroup = CurProfile.GetGrouplist().get(g);
					
					engine.debug("Saving Group: " + AppendGroup(CurGroup));
					gbw.write(AppendGroup(CurGroup));
					gbw.newLine();
					
					for(int s = 0; s < CurGroup.GetServerList().size(); s++) {
						Server CurServer = CurGroup.GetServerList().get(s);
						
						engine.debug("Saving Server: " + AppendServer(CurServer));
						sbw.write(AppendServer(CurServer));
						sbw.newLine();
						
						for(int c = 0; c < CurServer.GetCommentList().size(); c++) {
							Comment CurComment = CurServer.GetCommentList().get(c);
							cbw.write(AppendComment(CurComment));
							cbw.newLine();
						}
					}
				}
			}
			
			pbw.flush();
			gbw.flush();
			sbw.flush();
			cbw.flush();
			
			pbw.close();
			gbw.close();
			sbw.close();
			cbw.close();
			
		} catch (Exception e) {
			engine.log(e.getStackTrace());
		}
	}
	
	private String AppendProfile(Profile parProfile) {
		String tmp = String.valueOf(parProfile.getID());
		tmp = tmp + ";" + parProfile.getCaption();
		tmp = tmp + ";" + String.valueOf(parProfile.isDefault());
		
		return tmp;
	}
	
	private String AppendGroup(Group parGroup) {
		String tmp = String.valueOf(parGroup.GetID());
		tmp = tmp + ";" + parGroup.GetCaption();
		tmp = tmp + ";" + parGroup.GetProfileID();
		return tmp;
	}
	
	private String AppendServer(Server parServer) {
		String serv = "";
		
		serv = serv + parServer.getID(); 				//ID
		serv = serv + ";" + parServer.getURL();			//URL
		serv = serv + ";" + parServer.getCaption();		//Caption
		serv = serv + ";" + parServer.getGroupID();		//Group
		serv = serv + ";" + parServer.getVPN();			//VPN
		serv = serv + ";" + parServer.getUser();		//User
		serv = serv + ";" + parServer.isFullscreen();	//Fullscreen
		serv = serv + ";" + parServer.isMultimon();		//Multimon
		serv = serv + ";" + parServer.isSpan();			//Span
		serv = serv + ";" + parServer.isAdmin();		//Admin
		serv = serv + ";" + parServer.isPublic();		//Public
		serv = serv + ";" + parServer.getWidth();		//Width
		serv = serv + ";" + parServer.getHeight();		//Height

		return serv;
	}
	
	private String AppendComment(Comment parComment) {
		String com = "";
		
		com = com + parComment.getServer();
		com = com + ";" + parComment.pos();
		com = com + ";" + parComment.getContent();
		
		return com;
	}
}
