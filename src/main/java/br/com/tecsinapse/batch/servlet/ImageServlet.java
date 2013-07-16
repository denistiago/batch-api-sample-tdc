package br.com.tecsinapse.batch.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/images/*")
public class ImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        
        String imagePath = extractImagePath(req);

        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(imagePath))) {

            response.setHeader("Content-Type", getServletContext().getMimeType(imagePath));
            OutputStream output = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[8192];
            int length;
            while((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

        }

    }

    private String extractImagePath(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        int indexOf = requestURI.indexOf("/images/") + 7;
        String imagePath = requestURI.substring(indexOf);
        return imagePath;
    }
}
