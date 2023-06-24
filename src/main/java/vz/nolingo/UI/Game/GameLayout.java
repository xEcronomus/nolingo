package vz.nolingo.UI.Game;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.SneakyThrows;
import vz.nolingo.Entity.BaseWord;
import vz.nolingo.Entity.Noun;
import vz.nolingo.Entity.Verb;
import vz.nolingo.Service.BaseWordService;

public class GameLayout extends VerticalLayout {

    BaseWordService baseWordService;
    
    List<BaseWord> words;
    boolean tick = true;

    int counter = 0;

    VerticalLayout currentWordLayout;
    BaseWord currentWord;

    public GameLayout(BaseWordService baseWordService,List<BaseWord> words){
        this.baseWordService = baseWordService;
        this.words = words;
        setAlignItems(Alignment.CENTER);
        next();
    }

    private BaseWord selectRandomFromList(List<? extends BaseWord> list){
        Random rand = new Random();
        currentWord = list.get(rand.nextInt(list.size()));
        return currentWord;
    }

    @SuppressWarnings("all")
    public void next(){
        if(counter >= 5){
            int counter = 0;
            Collections.sort(words);
        }
        if(currentWordLayout != null){
            baseWordService.getController(currentWord.getClass()).save(currentWord);
            this.remove(currentWordLayout);
        }
        if(!words.isEmpty()){
            createWordLayout(selectRandomFromList(words));
            counter++;
        }
    }

    @SneakyThrows
    private void createWordLayout(BaseWord word){
        if(word instanceof Noun){
            NounGuesserLayout newNoun = new NounGuesserLayout(this,(Noun)word);
            add(newNoun);
            newNoun.german.focus();
            currentWordLayout = newNoun;
        }
        else if(word instanceof Verb){
            VerbGuesserLayout newVerb = new VerbGuesserLayout(this,(Verb)word);
            add(newVerb);
            newVerb.input.focus();
            currentWordLayout = newVerb;
        }else{
            throw new IllegalStateException(":)");
        }
    }


}
