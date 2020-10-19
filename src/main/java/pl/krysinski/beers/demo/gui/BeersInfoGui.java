package pl.krysinski.beers.demo.gui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.krysinski.beers.demo.controller.BeerController;
import pl.krysinski.beers.demo.model.BeersInfo;


@Route("beers-info")
public class BeersInfoGui extends HorizontalLayout {

    private BeerController beerController;

    @Autowired
    public BeersInfoGui (BeerController beerController){
        this.beerController = beerController;
        getBeers();


    }

    public void getBeers(){
        Grid<BeersInfo> grid = new Grid<>(BeersInfo.class);

//        grid.setItems(beerController.getBeersInfo());


        grid.setColumns("id", "name", "description", "ibu", "firstBrewed", "imageUrl");
        add(grid);
    }
}
