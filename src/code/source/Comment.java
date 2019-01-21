package code.source;

public class Comment {
	int ID;
	int Server;
	int Line;
	String Content;
	
	public Comment(int parID, int parServer, int parLine, String parContent) {
		ID = parID;
		Server = parServer;
		Line = parLine;
		Content = parContent;
	}
	
	public int getID() {
		return ID;
	}

	public int pos() {
		return Line;
	}

	public int getServerID() {
		return Server;
	}

	public void setServer(int server) {
		Server = server;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
