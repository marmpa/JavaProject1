//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Reservation 
{
    protected String reservation_ID;
    protected Date start_date;
    protected Date finish_date;
    protected String reservation_Name;
    protected Object rented;
    
    
    protected SimpleDateFormat dateFormat;
    
    protected HashMap<String,String> typeNamingScheme;
    
    public Reservation()
    {
        this.reservation_ID=null;
        this.start_date=null;
        this.finish_date=null;
        this.reservation_Name=null;
        dateFormat = new SimpleDateFormat("dd/MM/YYYY");//Θέτω το πώς θα φορματάρω την ημερομηνία για εκτύπωση
        
        typeNamingScheme = new HashMap<>();//Ενα HashMap για ονόματα των ενοικιαζομένων
        
        typeNamingScheme.put("Single_Room","Monoklino");
        typeNamingScheme.put("Double_Room","Diklino");
        typeNamingScheme.put("Triple_Room", "Triklino");
        typeNamingScheme.put("Luxury_Room", "Politeles Domatio");
        typeNamingScheme.put("Buggy", "Gourouna");
        typeNamingScheme.put("Car", "Autokinito");
        typeNamingScheme.put("Motorbike", "Mixanaki");
        typeNamingScheme.put("Hall", "Aithousa Ekdiloseon");
        
    }
        
        
    
    public Reservation(String reservation_ID,String reservation_Name,Date start_date,Date finish_date,Object rented)
    {
        //Αρχικοποιώ τις μεταβλητές με βάση της τιμές που δώθηκαν
        this.reservation_ID=reservation_ID;
        this.start_date=start_date;
        this.finish_date=finish_date;
        this.reservation_Name=reservation_Name;
        this.rented = rented;
        dateFormat = new SimpleDateFormat("dd/MM/YYYY");//Θέτω το πώς θα φορματάρω την ημερομηνία για εκτύπωση
        
        typeNamingScheme = new HashMap<>();//Ενα HashMap για ονόματα των ενοικιαζομένων
        
        typeNamingScheme.put("Single_Room","Monoklino");
        typeNamingScheme.put("Double_Room","Diklino");
        typeNamingScheme.put("Triple_Room", "Triklino");
        typeNamingScheme.put("Luxury_Room", "Politeles Domatio");
        typeNamingScheme.put("Buggy", "Gourouna");
        typeNamingScheme.put("Car", "Autokinito");
        typeNamingScheme.put("Motorbike", "Mixanaki");
        typeNamingScheme.put("Hall", "Aithousa Ekdiloseon");
    }
    
    public boolean Contains(Date date)
    {
        LocalDate tempStart_date,tempfinish_date,tempDate;
        
        tempStart_date = this.start_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        tempfinish_date = this.finish_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        tempDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        //return !(start_date.after(date)||finish_date.before(date));//Επιστρέφει εάν η ημερομηνία είναι ανάμεσα στις δύο τις τορινής κράτησης
        return !(tempStart_date.isAfter(tempDate)||tempfinish_date.isBefore(tempDate));//Επιστρέφει εάν η ημερομηνία είναι ανάμεσα στις δύο τις τορινής κράτησης
    }
    
     public String getID()
    {//Επιστρέφει το ID της κράτησης
        return this.reservation_ID;
    }
    public String getName()
    {//Επιστρέφει το όνομα της κράτησης
        return this.reservation_Name;
    }
    
    public Date getStart_date()
    {
        return this.start_date;
    }
    
    public Double Cost()
    {
        double cost=0;
        long difference = this.finish_date.getTime() - this.start_date.getTime();//παίρνουμε την διαφορά μεταξύ τους σε milliseconds
        float daysBetween = (difference / (1000*60*60*24))+1;//απο μιλι-δευτερολεπτα τα κάνουμε μέρες
        try 
        {
            String methodName = "getPrice";//Αποθηκεύω σε μία μεταβλητή το όνομα της μεθόδου που θέλω να καλέσω
            Method objectMethod = rented.getClass().getMethod(methodName, null);//απθηκεύω την μέθοδο της αντίστοιχης κλάσεις του αντικειμένου
            //rented ώστε να μπορέσω να έχω πρόσβαση σε αυτή και το αποτέλεσμα της
            
            cost = daysBetween * (double) objectMethod.invoke(rented, null);// δημιουργώ το κόστος το οποίο υπολογίζεται με βάση το γινόμενο
            //του αριθμό των ημερών οι οποίες ενοικιάζεται το αντικείμενο και το αποτέλεσμα της κλήσης της μεθόδου που δημιούργησα παραπάνω
            //αφού μετατραπεί σε double τύπο
            
            
            
        } catch (NoSuchMethodException ex) {//διάφορα catch τα οποία χρειάζονται για να μην πετάει error το παραπάνω
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cost;
    }
    public static Double CostBeforeReservation(Date start_date,Date finish_date,Object room_car_type)
    {
        double cost=0;
        long difference = finish_date.getTime() - start_date.getTime();//παίρνουμε την διαφορά μεταξύ τους σε milliseconds
        float daysBetween = (difference / (1000*60*60*24))+1;//απο μιλι-δευτερολεπτα τα κάνουμε μέρες
        try 
        {
            String methodName = "getPrice";//Αποθηκεύω σε μία μεταβλητή το όνομα της μεθόδου που θέλω να καλέσω
            Method objectMethod = room_car_type.getClass().getMethod(methodName, null);//απθηκεύω την μέθοδο της αντίστοιχης κλάσεις του αντικειμένου
            //rented ώστε να μπορέσω να έχω πρόσβαση σε αυτή και το αποτέλεσμα της
            
            cost = daysBetween * (double) objectMethod.invoke(room_car_type, null);// δημιουργώ το κόστος το οποίο υπολογίζεται με βάση το γινόμενο
            //του αριθμό των ημερών οι οποίες ενοικιάζεται το αντικείμενο και το αποτέλεσμα της κλήσης της μεθόδου που δημιούργησα παραπάνω
            //αφού μετατραπεί σε double τύπο
            
            
            
        } catch (NoSuchMethodException ex) {//διάφορα catch τα οποία χρειάζονται για να μην πετάει error το παραπάνω
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cost;
    }
    
    public Object GetRented()
    {
        return this.rented;
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
        String tempString;
        try
        {
        tempString = "\nΚράτηση: "+typeNamingScheme.get(this.rented.getClass().getSimpleName())
                    +"\nId κράτησης: "+this.reservation_ID
                    +"\nΌνομα κατόχου κράτησης: "+this.reservation_Name
                    +"\nEinai apo tis "+dateFormat.format(this.start_date)
                    +" mexri tis "+dateFormat.format(this.finish_date);
        }
        catch(Exception e)
        {
            tempString="none";
        }
        return tempString;
    }
    
    public String toString(boolean noNeed)
    {
        String tempString;
        try
        {
        tempString = "<html>Κράτηση: "+typeNamingScheme.get(this.rented.getClass().getSimpleName())
                    +"<br/>Id κράτησης: "+this.reservation_ID
                    +"<br/>Όνομα κατόχου κράτησης: "+this.reservation_Name
                    +"<br/>Einai apo tis "+dateFormat.format(this.start_date)
                    +" mexri tis "+dateFormat.format(this.finish_date)
                    +"</html>";
        }
        catch(Exception e)
        {
            tempString="none";
        }
        return tempString;
    }
    
    public String toFile()
    {
        String tempString;
        try
        {
        
        tempString = this.reservation_ID+", "
                + this.reservation_Name+", "
                + this.dateFormat.format(this.start_date)+", "
                + this.dateFormat.format(this.finish_date)+", "
                + typeNamingScheme.get(this.rented.getClass().getSimpleName())+ " ";
        }
        catch(Exception e)
        {
            tempString="none";
        }
        return tempString;
    }
}
