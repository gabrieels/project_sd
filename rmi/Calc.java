package rmi;

import java.rmi.RemoteException;

/**
 * rmi
 * @author gabriel
 * 18 de set de 2016
 */

public class Calc implements ICalc{

    public int add(int x, int y) throws RemoteException {
        return (x + y);
    }

    public int mult(int x, int y) throws RemoteException {
        return (x * y);
    }
}
