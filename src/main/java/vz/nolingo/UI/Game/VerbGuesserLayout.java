package vz.nolingo.UI.Game;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.Autocomplete;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.Lumo;

import vz.nolingo.Entity.Verb;

public class VerbGuesserLayout extends VerticalLayout {
    
    Span word = new Span();  

    public TextField input = new TextField();

    public GameLayout parent; 
    Verb verb;

    public VerbGuesserLayout(GameLayout parent,Verb verb){
        this.verb=verb;
        this.parent=parent;
        word.add(verb.english);
        String solution = verb.german;
        input.setAutocomplete(Autocomplete.OFF);
        input.setSuffixComponent(new Icon(VaadinIcon.ENTER));  

        createHelpDialog(word,solution);
        

        setSolutionValidator(solution);
        

        add(word,input);
        setAlignItems(Alignment.CENTER);
        setWidth("30%"); 
        getThemeList().add(Lumo.DARK);

    }

    private void setSolutionValidator(String solution){
        input.addKeyDownListener(e->{
            if(input.getValue().equals(solution)){
                input.setEnabled(false);
                finish();
            }else{
                verb.score--;
            }
        });
    }

    
    private void createHelpDialog(Span span,String solution) {
        span.addClickListener(e->{
            Dialog helpDialog = new Dialog();
            helpDialog.setHeaderTitle(solution);
            Button cancelButton = new Button("Ok", e2 -> helpDialog.close());
            
            helpDialog.getFooter().add(cancelButton);
            helpDialog.open();
        });
    }

    private void finish(){
        input.setEnabled(false);
        input.addKeyDownListener(e->{});
        verb.score++;
        parent.next();
    }

    


}
