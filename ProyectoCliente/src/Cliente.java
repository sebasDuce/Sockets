import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

	public Socket socket;
	public DataOutputStream dataOutputStream;
	public final int PUERTO = 1234;
	public final String HOST = "localhost";
	public String mensajeServer;
	public String palabraClave = "Cleopatra";

	public Cliente() throws UnknownHostException, IOException {
		this.socket = new Socket(HOST, PUERTO);
	}

	public void runCliente() throws IOException {
		
		DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
		System.out.println(dataInputStream.readUTF());
		
		this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
		
		Scanner scan = new Scanner(System.in);

		String mensaje = scan.nextLine();
		this.dataOutputStream.writeUTF(mensaje);
		
		
		while ((this.mensajeServer = dataInputStream.readUTF()) != null) {
			if(this.mensajeServer.equals(palabraClave))
			{
				System.out.println("El Servidor ha usado la palabra clave");
				return;
			}
			System.out.println("Servidor: "+this.mensajeServer);
			
			mensaje = scan.nextLine();
			this.dataOutputStream.writeUTF(mensaje);
		}
			
			this.dataOutputStream.writeUTF(palabraClave);
			dataInputStream.close();
			this.dataOutputStream.close();
			this.socket.close();
			scan.close();
		
	}
}
