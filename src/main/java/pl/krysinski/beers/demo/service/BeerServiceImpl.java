package pl.krysinski.beers.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.krysinski.beers.demo.dao.BeerDao;
import pl.krysinski.beers.demo.model.BeersInfo;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService{

    private BeerDao beerDao;

    @Autowired
    public BeerServiceImpl(BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    @Override
    public List<BeersInfo> getBeersList() {
        return beerDao.getBeers();
    }


    @Override
    public void editBeer(BeersInfo newBeer) {
        beerDao.editBeer(newBeer);
    }
}
