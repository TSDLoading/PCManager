package code.debug;

import javax.swing.DefaultListModel;

import code.main.Engine;
import code.source.Settings;

public class CurrentOptions{

	Engine engine;
	Settings settings;
	
	//models
	public ProfileModel pmodel;
	public GroupModel gmodel;
	public ServerModel smodel;
	public CommentModel cmodel;
	
	public DefaultListModel<String> cpmodel;
	public DefaultListModel<String> cgmodel;
	public DefaultListModel<String> csmodel;
	public DefaultListModel<String> ccmodel;
	
	public CurrentOptions(Engine parEngine) {
		engine = parEngine;
		settings = engine.getSettings();
		
		//models
		pmodel = new ProfileModel(this);
		gmodel = new GroupModel(this);
		smodel = new ServerModel(this);
		cmodel = new CommentModel(this);
		
		cpmodel = new DefaultListModel<String>();
		cgmodel = new DefaultListModel<String>();
		csmodel = new DefaultListModel<String>();
		ccmodel = new DefaultListModel<String>();
		

		

	}
	
	public void Update() {
		pmodel.UpdateContent();
		gmodel.UpdateContent();
		smodel.UpdateContent();
		cmodel.UpdateContent();
		
		cpmodel.removeAllElements();
		if(engine.GetProfileList() != null) {
			for(int p = 0; p < engine.GetProfileList().size(); p++) {
				cpmodel.addElement(engine.GetProfileList().get(p).getCaption());
			}
		}
		
		cgmodel.removeAllElements();
		if(engine.GetGrouplist() != null) {
			for(int g = 0; g < engine.GetGrouplist().size(); g++){
				cgmodel.addElement(engine.GetGrouplist().get(g).GetCaption());
			}
		}
		
		csmodel.removeAllElements();
		if(engine.GetServerList() != null) {
			for(int s = 0; s < engine.GetServerList().size(); s++){
				cgmodel.addElement(engine.GetServerList().get(s).getCaption());
			}
		}
		
		ccmodel.removeAllElements();
		if(engine.GetCommentList() != null) {
			for(int c = 0; c < engine.GetCommentList().size(); c++){
				cgmodel.addElement(engine.GetCommentList().get(c).getContent());
			}
		}
	}
	
	public Engine getEngine() {
		return engine;
	}
}
