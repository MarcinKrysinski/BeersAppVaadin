package pl.krysinski.beers.demo.gui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;
import pl.krysinski.beers.demo.model.BeersInfo;
import pl.krysinski.beers.demo.model.ShortBeersInfo;

public class BeerForm  extends FormLayout {

    TextField id = new TextField("Id");
    TextField name = new TextField("Name");
    TextField description = new TextField("Description");
    TextField ibu = new TextField("Ibu");
    TextField firstBrewed = new TextField("First brewed");
    TextField imageUrl = new TextField("Image url");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<ShortBeersInfo> binder = new BeanValidationBinder<>(ShortBeersInfo.class);

    public BeerForm() {
        addClassName("beers-form");

        binder.bindInstanceFields(this);


        add(
                id,
                name,
                description,
                ibu,
                firstBrewed,
                imageUrl,
                buttonsLayout()
        );

    }

    public void setBeer(ShortBeersInfo beersInfo){
        binder.setBean(beersInfo);
    }

    private Component buttonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(event -> save.setEnabled(binder.isValid()));


        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        if (binder.isValid()){
            fireEvent(new SaveEvent(this, binder.getBean()));
        }
    }

    // Events
    public static abstract class ContactFormEvent extends ComponentEvent<BeerForm> {
        private ShortBeersInfo beerInfo;

        protected ContactFormEvent(BeerForm source, ShortBeersInfo beerInfo) {
            super(source, false);
            this.beerInfo = beerInfo;
        }

        public ShortBeersInfo getBeerInfo() {
            return beerInfo;
        }
    }

    public static class SaveEvent extends ContactFormEvent {
        SaveEvent(BeerForm source, ShortBeersInfo beersInfo) {
            super(source, beersInfo);
        }
    }

    public static class DeleteEvent extends ContactFormEvent {
        DeleteEvent(BeerForm source, ShortBeersInfo beersInfo) {
            super(source, beersInfo);
        }

    }

    public static class CloseEvent extends ContactFormEvent {
        CloseEvent(BeerForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }

}
