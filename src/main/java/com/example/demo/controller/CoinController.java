package com.example.demo.controller;

import com.example.demo.domain.CoinDTO;
import com.example.demo.domain.CoinMarketModel;
import com.example.demo.service.CoinMarketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class CoinController {
    private final CoinMarketService coinMarketService;

    @Autowired
    public CoinController(CoinMarketService coinMarketService) {
        this.coinMarketService = coinMarketService;
    }


    /**
     * @param pSearchTerm coin name requested by the user
     * @param model view to be displayed
     * @return coinPage or errorPage
     */
    @GetMapping(value = "/coin")
    public String searchCoin(@RequestParam(value = "coinName", required = false) String pSearchTerm, Model model) {
        log.debug("searchCoinController");

        if (coinMarketService.coinExistByName(pSearchTerm)) {
            CoinMarketModel coin = coinMarketService.findByName(pSearchTerm);
            CoinDTO coinDTO = new CoinDTO();
            coinDTO.setCoin(coin);
            model.addAttribute("coinFullName", coinDTO.getName());
            model.addAttribute("coinName", coinDTO.getSymbol());
            model.addAttribute("change1h", coinDTO.getPercent_change_1h());
            model.addAttribute("change24h", coinDTO.getPercent_change_24h());
            model.addAttribute("change7d", coinDTO.getPercent_change_7d());
            model.addAttribute("marketCapRanking", coinDTO.getRank());
            model.addAttribute("marketCap", coinDTO.getMarket_cap_usd());
            model.addAttribute("volume", coinDTO.get_24h_volume_usd());
            return "coinPage";
        } else {
            return "redirect:404";
        }
    }
}