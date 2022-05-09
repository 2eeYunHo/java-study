package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static final int PORT = 5555;

	public static void main(String[] args) {

		ServerSocket serverSocket = null;
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			// . .Socket에 InetSocketAddress(IP Address + Port)를 바인딩 한다.
			// . .IPAddress(0.0.0.0) : 특정호스트 IP를 바인딩하지 않는다.
			// . .Backlog : 요청 queue(10)
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			log("starts... [Port : " + PORT + " ]");
			// 3. accept
			// . .클라이언트로 부터 요청을 기다린다.
			while(true) {
				Socket socket = serverSocket.accept();
				new EchoServerReceiveThread(socket).start();
			}
		} catch (IOException e) {
			System.out.println("[Server] Error : " + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void log(String log) {
		System.out.println("[EchoServer : " + log);
	}
}
