package ar.edu.itba.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Networking extends Remote {
    String ping() throws RemoteException;
    String time() throws RemoteException;
    String echo(String msg) throws RemoteException;
    String hello(String name) throws RemoteException;
    String fortune() throws RemoteException;
    User echoUser(User user) throws RemoteException;
    void insertUser(User user) throws RemoteException;
    void getFirstInQueue(UserAvailableCallBackHandler callback) throws RemoteException;
}