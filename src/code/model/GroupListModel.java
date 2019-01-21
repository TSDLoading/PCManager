package code.model;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import code.main.Engine;
import code.source.Group;

public class GroupListModel extends DefaultListModel<String> {
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	
	ArrayList<Group> Grouplist;
	
	public GroupListModel(Engine parEngine) {
		engine = parEngine;
		Grouplist = new ArrayList<Group>();
		
		UpdateContent();
	}
	
	public Group getGroupAt(int index) {
		if(index <= Grouplist.size() && index >= 0) {
			return Grouplist.get(index);
		}else {
			return null;
		}
	}
	
	public void UpdateContent() {
		this.removeAllElements();
		Grouplist.removeAll(Grouplist);
		
		if(engine.GetGrouplist() != null) {
			for(int g = 0; g < engine.GetGrouplist().size();g++) {
				this.addElement(engine.GetGrouplist().get(g).GetCaption());
				Grouplist.add(engine.GetGrouplist().get(g));
				
				engine.debug("GroupListModel adding Group " + engine.GetGrouplist().get(g).GetID() + " " + engine.GetGrouplist().get(g).GetCaption());
			}
		}
	}

}
