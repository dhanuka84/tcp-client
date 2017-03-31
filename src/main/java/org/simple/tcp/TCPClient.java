package org.simple.tcp;

import static java.nio.file.Files.readAllBytes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TCPClient {
	public static void main(String argv[]) throws Exception {
		String sentence;
		String modifiedSentence;
	
		byte[] bytes = getFileContent("/home/uranadh/kafka/kafka_10/analytics.json");
		int count = 0;
		
		
		while(true & count < 10){
			++count;
			
			Socket clientSocket = new Socket("localhost", 12345);
			
			clientSocket.setKeepAlive(false);
			clientSocket.setSendBufferSize(1*1024*1024);
		
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			/*BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));*/
			outToServer.writeBytes(new String(bytes)+"\n");
			System.out.println(new String(bytes,Charset.defaultCharset()));
			/*modifiedSentence = inFromServer.readLine();
			System.out.println("FROM SERVER: " + modifiedSentence);*/
			clientSocket.shutdownOutput();
			
			
		}
		//clientSocket.close();
		
		
		
	}
	
	public static byte[] getFileContent(final String fileLocation) throws IOException{
		Path path = Paths.get(fileLocation);
		return readAllBytes(path);
	}

}
