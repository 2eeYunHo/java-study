package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoServerReceiveThread extends Thread {
	private Socket socket;
	
	public EchoServerReceiveThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
		String remteHostAddress = inetSocketAddress.getAddress().getHostAddress();
		int remoteHostPort = inetSocketAddress.getPort();
		EchoServer.log("connected by client[" + remteHostAddress + ":" + "]" + remoteHostPort);
		
		try {
			// 4. IO Stream 받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter( socket.getOutputStream(),"UTF-8"), true);
			while (true) {
				// 5. 데이터 읽기
				String data = br.readLine();// blocking
				if (data == null) {
					// 클라이언트가 정상적으로 종료
					// close() 호출
					EchoServer.log("closed by client");
					break;
				}

				EchoServer.log("received : "+data);
				pw.println(data);
				// 6. 데이터 쓰기
				pw.println(data);
			}
		} catch (SocketException ex) {
			System.out.println("[Server] suddenly closed by client");
		} catch (IOException ex) {
			System.out.println("[server] Error : " + ex);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}