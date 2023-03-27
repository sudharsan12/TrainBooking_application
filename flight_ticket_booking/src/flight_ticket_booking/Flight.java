package flight_ticket_booking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Flight {
	
	int flightId;
	int economySeatCost;
	int BussinessSeatCost;
	int AvailableBussinessSeats;
	int AvailableEconomySeats;
	boolean[] seats;
	int TotalEarn;
	int totalseats;
	Map<Integer, Booking>booked;
	
	public Flight(int flightId, int economySeatCost, int bussinessSeatCost, int availableBussinessSeats,int availableEconomySeats)
	{
		this.flightId = flightId;
		this.economySeatCost = economySeatCost;
		BussinessSeatCost = bussinessSeatCost;
		AvailableBussinessSeats = availableBussinessSeats;
		AvailableEconomySeats = availableEconomySeats;
	    TotalEarn=0;
		seats=new boolean[availableBussinessSeats+availableEconomySeats];
		totalseats=availableBussinessSeats+availableEconomySeats;
		Arrays.fill(seats, false);
	
	  booked=new LinkedHashMap<>();
	}
	
	
	public void addBooking(int id,Booking book)
	{
		booked.put(id, book);
	}
	
	
	public void summary()
	{
		
		System.out.println("Summary Flight Number  :"+flightId);
		
		ArrayList<Integer>Booked=new ArrayList<>();
		ArrayList<Integer>meals=new ArrayList<>();
		Set set=booked.keySet();
		
		Iterator iter=set.iterator();
		
		while(iter.hasNext())
		{
		
			Booking book=booked.get((int)iter.next());
			if(book.status=='B')
			{
				for(int i=0;i<book.noSeats;i++)
				{
					Booked.add(book.seatNo.get(i)+1);
					if(book.meals==true)
						meals.add(book.seatNo.get(i)+1);
				}
			}
			
		}
		
		System.out.println("Booked Seats : ");
		for(int i=0;i<Booked.size();i++)
			System.out.print(Booked.get(i)+" ");
		System.out.println("");
		System.out.println("Meals Booked : ");
		for(int i=0;i<meals.size();i++)
			System.out.print(meals.get(i)+" ");
		System.out.println("");
        
        System.out.println("Total Cost  :"+TotalEarn);
        System.out.println("Seats Available : ");
        for(int i=0;i<totalseats;i++)
        {
        	if(seats[i]!=true)
        	System.out.print(i+1+" ");
        }
        System.out.println(" \n\n\n");
	}
	
	
}
