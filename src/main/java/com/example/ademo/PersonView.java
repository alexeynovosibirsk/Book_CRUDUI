package com.example.ademo;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;
@Route("")
public class PersonView extends VerticalLayout {

    public PersonView(PersonService personService) {
        GridCrud<Person> crud = new GridCrud<>(Person.class, personService);
        crud.getGrid().setColumns("id", "firstName", "lastName");

        TextField textFieldName = new TextField();
        textFieldName.setClearButtonVisible(true);
        textFieldName.setPlaceholder("Имя");

        Button buttonFind = new Button("Поиск");
        buttonFind.addClickShortcut(Key.ENTER);
        buttonFind.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonFind.addClickListener(e -> {
            crud.getGrid().setItems(personService.findAllByFirstName(textFieldName.getValue()));
                });

        add(crud);
        crud.getCrudLayout().addFilterComponents(textFieldName, buttonFind);
        setSizeFull();
    }
}
