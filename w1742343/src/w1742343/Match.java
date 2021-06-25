package w1742343;

import java.util.*;

public class Match 
{
    Date date; 
    FootballClub homeTeam;
    FootballClub awayTeam;
    int homeTeamGoals;
    int awayTeamGoals;
    
    public void setHomeTeam(FootballClub homeTeam) // setter which sets a value to home team when called
    {
        this.homeTeam = homeTeam;
    }
    
    public FootballClub getHomeTeam() // getter which gets the home team when called
    {
        return homeTeam;   
    }   
    
    public void setAwayTeam(FootballClub awayTeam) 
    {
        this.awayTeam = awayTeam;
    }
    
    public FootballClub getAwayTeam() 
    {
        return awayTeam;   
    }
    
    public void setDate(Date date) 
    {
        this.date = date;
    }
    
    public Date getDate() 
    {
        return date;
    }
    
    public void setHomeGoals(int homeTeamGoals) 
    {
        this.homeTeamGoals = homeTeamGoals;
    }
    
    public int getHomeGoals()
    {
        return homeTeamGoals;
    }
    
    public void setAwayGoals(int awayTeamGoals) 
    {
        this.awayTeamGoals = awayTeamGoals;
    }
    
    public int getAwayGoals()
    {
        return awayTeamGoals;
    }
}
