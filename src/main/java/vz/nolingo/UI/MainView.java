package vz.nolingo.UI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.menubar.MenuBarVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;

import vz.nolingo.Entity.BaseWord;
import vz.nolingo.Service.BaseWordService;
import vz.nolingo.Service.GenericService;
import vz.nolingo.Service.NewWordService;
import vz.nolingo.Service.NounService;
import vz.nolingo.Service.VerbService;
import vz.nolingo.Service.ViewWordsService;
import vz.nolingo.UI.Game.GameLayout;

@Route
public class MainView extends VerticalLayout {

    @Autowired(required = true)
    private NewWordService newWordService;
    @Autowired(required = true)
    private ViewWordsService viewWordsService;
    @Autowired(required = true)
    @Qualifier("BaseWordService")
    BaseWordService baseWordService;
    @Autowired(required = true)
    private NounService nounService;
    @Autowired(required = true)
    private VerbService verbService;
    @Autowired(required = true)
    private GenericService genericService;

    public MainView() {
        this.setAlignItems(Alignment.CENTER);
        getThemeList().add(Lumo.DARK);
        setSizeFull();

        MenuBar menuBar = new MenuBar();
        menuBar.addThemeVariants(MenuBarVariant.LUMO_PRIMARY);
        createNewWordButton(menuBar);
        createViewWordsButton(menuBar);
        createStartButton(menuBar);

        add(menuBar);

    }

    private void createNewWordButton(MenuBar menuBar) {
        MenuItem newWord = menuBar.addItem("New word");
        SubMenu newWordSubMenu = newWord.getSubMenu();
        for(Class<? extends BaseWord> clazz : ReflectionUtil.getClasses()){
            newWordSubMenu.addItem(clazz.getSimpleName(),e->newWordService.showNewNounDialogWindow(clazz));
        }
    }

    private void createViewWordsButton(MenuBar menuBar) {
        MenuItem newWord = menuBar.addItem("View words");
        SubMenu newWordSubMenu = newWord.getSubMenu();
        for(Class<? extends BaseWord> clazz : ReflectionUtil.getClasses()){
            newWordSubMenu.addItem(clazz.getSimpleName(),e->viewWordsService.showViewNounDialogWindow(clazz));
        }
    }

    private void createStartButton(MenuBar menuBar) {
        MenuItem startButton = menuBar.addItem("Start");
        startButton.addClickListener(e->{
            List<BaseWord> list = new ArrayList<BaseWord>();
            list.addAll(nounService.findAll());
            list.addAll(verbService.findAll());
            list.addAll(genericService.findAll());
            add(new GameLayout(baseWordService,list));
        });
    }
}