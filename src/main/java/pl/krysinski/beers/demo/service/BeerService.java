package pl.krysinski.beers.demo.service;

import pl.krysinski.beers.demo.model.BeersInfo;

import java.util.List;

public interface BeerService {

    List<BeersInfo> getBeersList();
    void editBeer(BeersInfo newBeer);

}
