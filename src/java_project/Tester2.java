/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project;

import graphics.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Tester2 
{
    public static void main(String[] args)
    {
        
            test();
        
    }
    
    public static void test() 
    {
        //φτιάχνουμε δύο arraylist τύπου room και vehicle για να βάλουμε μέσα ότι περιέχει το ξενοδοχείο
        List<Room> rooms = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();
        
        //διαβάζουμε με ένα scanner το βασικό ποσό που θέλει ο χρήστης να έχει ένα απλό δωμάτιο
        System.out.println("Xristi dose poso domation (basiko poso): ");
        Scanner sc =new Scanner(System.in);
        double price=30.0;
        
        //προσθέτουμε με την τιμή αυτή διάφορους τυχαίους constructor στην λίστα rooms
        rooms.add(new Single_Room("200", price, true));
        rooms.add(new Single_Room("201", price, false));
        rooms.add(new Luxury_Room("202",price, true,true));
        rooms.add(new Double_Room("203", price, true));
        
        //αντίστοιχα φτιάχνουμε και οχήματα
        vehicles.add(new Car("100"));
        vehicles.add(new Motorbike("101"));
        vehicles.add(new Buggy("102"));
        
        //δημιουργούμε ένα ξενοδοχείο με τις δύο λίστες από τα δωμάτια και οχήματα που έχουμε σαν ορισματα
        Hotel hotel = new Hotel("Aigaio", "Samos", 4, rooms, vehicles);
        
        //δείχνουμε ότι χωρίς προβλημα προσθέτουμε και αφαιρούμε αντικείμενα απο το ξενοδοχείο
        hotel.Add(new Triple_Room("120", price, true));
        hotel.Delete("120");
        
        
        
        Date from, to;//Δημιουργώ ένα νέο αντικείμενο τύπου Date
        Calendar calendar = Calendar.getInstance();//καλώ την Calendars.getInstance() ώστε να δώσει μία τυπική ημερομηνία στο Calendar

        calendar.set(2017, 11, 20);//θέτει την ημερομηνία του calendar σε αυτη στην παρενθεση
        from = calendar.getTime();//παίρνει την ημερομηνία απο την μεταβλητη calendar την μετατρεπη σε τύπου Date και την αποθηκεύη στη from
        calendar.set(2017, 11, 27);//θέτει την ημερομηνία του calendar σε αυτη στην παρενθεση
        to = calendar.getTime();//παίρνει την ημερομηνία απο την μεταβλητη calendar την μετατρεπη σε τύπου Date και την αποθηκεύη στη to
        
        hotel.NewReservation("1", "Marios", from, to, vehicles.get(2));
        
         calendar.set(2017, 11, 25);//παρομοίως
        from = calendar.getTime();//παρομοίως
        calendar.set(2017, 11, 29);//παρομοίως
        to = calendar.getTime();//παρομοίως
        
        hotel.NewReservation("2", "Giota", from, to, rooms.get(0));
        
        System.out.println(hotel.hReservations.Available(from, from, rooms.get(0)));
        
        calendar.set(2017, 11, 18);//παρομοίως
        from = calendar.getTime();//παρομοίως
        calendar.set(2017, 11, 25);//παρομοίως
        to = calendar.getTime();//παρομοίως
        
        hotel.NewReservation("3", "Panos", from, to, rooms.get(1));
        
        calendar.set(2017, 11, 20);//παρομοίως
        from = calendar.getTime();//παρομοίως
        calendar.set(2017, 11, 29);//παρομοίως
        to = calendar.getTime();//παρομοίως
        
        hotel.NewReservation("4", "Tom", from, to, rooms.get(2));
        
        calendar.set(2017, 11, 20);//παρομοίως
        from = calendar.getTime();//παρομοίως
        calendar.set(2017, 11, 29);//παρομοίως
        to = calendar.getTime();//παρομοίως
        
        hotel.NewReservation("5", "Greg", from, to, rooms.get(3));
        
        //ορίζουμε λοιπόν μια ημερομηνία τυχαία που θέλουμε betw
        Date betw;
        calendar.set(2017, 11, 25);//παρομοίως
        betw=calendar.getTime();//παρομοίως
        
        //και τρέχουμε με κάποιες προκαθορισμένες τιμές ενδεικτικά όλες τις συναρτήσεις που μας ζητείται να φτιάξουμε 
        //για να δείξουμε ότι πράγματι δουλεύουν με ότι τιμή και να δώσουμε!
        System.out.println(hotel.Search_code("5","Greg"));
        
        System.out.println("\nReservations:");
        for(Reservation reservation:hotel.Search_date(betw,"Greg"))
        {
            System.out.println(reservation);
            System.out.println();
        }
        System.out.println("End of reservations!!!");
        
        System.out.println(hotel.Search_name("Tom","Tom"));
        hotel.Delete_reservation("2","MArios");
        hotel.UniqueTypes(betw);
        hotel.TypeCount(betw);
        hotel.countPrice(betw);
        
        //hotel.UniqueTypes("Yes");
        
       
       
        
        Gui a = new Gui(hotel);
    }
    
    
}
