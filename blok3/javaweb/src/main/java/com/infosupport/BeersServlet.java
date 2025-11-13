package com.infosupport;

import com.infosupport.domain.Beer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class BeersServlet extends HttpServlet {

    List<Beer> beers = List.of(new Beer("Leffe", 6.8), new Beer("Plzen", 5.0));

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
