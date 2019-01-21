package code.debug;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Toolkit;

public class DebugFrame implements WindowListener{

	private JFrame frame;
	private JTable ProfileTable;
	private JTable GroupTable;
	private JTable ServerTable;
	private JTable table_3;
	
	private CurrentOptions opt;
	private JTable CommentTable;
	
	private JLabel pflcapID;
	private JLabel pflcapCaption;
	private JLabel pflcapDefault;
	
	private JLabel grpcapID;
	private JLabel grpcapCaption;
	private JLabel grpcapProfile;
	
	private JLabel srvcapID;
	private JLabel srvcapCaption;
	private JLabel srvcapGroup;
	
	
	public String CurProfileID;
	public String CurProfileCaption;
	public String CurProfileDefault;
	
	public String CurGroupID;
	public String CurGroupCaption;
	public String CurGroupProfile;
	
	public String CurServerID;
	public String CurServerCaption;
	public String CurServerGroup;
	
	/**
	 * Launch the application.
	 */
	public DebugFrame(CurrentOptions parOpt){
		opt = parOpt;
		
		CurProfileID = "";
		CurProfileCaption = "";
		CurProfileDefault = "";
		
		CurGroupID = "";
		CurGroupCaption = "";
		CurGroupProfile = "";
		
		CurServerID = "";
		CurServerCaption = "";
		CurServerGroup = "";
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(DebugFrame.class.getResource("/gfx/DebugIcon.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 930, 527);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(this);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 914, 488);
		frame.getContentPane().add(tabbedPane);
		
		JPanel GeneralTab = new JPanel();
		tabbedPane.addTab("General", null, GeneralTab, null);
		GeneralTab.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel ProfilePanel = new JPanel();
		GeneralTab.add(ProfilePanel);
		ProfilePanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 227, 155);
		ProfilePanel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel pflID = new JLabel("ID");
		panel_1.add(pflID);
		
		pflcapID = new JLabel(CurProfileID);
		panel_1.add(pflcapID);
		
		JLabel pflCaption = new JLabel("Profil");
		panel_1.add(pflCaption);
		
		pflcapCaption = new JLabel(CurProfileCaption);
		panel_1.add(pflcapCaption);
		
		JLabel pflDefault = new JLabel("Default");
		panel_1.add(pflDefault);
		
		pflcapDefault = new JLabel(CurProfileDefault);
		panel_1.add(pflcapDefault);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 154, 227, 306);
		ProfilePanel.add(scrollPane_1);
		
		JList CurProfileList = new JList();
		CurProfileList.setModel(opt.cpmodel);
		scrollPane_1.setViewportView(CurProfileList);
		
		JPanel GroupPanel = new JPanel();
		GeneralTab.add(GroupPanel);
		GroupPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 227, 156);
		GroupPanel.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel grpID = new JLabel("ID");
		panel.add(grpID);
		
		grpcapID = new JLabel(CurGroupID);
		panel.add(grpcapID);
		
		JLabel grpCaption = new JLabel("Caption");
		panel.add(grpCaption);
		
		grpcapCaption = new JLabel(CurGroupCaption);
		panel.add(grpcapCaption);
		
		JLabel grpProfile = new JLabel("Profile");
		panel.add(grpProfile);
		
