import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Menu extends JMenuBar{
			
	public Menu() {
		File file = new File("File");
		Edit edit = new Edit("Edit");
		Help help = new Help("Help");
		
		this.add(file);
		this.add(edit);
		this.add(help);
	}
	class File extends JMenu {
		public File(String s){
			super(s);
			Save save = new Save("Save");
			Load load = new Load("Load");
			this.add(save);
			this.add(load);
		}
		
		class Save extends JMenuItem{
			public Save(String s) {
				super(s);
			}
			
		}
		class Load extends JMenuItem{
			public Load(String s) {
				super(s);
			}
		}
		
		
	}
	class Edit extends JMenu {
		Edit(String s){
			super(s);
		}
	}
	class Help extends JMenu {
		Help(String s){
			super(s);
		}
	}
	
	
	
	
}

