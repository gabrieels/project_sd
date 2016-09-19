package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * rmi
 * @author gabriel
 * 18 de set de 2016
 */

public interface ICalc extends Remote {
    public int add(int x, int y) throws RemoteException;
    public int mult(int x, int y) throws RemoteException;
}
