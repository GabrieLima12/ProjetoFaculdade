package com.grupoaps.principal;

import com.fazecast.jSerialComm.SerialPort;

import java.io.Serial;

public class Arduino {
    private static SerialPort comPort;
    public static void init()
    {
        for (int i = 0; i < SerialPort.getCommPorts().length; i++) {
            if (SerialPort.getCommPorts()[i].getSystemPortName().contains("cu.usbmodem"))
                comPort = SerialPort.getCommPorts()[i];
        }
        comPort.openPort();
        comPort.setBaudRate(9600);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10, 0);
        byte[] buffer = new byte[8];
        int bytesRead = comPort.readBytes(buffer, buffer.length);
    }

    public static boolean detect()
    {
        if (comPort.bytesAvailable() > 0)
        {
            byte[] buffer = new byte[8];
            int bytesRead = comPort.readBytes(buffer, buffer.length);
            if (bytesRead > 0) {
                return true;
            }
        }
        return false;
    }
}
