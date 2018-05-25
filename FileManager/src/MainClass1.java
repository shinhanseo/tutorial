import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MainClass1 extends JFrame {
	JPanel p1 = new JPanel(new BorderLayout());
	JPanel p2 = new JPanel(new BorderLayout());
	JPanel p3 = new JPanel(new BorderLayout());
	JPanel pan = new JPanel(new BorderLayout());
	JComboBox<String> ch1 = new JComboBox();
	JLabel l1 = new JLabel("File Explorer");
	Container ctp;
	JPopupMenu jpm;
	JMenuItem jmiShowItemintheFolder;
	JMenuItem jmiCopy;
	JMenuItem jmiPaste;
	JMenuItem jmiDelete;
	JScrollPane jsp = new JScrollPane();
	// JPopupMenu;
	File[] files = null;
	JTree tree;
	JScrollPane scroll= new JScrollPane();
	private DefaultMutableTreeNode leaf3;
	private DefaultMutableTreeNode leaf1;
	private DefaultTreeModel treeModel;
	private String colName[] = { "Name", "Size", "Modified"};
	DefaultTableModel model;
	JTable table;
	JScrollPane s2 = new JScrollPane();
	File[] files1 = null;
	void Model(){
		
		Object filedata[][] = new Object[files.length][3];
		for(int i=0;i<files.length;i=i+1) {
			files[i].getName();
			files[i].length();
			files[i].lastModified();  
		
		filedata[i][0] = files[i].getName();
		filedata[i][1] = files[i].length();
		SimpleDateFormat test = new SimpleDateFormat("d/M/yyyy hh:mm:ss");
		filedata[i][2] = test.format(files[i].lastModified()); }
		table = new JTable(filedata,colName);
		s2.setViewportView(table);
		pan.add(new JScrollPane(table));
	}
	
	public void makeGUI() {
		
		jpm=new JPopupMenu();
		jpm.add(jmiShowItemintheFolder);
		jpm.addSeparator();
		jpm.add(jmiCopy);
		jpm.add(jmiPaste); 
		jmiPaste.setEnabled(false);
		jpm.addSeparator();
		jpm.add(jmiDelete);
		table.add(jpm);
		table.addMouseListener(new MouseAdapter() {
			
			public void mousePressed(java.awt.event.MouseEvent me) { 
				if(me.getModifiers() == me.BUTTON3_MASK) {
					jpm.show(ctp,me.getX(),me.getY());
					jpm.setVisible(true);
				}
				
			}
		});
	}
	
	public MainClass1() {
		super("/home/");
		ctp = getContentPane();
		jpm = new JPopupMenu("Edit");
		jmiShowItemintheFolder = new JMenuItem("Show Item in the Folder");
		jmiCopy = new JMenuItem("Copy");
		jmiPaste = new JMenuItem("Paste");
		jmiDelete= new JMenuItem("Delete");
		DefaultMutableTreeNode branch = new DefaultMutableTreeNode("내 컴퓨터");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("디스크 드라이브(C:)");
		treeModel = new DefaultTreeModel(branch);
		tree = new JTree(treeModel);
		File dir = new File("C:\\");
		files = dir.listFiles(); // 객체로 받옴
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) { // 디렉토리 받기
				return file.isDirectory();
			}
		};
		
		files = dir.listFiles(fileFilter);
		if (files.length == 0) {
			System.out.println("Either dir does not exist or is not a directory");
			JOptionPane.showMessageDialog(null, "폴더가 존재하지 않습니다.");
		} else {
			for (int i = 0; i < files.length; i++) {
				File filename = files[i];
				if (filename.toString().contains("$") || filename.toString().contains("Recovery")
						|| filename.toString().contains("System") || filename.toString().contains("Temp")
						|| filename.toString().contains("PerfLogs"))

					continue;
				else {	
						treeModel = new DefaultTreeModel(root);
						treeModel.insertNodeInto(root, branch, 0);
						Object a = filename;
						String w = a.toString();
						String e[] = w.split("\\\\");
						leaf3 = new DefaultMutableTreeNode(e[1]);
						root.add(leaf3);
					}
				}
			}
		
		
	 	ch1.addItem("English");
		ch1.addItem("한국어");	
		add(p1,BorderLayout.SOUTH);
		add(p2,BorderLayout.WEST);
		add(p3,BorderLayout.NORTH);
		add(pan,BorderLayout.EAST);
		p1.add(l1,BorderLayout.WEST);
		p1.add(ch1,BorderLayout.EAST);
		scroll.setPreferredSize(new Dimension(200,-1));
		scroll.setViewportView(tree);
		p2.add(scroll, BorderLayout.CENTER);
		
		Model();
		ChangecolName();
		
		this.add(pan);	
		setSize(750,600);
		setVisible(true);
		makeGUI();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void setVisible(int i, int j) {
		// TODO Auto-generated method stub
		
		
	}

	public static void main(String[] args) {
		 new MainClass1();
	}
	
				
	void ChangecolName() {
		ch1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().equals("한국어")){
					l1.setText("파일 탐색기");
					colName[0] = "이름";
					colName[1] = "크기";
					colName[2] = "수정한 날짜";	
					Model();
					jmiShowItemintheFolder = new JMenuItem("폴더에 항목 표시");
					jmiCopy = new JMenuItem("복사");
					jmiPaste = new JMenuItem("붙여넣기");
					jmiDelete= new JMenuItem("삭제");
					makeGUI();
				}
				else{
					l1.setText("File Manager");
					colName[0] = "Name";
					colName[1] = "Size";
					colName[2] = "Modified";	
					Model();
					jmiShowItemintheFolder = new JMenuItem("Show Item in the Folder");
					jmiCopy = new JMenuItem("Copy");
					jmiPaste = new JMenuItem("Paste");
					jmiDelete= new JMenuItem("Delete");
					makeGUI();
				
				}
			}
		});
	}
}
