package w1742343;

import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class LeagueJTable extends JPanel
{
    private ArrayList<FootballClub> league;
    
    public LeagueJTable()
    {
        league = new ArrayList<>();
    }
    
    public void ptsTable(PremierLeagueManager callerObject)
    {
        PremierLeagueManager classpass = callerObject; 
        league = classpass.getLeague(); // sets the league within this class the value of league within the PremierLeagueManager class

        String[] columnNames = {"Club",
                                "MP",
                                "W",
                                "D",
                                "L",
                                "GF",
                                "GA",
                                "GD",
                                "Pts"};

        Object[][] teamStats = new Object[league.size()][9]; /* creates a two dimensional Object array with rows equal to the size of the league ArrayList
        and 9 columns. This two dimensional Object array will be used to populate the JTable */
        int i = 0;
        
        Collections.sort(league, new ComparePts());
        while (i < league.size())  // loop which sets the column
        {
            for(FootballClub team : league)
            {
                teamStats[i][0] = team.getName();
                teamStats[i][1] = team.getMatchesPlayed();
                teamStats[i][2] = team.getWins();
                teamStats[i][3] = team.getDraws();
                teamStats[i][4] = team.getLosses();
                teamStats[i][5] = team.getGoalsFor();
                teamStats[i][6] = team.getGoalsAgainst();
                teamStats[i][7] = team.getGoalDifference();
                teamStats[i][8] = team.getPoints();
                i++;
            }
        }
        
        JTable table = new JTable(teamStats, columnNames); // creates a table with the following parameters
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table); 
        add(scrollPane);
        
        JButton sort_by_gf = new JButton("Sort by GF");        
	sort_by_gf.addActionListener(new SortActionListener(this, scrollPane, sort_by_gf, columnNames, teamStats, league)); // adds an action listener
	add(sort_by_gf); //adds the button
        
        TableColumn column;
        for (int x = 0; x < league.size(); x++) 
        {
            column = table.getColumnModel().getColumn(x); // changes the size of the 0 column
            if (x == 0) 
            {
                column.setPreferredWidth(200);
            } 
            else 
            {
                column.setPreferredWidth(50); // sets the other columns sizes
            }
        }
    }
}