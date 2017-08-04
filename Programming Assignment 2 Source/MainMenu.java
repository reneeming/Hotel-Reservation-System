 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: MainMenu.java
*/

import java.util.*;

public class MainMenu extends Menu {
	/**
	The DB to fetch hotels information.
	*/
	FakeDB DB;

	/**
	Parameterized constructor.
    * @param db
	*/
	MainMenu(FakeDB db) {
		DB = db;
	}

	/**
	Handle user's input for this menu.
	*/
	@Override
	public boolean listenerUserInput() {
		Scanner scan = new Scanner(System.in);
		menuLoop: while (true) {
			printPromptMessage();
			String input = scan.nextLine();
			switch (input) {
			case "1":
				new HotelListMenu(DB).listenerUserInput();
				break;
			case "2":
				print("The number of available rooms in hotel chain with queen-sized beds: "
						+ getNumberOfAvailableRoomsWithQueenSizedBeds());
				break;
			case "3":
				print("The number of available rooms in hotel chain with king-sized beds: "
						+ getNumberOfAvailableRoomsWithKingSizedBeds());
				break;
			case "4":
				print("The total number of available rooms in hotel chain: " + getNumberOfAvailableRooms());
				break;
			case "5":
				print("The total number of rooms in hotel chain: " + getTotalNumberOfRooms());
				break;
			case "6":
				break menuLoop;
			default:
				print(input + " is not supported, please enter number from 1 to 6");
			}
		}
		return false;
	}
	
	/**
	Get the number of available room with queen sized beds.
	*/
	private int getNumberOfAvailableRoomsWithQueenSizedBeds() {
		Hotel[] hotels = DB.getHotels();
		int res = 0;
		for (Hotel hotel : hotels) {
			res += hotel.getNumberOfAvailableRoomWithQueenSizedBeds();
		}
		return res;
	}

	/**
	Get the number of available room with king sized beds.
	*/
	private int getNumberOfAvailableRoomsWithKingSizedBeds() {
		Hotel[] hotels = DB.getHotels();
		int res = 0;
		for (Hotel hotel : hotels) {
			res += hotel.getNumberOfAvailableRoomWithKingSizedBeds();
		}
		return res;
	}

	/**
	Get the number of available rooms.
	*/
	private int getNumberOfAvailableRooms() {
		int res = 0;
		for (Hotel hotel: DB.getHotels()) {
			for (Room room : hotel.getRooms()) {
				if (room.getAvailablility()) {
					res++;
				}
			}
		}
		return res;
	}

	/**
	Get the total number of rooms.
	*/
	private int getTotalNumberOfRooms() {
		Hotel[] hotels = DB.getHotels();
		int res = 0;
		for (Hotel hotel : hotels) {
			res += hotel.getRooms().length;
		}
		return res;
	}

	/**
	Print the main prompt message.
	*/
	@Override
	public void printPromptMessage() {
		print("\n---------------Main Menu--------------\n" + "1. List the hotels in the hotel chain\n"
				+ "2. Get the # of available rooms in the hotel chain with queen-sized beds\n"
				+ "3. Get the # of available rooms in the hotel chain with king-sized beds\n"
				+ "4. Get the total # of available rooms in the hotel chain\n"
				+ "5. Get the total # of rooms in the hotel chain (available or otherwise)\n" + "6. Exit Program\n\n"
				+ "Please enter a number for one of the options given above:");

	}

}
