package java_project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class Reserve {

    private String reserve_ID;
    private String reserve_Name;
    private int start_date;
    private int finish_date;
    //private object rented;
    
    HashMap<Room,TreeMap<Integer,Reservation>> reserveList;
    
        
    public Reserve() 
    {
        reserveList = new HashMap<Room,TreeMap<Integer,Reservation>>();
    }
    
    public void Add(String reserve_ID,String reserve_Name,Date start_date,Date finish_date,Room room)
    {
        Reservation tempReservation = new Reservation(reserve_ID, reserve_Name, start_date, finish_date);//Φτιάχνει μια νέα κράτηση
        
        //ΠΡΟΣΟΧΗΗΗ ΝΑ ΚΑΝΩ ΕΛΕΝΧΩ ΓΙΑ ΔΙΠΛΩΤΥΠΑ
        
        if(reserveList.get(room) == null)
        {//Τσεκάρει αν υπάρχει το δωμάτιο στο ξενοδοχείο
            reserveList.put(room, new TreeMap<Integer,Reservation>());
        }
        reserveList.get(room).put(DateHashCode(start_date, finish_date), tempReservation);
        
    }
    
    public int DateHashCode(Date start_date,Date finish_date)
    {
        int day,month,year,convertionInput1,convertionInput2,hashCode;
        
        
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(start_date);
        
        day = tempCal.get(Calendar.DAY_OF_MONTH);
        month = tempCal.get(Calendar.MONTH);
        year = tempCal.get(Calendar.YEAR);
        
        convertionInput1 = day + (month-1)*30 + (year-2017)*365;
        
        tempCal.setTime(finish_date);
        day = tempCal.get(Calendar.DAY_OF_MONTH);
        month = tempCal.get(Calendar.MONTH);
        year = tempCal.get(Calendar.YEAR);
        
        convertionInput2 = day + (month-1)*30 + (year-2017)*365;
        
        hashCode = convertionInput1*1000+convertionInput2;
        return hashCode;
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
