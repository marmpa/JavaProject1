//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class Tester {

    public static void main(String[] args) 
    {
        List<Room> rooms = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        
        System.out.println("Xristi dose poso domation (basiko poso): ");
        Scanner sc =new Scanner(System.in);
        double price=sc.nextDouble();
        
        rooms.add(new Single_Room("200", price, true));
        rooms.add(new Single_Room("201", price, false));
        rooms.add(new Luxury_Room("202",price, true,true));
        rooms.add(new Double_Room("203", price, true));
        
        vehicles.add(new Car("100"));
        vehicles.add(new Motorbike("101"));
        vehicles.add(new Buggy("102"));
        
        Hotel hotel = new Hotel("Aigaio", "Samos", 4, rooms, vehicles);
        
        hotel.Add(new Triple_Room("120", price, true));
        hotel.Delete("120");
        
        
        
        Date from, to;
        Calendar calendar = Calendar.getInstance();

        calendar.set(2017, 11, 20);
        from = calendar.getTime();
        calendar.set(2017, 11, 27);
        to = calendar.getTime();
        
        hotel.NewReservation("1", "Marios", from, to, vehicles.get(2));
        
         calendar.set(2017, 11, 25);
        from = calendar.getTime();
        calendar.set(2017, 11, 29);
        to = calendar.getTime();
        
        hotel.NewReservation("2", "Giota", from, to, rooms.get(0));
        
        calendar.set(2017, 11, 18);
        from = calendar.getTime();
        calendar.set(2017, 11, 25);
        to = calendar.getTime();
        
        hotel.NewReservation("3", "Panos", from, to, rooms.get(1));
        
        calendar.set(2017, 11, 20);
        from = calendar.getTime();
        calendar.set(2017, 11, 29);
        to = calendar.getTime();
        
        hotel.NewReservation("4", "Tom", from, to, rooms.get(2));
        
        calendar.set(2017, 11, 20);
        from = calendar.getTime();
        calendar.set(2017, 11, 29);
        to = calendar.getTime();
        
        hotel.NewReservation("5", "Greg", from, to, rooms.get(3));
        
        Date betw;
        calendar.set(2017, 11, 25);
        betw=calendar.getTime();
        System.out.println(hotel.Search_code("5"));//δουλευει
        System.out.println(hotel.Search_date(betw).get(0));//!!!!εδω δεν επιστρέφει λίστα αλλά μόνο ένα στοιχείο!!!!
        System.out.println(hotel.Search_name("Tom"));//δουλευει
        hotel.Delete_reservation("1");//δουλευει
        hotel.UniqueTypes(betw);//δουλευει
        hotel.TypeCount(betw);//δουλευει
        hotel.countPrice(betw); //!!!!εχει θέμα δεν μπορεί να διαβάσει και να υπολογίσει ΜΕ ΟΧΗΜΑΤΑ λεφτά!!!! 
        //τα άλλα τα μετράει νομίζω απλα μετράει μια μέρα λιγότερη, πχ απο 20-25 είναι 5 μέρες και όχι 6! αυτο μόνο
    }

}
