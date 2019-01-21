package code.main;

import java.util.ArrayList;

import code.source.Comment;
import code.source.Server;

public class CommentManager {
	GeneralManager manager;
	Engine engine;
	
	ProfileManager profilemgr;
	GroupManager groupmgr;
	ServerManager servermgr;
//	CommentManager commentmgr;
	
	ArrayList<Comment> Commentlist;
	ArrayList<Integer> Comments;
	
	public CommentManager(GeneralManager parManager) {
		manager = parManager;
		engine = manager.getEngine();
		
		Commentlist = new ArrayList<Comment>();
		Comments = new ArrayList<Integer>();

	}
	
	public void CallManagers() {
		profilemgr = manager.getProfileManager();
		groupmgr = manager.getGroupManager();
		servermgr = manager.getServerManager();
//		commentmgr = manager.getCommentManager();
	}
	
	public void AddServerComments(Server parServer) {
		for(int s = 0; s < parServer.GetCommentList().size(); s++) {
			if(!Comments.contains(parServer.GetCommentList().get(s).getID())) {
				Commentlist.add(parServer.GetCommentList().get(s));
				Comments.add(parServer.GetCommentList().get(s).getID());
			}
		}
	}

	
	//Delete
	public void deleteComment(int parID) {
		if(Comments.contains(parID)) {
			servermgr.deleteCommentForServer(Commentlist.get(Comments.indexOf(parID)).getServerID(), Commentlist.get(Comments.indexOf(parID)).getID());
		}
	}
}
