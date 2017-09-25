package ar.edu.itba.pod.client;

import ar.edu.itba.pod.Networking;
import ar.edu.itba.pod.User;
import ar.edu.itba.pod.UserAvailableCallBackHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class Client {
    private static Logger logger = LoggerFactory.getLogger(Client.class);

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException, Exception {
        final Networking nw = (Networking) Naming.lookup("//0.0.0.0:1099/networking");
        System.out.println(nw.ping());
        System.out.println(nw.time());
        System.out.println(nw.echo("Szczęśliwy"));
        System.out.println(nw.hello("Chopi"));
        System.out.println(nw.fortune());

        User user = new User("dos", "Roberto el dinosaurio");
        nw.insertUser(user);
        UserAvailableCallBackHandler callback = user2 -> System.out.println(user2);

        final Remote remote = UnicastRemoteObject.exportObject(callback, 0);

            Thread thread = new Thread(() -> {
                try {
                    nw.getFirstInQueue(callback);
                } catch (RemoteException e){

                }
            });


        ExecutorService es = newFixedThreadPool(5);
        es.submit(thread);


        System.out.println("ASD");
    }


}
