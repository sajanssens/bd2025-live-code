package com.infosupport.jeedemo;

import com.infosupport.jeedemo.domain.Beer;
import com.infosupport.jeedemo.domain.Repo;
import com.infosupport.jeedemo.domain.qualifiers.BEER;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/beers")
@RequestScoped
public class BeersServlet extends HttpServlet {

    @Inject
    private Logger log;

    @Inject @BEER
    private Repo<Beer> beerDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Performing doGet");
        Collection<Beer> allBeers = beerDao.findAll();
        req.setAttribute("beers", allBeers);
        req.getRequestDispatcher("beers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String brand = req.getParameter("brand");
        Beer beer = Beer.builder().make(brand).build();
        beerDao.create(beer);
        resp.sendRedirect("beers");
    }
}
