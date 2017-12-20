
package java_project;

public class Buggy extends Quad_Vehicle
{
    public Buggy(String vID)
    {//Constructor buggy αρχικοποιεί τιμές
        super(vID);//Καλώ υπερκλάση με αλφαρηθμιτικό αναγνωριστικο
        this.vPrice = 30.0;//θέτω τιμή
    }
}
