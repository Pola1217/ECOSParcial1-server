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
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Bola;

import processing.core.PApplet;

public class MainServer extends PApplet {
	
	
	int bx, by, cant, r, g, b, moveB;
	int randomNum;
	
	boolean crear, borrar, mover;
	
	ArrayList<Bola> bola;
	String datos = " ";
	
	//TCP
	private TCPConnection tcp;
	
	
	
	public static void main(String[] args) {
		PApplet.main(MainServer.class.getName());
	}
	
	public void settings() {
		
		size(1300, 700);
		
	}
	
	
	public void setup() {
		
		//instancias
		bx = 1000;
		by = 1000;
		
		r = 0;
		g = 0;
		b = 0;
		
		//cant = 0;
		
		//arreglo
		bola = new ArrayList<Bola>();
		
		//movimiento
		mover = true;
		
		//TCP
		tcp = new TCPConnection();
		tcp.Main(this);
		tcp.start();
		
	}
	
	public void draw() {
		
		//System.out.println(mouseX+","+mouseY);
		
		background(255);
		
		textAlign(CENTER);
		textSize(18);
		fill(0);
		text("Server", 250, 30);
		
		if(crear==true) {
			
			for (int i = 0; i < bola.size(); i++) {
				
				int posX = bola.get(i).getPosX() + moveB;
				int posY = bola.get(i).getPosY() + moveB;
				
				int r = bola.get(i).getR();
				int g = bola.get(i).getG();
				int b = bola.get(i).getB();
				
				//nombre grupo 
				String data = bola.get(i).getDatos();
				fill(0);
				text(data, posX, posY+50);
				
				//particula
				fill (r, g, b);
				ellipse (posX, posY, 20, 20);
				
				//movimiento
				if (mover == true) {
					
					move();
					
				} else {
					
					move();
				}
				
				
			}
			
		}
		
	}

	public void bolas (Bola bolas) {
		
		/*System.out.println(bolas.getPosX());
		System.out.println(bolas.getDatos());
		System.out.println(bolas.isCrear());*/
		for (int i = 0; i < bola.size(); i++) {	
			
		bx = bolas.getPosX();
		by = bolas.getPosY();
		
		r = bolas.getR();
		g = bolas.getG();
		b = bolas.getB();
		
		datos = bolas.getDatos();
		
		crear = bolas.isCrear();
		borrar = bolas.isBorrar();
		
		cant = bolas.getCant();
		
			
		bola.add(new Bola(bolas.getPosX(), bolas.getPosY(), bolas.getR(), bolas.getG(), bolas.getB(), bolas.getDatos(), bolas.isCrear(), bolas.isBorrar(), bolas.getCant()));
		
		
		if(borrar == true) {
			
			//bola.clear();
			bola.remove(bola.size());
			
		}
		}
		
	}
	
	public void move() {
		
		for (int i = 0; i < bola.size(); i++) {

			moveB = (int) random(0, 5);
			
			if (bx >= 1300) {
				
				bx = 1299;

			}

			else if (bx <= 0) {
				
				bx = 1;
				
			}

			if (by >= 700) {
				
				by = 699;
				
			}

			else if (by <= 0) {
				
				by = 1;
				
			}

			if (moveB == 1) {

				bola.get(i).setPosX(bola.get(i).getPosX() + 2);
				
			}
			
			if (moveB == 2) {

				bola.get(i).setPosX(bola.get(i).getPosX() - 2);
				
			}
			
			if (moveB == 3) {

				bola.get(i).setPosY(bola.get(i).getPosY() + 2);
				
			}
			
			if (moveB == 4) {

				bola.get(i).setPosY(bola.get(i).getPosY() - 2);
				
				
			}
			
		}

	}
	

	public void sentMsg() {
		
		
		
	}

	
}
