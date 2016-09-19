package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * rmi
 * 
 * @author gabriel 18 de set de 2016
 */
public class CalcClient {

	public static void main(String args[]) {
		try {
			// Obter referencia do RMIRegistry
			Registry reg = LocateRegistry.getRegistry("localhost", 4040);

			// Obter a referencia do proxy do calc
			ICalc calc = (ICalc) reg.lookup("calcRemote");

			// Usar calculadora
			System.out.println(calc.add(5, 10));
			System.out.println(calc.mult(5, 10));
			System.out.println(calc.add(100, 100));
			
		} catch (RemoteException e) {
			System.out.println("RemoteException: " + e.getMessage());
		} catch (NotBoundException e) {
			System.out.println("Client: " + e.getMessage());
		}
	}
}
