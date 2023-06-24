package vz.nolingo.UI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.vaadin.flow.component.AbstractSinglePropertyField;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.theme.lumo.Lumo;

import vz.nolingo.Entity.BaseWord;

public class WordEditLayout extends VerticalLayout{
    
    public List<AbstractSinglePropertyField<?,?>> values = new ArrayList<>(); 

    @SuppressWarnings("all")
    public WordEditLayout(Class<? extends BaseWord> clazz){
        VerticalLayout dialogLayout = new VerticalLayout();
        List<Field> fields = Arrays.asList(clazz.getFields());
        Collections.reverse(fields);
        boolean first = true;
        for(Field f : fields){
            Class<?> type = f.getType();

            if(type.isInstance(new String())){
                TextField textField = new TextField(f.getName());
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
            }

        }
        
        dialogLayout.setPadding(false);
        dialogLayout.setSpacing(false);
        dialogLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        dialogLayout.getStyle().set("width", "18rem").set("max-width", "100%");
        add(dialogLayout);
        getThemeList().add(Lumo.DARK);
    }

    public List<String> getValues(){
        List<String> result = new ArrayList<>();
        for(AbstractSinglePropertyField<?,?> v : values){
            result.add(v.getValue().toString());
        }
        return result;
    }

    
}
