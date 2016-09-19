package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * rmi
 * 
 * @author gabriel 18 de set de 2016
 */

public class CalcServer {

	public static void main(String args[]) {

		try {
			// instanciar o objeto calculadora (Calc)
			Calc calc = new Calc();

			// obter a referencia remota ap objeto
			ICalc proxy = (ICalc) UnicastRemoteObject.exportObject(calc, 3030);

			// Colacar o RMIRegistry em execução
			Registry reg = LocateRegistry.createRegistry(4040);

			// Registrar a referencia remota da calculadora
			reg.rebind("calcRemote", proxy);
		} catch (RemoteException ex) {
			System.out.println("Remote Excepetion: " + ex.getMessage());
		}

	}
}