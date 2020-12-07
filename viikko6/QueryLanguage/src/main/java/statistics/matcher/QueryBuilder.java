/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author tulijoki
 */
public class QueryBuilder {
    
    Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }
    
    public Matcher build() {
        Matcher matcherToBeBuilt = matcher;
        matcher = new All();
        return matcherToBeBuilt;
    }
    
    public QueryBuilder playsIn(String team) {
        matcher = new And(matcher, new PlaysIn(team));
        return(this);
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        matcher = new And(matcher, new HasAtLeast(value, category));
        return(this);
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        matcher = new And(matcher, new HasFewerThan(value, category));
        return(this);
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        matcher = new Or(matchers);
        return(this);
    }
    
    
}
