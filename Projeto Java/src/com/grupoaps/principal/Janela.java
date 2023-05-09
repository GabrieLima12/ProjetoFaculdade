package com.grupoaps.principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Janela implements ActionListener{

    JFrame frame;
    JButton startButton;
    JButton testButton;
    JLabel lVoltaTotal;
    JLabel lVolta1;
    JLabel lVolta2;
    JLabel lVolta3;
    JLabel lVolta4;
    String sMillis;
    String sSeconds;
    String sMinutes;

    StopWatch sw;

    Janela(StopWatch stopwatch)
    {
        sw = stopwatch;
        sMillis = String.format("%03d", sw.getMillis());
        sSeconds = String.format("%02d", sw.getSeconds());
        sMinutes = String.format("%02d", sw.getMinutes());

        frame = new JFrame();
        frame.setTitle("Melhor cronômetro da APS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().setBackground(new Color(20, 20, 20));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
                    frame.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Tentativa de PrintScreen detectado!");
                    System.exit(0);
                }
            }
        });

        startButton = new JButton("START");
        startButton.setBounds(150, 20, 100, 40);
        startButton.setBorder(new LineBorder(Color.WHITE));
        startButton.setBackground(new Color(60, 60, 60));
        startButton.setForeground(new Color(230, 230, 230));
        startButton.setFont(new Font("Verdana", Font.BOLD, 12));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        testButton = new JButton("LAP");
        testButton.setBounds(150, 280, 100, 40);
        testButton.setBorder(new LineBorder(Color.WHITE));
        testButton.setBackground(new Color(60, 60, 60));
        testButton.setForeground(new Color(230, 230, 230));
        testButton.setFont(new Font("Verdana", Font.BOLD, 12));
        testButton.setFocusable(false);
        testButton.addActionListener(this);

        lVoltaTotal = new JLabel();
        lVoltaTotal.setText(sMinutes + ":" + sSeconds + "." + sMillis);
        lVoltaTotal.setBounds(100, 50, 200, 100);
        lVoltaTotal.setFont(new Font("Verdana", Font.BOLD, 32));
        lVoltaTotal.setForeground(new Color(250, 250, 250));
        lVoltaTotal.setHorizontalAlignment(JTextField.CENTER);

        lVolta1 = new JLabel();
        lVolta1.setText(sMinutes + ":" + sSeconds + "." + sMillis);
        lVolta1.setBounds(100, 100, 200, 100);
        lVolta1.setFont(new Font("Verdana", Font.BOLD, 22));
        lVolta1.setForeground(new Color(180, 180, 180));
        lVolta1.setHorizontalAlignment(JTextField.CENTER);

        lVolta2 = new JLabel();
        lVolta2.setText(sMinutes + ":" + sSeconds + "." + sMillis);
        lVolta2.setBounds(100, 130, 200, 100);
        lVolta2.setFont(new Font("Verdana", Font.BOLD, 22));
        lVolta2.setForeground(new Color(180, 180, 180));
        lVolta2.setHorizontalAlignment(JTextField.CENTER);

        lVolta3 = new JLabel();
        lVolta3.setText(sMinutes + ":" + sSeconds + "." + sMillis);
        lVolta3.setBounds(100, 160, 200, 100);
        lVolta3.setFont(new Font("Verdana", Font.BOLD, 22));
        lVolta3.setForeground(new Color(180, 180, 180));
        lVolta3.setHorizontalAlignment(JTextField.CENTER);

        lVolta4 = new JLabel();
        lVolta4.setText(sMinutes + ":" + sSeconds + "." + sMillis);
        lVolta4.setBounds(100, 190, 200, 100);
        lVolta4.setFont(new Font("Verdana", Font.BOLD, 22));
        lVolta4.setForeground(new Color(180, 180, 180));
        lVolta4.setHorizontalAlignment(JTextField.CENTER);

        frame.add(startButton);
        frame.add(testButton);
        frame.add(lVoltaTotal);
        frame.add(lVolta1);
        frame.add(lVolta2);
        frame.add(lVolta3);
        frame.add(lVolta4);
        frame.setVisible(true);

    }

    public void updateTimeLabel(String total, String volta)
    {
        lVoltaTotal.setText(total);

        switch (sw.getLap())
        {
            case 1:
            {
                lVolta1.setText(volta);
                break;
            }
            case 2:
            {
                lVolta2.setText(volta);
                break;
            }
            case 3:
            {
                lVolta3.setText(volta);
                break;
            }
            case 4:
            {
                lVolta4.setText(volta);
                break;
            }
        }
    }



    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == startButton)
        {
            sw.start();
        }

        if (e.getSource() == testButton)
            sw.lap();
    }
}
