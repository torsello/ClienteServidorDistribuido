import java.io.*;
import java.net.*;
import java.util.logging.*;
public class ServidorHilo extends Thread {
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessio;
    private boolean puedeLeer;
    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
    	String respuesta = "";
    	
    	try {

    		//Recibimos respuesta del Menu
    		while(1==1) {
    			
    			dis= new DataInputStream(socket.getInputStream());
    			respuesta=dis.readUTF();
    			switch(Integer.parseInt(respuesta)) {

		    			case 1: 
			    				Servidor.leer(socket);
			    				break;
		
		    			case 2: desconnectar();
		    			break;
		
		    			



    			}
    		
    			
    		}
            
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
   
    
    public void escribir() {
    	
    }
    
    public void desconectarse() {
    	
    }
}