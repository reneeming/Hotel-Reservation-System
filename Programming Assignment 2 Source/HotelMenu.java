 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: HotelMenu.java
*/

import java.util.Scanner;

public class HotelMenu extends Menu {
	/**
	The hotel for this menu.
	*/
	private Hotel hotel;

	/**
	Parameterized constructor.
    * @param db
	*/
	HotelMenu(Hotel val) {
		hotel = val;
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
				boolean val = new ReservationMenu(hotel).listenerUserInput();
				if (val) return true;
				break;
			case "2":
				print("Number of available rooms with queen-sized beds: " + hotel.getNumberOfAvailableRoomWithQueenSizedBeds());
				break;
			case "3":
				print("Number of available rooms with king-sized beds: " + hotel.getNumberOfAvailableRoomWithKingSizedBeds());
				break;
			case "4":
				print("Number of total available rooms: " + hotel.getNumberOfTotalAvailableRooms());
				break;
			case "5":
				break menuLoop;
			case "6":
				return true;
			default:
				print(input + " is not supported, please enter number from 1 to 6");
			}
		}
		return false;
	}

	/**
	Print the main prompt message.
	*/
	@Override
	public void printPromptMessage() {
		String message = "\n---------------Hotel Menu--------------\n" + hotel.getName() + "\n\nCity: " + hotel.getCity() + "\n" + hotel.getDescription()
				+ "\n\n"
				+ "1. View Reservations\n2. Get the # of available rooms in the hotel with queen-sized beds\n3. Get the # of available rooms in the hotel with king-sized beds\n4. Get the total # of available rooms in the hotel\n5.Return to the list of hotels\n6.Return to main menu\n\nPlease enter a number for one of the options given above:\n";
		print(message);
	}

}
