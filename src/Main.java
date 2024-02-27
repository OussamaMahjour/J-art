import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Frame fr = new Frame();

    
		
		
		
	}
	
	 static class Frame  extends JFrame{
		 Frame(){
			 
			 this.setResizable(false);
			 this.setSize(1000,700);
			 this.setLayout(new BorderLayout());
			 this.setLocationRelativeTo(null);
			 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 UtilePanel up = new UtilePanel();
			 PaintPanel PP = new PaintPanel();
			 
			 Menu me = new Menu();
			 this.add(up,BorderLayout.NORTH);
			 this.add(PP,BorderLayout.CENTER);
			 this.setJMenuBar(me);
			 this.setVisible(true);
			 
		 }
		 public void run() {
				
			} 
		 
		 
		 
	}

}

