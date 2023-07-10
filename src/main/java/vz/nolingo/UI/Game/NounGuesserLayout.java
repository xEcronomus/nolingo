package vz.nolingo.UI.Game;

import java.util.List;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.Lumo;

import lombok.SneakyThrows;
import vz.nolingo.Entity.BaseWord;
import vz.nolingo.Entity.Noun;
import vz.nolingo.Enum.NounPronoun;
import vz.nolingo.UI.Game.Component.ScoreBasedInput;

public class NounGuesserLayout extends VerticalLayout implements IFinishable {
    
    Span word = new Span();
    
    Noun noun;

    
    public TextField plural = new TextField();

    HorizontalLayout buttonLayout = new HorizontalLayout();
    Button der = new Button("Der");
    Button die = new Button("Die");
    Button das = new Button("Das");

    GameLayout parent;

    public NounGuesserLayout(GameLayout parent,Noun noun,List<BaseWord> variations){
        this.noun = noun;
        this.parent = parent;
        word.add(noun.english);  
        word.setSizeUndefined();
        String solution = noun.german;
        String pluralSolution = noun.plural;

        helpDialog(solution+" - "+pluralSolution);
        
        ScoreBasedInput sbi = new ScoreBasedInput(this ,noun, variations);
        validatePrularInputData(pluralSolution);
        formatButtons(noun);

        buttonLayout.add(der,die,das);
       
        plural.setAutocomplete(Autocomplete.OFF);
        plural.setSuffixComponent(new Icon(VaadinIcon.ENTER));    
        add(word);
        add(sbi);
        add(buttonLayout);
        add(plural);
        setWidth("30%");    
        setAlignItems(Alignment.CENTER);
        getThemeList().add(Lumo.DARK);
        setSizeUndefined();
    }

    private void formatButtons(Noun noun) {
        der.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        die.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        das.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        setButtonEvents(noun.getPronoun());
        setButtonsEnabled(false);
    }

    
    private void validatePrularInputData(String solution) {
        plural.addKeyDownListener(e->{
            if(plural.getValue().equals(solution)){
                finished();
            }
            else{
                noun.score--;
            }
        });
    }

    private void helpDialog(String solution) {
        word.addClickListener(e->{
            Dialog helpDialog = new Dialog();
            helpDialog.setHeaderTitle(solution);
            Button cancelButton = new Button("Ok", e2 -> helpDialog.close());
            helpDialog.getFooter().add(cancelButton);
            helpDialog.open();
        });
    }

    private void setButtonsEnabled(boolean value){
        der.setEnabled(value);
        die.setEnabled(value);
        das.setEnabled(value);
    }


    private void setButtonEvents(NounPronoun pronoun){
        der.addClickShortcut(Key.DIGIT_1);
        die.addClickShortcut(Key.DIGIT_2);
        das.addClickShortcut(Key.DIGIT_3);
        der.addClickListener(e->{
           onClickEvent(der,NounPronoun.DER,pronoun);
        }); 
        die.addClickListener(e->{
           onClickEvent(die,NounPronoun.DIE,pronoun);
        }); 
        das.addClickListener(e->{
           onClickEvent(das,NounPronoun.DAS,pronoun);
        });
        
    }

    private void onClickEvent(Button button, NounPronoun buttonPronoun,NounPronoun solution){
        if(buttonPronoun == solution){
            setButtonsEnabled(false);
            plural.setEnabled(true);
            plural.focus();
        }
        else{
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
            ButtonVariant.LUMO_ERROR);
            noun.score--;
        }
    }

    @SneakyThrows
    private void finished(){
        parent.next();
        noun.score++;

    }
    

    public void sbiFinished(){
        setButtonsEnabled(true);
    }

    @Override
    public void finish() {
        sbiFinished();
    }

}
