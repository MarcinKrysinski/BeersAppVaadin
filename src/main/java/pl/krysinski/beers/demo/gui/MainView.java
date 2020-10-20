package pl.krysinski.beers.demo.gui;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import pl.krysinski.beers.demo.model.BeersInfo;
import pl.krysinski.beers.demo.service.BeerService;
import pl.krysinski.beers.demo.service.BeerServiceImpl;



@Route("")
@CssImport("./styles/shared-styles.css")
public class MainView extends VerticalLayout {

    private BeerService beerService;
    private BeerForm form;

    Grid<BeersInfo> grid = new Grid<>(BeersInfo.class);

    public MainView(BeerService beerService) {
        this.beerService = beerService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new BeerForm();

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(content);
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setBeer(null);
        form.setVisible(false);
        removeClassName("editing");
    }


    private void configureGrid() {
        grid.addClassName("beers-grid");
        grid.setSizeFull();
        grid.getColumns().forEach(column -> column.setAutoWidth(true));
        grid.setColumns("id", "name", "description", "ibu", "firstBrewed", "imageUrl");
    }

    private void updateList() {
//        beerService.getBeersList().get(23).getIbu()
        grid.setItems(beerService.getBeersList());
    }
}
