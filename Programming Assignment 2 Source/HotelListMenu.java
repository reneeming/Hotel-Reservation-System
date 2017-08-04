 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: HotelListMenu.java
*/

import java.util.Scanner;

public class HotelListMenu extends Menu {
	/**
	The DB to fetch hotels information.
	*/
	FakeDB DB;
	
	/**
	The real size of DB.getHotels().
	*/
	private int numberOfValidHotels;

	/**
	Parameterized constructor.
    * @param db
	*/
	HotelListMenu(FakeDB db) {
		DB = db;
		updateNumberOfValidHotels();
	}
	
	/**
	Update numberOfValidHotels
	*/
	private void updateNumberOfValidHotels() {
		Hotel[] hotels = DB.getHotels();
		for (int i = 0; i < hotels.length; i++) {
			if (hotels[i] == null) {
				break;
			} else {
				numberOfValidHotels = i + 1;
			}
		}
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
			if (Utils.isPositiveInteger(input)) {
				Hotel[] hotels = DB.getHotels();
				int opt = Integer.parseInt(input);
				if (opt <= numberOfValidHotels) {
					boolean val = new HotelMenu(DB.getHotels()[opt - 1]).listenerUserInput();
					if (val) return true;
				} else if (opt == numberOfValidHotels + 1) {
					/*
					Delete a Hotel
					*/
					print("Deteing a hotel, please enter the number of hotel you want to select:\n");
					String message = "";
					for (int i = 0; i < numberOfValidHotels; i++) {
						message += (i + 1) + ". " + hotels[i].getName() + "\n"; 
					}
					print(message);
					String hotelNumber = scan.nextLine();
					if (!Utils.isPositiveInteger(hotelNumber) || Integer.parseInt(hotelNumber) > numberOfValidHotels) {
						print(hotelNumber + " is not an vaild option.");
						continue;
					}
					if (DB.deleteHotel(hotels[Integer.parseInt(hotelNumber) - 1])) {
						print("Delete hotel success!");
						updateNumberOfValidHotels();
					}
				} else if (opt == numberOfValidHotels + 2) {
					/*
					Add a new hotel
					*/
					print("please enter hotel name:\n");
					String name = scan.nextLine();
					print("please enter hotel city:\n");
					String city = scan.nextLine();
					print("please enter hotel description:\n");
					String desc = scan.nextLine();
					print("please enter the number of room with queen-sized bed:\n");
					String queenRoomNumber = scan.nextLine();
					if (!Utils.isPositiveInteger(queenRoomNumber) && !Utils.isZero(queenRoomNumber)) {
						print("invalid number " + queenRoomNumber);
						continue;
					}
					print("please enter the number of room with king-sized bed:\n");
					String kingRoomNumber = scan.nextLine();
					if (!Utils.isPositiveInteger(kingRoomNumber) && !Utils.isZero(kingRoomNumber)) {
						print("invalid number " + kingRoomNumber);
						continue;
					}
					Hotel newHotel = new Hotel(name, city, desc);
					newHotel.cleanRooms();
					for (int i = 0; i < Integer.parseInt(queenRoomNumber); i++) {
						newHotel.addRoom(new Room(i + 1, 1, 0));
					}
					for (int i = 0; i < Integer.parseInt(kingRoomNumber); i++) {
						newHotel.addRoom(new Room(Integer.parseInt(queenRoomNumber) + i + 1, 0, 1));
					}
					DB.addHotel(newHotel);
					print("Hotel added successfully!");
					updateNumberOfValidHotels();
				} else if (opt == numberOfValidHotels + 3) {
					/*
					Return to main menu
					*/
					return true;
				} else {
					print(opt + " is not supported, please enter number from 1 to " + numberOfValidHotels + 3);
				}
			} else {
				print(input + " is not supported, please enter number from 1 to " + (numberOfValidHotels + 3));
			}
		}
	}

	/**
	Print the main prompt message.
	*/
	@Override
	public void printPromptMessage() {
		Hotel[] hotels = DB.getHotels();
		String message = "";
		int counter = 1;
		for (Hotel hotel : hotels) {
			if (hotel == null) {
				break;
			}
			message += (counter++ + ". " + hotel.getName() + "\n");
		}
		message += counter++ + ". Delete a Hotel\n" + counter++ + ". Add a Hotel\n" + counter++
				+ ". Return to main menu\n\n" + "Please enter a number for one of the options given above:";
		print("\n-------------Hotel List Menu----------------\n" + message);
	}
}
