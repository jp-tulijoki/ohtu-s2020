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
public class Miinus extends Komento {
    
    protected Sovelluslogiikka s;
    protected IO io;
    protected Tapahtumankuuntelija t;

    public Miinus(IO io, Sovelluslogiikka s, Tapahtumankuuntelija t) {
        this.io = io;
        this.s = s;
        this.t = t;
    }
        
    @Override
    public void suorita() {
        int syote = io.haeSyote();
        s.miinus(syote);
        t.getKomennot().put(t.getUndo(), new Undo(new Plus(io, s, t), syote, s, io));
        io.tulosta(String.valueOf(s.tulos()));
    }
}
