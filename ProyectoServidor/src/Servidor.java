import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {

	public Socket socket;
	public ServerSocket serverSocket;
	public final int PUERTO = 1234;
	public DataOutputStream dataOutputStream;
	public String mensajeCliente;
	public String mensaje;
	public String palabraClave = "Cleopatra";
	
	public Servidor() throws IOException {
		this.serverSocket = new ServerSocket(PUERTO); //Inicializamos el servidor
		this.socket = new Socket();
	}
	
	public void runServer() throws IOException {
		System.out.println("Iniciado servidor OK");
		System.out.println("Esperando conexiones de algún cliente");
		
		this.socket = this.serverSocket.accept(); //Esperando a que algún cliente se conecte
		
		System.out.println("Cliente conectado OK");
		
		Scanner scan = new Scanner(System.in);
		
		this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream()); //Flujo donde se guarda lo que se envía al cliente
		this.dataOutputStream.writeUTF("Conexión aceptada");
		
		DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
		
		while ((this.mensajeCliente = dataInputStream.readUTF()) != null) {
			if(this.mensajeCliente.equals(palabraClave))
			{
				System.out.println("El cliente ha usado la palabra clave");
				return;
			}
			
			System.out.println("Cliente: "+this.mensajeCliente);
			String mensaje = scan.nextLine();
			this.dataOutputStream.writeUTF(mensaje);
		}
		
		this.dataOutputStream.close();
		dataInputStream.close();
			this.serverSocket.close();
			this.socket.close();
			scan.close();
	}
	
}
