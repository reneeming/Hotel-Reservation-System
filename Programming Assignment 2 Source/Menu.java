 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: Menu.java
*/

public abstract class Menu {
	/**
	Handle user's input for this menu.
	*/
	public abstract boolean listenerUserInput();

	/**
	Print the main prompt message.
	*/
	public abstract void printPromptMessage();

	/**
	Print out the object.
	*/
	public void print(Object o) {
		System.out.println(o);
	}
}
