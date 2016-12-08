package com.mygdx.game;

import org.usb4java.Device;

public class McuWithPeriBoard extends McuBoard
{
	private static final byte RQ_GET_SWITCH_1   = 1;
    private static final byte RQ_GET_SWITCH_2   = 2;
    private static final byte RQ_GET_SWITCH_3   = 3;
    private static final byte RQ_GET_SWITCH_4   = 4;
    private static final byte RQ_GET_SWITCH_5   = 5;
    private static final byte RQ_GET_LIGHT_1    = 6;
    private static final byte RQ_GET_LIGHT_2    = 7;
    private static final byte RQ_GET_LIGHT_3    = 8;
    public McuWithPeriBoard(Device device) {
		super(device);
	}

    public boolean getSwitch_1()
    {
        byte[] ret = this.read(RQ_GET_SWITCH_1, (short) 0, (short) 0);
//        System.out.format("%s from switch1\n",ret[0]);
        return ret[0] == 1;
    }
    public boolean getSwitch_2()
    {
        byte[] ret = this.read(RQ_GET_SWITCH_2, (short) 0, (short) 0);
//        System.out.format("%s from switch2\n",ret[0]);
        return ret[0] == 1;
    }
    public boolean getSwitch_3()
    {
        byte[] ret = this.read(RQ_GET_SWITCH_3, (short) 0, (short) 0);
//        System.out.format("%s from switch3\n",ret[0]);
        return ret[0] ==1;
    }
    public boolean getSwitch_4()
    {
        byte[] ret = this.read(RQ_GET_SWITCH_4, (short) 0, (short) 0);
//        System.out.format("%s from switch4\n",ret[0]);
        return ret[0] == 1;
    }
    public boolean getSwitch_5()
    {
        byte[] ret = this.read(RQ_GET_SWITCH_5, (short) 0, (short) 0);
//        System.out.format("%s from switch5\n",ret[0]);
        return ret[0] == 1;
    }
    
    public boolean getLight_1()
    {
        byte[] ret = this.read(RQ_GET_LIGHT_1, (short) 0, (short) 0);
//        System.out.format("%s from Red1\n",ret[0]);
        return ret[0] == 1;
    }
    public boolean getLight_2()
    {
        byte[] ret = this.read(RQ_GET_LIGHT_2, (short) 0, (short) 0);
//        System.out.format("%s from Green\n",ret[0]);
        return ret[0] == 1;
    }
    public boolean getLight_3()
    {
        byte[] ret = this.read(RQ_GET_LIGHT_3, (short) 0, (short) 0);
//        System.out.format("%s from Red2\n",ret[0]);
        return ret[0] == 1;
    }
}
