package code.model;

import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import code.main.Engine;
import code.panels.ContentPanel;

public class JListServer extends JList<String> implements ListSelectionListener{
	private static final long serialVersionUID = 1L;
	
	Engine engine;
	ContentPanel contentpanel;
	
	ServerListModel listmodel;
	
	public JListServer(Engine parEngine, ContentPanel parContentPanel) {
		engine = parEngine;
		contentpanel = parContentPanel;
		
		listmodel = new ServerListModel(engine);
		
		UpdateContent();
		
		this.setModel(listmodel);
		
		this.addListSelectionListener(this);
	}
	
	public void UpdateContent() {
		if(contentpanel.CurrentGroup != null) {
		
			listmodel.UpdateContent();
			
			if(this.getSelectedIndex() < 0) {
				this.setSelectedIndex(0);
			}
			
//			contentpanel.SetCurrentServer(listmodel.getServerAt(getSelectedIndex()));
		} else {
			engine.log("Keine Gruppe ausgewählt");
			contentpanel.SetCurrentServer(null);
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent lse) {
		if(listmodel.size() > 0) {
			engine.debug(this.getSelectedIndex());
			
			contentpanel.CurrentServer = listmodel.getServerAt(this.getSelectedIndex());
			contentpanel.PushCurrent();
			contentpanel.UpdateInfo();
		}			
	}

}
