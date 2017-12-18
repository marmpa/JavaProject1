package java_project;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Hotel {

    String hName;
    String hLocation;
    int hRating;
    Reserve hReservations;
    HashMap<String,String> typeNamingScheme;
    

    //na diloso domatia kai autokinita
    public Hotel(String hName, String hLocation, int hRating, List<Room> hRooms, List<Car> hCars) {//Constructor ξενοδοχείου δημιουργεί τις ιδιότητες του ξενοδοχείου

        //Sxoleia gia thoma: Prepei na baleis kratiseis ston constructora den ksero pos na to po kai ti tipou thes
        //Sxoleia gia thoma: Epeiseis den ksero an thes listes gia ta domatia kai pos opote kai auto an thes alakse to
        hReservations = new Reserve();

        this.hName = hName;
        this.hLocation = hLocation;

        if (hRating >= 2 && hRating <= 5) {//Ελένχω αν τα αστέρια του ξενοδοχείου είναι όσα απετείτε απο την άσκηση
            this.hRating = hRating;
        } else {//αλλιώς το θέτω με 2 (το χαμηλότερο πιθανό)
            this.hRating = 2;
        }
        
        typeNamingScheme = new HashMap<>();//Ενα HashMap για ονόματα των ενοικιαζομένων
        
        typeNamingScheme.put("Single_Room","Monoklino");
        typeNamingScheme.put("Double_Room","Diklino");
        typeNamingScheme.put("Triple_Room", "Triklino");
        typeNamingScheme.put("Luxury_Room", "Politeles Domatio");
        typeNamingScheme.put("Buggy", "Gourouna");
        typeNamingScheme.put("Car", "Autokinito");
        typeNamingScheme.put("Motorbike", "Mixanaki");

    }

    public void Add(Object room_car_type) {//προσθέτει νεα κράτηση
       for(Object reservationType:hReservations.reserveList)
       {
           if(reservationType.getClass().equals(room_car_type.getClass()))
           {//Εάν ο τύπος της κλασης είναι ίσιος με αυτό που έδωσε ο χρήστης
               HashMap tempHashMap = (HashMap) reservationType;//Μετατρέπει σε HashMap
               tempHashMap.put(room_car_type, new TreeMap<Date, Reservation>());//Βάζει ένα νέο
           }
       }
    }
    
    public void Delete(Object room_car_type)
    {
        for(Object reservationType:hReservations.reserveList)
       {
           if(reservationType.getClass().equals(room_car_type.getClass()))
           {//Εάν ο τύπος της κλασης είναι ίσιος με αυτό που έδωσε ο χρήστης
               HashMap tempHashMap = (HashMap) reservationType;//Μετατρέπει σε HashMap
               tempHashMap.remove(room_car_type);//Σβήνω αυτό το αντικείμενο
           }
       }
    }

    public Reservation Search_code(String reserve_ID) {
        Reservation temp_reserve = new Reservation();

        

        for (Object tempHashMap : hReservations.reserveList) {
            Set tempEntrySet = ((HashMap) tempHashMap).entrySet();//Μετατρέπει σε set ώστε να μπορέσει να διαβαστεί μετά

            Iterator tempIterator = tempEntrySet.iterator();//Φτιάχνει αντικείμενο iterator ώστε να μπορεί να γίνει προσπέλαση με 
            //το while loop παρακάτω

            while (tempIterator.hasNext()) 
            {//ώσο υπάρχει επόμενη εγραφή στο iterator
                Map.Entry tempMapEntry = (Map.Entry) tempIterator.next();//Κάνει cast σε Map.Entry ώστε να έχω πρόσβαση στο key και value 
                //του HashMap

                TreeMap tempTreeMap = (TreeMap) tempMapEntry.getValue();//Μετατρέπει το tempMapEntry.getValue σε αντικείμενο
                //τύπου treeMap ώστε να μπορώ να έχω πρόσβαση στα αντικείμενα του
                for (Object entry : tempTreeMap.entrySet()) 
                {//Προσπελνά το treeMap
                    Map.Entry tempMapEntry2 = (Map.Entry) entry;//Κάνω cast την εγγραφή σε Map.Entry ώστε να μπορέι να πάρει το value
                    Reservation tempReservation = ((Reservation) tempMapEntry2.getValue());//μετατρέπει tempMapEntry2.getValue 
                    //σε Reservation για να πάρει το ID του

                    if (tempReservation.getID().equals(reserve_ID))
                    {//Εαν το ID τις κράτης που δώθηκε ο χρήστης ισούτε με αυτό στην tempReservation
                        System.out.println("Leitourgei seira 58 Hotel to SearchCode");
                        temp_reserve = tempReservation; //θέτει την temp_reserve με την κράτηση που γίνεται το iterate αυτή τη στιγμή
                        return temp_reserve;
                    }
                }
            }
           
        }
        return temp_reserve;//επιστρέφει την κράτηση
    }

    public Reservation Search_name(String reserve_Name) 
    {
        Reservation temp_reserve = new Reservation();

        for (Object tempHashMap : hReservations.reserveList) 
        {
            Set tempEntrySet = ((HashMap) tempHashMap).entrySet();//Μετατρέπει σε set ώστε να μπορέσει να διαβαστεί μετά

            Iterator tempIterator = tempEntrySet.iterator();//Φτιάχνει αντικείμενο iterator ώστε να μπορεί να γίνει προσπέλαση με 
            //το while loop παρακάτω

            while (tempIterator.hasNext()) {//ώσο υπάρχει επόμενη εγραφή στο iterator
                Map.Entry tempMapEntry = (Map.Entry) tempIterator.next();//Κάνει cast σε Map.Entry ώστε να έχω πρόσβαση στο key και value 
                //του HashMap

                TreeMap tempTreeMap = (TreeMap) tempMapEntry.getValue();//Μετατρέπει το tempMapEntry.getValue σε αντικείμενο
                //τύπου treeMap ώστε να μπορώ να έχω πρόσβαση στα αντικείμενα του
                for (Object entry : tempTreeMap.entrySet()) 
                {//Προσπελνά το treeMap
                    Map.Entry tempMapEntry2 = (Map.Entry) entry;//Κάνω cast την εγγραφή σε Map.Entry ώστε να μπορέι να πάρει το value
                    Reservation tempReservation = ((Reservation) tempMapEntry2.getValue());//μετατρέπει tempMapEntry2.getValue 
                    //σε Reservation για να πάρει το ID του

                    if (tempReservation.getName().equals(reserve_Name)) 
                    {//Εαν το ID τις κράτης που δώθηκε ο χρήστης ισούτε με αυτό στην tempReservation
                        System.out.println("Leitourgei seira 58 Hotel to SearchCode");
                        temp_reserve = tempReservation; //θέτει την temp_reserve με την κράτηση που γίνεται το iterate αυτή τη στιγμή

                        return temp_reserve;
                    }
                }
            }
          
        }
        return temp_reserve;//επιστρέφει την κράτηση
    }

    
    public List<Reservation> Search_date(Date search_date) 
    {
        List<Reservation> temp_reserve = new ArrayList<Reservation>();

        

        for (Object tempHashMap : hReservations.reserveList) {
            Set tempEntrySet = ((HashMap) tempHashMap).entrySet();//Μετατρέπει σε set ώστε να μπορέσει να διαβαστεί μετά

            Iterator tempIterator = tempEntrySet.iterator();//Φτιάχνει αντικείμενο iterator ώστε να μπορεί να γίνει προσπέλαση με 
            //το while loop παρακάτω

            while (tempIterator.hasNext()) 
            {//ώσο υπάρχει επόμενη εγραφή στο iterator
                Map.Entry tempMapEntry = (Map.Entry) tempIterator.next();//Κάνει cast σε Map.Entry ώστε να έχω πρόσβαση στο key και value 
                //του HashMap

                TreeMap tempTreeMap = (TreeMap) tempMapEntry.getValue();//Μετατρέπει το tempMapEntry.getValue σε αντικείμενο
                //τύπου treeMap ώστε να μπορώ να έχω πρόσβαση στα αντικείμενα του
                for (Object entry : tempTreeMap.entrySet()) 
                {//Προσπελνά το treeMap
                    Map.Entry tempMapEntry2 = (Map.Entry) entry;//Κάνω cast την εγγραφή σε Map.Entry ώστε να μπορέι να πάρει το value
                    Reservation tempReservation = ((Reservation) tempMapEntry2.getValue());//μετατρέπει tempMapEntry2.getValue 
                    //σε Reservation για να πάρει το ID του

                    if (tempReservation.Contains(search_date))
                    {//Εαν η ημερομηνία που έδωσε ο χρήστης περιέχεται σε κάποια κράτηση
                        System.out.println("Leitourgei seira 161 Hotel to SearchDate");
                        temp_reserve.add(tempReservation); //θέτει την temp_reserve με την κράτηση που γίνεται το iterate αυτή τη στιγμή
                    }
                }
            }
           
        }
        return temp_reserve;//επιστρέφει την κράτηση
    }
    
    public void UniqueTypes(Date occupied_date)
    {
        HashSet<String> typeHashSet = new HashSet<>();
        
        for (Object tempHashMap : hReservations.reserveList) 
        {
            Set tempEntrySet = ((HashMap) tempHashMap).entrySet();//Μετατρέπει σε set ώστε να μπορέσει να διαβαστεί μετά

            Iterator tempIterator = tempEntrySet.iterator();//Φτιάχνει αντικείμενο iterator ώστε να μπορεί να γίνει προσπέλαση με 
            //το while loop παρακάτω

            while (tempIterator.hasNext()) 
            {//ώσο υπάρχει επόμενη εγραφή στο iterator
                Map.Entry tempMapEntry = (Map.Entry) tempIterator.next();//Κάνει cast σε Map.Entry ώστε να έχω πρόσβαση στο key και value 
                //του HashMap

                TreeMap tempTreeMap = (TreeMap) tempMapEntry.getValue();//Μετατρέπει το tempMapEntry.getValue σε αντικείμενο
                //τύπου treeMap ώστε να μπορώ να έχω πρόσβαση στα αντικείμενα του
                
                Map.Entry<Date, Reservation> entry = tempTreeMap.floorEntry(occupied_date);//Περνει το entry που βρήσκεται στο floorEntry
                if (entry != null) 
                {//εαν βρήκε κάποιο entry
                    Reservation tempReservation = entry.getValue();//Πέρνει το Value του entry
                    
                    if (tempReservation.Contains(occupied_date)) 
                    {//εαν η μέρα που δώθηκε περιέχεται στην κράτηση
                        typeHashSet.add(tempReservation.getClass().getSimpleName());//Προσθέτω το όνομα της κλάσης στο πίνακα
                        //μονο μια φορά καθώς είναι hashset
                        
                    }
                }
                
            }
        }
        
        System.out.println("Periexei tous: ");
        
        for(String tempWord: typeHashSet)
        {
            System.out.println(typeNamingScheme.get(tempWord));
        }
    }
    
    public void TypeCount(String roomType)
    {
        //Na alakso tipo epistrofis!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }
    
    public void Delete_reservation(String reserve_ID) 
    {//Δειαγράφη μία κράτηση
        Reservation temp_reserve = new Reservation();

        for (Object tempHashMap : hReservations.reserveList) 
        {//Προσπελνά τη λίστα με τα Reservations
            Set tempEntrySet = ((HashMap) tempHashMap).entrySet();//Μετατρέπει σε set ώστε να μπορέσει να διαβαστεί μετά

            Iterator tempIterator = tempEntrySet.iterator();//Φτιάχνει αντικείμενο iterator ώστε να μπορεί να γίνει προσπέλαση με 
            //το while loop παρακάτω

            while (tempIterator.hasNext()) 
            {//ώσο υπάρχει επόμενη εγραφή στο iterator
                Map.Entry tempMapEntry = (Map.Entry) tempIterator.next();//Κάνει cast σε Map.Entry ώστε να έχω πρόσβαση στο key και value 
                //του HashMap

                TreeMap tempTreeMap = (TreeMap) tempMapEntry.getValue();//Μετατρέπει το tempMapEntry.getValue σε αντικείμενο
                //τύπου treeMap ώστε να μπορώ να έχω πρόσβαση στα αντικείμενα του
                for (Object entry : tempTreeMap.entrySet()) 
                {//Προσπελνά το treeMap
                    Map.Entry tempMapEntry2 = (Map.Entry) entry;//Κάνω cast την εγγραφή σε Map.Entry ώστε να μπορέι να πάρει το value
                    Reservation tempReservation = ((Reservation) tempMapEntry2.getValue());//μετατρέπει tempMapEntry2.getValue 
                    //σε Reservation για να πάρει το ID του

                    if (tempReservation.getID().equals(reserve_ID)) 
                    {//Εαν το ID τις κράτης που δώθηκε ο χρήστης ισούτε με αυτό στην tempReservation
                       System.out.println("Leitourgei seira 152 Hotel to Delete");
                       tempTreeMap.remove(tempReservation.getStart_date());//αφερεί μια εγραφή απο το treeMap
                       //tempTreeMap.get(tempReservation.getStart_date());
                       return;
                    }
                }

            }

        }

    }
}
