 /**
@author Ming Ni
@version 1.1

COP5007 Project #: 2
File Name: ReservationMenu.java
*/

import java.util.Scanner;

public class ReservationMenu extends Menu {
	/**
	The hotel for this menu.
	*/
	private Hotel hotel;
	
	/**
	The real size of reservation for the hotel.
	*/
	private int numberOfValidReservations;

	/**
	Parameterized constructor that accepts the hotel
	* @param hotel
	*/
	ReservationMenu(Hotel hotel) {
		this.hotel = hotel;
		updateNumberOfValidReservation();
	}
	
	/**
	Update numberOfValidReservations
	*/
	private void updateNumberOfValidReservation() {
		for (int i = 0; i < hotel.getReservation().length; i++) {
			if (hotel.getReservation()[i] == null) {
				break;
			} else {
				numberOfValidReservations = i + 1;
			}
		}
	}

	/**
	Handle user's input for this menu, return true if want to go main menu.
	*/
	@Override
	public boolean listenerUserInput() {
		Scanner scan = new Scanner(System.in);
		menuLoop: while (true) {
			printPromptMessage();
			String input = scan.nextLine();
			if (Utils.isPositiveInteger(input)) {
				int opt = Integer.parseInt(input);
				if (opt <= numberOfValidReservations) {
					/*
					Print the details of a reservation.
					*/
					print("Reservation: " + hotel.getReservation()[opt - 1]);
				} else 
				if (opt == numberOfValidReservations + 1) {
					/*
					Make a Reservation
					*/
					if (createReservation(scan) != null) {
						print("Reservation successfully!");
						updateNumberOfValidReservation();
					}
				} else if (opt == numberOfValidReservations + 2) {
					/*
					Delete a Reservation
					*/
					if (hotel.deleteReservation(selectReservation(scan))) {
						updateNumberOfValidReservation();
						print("Delete reservation successfully!");
					}
				} else if (opt == numberOfValidReservations + 3) {
					/*
					Modify a Reservation
					*/
					Reservation reservation = selectReservation(scan);
					if (reservation == null) continue;
					Room[] rooms = 	reservation.getRooms();
					for (Room room: rooms) {
						room.setAvailablility(true);
					}
					print("Begin to modify reservation:\n");
					if (createReservation(scan) != null) {
						hotel.deleteReservation(reservation);
						print("Successfully modified reservation");
					} else {
						for (Room room: rooms) {
							room.setAvailablility(false);
						}
					}
				} else if (opt == numberOfValidReservations + 4) {
					return false;
				} else if (opt == numberOfValidReservations + 5) {
					return true;
				} else {
					print(opt + " is not supported, please enter number from 1 to " + numberOfValidReservations + 5);
				}
			}
		}
	}
	
	/**
	Select the reservation from user's input
	* @param scan
	* @return Reservation
	*/
	private Reservation selectReservation(Scanner scan) {
		Reservation[] reservations = hotel.getReservation();
		if (reservations.length == 0) {
			print("There is no reservation to select");
			return null;
		}
		print("please enter the number of reservation you want to select:\n");
		String message = "";
		for (int i = 0; i < numberOfValidReservations; i++) {
			message += (i + 1) + ". " + reservations[i].getName() + "\n"; 
		}
		print(message);
		String reservationNumber = scan.nextLine();
		if (!Utils.isPositiveInteger(reservationNumber) || Integer.parseInt(reservationNumber) > numberOfValidReservations) {
			print(reservationNumber + " is not an vaild option.");
			return null;
		}
		return reservations[Integer.parseInt(reservationNumber) - 1];
	}
	
	/**
	Create the reservation from user's input
	* @param scan
	* @return Room
	*/
	private Room createReservation(Scanner scan) {
		print("please enter your name:\n");
		String name = scan.nextLine();
		print("please enter the number of occupants:\n");
		String occupantsNumber = scan.nextLine();
		if (!Utils.isPositiveInteger(occupantsNumber)) {
			printNotSupportMessage(occupantsNumber);
			return null;
		}
		print("Please enter the type of bed you desire(1 for queen size, 2 for king size): ");
		String typeOfBed = scan.nextLine();
		Room room = null;
		if (typeOfBed.equals("1")) {
			room = hotel.getNextAvailableQueenRoom();
			if (room == null) {
				print("Sorry, queen size bed is not available now");
				return null;
			} 
		} else if (typeOfBed.equals("2")) {
			room = hotel.getNextAvailableKingRoom();
			if (room == null) {
				print("Sorry, King size bed is not available now");
				return null;
			} 
		} else {
			print("bed type is not supported, only support 1(queen size) or 2(king size)");
		}
		room.setAvailablility(false);
		Room[] rooms = {room};
		hotel.addReservation(new Reservation(name, Integer.parseInt(occupantsNumber), rooms));
		return room;
	}
	
	/**
	A help method to print not support prompt message
	*/
	private void printNotSupportMessage(String input) {
		print(input + " is not supported, please enter number from 1 to " + (numberOfValidReservations + 5));
	}

	
	@Override
	public void printPromptMessage() {
		String message = "";
		int counter = 1;
		for (int i = 0; i < hotel.getReservation().length; i++) {
			Reservation reservation = hotel.getReservation()[i];
			if (reservation == null) {
				break;
			}
			message += counter++ + ". " + reservation.getName() + "\n";
		}
		message += counter++ + ". Make a Reservation\n" + counter++ + ". Delete a Reservation\n" + counter++
				+ ". Modify a Reservation\n" + counter++ + ". Rreturn to the hotel menu\n" + counter++
				+ ". Return to main menu\n\nPlease enter a number for one of the options given above:\n";
		print("\n--------------Reservation Menu---------------\n" + message);
	}

}
