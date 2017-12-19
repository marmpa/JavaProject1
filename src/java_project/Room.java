package java_project;
public class Room
{
       protected String rID;//Μοναδικό αλφαρηθμιτικό
       protected int rBedCount;//αριθμός κρεβατίων
       protected double rPrice;//τιμή δωματίου
       protected double rBaseValue;//συντελεστής ο οποίος πολλαπλασιάζεται με τα χρήματα ώστε να δώσει το π.χ 160% της τιμής μονόκλινου
       protected boolean rView;//Αν έχει θέα η όχι
       
       protected double rViewPercent;//το επιπλέον ποσοστό που κοστίζει δομάτιο με θέα
       
       public Room(String rID,int rBedCount,double rPrice,double rBaseValue,boolean rView)
       {//constructor θέτει rID,rBedCount,rPrice,rBaseValue,rView
           this.rID=rID;
           this.rBedCount=rBedCount;
           this.rBaseValue = rBaseValue;
           this.rView = rView;
           
           this.rViewPercent = 0.1;//Ορίζω το ποσοστό τις θέας
           
           this.rPrice = rPrice*this.rBaseValue;//τιμή χωρίς να συμπεριλαμβάνεται η θέα
           this.rPrice = rView?((this.rPrice*this.rViewPercent)+this.rPrice):this.rPrice;//αν έχει θέα τότε προσθέτω το αντίστοιχο ποσοστό
           
       }
       
       public String getID()
       {
           return this.rID;
       }
       
       public Double getPrice()
       {//επιστρέφει την τιμή
           return this.rPrice;
       }
       
}
