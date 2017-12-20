package java_project;
public class Motorbike extends Dual_Vehicle{
    public Motorbike(String vID)
    {//Constructor της κλάσης Motorbike αρχικοποιεί τις βασικές μεταβλητές
        super(vID);//καλή constructor υπερκλάσης
        this.vPrice=20.0;
    }
}
