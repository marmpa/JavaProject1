package java_project;
public class Dual_Vehicle extends Vehicle
{
   public Dual_Vehicle(int vID)
   {//Constructor δίτροχου οχήματος καθορίζει το μοναδικό ID καθώς και τον αριθμό των τροχών
       super(vID);//καλή την constructor της υπερκλάσης
       this.vWheels = 2; 
   }
}
