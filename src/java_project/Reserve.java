//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reserve {

    
    protected SimpleDateFormat dateFormat;
    
    
    public List reserveList;
    public HashMap<String,Object> rentalsList;
        
    public Reserve() 
    {
        reserveList = new ArrayList();
        rentalsList = new HashMap<>();
        
        reserveList.add(new HashMap<Room,TreeMap<Date,Reservation>>());
        reserveList.add(new HashMap<Car,TreeMap<Date,Reservation>>());
        
        dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    }
    
    public void AddRoom(Object room_car_type)
    {
        for(Object objectType:reserveList)
        {
            try
            {
                HashMap tempHashMap = (HashMap) objectType;
                tempHashMap.put(room_car_type, new TreeMap<Date, Reservation>());//Βάζει ένα νέο
                this.rentalsList.put(getObjectID(room_car_type),room_car_type);//Αντιστοιχό το id σε αντικείμενο
                return;
            }
            catch(Exception e)
            {
                System.out.println("Edoses lathos tipou antikeimeno: "+room_car_type.getClass().getSimpleName());
            }
            
        }
    }
    
    public boolean Add(String reserve_ID,String reserve_Name,Date start_date,Date finish_date,Object room_car_type)
    {
        Reservation tempReservation = new Reservation(reserve_ID, reserve_Name, start_date, finish_date,room_car_type);//Φτιάχνει μια νέα κράτηση
        
        //ΠΡΟΣΟΧΗΗΗ ΝΑ ΚΑΝΩ ΕΛΕΝΧΩ ΓΙΑ ΔΙΠΛΩΤΥΠΑ
        
        for(Object objectType:reserveList)
        {
            
            try
            {
                HashMap tempHashMap = (HashMap) objectType;
                
                if(tempHashMap.get(room_car_type) == null)
                {//Τσεκάρει αν υπάρχει το δωμάτιο στο ξενοδοχείο
                   AddRoom(room_car_type);
                    //System.out.println("Natos o paiktis ksexnaei ta antikeimena seira 49 reserve");
                }
                
                 if(Available(start_date, finish_date,room_car_type))
                 {//Εάν το δωμάτιο είναι ελεύθερο όλες τις μέρες που θέλουμε
                     
                     
                        System.out.println("Yes bike to "+reserve_ID);
                    
                     
                     ((TreeMap) tempHashMap.get(room_car_type)).putIfAbsent(start_date, tempReservation);//Βάζει την κράτηση μέσα στον πίνακα για να μπορεί να ελενχθεί
                     //αφού την κάνει cast σε TreeMap
                     return true;//Εαν καταφέρει να το βάλει στη
                 }
            }
            catch(Exception e)
            {
                
            }
            
        }
        return false;//Εάν δεν μπορέσει να το βάλει στη λίστα
    }
    
    //ΠΡΟΣΟΧΗΗΗΗ ! -- Προς το παρόν δεν Χρειάζεται το παρακάτω!!!!!!!!!!!!!!!!!!!!!
