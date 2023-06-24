package vz.nolingo.Service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;

import lombok.SneakyThrows;
import vz.nolingo.Entity.BaseWord;
import vz.nolingo.UI.WordEditLayout;

@Service
@Qualifier("NewWordService")
public class NewWordService extends BaseWordService {
 

    private Dialog dialog;
    private WordEditLayout paramsLayout;

    public void showNewNounDialogWindow(Class<? extends BaseWord> clazz){
        dialog = new Dialog();
        dialog.addThemeName("dark");
        dialog.setHeaderTitle("New Noun");
        createWordEditLayout(clazz);
        Button saveButton = new Button("Save", e -> save(clazz,paramsLayout));
        dialog.getFooter().add(saveButton);
        dialog.open();
    }

    @SneakyThrows
    @SuppressWarnings("all")
    private void save(Class<? extends BaseWord> clazz,WordEditLayout newWordLayout){
        BaseWord a = clazz.newInstance();
        a.build(newWordLayout.getValues());
        getController(clazz).save(a);
        dialog.remove(paramsLayout);
        createWordEditLayout(clazz);

    }
    private void createWordEditLayout(Class<? extends BaseWord> clazz){
        paramsLayout = new WordEditLayout(clazz);
        dialog.add(paramsLayout);
    }
    
   
}
