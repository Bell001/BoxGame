package com.mygdx.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Controller implements Runnable {
	private int value;
	private String word;
	
	public void monitor() throws Exception {
		String s = null;
		Socket sock = new Socket("localhost", 12345);
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(sock.getInputStream()));
		if(reader.ready()) {
			while (true) {
				s = reader.readLine();
				word =s;
				value = Integer.parseInt(s);
			}
		}       

//		reader.close();
//		sock.close();

	}
	
	public int getValue() { return value; }
	
	public String getword() { return word; }

	@Override
	public void run() {
		try {
			monitor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

