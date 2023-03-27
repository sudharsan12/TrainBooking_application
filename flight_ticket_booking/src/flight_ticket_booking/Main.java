package flight_ticket_booking;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
      FlightHandler handler=new FlightHandler();
      
      while(menu(handler));
		
	}

	private static boolean menu(FlightHandler handler) {
	
		System.out.println("*****************Welcome to India Airlines*****************");
		System.out.println("Select option\n 1 -> Booking \n 2 -> Cancelation \n 3 -> Flight Summary \n 4 -> Booking Details \n 5 -> Exit ");
		Scanner input=new Scanner(System.in);
		int option=input.nextInt();
		try{
			
			System.out.println(option);
			switch(option)
			{
			
			case 1:handler.booking();
			      break;
			case 2:handler.cancel();
			      break;
			case 3:handler.summary();
			      break;
			case 4:handler.BookingDetails();
			      break;
			case 5:return false;
			}
			
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return true;
	}

}
