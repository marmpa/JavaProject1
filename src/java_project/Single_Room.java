package java_project;
public class Single_Room extends Room
{
    public Single_Room(String rID,int rBedCount,double rPrice,boolean rView)
    {//Constructor ο οποίος είναι μόνο για Single Rooms
        super(rID,rBedCount,rPrice,1,rView);
    }
    
    public Single_Room(String rID,int rBedCount,double rPrice,double rBaseValue,boolean rView)
    {//Constructor ο οποίος καλείται απο όλες τις Υποκλάσεις εκτός απο όταν δημιουργείται απο την ίδια κλάση
        //θέτει rBaseValue για να βάλει σωστά την τιμή
        super(rID,rBedCount,rPrice,rBaseValue,rView);//καλείται η υπερκλάση με όλες τις μεταβλητές
    }
}
