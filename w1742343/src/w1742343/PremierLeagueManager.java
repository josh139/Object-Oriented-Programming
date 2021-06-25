package w1742343;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class PremierLeagueManager implements LeagueManager
{   
    Scanner in = new Scanner(System.in);
    private final ArrayList<FootballClub> league;   // ArrayList which holds all FootballClubs
    private final ArrayList<Match> fixtures;    // ArrayList which holds all matches
    
    public PremierLeagueManager() throws FileNotFoundException
    {
        league = new ArrayList<>();
        fixtures = new ArrayList<>();
    }

    public static void main(String[] args) throws FileNotFoundException // my main method
    {
        PremierLeagueManager start = new PremierLeagueManager();
        start.menuSwitchCase(); // This calls the menu method
    }
    
    public void menuSwitchCase() throws FileNotFoundException
    {
        char choice;
        do

        {
            menuText(); // This calls all of the text which is shown at the beginning of the program
            System.out.print("-- Enter a choice (Q) to exit: ");
            choice = in.next().toUpperCase().charAt(0); // Tells the program that choice is equal to the user input. The program then takes the first character and places it in uppercase

            switch(choice) //The user will be directed to the method based on their input (case)
            {
                case 'A' : addTeam();
                break;
                case 'D' : deleteTeam();
                break;
                case 'S' : displayStats();
                break;
                case 'T': displayTable();
                break;
                case 'M': addMatch();
                break;
                case 'N': saveData();
                break;
                case 'L': loadData();
                break;
                case 'G': jTable();
                break;
            }

        } while (choice != 'Q');
        
        System.out.println("Thanks for using the program. Goodbye!");
    }
    
    private void menuText() 
    {
        System.out.println("-- A: Add a new team --");
        System.out.println("-- D: Delete a team  --");
        System.out.println("-- S: Display a teams statistics --");
        System.out.println("-- T: Display the league table --");
        System.out.println("-- M: Add a match --");
        System.out.println("-- N: Save data --");
        System.out.println("-- L: Load data --");
        System.out.println("-- G: Display GUI --");
    }

    private void addTeam() 
    {       
        Scanner scan = new Scanner(System.in);
        
        FootballClub team = new FootballClub(); // create a new instance of the FootballClub class
        System.out.println("Enter team name: ");
        String str = scan.nextLine(); // the next input is str
        team.setName(str); // the football club class then goes inside the FootballClub class selects the setName method and passes it str which will become the football clubs name
        
        System.out.println("Enter team location: ");
        str = scan.nextLine();
        team.setLocation(str);
        
        System.out.println("Enter founded year: ");
        int intg = scan.nextInt();
        team.setFounded(intg);
        
        league.add(team); // add the instance of FootballClub to the league ArrayList
    }

    private void deleteTeam() 
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter name: ");
        String str = scan.nextLine();
        for(FootballClub team : league) // for every FootballClub instance in the league ArrayList do the following
        {
             if(team.getName().equals(str)) // if the users input is equal to a FootballClub
             {
                 league.remove(team); // removes the team from the league
                 System.out.println(team.getName() + " deleted");
                 return; // returns the new league ArrayList
             }
        }
    }

    private void displayStats() 
    {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter name: ");
        String str = scan.nextLine();
         for (FootballClub team : league) 
         {
             if(team.getName().equals(str))
             {
                 System.out.println("Located in: " + team.getLocation() + "   Founded: " + team.getFounded());
                 System.out.println("MP " + team.getMatchesPlayed() + " W " + team.getWins() + " D " + team.getDraws() + " L " + team.getLosses() + " GF " + team.getGoalsFor() + " GA " + team.getGoalsAgainst() + " GD " + team.getGoalDifference() + " P " + team.getPoints());
                 return;
             }
         }
    }

    private void displayTable() 
    {
        Collections.sort(league, new ComparePts()); // sort the league ArrayList according to the ComparePts class
        league.forEach((team) -> {
            System.out.println(team.getName() + "    " + " MP " + team.getMatchesPlayed() + " W " + team.getWins() + " D " + team.getDraws() + " L " + team.getLosses() + " GF " + team.getGoalsFor() + " GA " + team.getGoalsAgainst() + " GD " + team.getGoalDifference() + " P " + team.getPoints());
        });
    }

    private void addMatch() 
    {
        Scanner scan = new Scanner(System.in);
        Match match = new Match(); // creates a new instance of the Match class
        
        System.out.println("Enter date dd-mm-yyyy: ");
        String str = scan.nextLine();
        Date date;
        try 
        {
            date = new SimpleDateFormat("dd-mm-yyyy").parse(str);
        } 
        catch (ParseException pe) // runs if the users input isn't the correct dd-mm-yyyy format 
        {
            System.out.println("enter dd-mm-yyyy");
            return;
        }
        match.setDate(date);
        
        System.out.println("Enter home name: ");
        str = scan.nextLine();
        FootballClub home = null; // the home team is initialized to null
        for(FootballClub team : league)
        {
            if(team.getName().equals(str))
                home = team; // home is then initialized with the value of the FootballClub
        }  
        if (home == null) // if there is no FootballClub with the name equal to the users input
        {
            System.out.println("No such team. Add it here: ");
            addTeam(); // re-direct the user to the addTeam method allowing them to create the team they tried to enter
            return;
        }
        match.setHomeTeam(home); // goes into the Match class, selects the setHomeTeam method and passes it the value inside home
          
        System.out.println("Enter away name: ");
        str = scan.nextLine();
        FootballClub away = null;
        for(FootballClub team : league)
        {
            if(team.getName().equals(str))
                away = team;
        } 
        if (away == null)
        {
            System.out.println("No such team. Add it here: ");
            addTeam();
            return;
        }
        match.setAwayTeam(away);
           
        System.out.println("Enter home goals: ");
        int intg = scan.nextInt();
        int homeGoals;
        homeGoals = intg;              
        match.setHomeGoals(homeGoals);
        
        System.out.println("Enter away goals: ");
        intg = scan.nextInt();
        int awayGoals;
        awayGoals = intg;
        match.setAwayGoals(awayGoals);
       
        fixtures.add(match);
          
        home.setGoalsFor(home.getGoalsFor()+homeGoals); // sets the home FootballClubs goals for as their current goals for plus the new homeGoals input by the user
        away.setGoalsFor(away.getGoalsFor()+awayGoals);
        home.setGoalsAgainst(home.getGoalsAgainst()+awayGoals); // sets the home FootballClubs goals against as their current goals against plus the new awayGoals input by the user
        away.setGoalsAgainst(away.getGoalsAgainst()+homeGoals);
        home.setMatchesPlayed(home.getMatchesPlayed()+1);
        away.setMatchesPlayed(away.getMatchesPlayed()+1);
        home.setGoalDifference(home.getGoalDifference()+homeGoals-awayGoals);
        away.setGoalDifference(away.getGoalDifference()+awayGoals-homeGoals);
         
        if (homeGoals > awayGoals) // if the home FootballClub has scored more goals than the away FootballClub do the following etc.
        {            
            home.setPoints(home.getPoints()+3);
            home.setWins(home.getWins()+1);
            away.setLosses(away.getLosses()+1);
        }
         
        else if (homeGoals < awayGoals) 
        {            
            away.setLosses(away.getPoints()+3);
            away.setWins(away.getWins()+1);
            home.setLosses(home.getLosses()+1);
        }
        
        else 
        {
            home.setPoints(home.getPoints()+1);
            away.setPoints(away.getPoints()+1);
            home.setDraws(home.getDraws()+1);
            away.setDraws(away.getDraws()+1);
        }      
    }

    private void saveData() throws FileNotFoundException 
    {
        File fileName = new File("test.txt"); // create a new file named test.txt
        ArrayList aList = new ArrayList(); // the ArrayList which will contain all of the files data
        
        for (FootballClub team : league) 
        {
            aList.add(team.getName() + "\n" // adds the FootballClub name to the array list then enters a new line
                    + team.getLocation() + "\n" 
                    + team.getFounded() + "\n" 
                    + team.getMatchesPlayed() + "\n" 
                    + team.getWins() + "\n" 
                    + team.getDraws() + "\n" 
                    + team.getLosses() + "\n" 
                    + team.getPoints() + "\n" 
                    + team.getGoalsFor() + "\n" 
                    + team.getGoalsAgainst() + "\n" 
                    + team.getGoalDifference());
        }     
        
        try
        {
            FileWriter fw = new FileWriter(fileName);
            Writer output = new BufferedWriter(fw);
            
            for(int i = 0; i < aList.size(); i++) // until i is equal to the size of the aList ArrayList + 1 to i
            {
                output.write(aList.get(i).toString() + "\n");
            }
            output.close(); // closes the buffered writer
        }     
        catch(IOException e) // if the file is not found
        {
            System.out.println("FileNotFoundException");
        }
    }

    private void loadData() throws FileNotFoundException 
    {
        File file = new File("test.txt");
        Scanner scan = new Scanner(file); // creates a scanner and passes it the test.txt file
        
        while(scan.hasNextLine()) // while there is another line do the following
        {
            FootballClub team = new FootballClub(); 
            
            String str = scan.nextLine();
            String teamName = str;
            team.setName(teamName);

            str = scan.nextLine();
            String location = str;
            team.setLocation(location);

            str = scan.nextLine();
            int founded = Integer.parseInt(str);
            team.setFounded(founded);

            str = scan.nextLine();
            int matchesPlayed = Integer.parseInt(str);
            team.setMatchesPlayed(matchesPlayed);

            str = scan.nextLine();
            int wins = Integer.parseInt(str);
            team.setWins(wins);

            str = scan.nextLine();
            int draws = Integer.parseInt(str);
            team.setDraws(draws);

            str = scan.nextLine();
            int losses = Integer.parseInt(str);
            team.setLosses(losses);

            str = scan.nextLine();
            int points = Integer.parseInt(str);
            team.setPoints(points);

            str = scan.nextLine();
            int goalsf = Integer.parseInt(str);
            team.setGoalsFor(goalsf);

            str = scan.nextLine();
            int goalsa = Integer.parseInt(str);
            team.setGoalsAgainst(goalsa);

            str = scan.nextLine();
            int goald = Integer.parseInt(str);
            team.setGoalDifference(goald);
            league.add(team);

            System.out.println(team.getName() + "\n" 
                        + team.getLocation() + "\n" 
                        + team.getFounded() + "\n" 
                        + team.getMatchesPlayed() + "\n" 
                        + team.getWins() + "\n" 
                        + team.getDraws() + "\n" 
                        + team.getLosses() + "\n" 
                        + team.getPoints() + "\n" 
                        + team.getGoalsFor() + "\n" 
                        + team.getGoalsAgainst() + "\n" 
                        + team.getGoalDifference());
        }
    }
    
    public ArrayList getLeague()
    {
        return league;
    }

    private void jTable()
    {            
        JFrame frame = new JFrame("PremierLeagueManager"); // creates a new JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // while the x button is pressed the program will stop
        
        LeagueJTable newContentPane = new LeagueJTable();
        newContentPane.ptsTable(this);

        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane); // the JFrame sets the content pane as the ptsTable method inside the LeagueJTable class
        
        frame.pack();
        frame.setVisible(true); // display the window
    }
}