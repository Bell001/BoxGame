package com.mygdx.game;

import java.io.*;
import java.net.*;

public class Net
	{
	    public static void main(String[] args) throws Exception
	    {
	        Socket sock = new Socket("localhost", 12345);
	        BufferedReader reader = new BufferedReader(
	           new InputStreamReader(sock.getInputStream()));
	        while (true) {
	            String s = reader.readLine();
	            if (s == null) break;
	            System.out.println(s);
	        }
	        reader.close();
	        sock.close();
	    }

}


