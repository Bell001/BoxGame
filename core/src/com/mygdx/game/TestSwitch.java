package com.mygdx.game;

import org.usb4java.Device;

public class TestSwitch implements Runnable{
	
	public boolean AA = true;
	public static boolean sw1;
	public boolean sw2;
	public boolean sw3;
	public boolean sw4;
	public boolean sw5;
	public static boolean red1;
	public static boolean green;
	public boolean red2;
	static int A =0;


	public void monitor() throws Exception {
	    
		
		McuBoard.initUsb();
		
        try
        {
        	Device[] devices = McuBoard.findBoards();
        	
//        	if (devices.length == 0) {
//                System.out.format("** Practicum board not found **\n");
//                return;
//        	}
//        	else {
//                System.out.format("** Found %d practicum board(s) **\n", devices.length);
//        	}
            McuWithPeriBoard peri = new McuWithPeriBoard(devices[0]);

//            System.out.format("** Practicum board found **\n");
//            System.out.format("** Manufacturer: %s\n", peri.getManufacturer());
//            System.out.format("** Product: %s\n", peri.getProduct());
            

            while (true) 
            {   
            	Thread.sleep(500);
                sw1 = peri.getSwitch_1();   
                sw2 = peri.getSwitch_2();
                sw3 = peri.getSwitch_3();
                sw4 = peri.getSwitch_4();
                sw5 = peri.getSwitch_5();
                red1 = peri.getLight_1();
                green = peri.getLight_2();
                red2 = peri.getLight_3();
                
                
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        
        McuBoard.cleanupUsb();
	}
	
	public static int getValue2() { 
		if(green) {
			A =2;
		} else {
			A=1;
		}
		return A; }
	
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
