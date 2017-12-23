//icsd15107 Λιάρος Θωμάς
//icsd15137 Μπαντόλας Μάριος
package java_project;
public class Double_Room extends Room
{
    public Double_Room(String rID,double rPrice,boolean rView)
    {//Constructor για δίκλινο δωμάτιο
        super(rID,2,rPrice,1.6,rView);
    }
    
    public Double_Room(String rID,double rPrice,double rBaseValue,boolean rView)
    {//Constructor που καλείται απο υποκλάσεις
        super(rID,2,rPrice,rBaseValue,rView);//καλείτε ο constructor και περνά κάποιες μεταβλητές
    }
}
