import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;



public class PaintPanel extends JPanel {
	public Point mouseCoordinates;
	boolean painting = false;
	static public BufferedImage image;
    private static final int PREF_W = 1000;
    private static final int PREF_H = 550;

	
	public PaintPanel() {
		this.setPreferredSize(new Dimension(1000,550));
		this.setBackground(Color.white);
		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
        image = new BufferedImage(PREF_W, PREF_H, BufferedImage.TYPE_INT_ARGB);
		
		
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.setColor(UtilePanel.col);
        if (image != null) {
            g2D.drawImage(image, 0, 0, this);
            if(mouseCoordinates !=null) {
            if(UtilePanel.tool==2) {
        	 g2D.drawOval((int)mouseCoordinates.getX(),(int)mouseCoordinates.getY(),UtilePanel.brushSize, UtilePanel.brushSize);
            }
            else
            {
            	g2D.drawOval((int)mouseCoordinates.getX(),(int)mouseCoordinates.getY(),UtilePanel.brushSize, UtilePanel.brushSize);
            }
        	 this.setCursor( this.getToolkit().createCustomCursor(
                       new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
                       new Point(),
                       null ) );
            }
           
        }
         
		
		
		
	}
	 
	private class MyMouseAdapter extends MouseAdapter {
        private Point p0 = null;
        private Point p1 = null;
        BufferedImage  tempImage;
        Graphics2D g2D ; 
       
        	
        public void mouseMoved(MouseEvent e) {
        	if(painting == false){
        		mouseCoordinates = e.getPoint();
        		repaint();
        	}
        	
        }
        public void mouseExited(MouseEvent e) {
        	mouseCoordinates = null;
        	repaint();
        }
        public void mousePressed(MouseEvent e) {
            p0 = e.getPoint();
            painting = true;
            mouseCoordinates = null;
            tempImage = image;
           
            
        }

        public void mouseReleased(MouseEvent e) {
            painting = false;
            p1 = e.getPoint();
            Graphics2D g2D = image.createGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setColor(UtilePanel.col);
            g2D.setStroke(new BasicStroke(UtilePanel.brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int x = Math.min(p0.x, p1.x);
            int y = Math.min(p0.y, p1.y);
            int width = Math.abs(p1.x - p0.x);
            int height = Math.abs(p1.y - p0.y);
            switch(UtilePanel.tool) {
            case 3:// Oval tool
                g2D.drawOval(x, y, width, height);
                break;
            case 4:
            	g2D.drawRect(x,y,width,height);
            	break;
            }
            
            g2D.dispose();
            repaint();
            
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (p0 == null) {
                return;
            }
            p1 = e.getPoint();
            if(UtilePanel.tool>2) {
            	image = new BufferedImage(PREF_W, PREF_H, BufferedImage.TYPE_INT_ARGB);
            }            
            g2D = image.createGraphics();
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2D.setColor(UtilePanel.col);
            g2D.setStroke(new BasicStroke(UtilePanel.brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
           
            if(UtilePanel.tool<=2) { 
            	if(UtilePanel.tool == 2) {
            		g2D.setColor(Color.white);
            	}
            	g2D.drawLine(p0.x, p0.y, p1.x, p1.y);
            	p0 = p1;
            }
            else {
               g2D.drawImage(tempImage,0,0,null);
            	int x = Math.min(p0.x, p1.x);
                int y = Math.min(p0.y, p1.y);
                int width = Math.abs(p1.x - p0.x);
                int height = Math.abs(p1.y - p0.y);
                switch(UtilePanel.tool) {
                case 3: 
                	g2D.drawOval(x, y, width, height);
                	break;
                case 4:
                	g2D.drawRect(x,y,width,height);
                	break;
                case 5:
                	int[] X = {x,x+width/2,x+width};
                  if(p0.y-p1.y<1){  
                int[] Y = {y,y+height,y};
                  g2D.drawPolygon(X,Y,3);
                 }
                  else{
                int[] Y= {y+height,y,y+height};
                g2D.drawPolygon(X,Y,3);
          }
                	break;
                case 6:
                    g2D.drawLine(p0.x,p0.y,p1.x,p1.y);
                	break;
                	
    			default:
    				break;
                }
               
               
            
            
            }
            
            
            g2D.dispose();
            repaint();
            
        }
        

    }
	
	

}

