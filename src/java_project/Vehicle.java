package java_project;
public class Vehicle 
{
   protected int vID;//Μοναδικό ID του οχήματος
   protected int vPrice;//τιμή οχήματος ανα μέρα
   protected int vWheels;//αριθμός τροχών οχήματος
   
   public Vehicle(int vID)
   {//Constructor οχήματος καθορίζει το μοναδικό ID
       this.vID = vID;
   }
   
   public int getPrice()
   {//επιστρέφει την τιμή
       return this.vPrice;
   }
}