		grpcapProfile = new JLabel(CurGroupProfile);
		panel.add(grpcapProfile);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 154, 227, 306);
		GroupPanel.add(scrollPane);
		
		JList CurGroupList = new JList();
		CurGroupList.setModel(opt.cgmodel);
		scrollPane.setViewportView(CurGroupList);
		
		JPanel CurrentGroups = new JPanel();
		GeneralTab.add(CurrentGroups);
		CurrentGroups.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 227, 156);
		CurrentGroups.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel srvID = new JLabel("ID");
		panel_2.add(srvID);
		
		srvcapID = new JLabel(CurServerID);
		panel_2.add(srvcapID);
		
		JLabel srvCaption = new JLabel("Caption");
		panel_2.add(srvCaption);
		
		srvcapCaption = new JLabel(CurServerCaption);
		panel_2.add(srvcapCaption);
		
		JLabel srvGroup = new JLabel("Group");
		panel_2.add(srvGroup);
		
		srvcapGroup = new JLabel(CurServerGroup);
		panel_2.add(srvcapGroup);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 154, 227, 306);
		CurrentGroups.add(scrollPane_2);
		
		JList CurServerlist = new JList();
		CurServerlist.setModel(opt.csmodel);
		scrollPane_2.setViewportView(CurServerlist);
		
		JPanel CurrentServers = new JPanel();
		GeneralTab.add(CurrentServers);
		CurrentServers.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 0, 227, 460);
		CurrentServers.add(scrollPane_3);
		
		JList CurCommentList = new JList();
		CurCommentList.setModel(opt.ccmodel);
		scrollPane_3.setViewportView(CurCommentList);
		
		JPanel ProfileTab = new JPanel();
		tabbedPane.addTab("Profile", null, ProfileTab, null);
		ProfileTab.setLayout(null);
		
		JScrollPane ProfilelistComplete = new JScrollPane();
		ProfilelistComplete.setBounds(0, 0, 909, 460);
		ProfileTab.add(ProfilelistComplete);
		
		ProfileTable = new JTable();
		ProfileTable.setModel(opt.pmodel);
		ProfilelistComplete.setViewportView(ProfileTable);
		
		JPanel GroupTab = new JPanel();
		tabbedPane.addTab("Group", null, GroupTab, null);
		GroupTab.setLayout(null);
		
		JScrollPane GrouplistComplete = new JScrollPane();
		GrouplistComplete.setBounds(0, 0, 909, 460);
		GroupTab.add(GrouplistComplete);
		
		GroupTable = new JTable();
		GroupTable.setModel(opt.gmodel);
		GrouplistComplete.setViewportView(GroupTable);
		
		JPanel ServerTab = new JPanel();
		tabbedPane.addTab("Server", null, ServerTab, null);
		ServerTab.setLayout(null);
		
		JScrollPane ServerlistComplete = new JScrollPane();
		ServerlistComplete.setBounds(0, 0, 909, 460);
		ServerTab.add(ServerlistComplete);
		
		ServerTable = new JTable();
		ServerTable.setModel(opt.smodel);
		ServerlistComplete.setViewportView(ServerTable);
		
		JPanel CommentsTab = new JPanel();
		tabbedPane.addTab("Comments", null, CommentsTab, null);
		CommentsTab.setLayout(null);
		
		JScrollPane CommentlistComplete = new JScrollPane();
		CommentlistComplete.setBounds(0, 0, 909, 460);
		CommentsTab.add(CommentlistComplete);
		
		CommentTable = new JTable();
		CommentTable.setModel(opt.cmodel);
		CommentlistComplete.setViewportView(CommentTable);
		
		JPanel SettingsTab = new JPanel();
		tabbedPane.addTab("Settings", null, SettingsTab, null);
		SettingsTab.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(0, 0, 909, 460);
		SettingsTab.add(scrollPane_6);
		
		table_3 = new JTable();
		scrollPane_6.setViewportView(table_3);
	}
	
	public void Update() {
		pflcapID.setText(CurProfileID);
		pflcapCaption.setText(CurProfileCaption);
		pflcapDefault.setText(CurProfileDefault);

		grpcapID.setText(CurGroupID);
		grpcapCaption.setText(CurGroupCaption);
		grpcapProfile.setText(CurGroupProfile);

		srvcapID.setText(CurServerID);
		srvcapCaption.setText(CurServerCaption);
		srvcapGroup.setText(CurServerGroup);
		
	}
	
	public void Maximize() {
		frame.setState(JFrame.NORMAL);
		frame.setVisible(true);
	}
	
	public void Minimize() {
		frame.setState(JFrame.ICONIFIED);
		frame.setVisible(false);
	}

	@Override
	public void windowClosing(WindowEvent we) {
		Minimize();
	}
	
	@Override
	public void windowActivated(WindowEvent we) {		
	}

	@Override
	public void windowClosed(WindowEvent we) {		
	}

	@Override
	public void windowDeactivated(WindowEvent we) {		
	}

	@Override
	public void windowDeiconified(WindowEvent we) {		
	}

	@Override
	public void windowIconified(WindowEvent we) {		
	}

	@Override
	public void windowOpened(WindowEvent we) {		
	}
}
