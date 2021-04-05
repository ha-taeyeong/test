package test;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		Socket client = null;
		BufferedWriter bw = null;
		Scanner sc = null;
		
		try {
			client = new Socket();
			client.connect(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 1234));
			bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(), "UTF-8"));
			sc = new Scanner(System.in);
			
			System.out.print("서버에게 전송할 메시지를 입력하세요 >>> ");
			String toServer = sc.nextLine();
			bw.write(toServer + "\n");
			bw.flush();
			System.out.println("서버(127.0.0.1)로  " + toServer + " 데이터를 전송했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) { bw.close(); }
				if(!client.isClosed()) { client.close(); }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
