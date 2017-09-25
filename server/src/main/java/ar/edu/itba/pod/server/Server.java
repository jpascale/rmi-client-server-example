package ar.edu.itba.pod.server;

import ar.edu.itba.pod.Networking;
import ar.edu.itba.pod.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Server {
    private static Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) throws RemoteException{
        logger.info("class4 Server Starting ...");

        ArrayList<String> arr = new ArrayList<>();
        arr.add("Hoy aprobaras pod");
        arr.add("Manana aprobaras pod");
        arr.add("Pasado aprobaras pod");

        Queue<User> users = new LinkedList<>();

        final Networking nw = new NetworkingImpl(arr, users);
        final Remote remote2 = UnicastRemoteObject.exportObject(nw, 0);
        final Registry registry = LocateRegistry.getRegistry();
        registry.rebind("networking", remote2);

        System.out.println("Service bound");
    }
}
