package java_project;
public class Quad_Vehicle extends Vehicle
{
    public Quad_Vehicle(String vID)
    {//Constructor του τετράτροχου οχήματος αρχικοποιεί 
        super(vID);//καλή constructor υπερκλάσης
        this.vWheels = 4;
    }
}
