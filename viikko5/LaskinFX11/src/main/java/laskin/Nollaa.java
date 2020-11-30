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
public class Nollaa extends Komento {

    protected Sovelluslogiikka s;
    protected IO io;

    public Nollaa(IO io, Sovelluslogiikka s) {
        this.io = io;
        this.s = s;
    }
    
    @Override
    void suorita() {
        s.nollaa();
        io.tulosta(String.valueOf(s.tulos()));
    }
    
}
