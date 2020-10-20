package pl.krysinski.beers.demo.dao;

import pl.krysinski.beers.demo.model.BeersInfo;

import java.util.List;

public interface BeerDao {

    List<BeersInfo> getBeers();
    void editBeer(BeersInfo newBeer);
    void addBeerToDb();
}
