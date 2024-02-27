import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import java.awt.Image;
import java.awt.RenderingHints;
public class UtilePanel extends JPanel {
		static Color col=Color.black;
		static int brushSize = 5;
		static int tool=1;
		ButtonGroup tools = new ButtonGroup();
		public UtilePanel(){
			this.setPreferredSize(new Dimension (1000,150));
			JPanel container = new JPanel();
			container.setLayout(new GridLayout(1,0));
			this.setLayout(new GridLayout(0,1));
			Brushes brushes = new Brushes();
			Shapes shapes = new Shapes();
			LoadImage loadImage = new LoadImage();
			container.add(brushes);
			container.add(shapes);
			container.add(loadImage);
			this.add(new JPanel());
			this.add(container);
			this.add(new JPanel());
			setBorder(BorderFactory.createEtchedBorder(0));
		}
		public class Brushes extends JPanel{
			JRadioButton Size1;
			JRadioButton Size2;
			JRadioButton Size3;
			JRadioButton Size4;
			JRadioButton pincel;
			JRadioButton eareser;
			ButtonGroup  group;
			JButton color;
			
			
			public Brushes() {
				
				Size1 = new JRadioButton();
				Size2 = new JRadioButton();
				Size3 = new JRadioButton();
				Size4 = new JRadioButton();
				group = new ButtonGroup();
				color = new JButton();
				pincel = new JRadioButton();
				eareser = new JRadioButton();
				color.setBackground(col);
				color.setBorder(BorderFactory.createEtchedBorder(0));
				color.setPreferredSize(new Dimension(20,20));
				Size1.setSelected(true);
				JColorChooser  colorchooser= new JColorChooser();
				color.addActionListener(e->{
					col = JColorChooser.showDialog(null, "Pick a color ",Color.black);
					color.setBackground(col);
				});
				Size1.addActionListener(e->{
					brushSize = 5;
				});
				Size2.addActionListener(e->{
					brushSize = 10;
				});
				Size3.addActionListener(e->{
					brushSize = 15;
				});
				Size4.addActionListener(e->{
					brushSize = 20;
				});
				pincel.addActionListener(e->{
					tool=1;
				});
				eareser.addActionListener(e->{
					tool=2;
				});
				group.add(Size1);
				group.add(Size2);
				group.add(Size3);
				group.add(Size4);
				tools.add(pincel);
				tools.add(eareser);
				this.add(Size1);
				this.add(Size2);
				this.add(Size3);
				this.add(Size4);
				this.add(color);
				this.add(pincel);
				this.add(eareser);
				this.setLayout(new FlowLayout());
			}
		}
		public class Shapes extends JPanel{
			public Shapes() {
				ShapeButton  circule = new  ShapeButton ("Oval");
				ShapeButton  rect = new  ShapeButton ("Rect");
				ShapeButton triangle = new  ShapeButton ("Tri");
				ShapeButton line = new  ShapeButton ("Line");
				this.setLayout(new FlowLayout());
				
				rect.addActionListener(e->{
					UtilePanel.tool = 4;
				});
				circule.addActionListener(e->{
					UtilePanel.tool = 3;
				});
				triangle.addActionListener(e->{
					UtilePanel.tool = 5;
				});
				line.addActionListener(e->{
					UtilePanel.tool = 6;
				});
				
				
				
				tools.add(rect);
				tools.add(triangle);
				tools.add(circule);
				tools.add(line);
				this.add(circule);
				this.add(rect);
				this.add(triangle);
				this.add(line);
			}
			class ShapeButton extends JRadioButton {
				public ShapeButton(String s) {
					setIcon((new ImageIcon(creatImage(new Color(0x00FFFFFF, true),s))));
					setSelectedIcon(new ImageIcon(creatImage(Color.gray,s)));
						
					
					
				}
			}
			
			public BufferedImage creatImage(Color color,String shape) {
				BufferedImage bi = new BufferedImage(40,40, BufferedImage.TYPE_INT_ARGB);
				Graphics2D g = (Graphics2D) bi.getGraphics();
			    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setStroke(new BasicStroke(5));
				g.setColor(color);
				g.fillRect(0,0,40,40);
				
				g.setColor(Color.black);
				switch(shape) {
					case "Oval":	
						g.drawOval(5, 5, 30,30);
						break;
					case "Rect":
						g.drawRect(5,5,30,30);
						break;
					case "Tri":
						int[] X= {5,16,30};
						int[] Y= {30,5,30};
						g.drawPolygon(X,Y,3);
						break;
					case "Line":
						g.drawLine(5,30,30,5);
						break;
				}
				
				
				
				//g.dispose();
				return bi;
			}
			
		}
		public class LoadImage extends JPanel{
			public LoadImage() {
				JButton loadButton = new JButton("Import Image");
				loadButton.setPreferredSize(new Dimension(100,50));
				JFileChooser f = new JFileChooser();
				loadButton.addActionListener(e->{
					int resp = f.showOpenDialog(null);
					if(resp == JFileChooser.APPROVE_OPTION) {
						File file = new File(f.getSelectedFile().getAbsolutePath());
						
						System.out.println(file.getAbsolutePath());
						try {
						    // retrieve image
						    File outputfile = new File("/home/oussama/Desktop/Save.png");
						    ImageIO.write(PaintPanel.image, "png", outputfile);
						} catch (IOException l) {
						    // handle exception
							l.printStackTrace();
						}
					}
				});
				this.add(loadButton);
			}
		}
}

