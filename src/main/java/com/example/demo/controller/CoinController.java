package com.example.demo.controller;

import com.example.demo.domain.CoinMarketModel;
import com.example.demo.service.CoinMarketService;
import com.example.demo.service.CoinMarketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class CoinController {
    private final CoinMarketService coinMarketService;

    @Autowired
    public CoinController(CoinMarketService coinMarketService) {
        this.coinMarketService = coinMarketService;
    }


    /**
     * @param pSearchTerm
     * @param model
     * @return
     */
    @GetMapping(value = "/coin")
    public String searchCoin(@RequestParam(value = "coinName", required = false) String pSearchTerm, Model model) {
        log.debug("searchCoinController");

        if (coinMarketService.coinExist(pSearchTerm)) {
            CoinMarketModel coin = coinMarketService.findByName(pSearchTerm);

            model.addAttribute("coinFullName", coin.getName());
            model.addAttribute("coinName", coin.getSymbol());
            model.addAttribute("change1h", coin.getPercent_change_1h());
            model.addAttribute("change24h", coin.getPercent_change_24h());
            model.addAttribute("change7d", coin.getPercent_change_7d());
            model.addAttribute("marketCapRanking", coin.getRank());
            model.addAttribute("marketCap", coin.getMarket_cap_usd());
            model.addAttribute("volume", coin.get_24h_volume_usd());
            model.addAttribute("date", coin.getLast_updated());
            return "coinPage";
        } else {
            return "redirect:404";
        }
    }
}