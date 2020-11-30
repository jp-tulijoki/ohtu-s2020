package laskin;

import java.util.HashMap;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private TextField tuloskentta; 
    private TextField syotekentta; 
    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    IO io;
    private HashMap<Button, Komento> komennot;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.io = new IO(this);
        this.komennot = new HashMap();
        komennot.put(plus, new Plus(io, sovellus, this));
        komennot.put(miinus, new Miinus(io, sovellus, this));
        komennot.put(nollaa, new Nollaa(io, sovellus));
        komennot.put(undo, new Undo());
    }
    
    @Override
    public void handle(Event event) {
        int arvo = 0;
 
        Komento komento = komennot.get(event.getTarget());
        komento.suorita();
    }

    public TextField getSyotekentta() {
        return syotekentta;
    }

    public TextField getTuloskentta() {
        return tuloskentta;
    }

    public HashMap<Button, Komento> getKomennot() {
        return komennot;
    }

    public Button getUndo() {
        return undo;
    }

    
    
    
    

}
