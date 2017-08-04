 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: Project2_driver.java
*/

public class Project2_driver {

	/**
	Start the program.
	*/
	public static void main(String[] args) {
		MainMenu mainMenu = new MainMenu(new FakeDB());
		mainMenu.listenerUserInput();
	}

}
