package com.portfolio.application.controller;

import com.portfolio.application.clients.IEXClient;
import com.portfolio.application.clients.Readers;
import com.portfolio.application.model.Holding;
import com.portfolio.application.model.Portfolio;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class Controller {
    @RequestMapping("/")
    public String index() {
        return "hello -Andre";
    }

    @RequestMapping("/portfolios")
    public List<Portfolio> getPortfolios() throws IOException {
        return Readers.readPortfolios();
    }

    @RequestMapping("/portfolio/{portfolioId}")
    public List<Holding> getPortfolioDetail(@PathVariable("portfolioId") Integer portfolioId) throws IOException, URISyntaxException, InterruptedException {
        List<Portfolio> portfolios = Readers.readPortfolios();
        Optional<Portfolio> filtered = portfolios.stream().filter(portfolio -> portfolio.getPortfolioId().equals(portfolioId)).findFirst();
        if (filtered.isEmpty()) {
            throw new RuntimeException("portfolioId doesn't exist");
        }
        Portfolio portfolio = filtered.get();
        List<Holding> holdings = new LinkedList<>();
        for (int i = 0; i < portfolio.getHoldingSymbols().length; i++) {
            Holding holding = new Holding();
            holding.setUnits(portfolio.getHoldingAmounts()[i]);
            holdings.add(IEXClient.enrichHoldingDetail(portfolio.getHoldingSymbols()[i], holding));
        }

        return holdings;
    }

}
