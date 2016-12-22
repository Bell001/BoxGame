package com.mygdx.game;

import org.usb4java.Device;

public class TestSwitch implements Runnable{
	
	public static boolean sw1 = true;
	public static boolean sw2 = true;
	public static boolean sw3 = true;
	public static boolean sw4 = true;
	public static boolean sw5 = true;
	public static boolean red1 = true;
	public static boolean green= true;
	public static boolean red2= true;

	public void monitor() throws Exception {
	    
		
		McuBoard.initUsb();
		
        try
        {
        	Device[] devices = McuBoard.findBoards();
        	
            McuWithPeriBoard peri = new McuWithPeriBoard(devices[0]);

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
	
	public static boolean getsw1() { 
	    return sw1;
	}
	
	public static boolean getsw2() { 
		return sw2;
	}
	
	public static boolean getsw3() { 
		return sw3;
	}
	
	public static boolean getsw4() { 
		return sw4;
	}
	
	public static boolean getsw5() { 
		return sw5;
	}
	
	public static boolean getred1() { 
	    return red1;
	}
	
	public static boolean getgreen() { 
	    return green;
	}
	
	public static boolean getred2() { 
	    return red2;
	}
	
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
