package ar.edu.itba.pod.server;

import ar.edu.itba.pod.Networking;
import ar.edu.itba.pod.User;
import ar.edu.itba.pod.UserAvailableCallBackHandler;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;


public class NetworkingImpl implements Networking {

    private ArrayList<String> fortune;
    private Queue<User> userQueue;

    public NetworkingImpl(ArrayList<String> fortune, Queue<User> userQueue){
        this.fortune = fortune;
        this.userQueue = userQueue;
    }

    @Override
    public String ping() throws RemoteException {
        return "pong";
    }

    @Override
    public String time() throws RemoteException {
        return String.valueOf(System.currentTimeMillis());
    }

    @Override
    public String echo(String msg) throws RemoteException {
        return msg;
    }

    @Override
    public String hello(String name) throws RemoteException {
        return "Hello " + name;
    }

    @Override
    public String fortune() throws RemoteException {
        Random rand = new Random();
        return fortune.get(rand.nextInt(this.fortune.size() - 1));
    }

    @Override
    public User echoUser(User user) throws RemoteException {
        System.out.println(user.getId() + " - " + user.getName());
        return user;
    }

    @Override
    public void insertUser(User user) throws RemoteException {
        this.userQueue.add(user);
    }

    @Override
    public void getFirstInQueue(UserAvailableCallBackHandler callback) throws RemoteException {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.userAvailable(userQueue.peek());
    }
}
