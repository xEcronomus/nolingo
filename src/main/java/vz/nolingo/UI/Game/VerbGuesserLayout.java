package vz.nolingo.UI.Game;

import java.util.Random;

import com.vaadin.flow.component.Key;
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
    Span kojugation = new Span();  

    public TextField input = new TextField();
    public TextField konjugationInput = new TextField();

    public GameLayout parent; 
    Verb verb;

    public VerbGuesserLayout(GameLayout parent,Verb verb){
        this.verb=verb;
        this.parent=parent;
        word.add(verb.english);
        String solution = verb.german;
        String konjugationSolution = selectRandomConjugation(verb);

        input.setAutocomplete(Autocomplete.OFF);
        input.setSuffixComponent(new Icon(VaadinIcon.ENTER));  
        konjugationInput.setAutocomplete(Autocomplete.OFF);
        konjugationInput.setEnabled(false);
        konjugationInput.setSuffixComponent(new Icon(VaadinIcon.ENTER));  

        createHelpDialog(word,solution);
        
        createHelpDialog(kojugation,konjugationSolution);

        setSolutionValidator(solution);
        setKonjugationSolutionValidator(konjugationSolution);
        

        add(word,input,kojugation,konjugationInput);
        setAlignItems(Alignment.CENTER);
        setWidth("30%"); 
        getThemeList().add(Lumo.DARK);

    }

    private void setSolutionValidator(String solution){
        input.addKeyDownListener(Key.ENTER,e->{
            if(input.getValue().equals(solution)){
                input.setEnabled(false);
                konjugationInput.setEnabled(true);
                konjugationInput.focus();
            }else{
                verb.score--;
            }
        });
    }

    private void setKonjugationSolutionValidator(String konjugationSolution){
        konjugationInput.addKeyDownListener(Key.ENTER,e->{
            if(konjugationInput.getValue().equals(konjugationSolution)){
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
        konjugationInput.setEnabled(false);
        input.addKeyDownListener(e->{});
        konjugationInput.addKeyDownListener(e->{});
        verb.score++;
        parent.next();
    }

    private String selectRandomConjugation(Verb verb){
        Random random = new Random();
        int r = random.nextInt(6)+1;
        String result = null; 

        switch (r) {
            case 1:
                result = verb.ich;
                kojugation.add("ich");
                break;
            case 2:
                result = verb.du;
                kojugation.add("du");
                break;
            case 3:
                result = verb.er_sie_es;
                kojugation.add("er / sie / es");
                break;
            case 4:
                result = verb.wir;
                kojugation.add("wir");
                break;
            case 5:
                result = verb.ihr;
                kojugation.add("ihr");
                break;
            case 6:
                result = verb.sie;
                kojugation.add("sie / Sie");
                break;
        }
        return result;
    }


}
