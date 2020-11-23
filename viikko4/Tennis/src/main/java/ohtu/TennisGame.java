package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    private String[] normalScores;

    public TennisGame(String player1name, String player2name) {
        this.player1Name = player1name;
        this.player2Name = player2name;
        this.normalScores= new String[]{"Love", "Fifteen", "Thirty", "Forty"};
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String getScore() {
        String score = "";
        if (m_score1 == m_score2) {
            return evenScore();
        } 
        return nonEvenScore();
    }
    
    public String evenScore() {
        if (m_score1 > 3) {
            return "Deuce";
        }
        return normalScores[m_score1] + "-All";
    }
    
    public String nonEvenScore() {
        if (m_score1 <= 3 && m_score2 <=3) {
            return normalScores[m_score1] + "-" + normalScores[m_score2];
        }
        if (Math.abs(m_score1 - m_score2) > 1 || m_score1 < 3 || m_score2 < 3) {
            return "Win for " + winning();
        } 
        return "Advantage " + winning();
    }
    
    public String winning() {
        if (m_score1 > m_score2) {
            return "player1";
        }
        return "player2";
    }
}