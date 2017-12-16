package java_project;

import java.util.List;

public class Hotel 
{
    String hName;
    String hLocation;
    int hRating;
    
    public Hotel(String hName,String hLocation,int hRating,List<Room> hRooms,List<Car> hCars)
    {//Constructor ξενοδοχείου δημιουργεί τις ιδιότητες του ξενοδοχείου
        
        //Sxoleia gia thoma: Prepei na baleis kratiseis ston constructora den ksero pos na to po kai ti tipou thes
        //Sxoleia gia thoma: Epeiseis den ksero an thes listes gia ta domatia kai pos opote kai auto an thes alakse to
        
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
}
