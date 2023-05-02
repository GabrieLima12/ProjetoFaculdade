package com.grupoaps.principal;

import com.fazecast.jSerialComm.SerialPort;

public class Arduino {
    private static SerialPort comPort;
    public static void init()
    {
        comPort = SerialPort.getCommPort("cu.usbmodem1301");
        comPort.openPort();
        comPort.setBaudRate(9600);
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 10, 0);
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
