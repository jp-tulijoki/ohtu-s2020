/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

/**
 *
 * @author tulijoki
 */
public class Undo extends Komento {
    protected Komento komento;
    protected int luku;
    protected Sovelluslogiikka s;
    protected IO io;

    public Undo() {
        this.komento = new Tuntematon();
        this.luku = 0;
    }

    public Undo(Komento komento, int luku, Sovelluslogiikka s, IO io) {
        this.komento = komento;
        this.luku = luku;
        this.s = s;
        this.io = io;
    }

    @Override
    void suorita() {
        komento.suorita();
        io.tulosta(String.valueOf(s.tulos));
    }
    
    
    
    
}
