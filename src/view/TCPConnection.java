package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import model.Bola;


public class TCPConnection extends Thread{
	
	private MainServer mainS;
	
	private BufferedReader bfr;
	private BufferedWriter bfw;

	public void run () {
		
		try {
			
			System.out.println("Iniciando Server");
			
			//puerto para que incie
			ServerSocket ss = new ServerSocket(9000);
			System.out.println("Esperando cliente");
			
			Socket conexion = ss.accept();// stops flujo hasta que haya conexion
			System.out.println("Se conecto un cliente");
			
			//para leer los msg
			InputStream is = conexion.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bfr = new BufferedReader(isr);
			
			//escribir los msg
			OutputStream os = conexion.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bfw = new BufferedWriter(osw);
			
		while (true) {
			
			System.out.println("Esperando mensaje");
			String msg = bfr.readLine(); 
			System.out.println(msg);
			
			Gson gson = new Gson();
			
			Bola name = gson.fromJson(msg, Bola.class);
			mainS.bolas(name);

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void Main(MainServer main) {
		this.mainS = main;
	}
	
}
