// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
package com.grupoaps.principal;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ProjetoAps {

    static int contador = 0;
    static long totalVolta = 0;
    static List<Long> voltas;

    public static void main(String[] args) {
        Arduino.init();
        Instant start = Instant.now();
        voltas = new ArrayList<>();

        while(true)
        {
            // nosso codigo
            if (Arduino.detect())
            {
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                totalVolta += timeElapsed.toMillis();
                voltas.add(timeElapsed.toMillis());
                start = end;
                for (int i = 0; i < voltas.size(); i++)
                {
                    contador += 1;
                    long n = voltas.get(i);
                    System.out.println("Volta " + (i+1) + ": " + String.format("%02d:%02d.%03d", n / 60000, (n / 1000) % 60, (n % 1000)));
                }
                System.out.println("Tempo total: "+ String.format("%02d:%02d.%03d", totalVolta / 60000, (totalVolta / 1000) % 60, (totalVolta % 1000)));


                System.out.println("--------------------------------------");
                if (contador > 3) break;
            }
        }

        // comPort.closePort(); // Descomente esta linha caso queira fechar a porta após um determinado evento
    }


}