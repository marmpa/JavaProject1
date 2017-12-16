package java_project;
public class Car extends Quad_Vehicle
{
    public Car(int vID)
    {//Constructor αυτοκινήτου αρχικοποιεί rID και τιμή
        super(vID);//καλεί υπερκλάση του Car
        this.vPrice = 50;
    }
}
