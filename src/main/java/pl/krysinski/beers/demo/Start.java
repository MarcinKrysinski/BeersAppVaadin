package pl.krysinski.beers.demo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.krysinski.beers.demo.dao.BeerDao;

@Component
public class Start {

    private BeerDao beerDao;

    @Autowired
    public Start(BeerDao beerDao) {
        this.beerDao = beerDao;
        beerDao.addBeerToDb();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getAll(){
        System.out.println(beerDao.getBeers());
    }

}
