import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;



class Persona extends Thread {
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private String archivo;
    private int id;
    public Persona(int id) {
        this.id = id;
    }
    
    public void run() {
        try {
        	//Se conecta al servidor
            sk = new Socket("127.0.0.1", 10578);
            while(1==1) {
            	//Muestra Menu
                System.out.println("Menu:\n1) Leer\n2) Desconectarse\n");
                //Scanner respMenu= new Scanner(System.in);
                
                //String respuestaMenu=respMenu.nextLine();
                
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String respuestaMenu = br.readLine();
                
                
                //ENVIAMOS RESPUESTA DEL MENU AL SERVIDORHILO
                dos=new DataOutputStream(sk.getOutputStream());
                dos.writeUTF(respuestaMenu);
                
                //Quiere leer el archivo
                if(respuestaMenu.equalsIgnoreCase("1")) {
                	System.out.println("Esperando el recurso.");
                	dis= new DataInputStream(sk.getInputStream());
                	archivo=dis.readUTF();
                	System.out.println(archivo);
                }
                
                if(respuestaMenu.equalsIgnoreCase("2")) {
                }
                
                if(respuestaMenu.equalsIgnoreCase("3")) {
                }
                
                //VOLVER AL MENU? S/N
                String respuestaVolver="n";
                while(respuestaVolver.equalsIgnoreCase("n")) {
                	System.out.println("Desea volver al menu?");
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    respuestaVolver= br1.readLine();
                }
                
                dos=new DataOutputStream(sk.getOutputStream());
                dos.writeUTF(respuestaVolver);
                
                
                dos.flush();
                
         
                
                
            }
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}



public class Cliente {
    public static void main(String[] args) {
        ArrayList clients = new ArrayList();
        for (int i = 0; i < 1; i++) {
            clients.add(new Persona(i));
        }
        
        for(Iterator rowIterator = clients.iterator(); rowIterator.hasNext(); ) {
        	Persona thread = (Persona) rowIterator.next();
            thread.start();
        }
        
        
        
    }
}