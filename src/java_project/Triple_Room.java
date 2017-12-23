//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;
public class Triple_Room extends Room
{
    public Triple_Room(String rID,double rPrice,boolean rView)
    {//Constructor τρίκλινου δωμάτιο
        super(rID,3,rPrice,2.1,rView);//Καλή constructor  υπερκλάσης με ορίσματα που χρειάζεται
    }
}
