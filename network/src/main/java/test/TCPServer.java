package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩(binding)
			// . .Socket에 InetSocketAddress(IP Address + Port)를 바인딩 한다.
			// . .IPAddress(0.0.0.0) : 특정호스트 IP를 바인딩하지 않는다.
			// . .Backlog : 요청 queue(10)
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5555), 10);

			// 3. accept
			// . .클라이언트로 부터 요청을 기다린다.
			Socket socket = serverSocket.accept(); // blocking

			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
			String remteHostAddress = inetSocketAddress.getAddress().getHostAddress();
			int remoteHostPort = inetSocketAddress.getPort();
			System.out.println("[server] connected by client[" + remteHostAddress + ":" + "]" + remoteHostPort);
			//
			try {
				// 4. IO Stream 받아오기
				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				while (true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = is.read(buffer); // blocking
					if (readByteCount == -1) {
						// 클라이언트가 정상적으로 종료
						// close() 호출
						System.out.println("[server] closed by client");
						break;
					}

					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[server] received :"+ data);

					// 6. 데이터 쓰기
					try {
						Thread.sleep(2000);
						os.write(data.getBytes("utf-8"));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
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
}
