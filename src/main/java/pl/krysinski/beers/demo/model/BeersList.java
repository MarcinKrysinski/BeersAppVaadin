package pl.krysinski.beers.demo.model;


import java.util.ArrayList;
import java.util.List;

public class BeersList {
    private List<ShortBeersInfo> beers;

    public BeersList() {
        beers = new ArrayList<>();
    }

    public BeersList(List<ShortBeersInfo> beers) {
        this.beers = beers;
    }

    public List<ShortBeersInfo> getBeers() {
        return beers;
    }

    public void setBeers(List<ShortBeersInfo> beers) {
        this.beers = beers;
    }
}
