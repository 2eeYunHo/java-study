package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private static final String IP= "0.0.0.0";
	private static final int PORT = 6666;
	private static List<Writer> listWriters;
	public static void main(String[] args) {
		ServerSocket serverSocket=null;
		listWriters = new ArrayList<Writer>();

		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			// 2. 소켓을 호스트의 포트와 바인딩
			//String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(IP, PORT));
			log("연결 대기중 :" + IP + ":" + PORT);
			// 3. 요청대기
			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				log("error:" + e);
			}
		}
	}

	public static void log(String log) {
		System.out.println("[ChatServer]" + log);
	}

}