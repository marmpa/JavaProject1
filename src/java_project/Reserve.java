package java_project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Reserve {

    private String reserve_ID;
    private String reserve_Name;
    private int start_date;
    private int finish_date;
    
    protected SimpleDateFormat dateFormat;
    //private object rented;
    
    HashMap<Room,TreeMap<Date,Reservation>> reserveList;
    
        
    public Reserve() 
    {
        reserveList = new HashMap<Room,TreeMap<Date,Reservation>>();
        
        dateFormat = new SimpleDateFormat("dd/MM/YYYY");
    }
    
    public void Add(String reserve_ID,String reserve_Name,Date start_date,Date finish_date,Room room)
    {
        Reservation tempReservation = new Reservation(reserve_ID, reserve_Name, start_date, finish_date);//Φτιάχνει μια νέα κράτηση
        
        //ΠΡΟΣΟΧΗΗΗ ΝΑ ΚΑΝΩ ΕΛΕΝΧΩ ΓΙΑ ΔΙΠΛΩΤΥΠΑ
        
        if(reserveList.get(room) == null)
        {//Τσεκάρει αν υπάρχει το δωμάτιο στο ξενοδοχείο
            reserveList.put(room, new TreeMap<Date,Reservation>());
        }
        
        if(RoomAvailable(start_date, finish_date,room))
        {//Εάν το δωμάτιο είναι ελεύθερο όλες τις μέρες που θέλουμε
            reserveList.get(room).put(start_date, tempReservation);//Βάζει την κράτηση μέσα στον πίνακα για να μπορεί να ελενχθεί
        }
        
        
        
    }
    
    //ΠΡΟΣΟΧΗΗΗΗ ! -- Προς το παρόν δεν Χρειάζεται το παρακάτω
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
    
    public boolean RoomAvailable(Date start_date,Date finish_date,Room room)
    {
        int day,month,year;
        
        
        Date tempDate;
        Calendar tempCal = Calendar.getInstance();//Δημιουργείτε ένα νέο αντικείμενο τύπου Calendar το οποίο παίρνει ένα instance Calendar
        tempCal.setTime(start_date);//Θέτη την ημέρομηνια του calendar  σε αυτή του start_date
        tempDate=tempCal.getTime();//Μετατρέπει το calendar σε date 
        
        
        
        do
        {
            Map.Entry<Date, Reservation> entry = reserveList.get(room).floorEntry(tempDate);//Παίρνω την πιο κοντινή μικρότερη ημερομηνία
            //και την βάζω σε ένα Entry ώστε να μπορέσω να αντλήσω πληροφορίες απο αυτή
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
        
        
        return true;//Επιστρέφει αληθές αν ποτέ μέσα στο Loop δεν βρείκε εμπλεκόμενη ημερομηνία
    }
    
    public String getID()
    {
        return this.reserve_ID;
    }
    public String getName()
    {
        return this.reserve_Name;
    }

    public Reserve Search_code(String reserve_ID) {
        Reserve temp_reserve = new Reserve();
        for (Reserve i : reserveList) {
            int position=reserveList.indexOf(i);
            if (reserve_ID.equalsIgnoreCase(reserveList.get(position).getID())) {
                temp_reserve = reserveList.get(position);
                break;
            }
        }
        return temp_reserve;
    }

    public Reserve Search_name(String reserve_Name) {
        Reserve temp_reserve = new Reserve();
        for (Reserve i : reserveList) {
            int position=reserveList.indexOf(i);
            if (reserve_Name.equalsIgnoreCase(reserveList.get(position).getName())) {
                temp_reserve = reserveList.get(position);
                break;
            }
        }
        return temp_reserve;
    }
/* --ΗΜΙΤΕΛΗΣ ΑΚΟΜΑ--
    public void Search_date(int Start,int Finish) {
        for (Reserve i : reserveList) {
            int position=reserveList.indexOf(i);
            if (reserve_ID.equalsIgnoreCase(reserveList.get(position).getName())) {
                reserveList.remove(position);
                break;
            }
        }
    }
*/
    public void Delete_reserve(String reserve_ID) {
        for (Reserve i : reserveList) {
            int position=reserveList.indexOf(i);
            if (reserve_ID.equalsIgnoreCase(reserveList.get(position).getID())) {
                reserveList.remove(position);
                break;
            }
        }
    }
}
