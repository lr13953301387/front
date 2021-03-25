package com.example.demo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class draw {
    public static void main(String[] args) throws Exception
    {
        File sourceimage = new File("G:\\input\\download.jfif");

        BufferedImage image = ImageIO.read(sourceimage);
        Graphics2D g2d = image.createGraphics();
        Stroke stroke = new BasicStroke(1,
                BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_BEVEL,
                15.0f,
                null,
                5.0f);
        g2d.setStroke(stroke);
        g2d.setColor(new Color(255, 255, 255));
        List<CoordinateDto> list2 = new ArrayList<>();
        int ix=60;
        int iy=100;
        int fx=85;
        int fy=150;
        int ixx= 90;
        int iyy =150;
        int fxx=115;
        int fyy=180;
        list2.add(new CoordinateDto(ix,iy));
        list2.add(new CoordinateDto(fx,fy));
        list2.add(new CoordinateDto(ixx,iyy));
        list2.add(new CoordinateDto(fxx,fyy));
        int width =1;
        int height =1;
        getDirectionLine(list2,g2d);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.LIGHT_GRAY);
        //graphics.fillRect(0, 0, 200, 50);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("微软雅黑", Font.BOLD, 20));
        int wx=(fx+ix)/2;
        int wy=(fy+iy)/2;
        String word="river";
        graphics.drawString(word, wx, wy);

       // ImageIO.write(image, "PNG", new File("F:\\picture\\"+url+".png"));


        JFrame frame = new JFrame();
        JLabel label = new JLabel(new ImageIcon(image));
        frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.pack();
         frame.setVisible(true);
    }
    private static void getDirectionLine(List<CoordinateDto> list, Graphics2D g2){
        for (int i=0; i<list.size();i=i+2) {
            CoordinateDto startPoint = list.get(i);
            CoordinateDto endPoint = list.get(i+1);
            int sx = (int) (startPoint.x );
            int sy = (int) (startPoint.y );
            int ex = (int) (endPoint.x );
            int ey = (int) (endPoint.y );
            drawAL(sx, sy, ex, ey, g2);
        }
    }
    private static void drawAL(int sx, int sy, int ex, int ey, Graphics2D g2) {
        double H = 10;
        double L = 5;
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H);
        double arraow_len = Math.sqrt(L * L + H * H);
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0];
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0];
        double y_4 = ey - arrXY_2[1];

        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();

        g2.drawLine(sx, sy, ex, ey);

        g2.drawLine(ex, ey, x3, y3);
        g2.drawLine(ex, ey, x4, y4);


    }
    private static double[] rotateVec(int px, int py, double ang,
                                      boolean isChLen, double newLen) {
        double mathstr[] = new double[2];

        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

}
