//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;
public class Luxury_Room extends Double_Room
{
    private static double rBaseValueT=2.4;//ορίζω μια static μεταβλητή για να μπορώ να την χρήσειμοποιήσω στην αρχικοποιήση
    public Luxury_Room(String rID,double rPrice,boolean rView,boolean rHydroMassage)
    {//Constructor για πολυτελές δωμάτιο το οποίο αρχικοποιεί της μεταβλητές
        super(rID,rPrice,rHydroMassage?(rBaseValueT):(rBaseValueT*1.1),false);//Αν έχει υδρομασάζ τότε συμπεριλαμβάνω 10% στην τιμή
    }
}
