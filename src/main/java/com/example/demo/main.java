package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestParam;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.io.IOException;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class main {
    public static void main(String[] args) {
        SpringApplication.run(main.class, args);
    }


}
