//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;
public class Car extends Quad_Vehicle
{
    public Car(String vID)
    {//Constructor αυτοκινήτου αρχικοποιεί rID και τιμή
        super(vID);//καλεί υπερκλάση του Car
        this.vPrice = 50.0;
    }
}
