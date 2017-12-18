
package java_project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Reservation 
{
    protected String reservation_ID;
    protected Date start_date;
    protected Date finish_date;
    protected String reservation_Name;
    
    protected SimpleDateFormat dateFormat;
    
    public Reservation(String reservation_ID,String reservation_Name,Date start_date,Date finish_date)
    {
        //Αρχικοποιώ τις μεταβλητές με βάση της τιμές που δώθηκαν
        this.reservation_ID=reservation_ID;
        this.start_date=start_date;
        this.finish_date=finish_date;
        this.reservation_Name=reservation_Name;
        dateFormat = new SimpleDateFormat("dd/MM/YYYY");//Θέτω το πώς θα φορματάρω την ημερομηνία για εκτύπωση
    }
    
    public boolean Contains(Date date)
    {
        return !(date.after(start_date)||date.before(finish_date));//Επιστρέφει εάν η ημερομηνία είναι ανάμεσα στις δύο τις τορινής κράτησης
    }
    
     public String getID()
    {//Επιστρέφει το ID της κράτησης
        return this.reservation_ID;
    }
    public String getName()
    {//Επιστρέφει το όνομα της κράτησης
        return this.reservation_Name;
    }
    
    @Override
    public int hashCode()
    {//Επιστρέφει το μοναδικό νούμερο που αντιστοιχεί στην κράτηση των συγκεκριμένων ημερών
        String tempHash= 
                dateFormat.format(this.start_date) //μετατρέπω την ημερομηνία που θέλει να κλείσει ο χρήστης σε String
                + " " 
                + dateFormat.format(this.finish_date) //μετατρέπω την ημερομηνία που θέλει να κλείσει ο χρήστης σε String
                + " " 
                + this.reservation_ID;
        return Objects.hashCode(tempHash);//Επιστρέφω μία μοναδική τιμη ώστε να μην ξαναυπάρχει
    }
    @Override
    public String toString()
    {
        return dateFormat.format(start_date);
    }
}
