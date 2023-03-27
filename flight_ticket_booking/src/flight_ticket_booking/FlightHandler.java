package flight_ticket_booking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FlightHandler {

	private  static int flightNo=100;
	private static int bookId=0;
	private final int noOfflight=2;
	private final int economySeats=12;
	private final int bussinessSeats=6;
	private final int economyCost=1000;
	private final int bussinessCost=2000;
	
	Map<Integer, Flight>flights;
	ArrayList<Booking>bookingList;
	public FlightHandler() {
		
		flights=new LinkedHashMap<Integer, Flight>();
		bookingList=new ArrayList<>();
		
		for(int i=0;i<noOfflight;i++)
		{
		   Flight flight=new Flight(++flightNo, economyCost, bussinessCost, economySeats, bussinessSeats);
		   flights.put(flightNo, flight);
		}
	
	}
	
	
	public boolean cancel()
	{
		int flightNo;
		int BookingId;
		Scanner input =new Scanner(System.in);

		System.out.println("Enter the Flight No");
		  flightNo=input.nextInt();
		  
		System.out.println("Enter the Booking Id");
		 BookingId=input.nextInt();
		  
		  Flight flight=flights.get(flightNo);
		
		  if(flight==null)
		  {
			System.out.println("The Flight No is wrong");
			  return false;
		  }
		  
		  Booking book=flight.booked.get(BookingId);
		  
		  if(book==null)
		  {
			  System.out.println("The Booking Id is wrong");
			  return false; 
		  }
		
		  book.status='C';
		  
		  for(int i=0;i<book.noSeats;i++)
		  {
			  
			  flight.seats[book.seatNo.get(i)]=false;
		  }
		  
		  flight.TotalEarn-=(book.TotalCost-book.noSeats*200);
		  
		book.TotalCost=book.TotalCost-book.noSeats*200;
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	

  boolean booking()
  {
	  int flightNo;
	  int noOfseats;
	  char meals;
	  char Class;
	  
	  Scanner input =new Scanner(System.in);
	  
	  try
	  {
	  System.out.println("Enter the Flight No");
	  flightNo=input.nextInt();
	  
	  System.out.println("Select  Travel Mode press \n Bussiness -> B  \n Economy -> E");
	  Class=input.next().charAt(0);
	  
	  if(Class!='B' && Class!='b' && Class!='E'&& Class!='e')
	  throw new UserDefinedException("Invalid Option Enter Class");
	  
	  System.out.println("Enter the no of Seats");
	  noOfseats=input.nextInt();
	  
	  if(checkSeatsAvailability(flightNo,noOfseats,Class)==false)
	  {
		return false;  
	  } 
	  
	  
	  System.out.println("DO you need food.......! press  y -> Yes    N  -> No");
	  meals=input.next().charAt(0);
	  
	  if(!(meals=='Y' || meals=='y' || meals=='N'|| meals=='n'))
		 throw new UserDefinedException("Invalid Option Enter Class");
		  
	  
	  ArrayList<Integer>seats=allotedSeats(flightNo,noOfseats,Class);
	  
	  int totalcost;
	  
	  Flight flight=flights.get(flightNo);
	  
	  if(Class=='B'||Class=='b')
	  {
		if(meals=='Y'||meals=='y')
			totalcost=noOfseats*flight.BussinessSeatCost+noOfseats*200;
		else
			totalcost=noOfseats*flight.BussinessSeatCost;
		  
		flight.BussinessSeatCost+=200;
	  }else
	  {
		  if(meals=='Y'||meals=='y')
				totalcost=noOfseats*flight.economySeatCost+noOfseats*200;
			else
				totalcost=noOfseats*flight.economySeatCost;
		  flight.economySeatCost+=100;
	  }
	  
	  
	  flight.TotalEarn+=totalcost;
	  
	  Booking book=new Booking(++bookId,flightNo,noOfseats, seats, Class=='B'||Class=='b'?"BC":"EC", meals=='Y'||meals=='y'?true:false,totalcost, 'B');
	  bookingList.add(book);
	  
	  flight.addBooking(bookId, book);
	  
	  
	  }catch (Exception e) {
		
		  System.out.println(e);
	}
	  
	  
	  return false;
  }

private ArrayList<Integer> allotedSeats(int flightNo2, int noseats, char Class) {
	
	ArrayList<Integer>seats=new ArrayList<>();
    
	Flight flight=flights.get(flightNo2);
	
    if(Class=='B'||Class=='b')
    {
   	 
   	 for(int i=0,j=0;i<bussinessSeats;i++)
   	 {
   		 if(flight.seats[i]==false&& j<noseats)
   		 {
   			 flight.AvailableBussinessSeats--;
   			 flight.seats[i]=true;
   			 j++;
   			 seats.add(i);
   		 }
   	 }
    }else
    {
    	 for(int i=0,j=0;i<economySeats;i++)
       	 {
       		 if(flight.seats[i+bussinessSeats]==false&& j<noseats)
       		 {
       			 flight.AvailableEconomySeats--;
       			 flight.seats[i+bussinessSeats]=true;
       			 j++;
       			 seats.add(i+bussinessSeats);
       		 }
       	 }
    	
    	
    	
    }
	return seats;
}

private boolean checkSeatsAvailability(int flightNo2,int noseats,char Class) {
	
	     Flight flight=flights.get(flightNo2);
	     
	     if(flight==null)
	     {
	    	 System.out.println("Invalid flight No");
	    	 return false;
	     }
	     
	     if(Class=='B'||Class=='b')
	     {
	    	 
	    	 return flight.AvailableBussinessSeats>=noseats?true:false;
	     }else
	     {
	    	 return flight.AvailableEconomySeats>=noseats?true:false;	 
	     }
	     
}
	

public void  summary()
{
	
	Set set=flights.keySet();
	
	Iterator iterator=set.iterator();
	
	while(iterator.hasNext())
	{
		 flights.get( (int) iterator.next()).summary();
	}
	
}
	
	

public void BookingDetails()
{
	for(int i=0;i<bookingList.size();i++)
	{
		
		bookingList.get(i).print();
	}
	
	
}



}
