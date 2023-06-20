package com.example.nolingo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "nolingo")
public class MainView extends VerticalLayout {

    Component currentLayout;

    public MainView() {
        this.setAlignItems(Alignment.CENTER);

        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_PRIMARY);
        createNewWordButton(menuBar);

        add(menuBar);
    }

    private void createNewWordButton(MenuBar menuBar) {
        MenuItem newWord = menuBar.addItem("New word");
        SubMenu newWordSubMenu = newWord.getSubMenu();
        newWordSubMenu.addItem("Noun", e->showNounLayout());
        newWordSubMenu.addItem("Verb");
    }

    private void showNounLayout(){
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("New Noun");
        Button saveButton = createSaveButton(dialog);
        Button cancelButton = new Button("Cancel", e -> dialog.close());
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(saveButton);
        dialog.open();
        getStyle().set("position", "fixed").set("top", "0").set("right", "0")
                .set("bottom", "0").set("left", "0").set("display", "flex")
                .set("align-items", "center").set("justify-content", "center");
    }

    private Button createSaveButton(Dialog dialog) {
        return null;
    }

}
