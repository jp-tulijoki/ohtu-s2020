
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alkiot = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            kapasiteetti = KAPASITEETTI;
        }
        alkiot = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            kapasiteetti = KAPASITEETTI;
        }
        if (kasvatuskoko < 0) {
            kasvatuskoko = OLETUSKASVATUS;
        }
        alkiot = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }

    public int getAlkio(int indeksi) {
        return this.alkiot[indeksi];
    }
    
    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            alkiot[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (!kuuluu(luku)) {
            alkiot[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm == alkiot.length) {
                alkiot = kasvataTaulukonKokoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                kohta = i;
                break;
            }
        }
        if (kohta == -1) {
            return false;
        }
        siirraLuvut(kohta);
        alkioidenLkm--;
        return true;
    }

    private int[] kasvataTaulukonKokoa() {
        int[] uusiTaulukko = new int[alkiot.length + kasvatuskoko];
        for (int i = 0; i < alkiot.length; i++) {
            uusiTaulukko[i] = alkiot[i];
        }
        return uusiTaulukko;
    }
    
    private void siirraLuvut(int siirtoKohta) {
        for (int i = siirtoKohta; i < alkioidenLkm; i++) {
            alkiot[i] = alkiot[i +1];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }
        String joukko = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            joukko += alkiot[i] + ", ";
        }
        joukko += alkiot[alkioidenLkm - 1] + "}";
        return joukko;

    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = alkiot[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            x.lisaa(a.getAlkio(i));
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            x.lisaa(b.getAlkio(i));
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            for (int j = 0; j < b.mahtavuus(); j++) {
                if (a.getAlkio(i) == b.getAlkio(j)) {
                    y.lisaa(b.getAlkio(j));
                }
            }
        }
        return y;
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            z.lisaa(a.getAlkio(i));
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            z.poista(b.getAlkio(i));
        }
        return z;
    }
}
