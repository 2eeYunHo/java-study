package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "0.0.0.0";
	private static final int SERVER_PORT = 6666;

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		try {
			// 스캐너 연결
			scanner = new Scanner(System.in);
			// 1. 소켓생성
			socket = new Socket();
			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			// 연결성공시 메시지
			log("connecting");

			// 3. IO Stream 받아오기 Reader랑Writer
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);

			System.out.println("대화명을 입력하시오 : ");
			String nickname = scanner.nextLine();
			pw.println("join:" + nickname);

			new ChatClientThread(socket).start();
			while (true) {
				System.out.print(">>");
				String input = scanner.nextLine();

				if ("quit".equals(input) == true)
					break;
				else
					pw.println("message:" + input);
			}
		} catch (IOException e) {
			log("error:" + e);
		}
		finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static void log(String log) {
		System.out.println("[Chat Client] " + log);
	}
}
