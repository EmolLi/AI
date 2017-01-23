
import assign1.Test;

import java.io.*;
import java.net.Socket;


public class Main {

    public static void main(String[] args) {
        //Student student = new Student(4, "LIly");
        SOSPFPacket sos = new SOSPFPacket();
        sos.dstIP="fasdf";
        try {
            Socket s = new Socket("127.0.0.1",1450);
            //sos.dstIP="fdsafsa";
            //sos.routerID="fasdf";


//            Object o = new Object();
            ObjectOutputStream outputStream = new ObjectOutputStream(s.getOutputStream());
            outputStream.writeObject(sos);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //Test.iterativeDeepeningSearch();


    }


}
