package com.infosupport.jeedemo;

import com.infosupport.jeedemo.domain.Beer;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/beers")
@RequestScoped
public class BeersServlet extends HttpServlet {

    @Inject
    private BeerDao beerDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Beer> allBeers = beerDao.findAll();
        req.setAttribute("beers", allBeers);
        req.getRequestDispatcher("beers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        Beer beer = new Beer(brand, 4.0);
        System.out.println(beer);
        beerDao.create(beer);
        req.getRequestDispatcher("ok.jsp").forward(req, resp);
    }
}
