package java_project;
public class Single_Room extends Room
{
    public Single_Room(String rID,double rPrice,boolean rView)
    {//Constructor ο οποίος είναι μόνο για Single Rooms
        super(rID,1,rPrice,1,rView);
    }
}
