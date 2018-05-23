package ASS2;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class MapAPI extends JPanel implements MouseWheelListener,MouseListener,MouseMotionListener {
	 private int x =0;
	 private int y =0;
	 private int vertex;
	 public String[] vertexName;
	 private int[][] graphMatrix;
	 public Point[] point;
	 private final int MAX = 999999999;
	 private boolean[] S;
	 private int[] L;
	 public int[] P;
	 private int start;
	 private int end;
	 private double zoomFactor = 1;
	 private double prevZoomFactor = 1;
	 private boolean zoomer;
	 private boolean dragger;
	 private boolean released;
	 private double xOffset = 0;
	 private double yOffset = 0;
	 private int xDiff;
	 private int yDiff;
	 private Point startPoint;
	 MapAPI(){
		 addMouseWheelListener(this);
		 addMouseListener(this);
		 addMouseMotionListener(this);
	 }
	 
	public MapAPI(String file){
		 addMouseWheelListener(this);
		 addMouseListener(this);
		 addMouseMotionListener(this);
		 try{
			 File f = new File(file);
			 FileReader fr = new FileReader(f);
			 BufferedReader br =new BufferedReader(fr);
			 this.vertex = Integer.parseInt(br.readLine());
			 this.vertexName = new String[vertex];
			 this.graphMatrix = new int[this.vertex][this.vertex];
			 this.point = new Point[this.vertex];
			 for(int i=0;i<this.vertex;i++){
				 for(int j=0;j<this.vertex;j++){
					 if(i==j){
						 graphMatrix[i][j]=0;
					 }
					 else{
						 graphMatrix[i][j]=MAX;
					 }
				 }
			 }
			 
			 for(int i=0;i<this.vertex;i++){
				 String[] buff = br.readLine().split(" ");
				 int number = Integer.parseInt(buff[0]);
				 for(int j=0;j<number;j++){
					 int v = Integer.parseInt(buff[j*2+1]); // đỉnh kề
					 int w = Integer.parseInt(buff[j*2+2]); // trọng số
					 this.graphMatrix[i][v] = w;
				 }
				 this.vertexName[i]=buff[number*2+1];
				 this.point[i] = new Point();
				 point[i].x=Integer.parseInt(buff[number*2+2]);
				 point[i].y=Integer.parseInt(buff[number*2+3]);
			 }
			 
			 br.close();
			 fr.close();
			 
		 }catch(IOException e){
			 System.out.println(e.getMessage());
		 }
	 }
	 
	 
	 public void Dijkstra(int a,int b){
		 start = a;
		 end = b;
		 S = new boolean[vertex];
		 L = new int[vertex];
		 P = new int[vertex];
		for(int i=0;i<vertex;i++){
			S[i]=false;
			P[i]=a;
			if(i==a){
				L[i]=0;
			}
			else{
				L[i]=MAX;
			}
		}
		int i=0;
		while(S[b]==false){
			for(i=0;i<vertex;i++){
				if(!S[i]==true && L[i] < MAX){
					break;
				}
			}
			if(i>=vertex){
				return;
			}
			for(int j=0;j<vertex;j++){
				if(!S[j] && L[i] > L[j]){
					i=j;
				}
			}
			S[i] = true;
			for(int j=0; j<vertex;j++){
				if(!S[j]==true && L[i]+graphMatrix[i][j] < L[j] && graphMatrix[i][j] != MAX){
					L[j] = L[i] + graphMatrix[i][j];
					P[j]=i;
				}
			}
		}
	}
	 
	 
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		Graphics2D g =  (Graphics2D) arg0;
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHints(rh);
		dragMap(g);
		ImageIcon map = new ImageIcon("gg2.png");
		g.drawImage(map.getImage(),0,0,map.getIconWidth(),map.getIconHeight(),null);
		if(S == null || L == null || P == null){
			System.out.println("Chưa nhập đường");
		}
		else{
			int i = end;
			while(i!=start){
				g.setColor(Color.RED);
				g.setStroke(new BasicStroke(2));
				g.drawLine(point[i].x, point[i].y, point[P[i]].x, point[P[i]].y);
				i = P[i];
			}
		}
	}
	
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		 Point curPoint = arg0.getLocationOnScreen();
	        xDiff = curPoint.x - startPoint.x;
	        yDiff = curPoint.y - startPoint.y;

	        dragger = true;
	        repaint();
	}

	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		x = arg0.getX();
		y = arg0.getY();
		System.out.println("x là :"+x);
		System.out.println("y là :"+y);
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		  released = false;
	        startPoint = MouseInfo.getPointerInfo().getLocation();
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		released = true;
        repaint();
	}

	public void mouseWheelMoved(MouseWheelEvent arg0) {
		// TODO Auto-generated method stub
		  zoomer = true;

	        //Zoom in
	        if (arg0.getWheelRotation() < 0) {
	        	System.out.println(arg0.getWheelRotation());
	            zoomFactor *= 1.1;
	            repaint();
	        }
	        //Zoom out
	        if (arg0.getWheelRotation() > 0) {
	        	System.out.println(arg0.getWheelRotation());
	            zoomFactor /= 1.1;
	            repaint();
	        }
	}
	
	private void dragMap(Graphics2D g){
		if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g.transform(at);
            zoomer = false;
        }

        if (dragger) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            at.scale(zoomFactor, zoomFactor);
            g.transform(at);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
            }

        }
	}
}
