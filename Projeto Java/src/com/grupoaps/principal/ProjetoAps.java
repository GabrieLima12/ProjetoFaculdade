package com.grupoaps.principal;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ProjetoAps {

    static int contador = 0;
    static long totalVolta = 0;
    static List<String> voltas;

    public static void main(String[] args) throws InterruptedException {

        StopWatch sw = new StopWatch();
        Janela janela = new Janela(sw);

        while (true) {
            if (sw.isRunning())
                break;

            Thread.sleep(40);
        }

        Arduino.init();

        voltas = new ArrayList<>();


        while (true) {
            sw.update();
            janela.updateTimeLabel(sw.getFormattedTotalTime(), sw.getFormattedLapTime());

            if (Arduino.detect()) {
                voltas.add(sw.getFormattedLapTime());
                sw.lap();
            }

            if (sw.getLap() > 4)
                break;

        }

        // Save lap times to MySQL database
        try {
            saveLapTimes(voltas, sw.getFormattedTotalTime());
            JOptionPane.showMessageDialog(null, "Corrida salva no banco de dados!");
            System.exit(0);
        } catch (SQLException | IndexOutOfBoundsException e) {
            System.err.println("Error saving lap times to MySQL: " + e.getMessage());
        }

    }

    private static void saveLapTimes(List<String> lapTimes, String totalTime) throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/arduino_data";
        String DB_USER = "root";
        String DB_PASSWORD = "Cuzao798!@#";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO lap_times (lap_time_1, lap_time_2, lap_time_3, lap_time_4, total_time) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            for (int i = 0; i < lapTimes.size(); i++) {
                stmt.setString(i + 1, lapTimes.get(i));
            }
            stmt.setString(5, totalTime);
            stmt.executeUpdate();
        }
    }
}