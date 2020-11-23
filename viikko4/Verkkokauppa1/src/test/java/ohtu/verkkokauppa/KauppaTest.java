package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;
    
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }
    
    @Test
    public void kahdenEriOstoksenJalkeenTilisiirtoaKutsutaanOikein() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 2));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("mikko", "54321");
        
        verify(pankki).tilisiirto(eq("mikko"), anyInt(), eq("54321"), anyString(), eq(7));
    }
    
    @Test
    public void kahdenSamanOstoksenJalkeenTilisiirtoaKutsutaanOikein() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("mikko", "54321");
        
        verify(pankki).tilisiirto(eq("mikko"), anyInt(), eq("54321"), anyString(), eq(10));
    }
    
    @Test
    public void tilisiirtoaKutsutaanOikeinJosTuoteOnLoppu() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 2));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("mikko", "54321");
        
        verify(pankki).tilisiirto(eq("mikko"), anyInt(), eq("54321"), anyString(), eq(5));
    }

    @Test
    public void asioinninAlkaessaEdellisenAsioinninTiedotNollataan() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 2));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("mikko", "54321");
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("matti", "11111");
        
        verify(pankki).tilisiirto(eq("matti"), anyInt(), eq("11111"), anyString(), eq(2));
    }
    
    @Test
    public void jokaiselleTapahtumalleOnOmaViite() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 2));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("mikko", "54321");
        
        verify(viite, times(1)).uusi();
        
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("matti", "11111");
        
        verify(viite, times(2)).uusi();
    }
    
    @Test
    public void ostoskoristaPoistettuTuotePalautuuVarastoon() {
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 2));
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(2);
        k.tilimaksu("mikko", "54321");
        
        verify(varasto, times(1)).palautaVarastoon(new Tuote(2, "leipa", 2));
    }
}
