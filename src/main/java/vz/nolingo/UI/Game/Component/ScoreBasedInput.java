package vz.nolingo.UI.Game.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;

import vz.nolingo.Entity.BaseWord;
import vz.nolingo.UI.Game.IFinishable;

public class ScoreBasedInput extends VerticalLayout{


    private final int scoreThreshold = 2;

    public TextField german = new TextField();
    
    public VerticalLayout buttonsLayout = new VerticalLayout();
    BaseWord solution;
    Button solutionButton;
    Button fakeButton1;
    Button fakeButton2;

    IFinishable parent;

    public ScoreBasedInput(IFinishable parent,BaseWord solution, List<BaseWord> variations){
        this.parent = parent;
        this.solution = solution;
        if(solution.score<scoreThreshold){
            solutionButton = new Button(solution.german);
            fakeButton1 = new Button(variations.get(0).german);
            fakeButton2 = new Button(variations.get(1).german);
            List<Button> buttons = new ArrayList<>();
            buttons.add(solutionButton);
            buttons.add(fakeButton1);
            buttons.add(fakeButton2);
            Collections.shuffle(buttons, new Random());
            //setButtonEvents(buttons);
            buttons.stream().forEach(b-> buttonsLayout.add(b));
            buttons.stream().forEach(b-> {b.setWidthFull();b.getStyle().set("text-align", "center");
});
            buttonsLayout.setPadding(false);
            buttonsLayout.setSpacing(false);
            
            add(buttonsLayout);
            setPadding(false);
            setSpacing(false);
            setSizeUndefined();
            buttonsLayout.setSizeUndefined();
        }
        german.setAutocomplete(Autocomplete.OFF);
        german.setSuffixComponent(new Icon(VaadinIcon.ENTER));  
        validateInputData();
        add(german);
        german.focus();
        setAlignItems(Alignment.CENTER);

    }

    private void setButtonEvents(List<Button> buttons){
        buttons.forEach(b-> {
            b.addClickListener(e->{onClickEvent(b);});
            b.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        });

        buttons.get(0).addClickShortcut(Key.DIGIT_1);
        buttons.get(1).addClickShortcut(Key.DIGIT_2);
        buttons.get(2).addClickShortcut(Key.DIGIT_3);
        
    }

    private void onClickEvent(Button button){
        if(button.getText().equals(solution.german)){
            setButtonsEnabled(false);
            parent.finish();
        }
        else{
            button.addThemeVariants(ButtonVariant.LUMO_PRIMARY,
            ButtonVariant.LUMO_ERROR);
            solution.score--;
        }
    }
     private void setButtonsEnabled(boolean value){
        solutionButton.setEnabled(value);
        fakeButton1.setEnabled(value);
        fakeButton2.setEnabled(value);
    }

    private void validateInputData() {
        german.addValueChangeListener(e->{ 
            System.out.println(german.getValue());
            System.out.println("-");
            System.out.println(solution.german);
            if(german.getValue().equals(solution.german)){
                german.setEnabled(false);
                parent.finish();
            }
            else{
                solution.score--;
            }
        });
    }
    

    
}
