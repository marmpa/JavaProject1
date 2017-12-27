//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hotel {

    String hName;
    String hLocation;
    int hRating;
    public Reserve hReservations;
    HashMap<String,String> typeNamingScheme;
    

    //na diloso domatia kai autokinita
    public Hotel(String hName, String hLocation, int hRating, List<Room> hRooms, List<Vehicle> hVehicle) {//Constructor ξενοδοχείου δημιουργεί τις ιδιότητες του ξενοδοχείου

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
        
        for(Room tempRoom:hRooms)
        {//Εάν δωθεί δωμάτιο τα προσθέτη στο ξενοδοχείο
            this.Add(tempRoom);
        }
        
        for(Vehicle tempCar:hVehicle)
        {//Εαν δωθεί όχημα τα προσθέτη στο ξενοδοχείο
            this.Add(tempCar);
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
           try
           {
               HashMap tempHashMap = (HashMap) reservationType;//Μετατρέπει σε HashMap
               tempHashMap.put(room_car_type, new TreeMap<Date, Reservation>());//Βάζει ένα νέο
               
               return;
           }
           catch(Exception e)
           {
               System.out.println("Edoses lathos tipou antikeimeno: "+room_car_type.getClass().getSimpleName());
           }
       }
    }
    
    public void NewReservation(String reserve_ID,String reserve_Name,Date start_date,Date finish_date,Object room_car_type)
    {//Προσθέτω μια νέα κράτηση στην λίστα
        this.hReservations.Add(reserve_ID, reserve_Name, start_date, finish_date, room_car_type);
    }
    
    public void Delete(String reservetion_id)
    {
        Object objectToDelete=null;
        for(Object reservationType:hReservations.reserveList)
       {
           
           for(Object reservationRV:((HashMap) reservationType).keySet())
           {//Προσπελνώ τα κλειδία του Hashmap για να βρώ αυτο που θέλω
                try 
                {   
                    String methodName = "getID";//Αποθηκεύω σε μία μεταβλητή το όνομα της μεθόδου που θέλω να καλέσω
                    Method objectMethod = reservationRV.getClass().getMethod(methodName, null);//απoθηκεύω την μέθοδο της αντίστοιχης κλάσεις του αντικειμένου
                    //rented ώστε να μπορέσω να έχω πρόσβαση σε αυτή και το αποτέλεσμα της

                    String reservationRVID = (String) objectMethod.invoke(reservationRV, null);//Καλώ την συνάρτηση getID ώστε να μου επιστρέψει
                    //το id του αντικειμένου(οχήματος ή δωματίου) για να το συγκρίνω με αυτο που έδωσε ο χρήστης
                    
                    if(reservationRVID.equals(reservetion_id))
                    {//Εάν το ID που δώθηκε είναι ίδιο με αυτό που βρέθηκε
                        objectToDelete=reservationRV;
                    }


                } catch (NoSuchMethodException ex) { //Διάφορα catch ώστε να λειτουργεί το παραπάνω reflection
                   Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SecurityException ex) {
                   Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IllegalAccessException ex) {
                   Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IllegalArgumentException ex) {
                   Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
               } catch (InvocationTargetException ex) {
                   Logger.getLogger(Hotel.class.getName()).log(Level.SEVERE, null, ex);
               } 
           }
           HashMap tempHashMap = (HashMap) reservationType;//Μετατρέπει σε HashMap
           if(tempHashMap.remove(objectToDelete)!=null);//Σβήνω αυτό το αντικείμενο
           {
                return;//Σταματάει αν διαγράψει ένα αντικείμενο
           }
       
       }
    }

    public Reservation Search_code(String reserve_ID,String Username) {
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

                    if (tempReservation.getID().equals(reserve_ID)&&tempReservation.getName().equalsIgnoreCase(Username))
                    {//Εαν το ID τις κράτης που δώθηκε ο χρήστης ισούτε με αυτό στην tempReservation και ο χρήστης είναι ο ίδιος με
                     //που έκανε την κράτηση τότε μπορεί να συνεχίσει
                       
                        temp_reserve = tempReservation; //θέτει την temp_reserve με την κράτηση που γίνεται το iterate αυτή τη στιγμή
                        return temp_reserve;
                    }
                }
            }
           
        }
        return temp_reserve;//επιστρέφει την κράτηση
    }

    public Reservation Search_name(String reserve_Name,String Username) 
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

                    if (tempReservation.getName().equals(reserve_Name)&&tempReservation.getName().equalsIgnoreCase(Username)) 
                    {//Εαν το ID τις κράτης που δώθηκε ο χρήστης ισούτε με αυτό στην tempReservation και ο χρήστης είναι ο ίδιος με
                     //που έκανε την κράτηση τότε μπορεί να συνεχίσει
                        
                        temp_reserve = tempReservation; //θέτει την temp_reserve με την κράτηση που γίνεται το iterate αυτή τη στιγμή

                        return temp_reserve;
                    }
                }
            }
          
        }
        return temp_reserve;//επιστρέφει την κράτηση
    }

    
    public List<Reservation> Search_date(Date search_date,String Username) 
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
                    
                    
                    if (tempReservation.Contains(search_date)&&tempReservation.getName().equalsIgnoreCase(Username))
                    {//Εαν η ημερομηνία που έδωσε ο χρήστης περιέχεται σε κάποια κράτηση και ο χρήστης είναι ο ίδιος με
                     //που έκανε την κράτηση τότε μπορεί να συνεχίσει
                       
                        temp_reserve.add(tempReservation); //θέτει την temp_reserve με την κράτηση που γίνεται το iterate αυτή τη στιγμή
                    }
                }
            }
           
        }
        return temp_reserve;//επιστρέφει την κράτηση
    }
    
    
    public void Delete_reservation(String reserve_ID,String Username) 
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

                    if (tempReservation.getID().equals(reserve_ID)&&tempReservation.getName().equalsIgnoreCase(Username)) 
                    {//Εαν το ID τις κράτης που δώθηκε ο χρήστης ισούτε με αυτό στην tempReservation και ο χρήστης είναι ο ίδιος με
                     //που έκανε την κράτηση τότε μπορεί να συνεχίσει
                       
                       tempTreeMap.remove(tempReservation.getStart_date());//αφερεί μια εγραφή απο το treeMap
                       //tempTreeMap.get(tempReservation.getStart_date());
                       return;
                    }
                }

            }

        }

    }
    
    public void UniqueTypes(Date occupied_date)
    {
        HashSet<String> typeHashSet = new HashSet<>();//Δημιουργώ ένα νέο αντικείμενο τύπου Hashset για να αποθηκεύσω μοναδικά string
        
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
                        typeHashSet.add(tempReservation.GetRented().getClass().getSimpleName());//Προσθέτω το όνομα της κλάσης στο πίνακα
                        //μονο μια φορά καθώς είναι hashset
                        
                    }
                }
                
            }
        }
        
        System.out.println("Periexei: ");
        
        for(String tempWord: typeHashSet)
        {
            System.out.println(typeNamingScheme.get(tempWord));
        }
    }
    
    public HashSet<String> UniqueTypes()
    {//Αυτή η κλάση επιστρέφει όλους τους τύπους δωματίων που υπάρχουν στο ξενοδοχείο
        HashSet<String> typeHashSet = new HashSet<>();//Δημιουργώ ένα νέο αντικείμενο τύπου Hashset για να αποθηκεύσω μοναδικά string
        
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
                        typeHashSet.add(tempReservation.GetRented().getClass().getSimpleName());//Προσθέτω το όνομα της κλάσης στο πίνακα
                        //μονο μια φορά καθώς είναι hashset
                        
                    }
                }
                
            }
        }
        
        System.out.println("Periexei: ");
        
        for(String tempWord: typeHashSet)
        {
            System.out.println(typeNamingScheme.get(tempWord));
        }
    }
    
    public void TypeCount(Date occupied_date)
    {
        /*Marie sosto to exeis void einai apla thelei na pairnei oxi string alla imerominia kai na kanei idio 
        elegxo me prin apla me 2 counter pleon!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
        
        //Na alakso tipo epistrofis!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        int normal_counter=0; //counter κοινών
        int luxury_counter=0; //counter πολυτελών
        List<String> typeHashSet = new ArrayList<>();
        
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
                        typeHashSet.add(tempReservation.GetRented().getClass().getSimpleName());//Προσθέτω το όνομα της κλάσης στο πίνακα
                        //μονο μια φορά καθώς είναι hashset
                        
                    }
                }
                
            }
        }
        for(String tempWord: typeHashSet)
        {
            
            //τσεκάρω αν τα αντικείμενα που έβαλα στο typeHashSet είναι μονόκλινα ή δίκλινα ή τρίκλινα
            if(typeNamingScheme.get(tempWord).equalsIgnoreCase("Monoklino")||typeNamingScheme.get(tempWord).equalsIgnoreCase("Diklino")||typeNamingScheme.get(tempWord).equalsIgnoreCase("Triklino"))
            {
                normal_counter++; //και αυξάνω ένα normal counter
            }
            else if(typeNamingScheme.get(tempWord).equalsIgnoreCase("Politeles Domatio"))//ή αν είναι πολυτελή
            {
                luxury_counter++; //και αυξάνω πάλι αντίστοιχο counter
            }
        }
        System.out.println("To koina domatia pou einai kleismena tin imerominia "+ occupied_date + " einai: " + normal_counter);
        System.out.println("To politeli domatia pou einai kleismena tin imerominia "+ occupied_date + " einai: " + luxury_counter);     
    }
    
    public void countPrice(Date occupied_date)
    {
        int month=occupied_date.getMonth();//παίρνω τον τρέχων μήνα
        
        double Sum_cost=0;
         
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
                //ΤΟ MONTH ΑΠΟ ΠΑΝΩ ΘΈΛΕΙ ΑΛΑΓΕΊ ΘΈΛΕΙ ΤΎΠΟΥ Date!!!!!!!!!!!!!!!!!!!!!!!!!
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                if (entry != null) 
                {//εαν βρήκε κάποιο entry
                    Reservation tempReservation = entry.getValue();//Πέρνει το Value του entry
                    
                    if (tempReservation.getStart_date().getMonth()==month)
                    {//εαν μηνας που έχουμε περιέχεται στην κράτηση
                        Sum_cost+=tempReservation.Cost(); //προσθέτουμε στο άρθοισμα τα χρήματα που λάβαμε
                    }
                }
            }
        }
        System.out.println("Ta xrimata pou ebgale to ksenodoxeio auton ton mina einai: " + Sum_cost);
    }
    
    
    
}
