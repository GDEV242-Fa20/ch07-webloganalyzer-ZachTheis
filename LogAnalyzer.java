/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Zach Theis.
 * @version    2020.10.19
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    private int[] dayCounts;
    private int[] monthCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly, daily, and monthly
        // access counts.
        hourCounts = new int[24];
        dayCounts = new int[31];
        monthCounts = new int[12];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    /**
     * Overloaded constructor
     * @param The name of the file.
     * Satisfies 7.12
     */
    public LogAnalyzer(String fileName)
    {
        hourCounts = new int[24];
        dayCounts = new int[31];
        monthCounts = new int[12];
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    
    public void analyzeDailyData()
    {
        while(reader.hasNext())
        {
            LogEntry entry = reader.next();
            int day = entry.getDay();
            dayCounts[day]++;
        }
    }
    
    public void analyzeMonthlyData()
    {
        while(reader.hasNext())
        {
            LogEntry entry = reader.next();
            int month = entry.getMonth();
            monthCounts[month]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Return the number of accesses recorded in the log file, once it has
     * been analyzed.
     * Satisfies 7.14
     * @return The total number of recorded accesses.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            //System.out.println(hourCounts[hour]);
            total += hourCounts[hour];
        }
        return total;
    }
    
    /**
     * Finds and returns the busiest hour.
     * This satisfies 7.15
     * @return The busiest hour, based on a 24-hr clock.
     */
    public int busiestHour()
    {
        int busiest = 0;
        int firstPlace = hourCounts[0];
        for(int hour = 1; hour < hourCounts.length; hour++)
        {
            if(hourCounts[hour] > firstPlace)
            {
                firstPlace = hourCounts[hour];
                busiest = hour;
            }
        }
        return busiest;
    }
    
    /**
     * Finds and returns the busiest two-hour period based on a 24-hr clock.
     * This satisfies 7.18
     * @return The first hour of the two-hour period.
     */
    public int busiestTwoHours()
    {
        int busiest = 23;
        int firstPlace = hourCounts[0] + hourCounts[23];
        for(int hour = 0; hour < hourCounts.length; hour++)
        {
            if(hourCounts[hour] + hourCounts[hour + 1] > firstPlace)
            {
                busiest = hour;
            }
        }
        return busiest;
    }
    
    /**
     * Finds and returns the quietest hour.
     * This satisfies 7.16.
     * @return The quiestest hour, based on a 24-hr clock.
     */
    public int quietestHour()
    {
        int quietest = 0;
        int lastPlace = hourCounts[0];
        for(int hour = 1; hour < hourCounts.length; hour++)
        {
            if(hourCounts[hour] < lastPlace)
            {
                lastPlace = hourCounts[hour];
                quietest = hour;
            }
        }
        return quietest;
    }
    
    public int busiestDay()
    {
        int busiest = 0;
        int firstPlace = dayCounts[0];
        for(int day = 1; day < dayCounts.length; day++)
        {
            if(dayCounts[day] > firstPlace)
            {
                firstPlace = dayCounts[day];
                busiest = day;
            }
        }
        return busiest;
    }
    
    public int busiestMonth()
    {
        int busiest = 0;
        int firstPlace = monthCounts[0];
        for(int month = 1; month < monthCounts.length; month++)
        {
            if(monthCounts[month] > firstPlace)
            {
                firstPlace = monthCounts[month];
                busiest = month;
            }
        }
        return busiest;
    }
}