//    public int DateHashCode(Date start_date,Date finish_date)
//    {
//        int day,month,year,convertionInput1,convertionInput2,hashCode;
//        
//        
//        Calendar tempCal = Calendar.getInstance();
//        tempCal.setTime(start_date);
//        
//        day = tempCal.get(Calendar.DAY_OF_MONTH);
//        month = tempCal.get(Calendar.MONTH);
//        year = tempCal.get(Calendar.YEAR);
//        
//        convertionInput1 = day + (month-1)*30 + (year-2017)*365;
//        
//        tempCal.setTime(finish_date);
//        day = tempCal.get(Calendar.DAY_OF_MONTH);
//        month = tempCal.get(Calendar.MONTH);
//        year = tempCal.get(Calendar.YEAR);
//        
//        convertionInput2 = day + (month-1)*30 + (year-2017)*365;
//        
//        hashCode = convertionInput1*1000+convertionInput2;
//        return hashCode;
//    }
    //ΠΡΟΣΟΧΗΗΗΗ ! -- Προς το παρόν δεν Χρειάζεται το απο επάνω!!!!!!!!!!!!!!
    
    public boolean Available(Date start_date,Date finish_date,Object room_car_type)
    {
        int day,month,year;
        
        
        Date tempDate;
        Calendar tempCal = Calendar.getInstance();//Δημιουργείτε ένα νέο αντικείμενο τύπου Calendar το οποίο παίρνει ένα instance Calendar
        tempCal.setTime(start_date);//Θέτη την ημέρομηνια του calendar  σε αυτή του start_date
        tempDate=tempCal.getTime();//Μετατρέπει το calendar σε date 
        
        for(Object objectType:reserveList)
        {
            
            try
            {//Αντιστοιχο το αντικείμενο που δώθηκε με την αντίστοιχη κλάση
                
                HashMap tempHashMap = (HashMap) objectType;//Μετατρέπει το objectType σε HashMap για να μπορώ να χρεισημοποιείσω συναρτήσεις του
                
                do
                {//Ωσο η ημερομηνία που ψάχνουμε είναι μικροτερη του finish_date
                    Map.Entry<Date, Reservation> entry = ((TreeMap) tempHashMap.get(room_car_type)).floorEntry(tempDate);//Παίρνω την πιο κοντινή μικρότερη κράτηση με
                    //βάση την ημερομηνία και την βάζω σε ένα Entry ώστε να μπορέσω να αντλήσω πληροφορίες απο αυτή
                    if(entry!=null)
                    {//Εάν δεν βρήκε τπτ το floorEntry σημαίνει πως δεν υπάρχει κράτηση πριν την συγκεκριμένη μέρα
                        Reservation tempReservation = entry.getValue();//παίρνουμε την κράτηση και την αποθηκεύουμαι σε μία μεταβλητή
                            
                        if(tempReservation.Contains(tempDate))
                        {//Εάν η κράτηση περιέχει την ημερομηνία που δώσαμε επιστρέφουμαι false
                            
                            return false;
                        }
                    }
                    
                    tempCal.add(Calendar.DATE,1);//Προσθέτει 1 ημερολογιακή μέρα στην είδη υπάρχοντα μέρα
                    tempDate=tempCal.getTime();//Μετατρέπει το calendar σε date 
                }
                while(tempDate.compareTo(finish_date)<=0);//Ώσο η tempDate είναι μικρότερη η ίση με την finish_date
            }
            catch(Exception e)
            {
                
            }
        }
        
        return true;//Επιστρέφει αληθές αν ποτέ μέσα στο Loop δεν βρείκε εμπλεκόμενη ημερομηνία
    }
    
    public String getObjectID(Object room_car_type)
    {//Γυρνάει το ID του αντικειμένου
        String objectID = "0";
        try
        {
            String methodName = "getID";
            Method objectMethod = room_car_type.getClass().getMethod(methodName, null);//Παίρνει την μέθοδο με όνομα getID
            //ώστε να μπορώ να την καλώ κοινά και για τα δωμάτια και για τα αυτοκίνητα
            
            objectID = (String) objectMethod.invoke(room_car_type,null);//καλώ την μέθοδο απου αντιστοιχή στο αυτοκίνητο
            //και κάνω cast το αποτέλεσμα σε String
        } catch (NoSuchMethodException ex) {//exception για να ελένχω τα λάθοι
            Logger.getLogger(Reserve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Reserve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Reserve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Reserve.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Reserve.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objectID;
    }
    
    public String randomReservationIdGenarator()
    {//επιστρέφει ένα τυχαίο string
        Random randomGenerator = new Random();//Δημιουργεί ένα νέο αντικείμενο Random το οποίο θα χρησιμοποιηθεί για να βρεί τυχαίους
        //αριθμούς
        String generatedNumberToString = Integer.toString(randomGenerator.nextInt(90000)+10000);//Παράγει το τυχαίο αριθμό και το μετατρέπει
        //σε string
        
        return generatedNumberToString;//Επιστρέφει το String
    }

    
}
