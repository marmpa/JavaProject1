package java_project;

import java.util.List;

public class Hotel 
{
    String hName;
    String hLocation;
    int hRating;
    Reserve hReservations;
    
    //na diloso domatia kai autokinita
    
    
    public Hotel(String hName,String hLocation,int hRating,List<Room> hRooms,List<Car> hCars)
    {//Constructor ξενοδοχείου δημιουργεί τις ιδιότητες του ξενοδοχείου
        
        //Sxoleia gia thoma: Prepei na baleis kratiseis ston constructora den ksero pos na to po kai ti tipou thes
        //Sxoleia gia thoma: Epeiseis den ksero an thes listes gia ta domatia kai pos opote kai auto an thes alakse to
        
        hReservations = new Reserve();
        
        this.hName = hName;
        this.hLocation = hLocation;
        
        if(hRating>=2&&hRating<=5)
        {//Ελένχω αν τα αστέρια του ξενοδοχείου είναι όσα απετείτε απο την άσκηση
            this.hRating=hRating;
        }
        else
        {//αλλιώς το θέτω με 2 (το χαμηλότερο πιθανό)
            this.hRating=2;
        }
        
        
    }
    public Reserve Search_code(String reserve_ID) {
        Reserve temp_reserve = new Reserve();
        
        Set entrySet = 
        
        for (Reservation i : hotelReserveList) {
            int position=hotelReserveList.indexOf(i);
            if (reserve_ID.equalsIgnoreCase(hotelReserveList.get(position).getID())) {
                temp_reserve = hotelReserveList.get(position);
                break;
            }
        }
        return temp_reserve;
    }

    public Reserve Search_name(String reserve_Name) {
        Reserve temp_reserve = new Reserve();
        for (Reserve i : hotelReserveList) {
            int position=hotelReserveList.indexOf(i);
            if (reserve_Name.equalsIgnoreCase(hotelReserveList.get(position).getName())) {
                temp_reserve = hotelReserveList.get(position);
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
        for (Reserve i : hotelReserveList) {
            int position=hotelReserveList.indexOf(i);
            if (reserve_ID.equalsIgnoreCase(hotelReserveList.get(position).getID())) {
                hotelReserveList.remove(position);
                break;
            }
        }
    }
}
