package java_project;

import java.util.ArrayList;
import java.util.TreeMap;

public class Reserve {

    private String reserve_ID;
    private String reserve_Name;
    private int start_date;
    private int finish_date;
    //private object rented;
    
    ArrayList<Reserve> reserveList=new ArrayList<Reserve>();
    TreeMap<Date, Reservation> 
        
    public Reserve() {}
    
    public Add()
    {
        
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
