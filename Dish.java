import java.io.*;

public class Dish {
    String name;
    int weight;
    int cost;

    // recognition data of dish from menu.txt string
    public void createFromString (String inputString) throws FileNotFoundException {
        
        String delimiter = ",";
        int currentDelimiterPosition = 0;
        int nextDelimiterPosition = inputString.indexOf(delimiter);
        
        this.name = nextDelimiterPosition > currentDelimiterPosition ? inputString.substring(currentDelimiterPosition, nextDelimiterPosition) : "";
        currentDelimiterPosition = nextDelimiterPosition+1;
        
        nextDelimiterPosition = inputString.indexOf(delimiter, currentDelimiterPosition);
        this.weight = Integer.valueOf(nextDelimiterPosition > currentDelimiterPosition ? inputString.substring(currentDelimiterPosition, nextDelimiterPosition) : "");
        currentDelimiterPosition = nextDelimiterPosition+1;
        
        nextDelimiterPosition = inputString.indexOf(delimiter, currentDelimiterPosition);
        this.cost = Integer.valueOf(nextDelimiterPosition > currentDelimiterPosition ? inputString.substring(currentDelimiterPosition, nextDelimiterPosition) : "");
    }
}
