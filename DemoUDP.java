package demoudp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoUDP 
{
    
    public static int data = 0;
    
    private DatagramSocket socket;
    private DatagramPacket packet;
    private final int receiveport = 16789;
    private final int sendport = receiveport +1;
    private final int bufferSize = 1024;

    public DemoUDP() {
        try {
            socket = new DatagramSocket(sendport);
        } catch (SocketException e) {
            System.out.println("Socket exception occur. " + e.getMessage());
        }
    }

    public void closeUdp() {
        socket.close();
    }

    public void sendUdp() {
        String obj = String.valueOf(data);
        ObjectOutputStream os = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            os =  new ObjectOutputStream(baos);
            os.writeObject(obj);
            byte[] buffer = baos.toByteArray();
            packet = new DatagramPacket(buffer, buffer.length);
            packet.setAddress(InetAddress.getByName("localhost"));
            packet.setPort(receiveport);
            socket.send(packet);
        } catch (SocketException e) {
            System.out.println("Socket exception occur. " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException occur. " + e.getMessage());
        } finally {
            if (os != null) try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(DemoUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    public void receiveUdp() {
        ObjectInputStream is = null;
        try {
            byte[] buffer = new byte[bufferSize];
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            ByteArrayInputStream bais = new ByteArrayInputStream(packet.getData());
            is = new ObjectInputStream(bais);
        } catch (SocketException e) {
            System.out.println("Socket exception occur. " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IOException occur. " + e.getMessage());
        }
        finally {
            if (is != null) try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(DemoUDP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
	
	public static void main(String arg[]) throws Exception {
        DemoUDP hendler = new DemoUDP();
        
        MainPanel panel = new MainPanel();
        
        for (int count = 0; count < 10; count = count++ ) {
            
        hendler.sendUdp();
        Thread.sleep(1000/5); // 5Hz frequency
        DemoUDP.data = MainPanel.sliderData;
        System.out.println("data is" + String.valueOf(DemoUDP.data));
        }
        hendler.closeUdp();
    }
}