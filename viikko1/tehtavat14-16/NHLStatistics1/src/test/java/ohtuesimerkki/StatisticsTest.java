/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author tulijoki
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchFindsAnExistingPlayer() {
        Player kurri = stats.search("Kurri");
        assertEquals("Kurri", kurri.getName());
        assertEquals("EDM", kurri.getTeam());
    }
    
    @Test
    public void searchReturnsNullForNonExistingPlayer() {
        Player nobody = stats.search("Nobody");
        assertNull(nobody);
    }
    
    @Test
    public void teamwisePlayerSelectionWorksProperly() {
        List<Player> edmonton = stats.team("EDM");
        assertEquals(3, edmonton.size());
        assertEquals("Semenko", edmonton.get(0).getName());
        assertEquals("Kurri", edmonton.get(1).getName());
        assertEquals("Gretzky", edmonton.get(2).getName());
    }
    
    @Test
    public void topScorerSelectionWorksProperly() {
        List<Player> topScorers = stats.topScorers(3);
        assertEquals(3, topScorers.size());
        assertEquals("Gretzky", topScorers.get(0).getName());
        assertEquals("Lemieux", topScorers.get(1).getName());
        assertEquals("Yzerman", topScorers.get(2).getName());
    }
}
