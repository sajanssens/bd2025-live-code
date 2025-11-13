package com.infosupport;

import com.infosupport.domain.Beer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/beers2")
public class Beers2Servlet extends HttpServlet {

    List<Beer> beers = List.of(new Beer("Leffe2", 6.8), new Beer("Plzen2", 5.0));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = """
                    <html>
                        <body>
                            $beers
                        </body>
                    </html>
                """.replace("$beers", beers.toString());
        resp.getWriter().println(html);
    }
}
