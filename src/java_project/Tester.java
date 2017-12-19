//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Tester {

    public static void main(String[] args) 
    {
        List<Room> rooms = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        
        rooms.add(new Single_Room("50", 2, true));
        rooms.add(new Luxury_Room("65",50, true,true));
        rooms.add(new Double_Room("70", 100, true));
        
        vehicles.add(new Car("70"));
        vehicles.add(new Motorbike("1210"));
        vehicles.add(new Buggy("39"));
        
        Hotel hotel = new Hotel("Aigaio", "Samos", 4, rooms, vehicles);
        
        hotel.Add(new Triple_Room("120", 70, true));
        hotel.Delete("120");
        
        
        
        Date from, to;
        Calendar calendar = Calendar.getInstance();

        calendar.set(2011, 0, 4);
        from = calendar.getTime();
        calendar.set(2011, 0, 7);
        to = calendar.getTime();
        
        hotel.NewReservation("120", "Marios", from, to, vehicles.get(0));
        
         calendar.set(2011, 1, 4);
        from = calendar.getTime();
        calendar.set(2011, 3, 7);
        to = calendar.getTime();
        
        hotel.NewReservation("1200", "Giota", from, to, rooms.get(0));
        
        calendar.set(2011, 1, 4);
        from = calendar.getTime();
        calendar.set(2011, 3, 7);
        to = calendar.getTime();
        
        hotel.NewReservation("1300", "Panos", from, to, rooms.get(1));
        
        Date betw;
        calendar.set(2011, 1, 26);
        betw=calendar.getTime();
        System.out.println(hotel.Search_code("120"));
        System.out.println(hotel.Search_date(betw).get(0));
        System.out.println(hotel.Search_name("Panos"));
        hotel.Delete("70");
        System.out.println(hotel.Search_code("120"));
        hotel.UniqueTypes(betw);
        
        hotel.TypeCount(betw);
        
    }

}
