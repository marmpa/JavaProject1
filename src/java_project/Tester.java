//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Tester {

    public static void main(String[] args) 
    {
        List<Room> rooms = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        
        rooms.add(new Single_Room("50", 0, true));
        rooms.add(new Luxury_Room("50",50, true,true));
        rooms.add(new Double_Room("50", 100, true));
        
        vehicles.add(new Car(70));
        vehicles.add(new Motorbike(80));
        vehicles.add(new Buggy(100));
        
        Hotel hotel = new Hotel("Aigaio", "Samos", 4, rooms, vehicles);
        
        Date from, to;
        Calendar calendar = Calendar.getInstance();

        calendar.set(2011, 0, 4);
        from = calendar.getTime();
        calendar.set(2011, 0, 7);
        to = calendar.getTime();
        
        hotel.NewReservation("120", "Marios", from, to, vehicles.get(2));
        
        //System.out.println(hotel.Search_date(from).get(0).getName());
        
    }

}
