package com.example.demo;

import org.apache.catalina.util.RequestUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import java.lang.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.FileInputStream;
import java.io.OutputStream;
import javax.servlet.ServletOutputStream;


import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @RequestMapping("/set")
    public void hello(@RequestParam int[] thick,
                        @RequestParam int[] ix,@RequestParam int[] iy,@RequestParam int[] fx
            , @RequestParam int[] fy,@RequestParam String[] word
            ,@RequestParam int[] r,@RequestParam int[] g,@RequestParam int[] b,
                      HttpServletResponse response) throws Exception {


        File sourceimage = new File("G:\\input\\download.jfif");

        BufferedImage image = ImageIO.read(sourceimage);
        Graphics2D g2d = image.createGraphics();

        for (int i=0; i<=thick.length-1;i++) {
           // System.out.println(i);
            Stroke stroke = new BasicStroke(thick[i],
                    BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_BEVEL,
                    15.0f,
                    null,
                    5.0f);
            g2d.setStroke(stroke);
            g2d.setColor(new Color(r[i], g[i], b[i]));
            List<CoordinateDto> list2 = new ArrayList<>();
            list2.add(new CoordinateDto(ix[i], iy[i]));
            list2.add(new CoordinateDto(fx[i], fy[i]));
            int width = 1;
            int height = 1;
            getDirectionLine(list2, width, height, g2d);
            Graphics graphics = image.getGraphics();
            graphics.setColor(Color.LIGHT_GRAY);
            //graphics.fillRect(0, 0, 200, 50);
            graphics.setColor(Color.BLACK);
            graphics.setFont(new Font("微软雅黑", Font.BOLD, 20));
            int wx = (fx[i] + ix[i]) / 2+10;
            int wy = (fy[i] + iy[i]) / 2+10;
            //System.out.println(wx);
            //System.out.println(wy);
            graphics.drawString(word[i], wx, wy);
    }
        ServletOutputStream responseOutputStream = response.getOutputStream();
        ImageIO.write(image, "PNG", responseOutputStream);
        ImageIO.write(image, "PNG", new File("F:\\picture\\"+"modefiy.png"));

}
       // JFrame frame = new JFrame();
        //JLabel label = new JLabel(new ImageIcon(image));
        //frame.getContentPane().add(label, BorderLayout.CENTER);
        //frame.pack();
       // frame.setVisible(true);




    private static void getDirectionLine(List<CoordinateDto> list, int width, int height, Graphics2D g2){
        CoordinateDto startPoint = list.get(0);
        CoordinateDto endPoint = list.get(1);
        int sx = (int)(startPoint.x*width);
        int sy = (int)(startPoint.y*height);
        int ex = (int)(endPoint.x*width);
        int ey = (int)(endPoint.y*height);
        drawAL(sx, sy, ex, ey, g2);
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
