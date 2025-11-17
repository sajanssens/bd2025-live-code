package com.infosupport.jeedemo;

import com.infosupport.jeedemo.domain.Beer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/beers")
public class BeersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("beers", BeerDao.beers);
        req.getRequestDispatcher("beers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = (String) req.getAttribute("brand");
        Beer beer = new Beer(brand, 4.0);
        BeerDao.beers.add(beer);
        req.getRequestDispatcher("ok.jsp").forward(req, resp);
    }
}
