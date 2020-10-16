package pl.krysinski.beers.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.krysinski.beers.demo.model.BeersInfo;

@RestController
public class BeerController {

    @CrossOrigin(origins = "https://api.punkapi.com/v2/beers")

    public BeersInfo[] getBeersInfo() {
        RestTemplate restTemplate = new RestTemplate();
        BeersInfo[] beersInfoObject = restTemplate.getForObject("https://api.punkapi.com/v2/beers", BeersInfo[].class);
        return beersInfoObject;

    }
}
