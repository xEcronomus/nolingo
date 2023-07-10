package vz.nolingo.Service;

import java.util.Optional;

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
        createEmptyWordEditLayout(clazz);
        Button saveButton = new Button("Save", e -> save(clazz,paramsLayout));
        dialog.getFooter().add(saveButton);
        dialog.open();
    }

    public Dialog showUpdateDialogWindow(Class<? extends BaseWord> clazz,BaseWord word){
        dialog = new Dialog();
        dialog.addThemeName("dark");
        dialog.setHeaderTitle("New Noun");
        createWordEditLayout(clazz, word);
        Button saveButton = new Button("Save", e -> update(clazz,paramsLayout,word));
        dialog.getFooter().add(saveButton);
        dialog.open();
        return dialog;
    }

    @SneakyThrows
    @SuppressWarnings("all")
    private void save(Class<? extends BaseWord> clazz,WordEditLayout newWordLayout){
        BaseWord a = clazz.newInstance();
        a.build(newWordLayout.getValues());
        getController(clazz).save(a);
        dialog.remove(paramsLayout);
        createEmptyWordEditLayout(clazz);

    }

    @SneakyThrows
    @SuppressWarnings("all")
    private void update(Class<? extends BaseWord> clazz,WordEditLayout newWordLayout, BaseWord word){
        word.build(newWordLayout.getValues());
        getController(clazz).save(word);
        dialog.close();

    }
    private void createEmptyWordEditLayout(Class<? extends BaseWord> clazz){
        paramsLayout = new WordEditLayout(clazz, Optional.empty());
        dialog.add(paramsLayout);
    }
    
    public void createWordEditLayout(Class<? extends BaseWord> clazz, BaseWord word){
        paramsLayout = new WordEditLayout(clazz, Optional.of(word));
        dialog.add(paramsLayout);
    }
    
   
}
