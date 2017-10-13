package abc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class SocketsTest {

	public SocketsTest() throws IOException {
		
	}

	public static void main(String[] args) throws Exception {
		new SocketsTest();
		ServerSocket ss = new ServerSocket(9090);
		Socket socket = ss.accept();
		socket.getOutputStream().write("Welcome!\n".getBytes());
		
		System.out.println("Done");
		Thread.sleep(500);
		ss.close();
		

	}

}
