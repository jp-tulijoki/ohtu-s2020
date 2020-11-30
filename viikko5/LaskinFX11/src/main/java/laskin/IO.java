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
public class IO {
    
    Tapahtumankuuntelija t;

    public IO(Tapahtumankuuntelija t) {
        this.t = t;
    }
    
    protected int haeSyote() {
        String syote = t.getSyotekentta().getText();
        return Integer.valueOf(syote);
    }
    
    protected void tulosta(String tulos) {
        t.getTuloskentta().setText(tulos);
    }
    
}
