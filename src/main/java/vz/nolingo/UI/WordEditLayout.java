package vz.nolingo.UI;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.Lumo;

import lombok.SneakyThrows;
import vz.nolingo.Entity.BaseWord;
import vz.nolingo.Enum.NounPronoun;

public class WordEditLayout extends VerticalLayout{
    
    public List<AbstractSinglePropertyField<?,?>> values = new ArrayList<>(); 

    @SuppressWarnings("all")
    @SneakyThrows
    public WordEditLayout(Class<? extends BaseWord> clazz, Optional<BaseWord> word){
        VerticalLayout dialogLayout = new VerticalLayout();
        List<Field> fields = Arrays.asList(clazz.getFields());
        Collections.reverse(fields);
        boolean first = true;
        for(Field f : fields){
            Class<?> type = f.getType();

            if(type.isInstance(new String())){
                TextField textField = new TextField(f.getName());
                setTextValueIfEdit(textField,word,f);
                dialogLayout.add(textField);
                values.add(textField);
                if(first){
                    textField.focus();
                }
            }
            else if(type.isEnum()){
                Select<String> select = new Select<>();
                select.setLabel(type.getSimpleName());
                Object[] enums = type.getEnumConstants();
                List<String> enumStrings =new ArrayList<String>();
                for(Object o : enums){
                    enumStrings.add(o.toString());
                }
                select.setItems(enumStrings);
                select.setValue(type.getSimpleName());
                values.add(select);
                dialogLayout.add(select);
                if(first){
                    select.focus();
                }

                if(word.isPresent()){
                    for(Method method : word.get().getClass().getMethods()){
                        System.out.println(method.getName().toLowerCase());
                        if(method.getName().toLowerCase().equals("get"+f.getName())){
                        select.setValue(((NounPronoun)method.invoke(word.get())).toString());
                    }

        }
                }
                
            }

        }
        
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");
        add(dialogLayout);
        getThemeList().add(Lumo.DARK);
    }

    @SneakyThrows
    private void setTextValueIfEdit(TextField textField, Optional<BaseWord> maybeWord, Field f) {
        if(!maybeWord.isPresent()) return;
        BaseWord word = maybeWord.get();
        for(Method method : word.getClass().getMethods()){
            if(method.getName().toLowerCase().equals("get"+f.getName())){
                textField.setValue((String)method.invoke(word));
            }
            
            System.out.println(method.getName().toLowerCase());
        }
        System.out.println("------------------");
        
    }

    public List<String> getValues(){
        List<String> result = new ArrayList<>();
        for(AbstractSinglePropertyField<?,?> v : values){
            result.add(v.getValue().toString());
        }
        return result;
    }

    
}
