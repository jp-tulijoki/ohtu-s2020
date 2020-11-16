package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText , Player[].class);
        
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter dtf = ISO_ZONED_DATE_TIME;
        String dateText = dateTime.format(dtf);
        ZonedDateTime parsedDateTime = ZonedDateTime.parse(dateText, dtf);
        
        String selectedNationality = "FIN";
        ArrayList<Player> selectedPlayers = new ArrayList<>();
        for (Player player : players) {
            if (player.getNationality().equals(selectedNationality)) {
                selectedPlayers.add(player);
            }
        }
        
        System.out.println("Players from " + selectedNationality + " " + parsedDateTime);
        for (Player player : selectedPlayers) {
            System.out.println(player.getName() + " team " + player.getTeam() + " goals " + player.getGoals() + " assists " + player.getAssists());
        }
        
        System.out.println("");
        
        Collections.sort(selectedPlayers);
        
        System.out.println("Players from " + selectedNationality + " " + parsedDateTime);
        for (Player player : selectedPlayers) {
            System.out.println(player.getName() + " " + player.getTeam() + " "  + player.getGoals() + " + " + player.getAssists() + " = " + (player.getGoals() + player.getAssists()));
        }
        
    }
  
}
