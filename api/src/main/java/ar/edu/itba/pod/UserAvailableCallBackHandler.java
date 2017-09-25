package ar.edu.itba.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by jpascale on 9/24/17.
 */
public interface UserAvailableCallBackHandler extends Remote {

    public void userAvailable(User user) throws RemoteException;
}
