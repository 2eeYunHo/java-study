package chat.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatWindow {
	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private String nickname;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	public ChatWindow(String nickname, Socket socket) {
		frame = new Frame(nickname + "의 채팅");
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		this.nickname = nickname;
		this.socket = socket;
		System.out.println(socket.getLocalSocketAddress() + ":" + socket.getPort());
	}

	public void show() {
		/**
		 * 1. UI 초기화
		 */
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener((ActionEvent actionEvent) -> {
			if (textField.getText().equals("")) {
				return;
			}
			sendMessage();
		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if (keyCode == KeyEvent.VK_ENTER) {
					if (textField.getText().equals("")) {
						return;
					}
					sendMessage();
				}
			}
		});
		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();
		IOStream();

		ChatClientThread chatClientThread = new ChatClientThread(socket);
		chatClientThread.run();
	}

	public void IOStream() {
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
		} catch (IOException e) {
			textArea.append("접속 Error");
			System.out.println("접속 Error" + e);
			System.exit(1);
		}
	}

	protected void finish() {
		pw.println("quit:");
		pw.flush();
		System.exit(0);
	}

	private void sendMessage() {
		String message = textField.getText();
		while (true) {
			pw.println("message:" + message);
			pw.flush();
			textField.setText("");
			textField.requestFocus();
			return;
		}
	}

	private void updateTextArea(String message) {
		textArea.append(message+"\n");
	}

	private class ChatClientThread extends Thread {
		private Socket socket;

		public ChatClientThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
				while (true) {
					String msg = reader.readLine();
					updateTextArea(msg);
					System.out.println(msg);
				}
			} catch (IOException e) {
				System.out.println("Error: " + e);
			}
		}
	}
}