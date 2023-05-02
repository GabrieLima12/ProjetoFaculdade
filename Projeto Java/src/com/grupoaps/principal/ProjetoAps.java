package com.grupoaps.principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjetoAps {

    static int contador = 0;
    static long totalVolta = 0;
    static List<String> voltas;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a quantidade de voltas desejadas: ");
        int voltasDesejadas = scanner.nextInt();

        Arduino.init();
        System.out.println("Detectando:");
        Instant start = Instant.now();
        voltas = new ArrayList<>();

        while (true) {
            // nosso codigo
            if (Arduino.detect()) {
                Instant end = Instant.now();
                Duration timeElapsed = Duration.between(start, end);
                totalVolta += timeElapsed.toMillis();
                long n = timeElapsed.toMillis();
                String formattedTime = String.format("%02d:%02d.%03d", n / 60000, (n / 1000) % 60, (n % 1000));
                voltas.add(formattedTime);
                start = end;
                for (int i = 0; i < voltas.size(); i++) {
                    System.out.println("Volta " + (i + 1) + ": " + voltas.get(i));
                }
                contador += 1;
                System.out.println("Tempo total: " + String.format("%02d:%02d.%03d", totalVolta / 60000, (totalVolta / 1000) % 60, (totalVolta % 1000)));

                System.out.println("--------------------------------------");
                if (contador > voltasDesejadas - 1) break;
            }
        }

        // Save lap times to MySQL database
        try {
            saveLapTimes(voltas, String.format("%02d:%02d.%03d", totalVolta / 60000, (totalVolta / 1000) % 60, (totalVolta % 1000)));
        } catch (SQLException | IndexOutOfBoundsException e) {
            System.err.println("Error saving lap times to MySQL: " + e.getMessage());
            // System.out.println("O número de voltas tem que ser no mínimo: 1 e no máximo: 4. Total de voltas desejadas: " + voltasDesejadas);
        }

        // comPort.closePort(); // Descomente esta linha caso queira fechar a porta após um determinado evento
    }

    private static void saveLapTimes(List<String> lapTimes, String totalTime) throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/arduino_data";
        String DB_USER = "root";
        String DB_PASSWORD = "Cuzao798!@#";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO lap_times (lap_time_1, lap_time_2, lap_time_3, lap_time_4, total_time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            for (int i = 0; i < lapTimes.size(); i++) {
                stmt.setString(i+1, lapTimes.get(i));
            }
            stmt.setString(5, totalTime);
            stmt.executeUpdate();
        }
    }
}
