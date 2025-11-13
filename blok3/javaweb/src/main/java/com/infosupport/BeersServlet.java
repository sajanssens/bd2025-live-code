package com.infosupport;

import com.infosupport.domain.Beer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.stream.Collectors.joining;

public class BeersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = """
                    <!DOCTYPE html>
                    <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <title>Beers</title>
                        </head>
                        <body>
                            <ul>
                                $beers
                            </ul>
                        </body>
                    </html>
                """.replace("$beers",
                BeerDao.beers.stream()
                        .map(beer -> "<li>" + beer + "</li>\n")
                        .collect(joining("")));
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = (String) req.getAttribute("brand");
        Beer beer = new Beer(brand, 4.0);
        BeerDao.beers.add(beer);
        req.getRequestDispatcher("ok.jsp").forward(req, resp);
    }
}
