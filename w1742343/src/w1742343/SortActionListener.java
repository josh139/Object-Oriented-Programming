package w1742343;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class SortActionListener implements ActionListener 
{
    private final JPanel mainPanel;
    private final JScrollPane mainScrollPane;
    private final JButton sort_by_gf;
    private String[] columnNames;
    private Object[][] teamStats;
    private ArrayList<FootballClub> league;

    SortActionListener(JPanel mPanel, JScrollPane jscroll, JButton sort_by_gf, String[] columnNames, Object[][] teamStats, ArrayList<FootballClub> league) 
    {
        mainPanel = mPanel; // initializing my global variable value to the same as the ones in the constructor parameter
	mainScrollPane = jscroll;
	this.sort_by_gf = sort_by_gf;
	this.columnNames = columnNames;
	this.teamStats = teamStats;
        this.league = league;
    }
	
    @Override
    public void actionPerformed(ActionEvent e) 
    {
	mainPanel.remove(mainScrollPane);
	mainPanel.remove(sort_by_gf); // removes the button from the mainPanel
        
        String[] columnNamesGF = {"Club",
                                "MP",
                                "W",
                                "D",
                                "L",
                                "GF",
                                "GA",
                                "GD",
                                "Pts"};

        Object[][] teamStatsGF = new Object[league.size()][9]; // creates a two dimensional Object array which will store the FootballClub data fot the JTable
        
        int i = 0;
        
        Collections.sort(league, new CompareGF()); // creates a league which is sorted according to the CompareGF class
        while (i < league.size()) // a loop which determines which coloumn the data should be in
        {
            for(FootballClub team : league) // do for all FootballClubs in the league
            {
                teamStatsGF[i][0] = team.getName();
                teamStatsGF[i][1] = team.getMatchesPlayed();
                teamStatsGF[i][2] = team.getWins();
                teamStatsGF[i][3] = team.getDraws();
                teamStatsGF[i][4] = team.getLosses();
                teamStatsGF[i][5] = team.getGoalsFor();
                teamStatsGF[i][6] = team.getGoalsAgainst();
                teamStatsGF[i][7] = team.getGoalDifference();
                teamStatsGF[i][8] = team.getPoints();
                i++;
            }
        }
        
        JTable tableGF = new JTable(teamStatsGF, columnNamesGF); // new JTable with the following parameters created above
        tableGF.setFillsViewportHeight(true); // sets the table large enough to fill the height of the enclosing viewport
        
        JScrollPane scrollPane = new JScrollPane(tableGF); // create a new JScrollPane
        mainPanel.add(scrollPane); // adds the JScrollPane to the main panel

	JButton sort_by_gf = new JButton("Sort by GF"); // create a JButton
	sort_by_gf.addActionListener(new SortActionListener(mainPanel, scrollPane, sort_by_gf, columnNames, teamStats, league)); // adds an action listener to JButton
	mainPanel.add(sort_by_gf); // adds JButton to the main panel
        
        TableColumn column = null;
        for (int x = 0; x < league.size(); x++) 
        {
            column = tableGF.getColumnModel().getColumn(x);
            if (x == 0) 
            {
                column.setPreferredWidth(200); // sets the width of the first column to 200 to display full club name
            } 
            else 
            {
                column.setPreferredWidth(50);
            }
        }
	
        mainPanel.revalidate(); // this resets the layout of the mainPanel
	mainPanel.repaint();
    }
}