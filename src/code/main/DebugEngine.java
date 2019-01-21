package code.main;

import code.debug.CurrentOptions;
import code.debug.DebugFrame;
import code.source.Group;
import code.source.Profile;
import code.source.Server;
import code.source.Settings;

public class DebugEngine implements Runnable{
	Engine engine;
	Settings settings;
	CurrentOptions curopt;
	
	DebugFrame dframe;
	
	public DebugEngine(Engine parEngine) {

		engine = parEngine;
		settings = engine.getSettings();
		
		if(Boolean.parseBoolean(settings.Get("Debug"))) {			
			curopt = new CurrentOptions(engine);

			dframe = new DebugFrame(curopt);
			
			Thread thread = new Thread(this);
			thread.start();
			
			dframe.Maximize();
		}else {
			System.out.println(settings.Get("Debug"));
		}
	}
	
	@Override
	public void run() {
		while(true) {
			
			curopt.Update();
			Update();
			dframe.Update();
			
			try {
				Thread.sleep(1000);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Update() {
		if(engine.getProfileManager().getCurrentProfile() != null) {
			Profile CurProfile = engine.getProfileManager().getCurrentProfile();
			
			dframe.CurProfileID = String.valueOf(CurProfile.getID());
			dframe.CurProfileCaption = CurProfile.getCaption();
			dframe.CurProfileDefault = String.valueOf(CurProfile.isDefault());
			
			if(CurProfile.getCurrentGroup() != null) {
				Group CurGroup = CurProfile.getCurrentGroup();
				dframe.CurGroupID = String.valueOf(CurGroup.GetID());
				dframe.CurGroupCaption = CurGroup.GetCaption();
				dframe.CurGroupProfile = String.valueOf(CurGroup.GetProfileID());
				
				if(CurGroup.GetCurrentServer() != null) {
					Server CurServer = CurGroup.GetCurrentServer();
					
					dframe.CurServerID = String.valueOf(CurServer.getID());
					dframe.CurServerCaption = CurServer.getCaption();
					dframe.CurServerGroup = String.valueOf(CurServer.getGroupID());
					
				}else {
					dframe.CurServerID = "null";
					dframe.CurServerCaption = "";
					dframe.CurServerGroup = "";
					
				}
				
			}else {
				dframe.CurGroupID = "null";
				dframe.CurGroupCaption = "";
				dframe.CurGroupProfile = "";
				
				dframe.CurServerID = "null";
				dframe.CurServerCaption = "";
				dframe.CurServerGroup = "";

			}
			
		}else {
			dframe.CurProfileID = "null";
			dframe.CurProfileCaption = "";
			dframe.CurProfileDefault = "";
			
			dframe.CurGroupID = "null";
			dframe.CurGroupCaption = "";
			dframe.CurGroupProfile = "";
			
			dframe.CurServerID = "null";
			dframe.CurServerCaption = "";
			dframe.CurServerGroup = "";

		}
	}
	
	public void Maximize() {
		dframe.Maximize();
	}
}
