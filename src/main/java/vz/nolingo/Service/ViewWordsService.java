package vz.nolingo.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.SneakyThrows;
import vz.nolingo.Entity.BaseWord;

@Service
@Qualifier("ViewWordService")
public class ViewWordsService extends BaseWordService {
  

    private Dialog dialog;

    private Grid<? extends BaseWord> gridLayout;

    public void showViewNounDialogWindow(Class<? extends BaseWord> clazz){
        dialog = new Dialog();
        
        dialog.setHeaderTitle(clazz.getSimpleName());
        

        gridLayout = new Grid<>(clazz, true);
        gridLayout.setItems(getItems(clazz));
        gridLayout.setSizeFull();
       
        dialog.add(gridLayout);


        Button cancelButton = new Button("Cancel", e -> dialog.close());

        Button deleteAllButton = new Button("Delete", e -> delete(clazz,gridLayout.getSelectedItems()));
        dialog.getFooter().add(cancelButton);
        dialog.getFooter().add(deleteAllButton);
        dialog.setSizeFull();
        dialog.setThemeName("dark");
        gridLayout.setThemeName("dark");
        dialog.open();
    }
    @SneakyThrows
    @SuppressWarnings("all")
    private void delete(Class<? extends BaseWord> clazz, Set<? extends BaseWord> selectedItems){
        BaseWord a = clazz.newInstance();
        getController(clazz).delete(selectedItems);
        gridLayout.setItems(getItems(clazz));
    }

    @SneakyThrows
    @SuppressWarnings("all")
    private <T extends BaseWord> List<T> getItems(Class<? extends BaseWord> clazz){
        BaseWord a = clazz.newInstance();
        return getController(clazz).findAll();
    }

    @SneakyThrows
    @SuppressWarnings("all")
    private void save(Class<? extends BaseWord> clazz,VerticalLayout newWordLayout){
        BaseWord a = clazz.newInstance();
        a.build(null);
        getController(clazz).save(a);
        
        
        
        dialog.close();
    }
    
}
