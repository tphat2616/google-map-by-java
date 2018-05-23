package ASS2;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

public class MapMini extends JFrame {
	MapAPI api;
	private JTextField textStart;
	private JTextField textEnd;
	private JPanel panelStart;
	private JPanel panelEnd;
	private JScrollPane scrollPane;
	private JList listStart;
	private JList listEnd;
	private JList listFind;
	private JPanel panelFind;
	private JButton buttonAn;
	private int s;
	private int e;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapMini frame = new MapMini();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MapMini() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1897,1011);
		  api= new MapAPI("textmap.txt");
		api.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(api);
		listStart = new JList();
		listStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textStart.setText(listStart.getSelectedValue().toString());
				panelStart.setVisible(false);
			}
		});
		listEnd = new JList();
		listEnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textEnd.setText(listEnd.getSelectedValue().toString());
				panelEnd.setVisible(false);
			}
		});
		listFind = new JList();
		JButton btSearch = new JButton("Tìm");
		btSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String a =textStart.getText().replace(' ', '_');
				String b =textEnd.getText().replace(' ', '_');
				TimKiem(a, b);
				int i=e;
				ArrayList<String> name = new ArrayList<String>();
				while(i!=s){
					name.add(api.vertexName[i]);
					i = api.P[i];
				}
				Collections.reverse(name);
				DefaultListModel<String> model = new DefaultListModel<String>();
				for(int j=0;j<name.size();j++){
					if(model.size()==0){
						model.addElement(name.get(j).replace('_', ' '));
					}
					if(name.get(j).equals("_")){
						continue;
					}
					else{
						int count=0;
						for(int z=0;z<model.size();z++){
							if(name.get(j).replace('_', ' ').equals(model.get(z))){
								count = count+1;
							}
						}
						if(count ==0){
							model.addElement(name.get(j).replace('_', ' '));
						}
					}
				}
				listFind.setModel(model);
				panelFind.setVisible(true);
				buttonAn.setVisible(true);
			}
		});
		
		textStart = new JTextField();
		textStart.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textStart.getText().equals("")==false && textStart.getText().equals(" ")==false){
					ArrayList<String> list = new ArrayList<String>();
					list.clear();
					String chuoi = textStart.getText().replace(' ', '_');
					System.out.println(chuoi.length());
					for(int i =0;i<api.vertexName.length;i++){
						int count =0;
						if(api.vertexName[i].equals("_")){
							continue;
						}
						for (int j=0;j<chuoi.length();j++){
							Character a = chuoi.charAt(j);
							Character b = api.vertexName[i].charAt(j);
							if(a.toString().equals(b.toString())){
								count = count +1;
							}
							else{
								count =0;
							}
						}
						if(count == chuoi.length()){
							if(list.size()==0){
								list.add(api.vertexName[i]);
							}
							else{
								int dem=0;
								for(int k=0;k<list.size();k++){
									if(list.get(k).equals(api.vertexName[i])==true){
										dem=dem+1;
									}
								}
								if(dem==0){
									list.add(api.vertexName[i]);
								}
							}
						}
					}
					DefaultListModel<String> model = new DefaultListModel<String>();
					for(int i=0;i<list.size();i++){
						String s=list.get(i).replace('_', ' ');
						model.addElement(s);
					}
					listStart.setModel(model);
					panelStart.setVisible(true);
				}
				else{
					panelStart.setVisible(false);
				}
			}
		});
		textStart.setColumns(10);
		
		textEnd = new JTextField();
		textEnd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(textEnd.getText().equals("")==false && textEnd.getText().equals(" ")==false){
					ArrayList<String> list = new ArrayList<String>();
					String chuoi = textEnd.getText().replace(' ', '_');
					System.out.println(chuoi.length());
					for(int i =0;i<api.vertexName.length;i++){
						int count =0;
						if(api.vertexName[i].equals("_")){
							continue;
						}
						for (int j=0;j<chuoi.length();j++){
							Character a = chuoi.charAt(j);
							Character b = api.vertexName[i].charAt(j);
							if(a.toString().equals(b.toString())){
								count = count +1;
							}
							else{
								count =0;
							}
						}
						if(count == chuoi.length()){
							if(list.size()==0){
								list.add(api.vertexName[i]);
							}
							else{
								int dem=0;
								for(int k=0;k<list.size();k++){
									if(list.get(k).equals(api.vertexName[i])==true){
										dem=dem+1;
									}
								}
								if(dem==0){
									list.add(api.vertexName[i]);
								}
							}
						}
					}
					DefaultListModel<String> model = new DefaultListModel<String>();
					for(int i=0;i<list.size();i++){
						String s=list.get(i).replace('_', ' ');
						model.addElement(s);
					}
					listEnd.setModel(model);
					panelEnd.setVisible(true);
				}
				else{
					panelEnd.setVisible(false);
				}
			}
		});
		textEnd.setColumns(10);
		
		panelStart = new JPanel();
		 panelEnd = new JPanel();
		panelStart.setVisible(false);
		panelEnd.setVisible(false);
		
		panelFind = new JPanel();
		panelFind.setVisible(false);
		
		 buttonAn = new JButton("Ẩn");
		 buttonAn.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		buttonAn.setVisible(false);
		 		panelFind.setVisible(false);
		 	}
		 });
		 buttonAn.setVisible(false);
		GroupLayout gl_contentPane = new GroupLayout(api);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textStart, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelStart, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(textEnd, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btSearch))
						.addComponent(panelEnd, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 983, Short.MAX_VALUE)
					.addComponent(buttonAn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 242, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textStart, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textEnd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btSearch)
								.addComponent(buttonAn))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(panelEnd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelStart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
						.addComponent(panelFind, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(837, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(panelFind);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
		);
		scrollPane_2.setViewportView(listFind);
		panelFind.setLayout(groupLayout);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panelEnd = new GroupLayout(panelEnd);
		gl_panelEnd.setHorizontalGroup(
			gl_panelEnd.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
		);
		gl_panelEnd.setVerticalGroup(
			gl_panelEnd.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEnd.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
		);
		
		scrollPane_1.setViewportView(listEnd);
		panelEnd.setLayout(gl_panelEnd);
		
		scrollPane = new JScrollPane();
		GroupLayout gl_panelStart = new GroupLayout(panelStart);
		gl_panelStart.setHorizontalGroup(
			gl_panelStart.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
		);
		gl_panelStart.setVerticalGroup(
			gl_panelStart.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelStart.createSequentialGroup()
					.addGap(2)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
		);
		
		scrollPane.setViewportView(listStart);
		panelStart.setLayout(gl_panelStart);
		api.setLayout(gl_contentPane);
	}
	private void TimKiem(String start,String end){
		 s=0;
		 e=0;
		for(int i=0;i<api.vertexName.length;i++){
			if(api.vertexName[i].equals(start)){
				 s= i;
				 break;
			}
		}
		for(int i=0;i<api.vertexName.length;i++){
			if(api.vertexName[i].equals(end)){
				 e= i;
				 break;
			}
		}
		api.Dijkstra(s,e);
		api.repaint();
	}
}
