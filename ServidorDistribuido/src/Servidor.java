import java.io.*;
import java.net.*;
import java.util.logging.*;
import java.io.Console;

public class Servidor {
	private static DataOutputStream dos;
    private static DataInputStream dis;
    private int idSessio;
    private boolean puedeLeer;
    
    
    public static void main(String args[]) throws IOException {
    	
        ServerSocket ss;
        System.out.print("Inicializando servidor... ");
        try {
            ss = new ServerSocket(10578);
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexión entrante: "+socket);
                ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
                idSession++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    static synchronized void leer(Socket socket) {
    	
    	
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
				
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File ("archivo.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);

			// Lectura del fichero
			String linea;
			String texto="";
			while((linea=br.readLine())!=null) {
				texto=texto+linea+"\n";
			}
			//ENVIAMOS EL TEXTO AL CLIENTE
			dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(texto);
			
			//ESPERAMOS A QUE EL CLIENTE TERMINE DE LEER
			
			String leido="n";
			
			while(1==1) {
				if(leido.equalsIgnoreCase("n")) {
					dis=new DataInputStream(socket.getInputStream());
	    			leido = dis.readUTF();
    			}else {
    				break;
    			}
			}
			

		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			// En el finally cerramos el fichero, para asegurarnos
			// que se cierra tanto si todo va bien como si salta 
			// una excepcion.
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
	
}
}