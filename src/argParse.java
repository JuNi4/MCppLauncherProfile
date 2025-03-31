import java.sql.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class argParse {

    // parse a list of arguments
    public static Map<String, String> parse( String[] args )
    {
        // create the map
        Map<String, String> parsedArgs = new HashMap<>();

        // the last argument being parsed
        String lastArg = "";

        for ( String arg : args )
        {
            if (arg.endsWith(" ")) // remove trailing spaces
            { arg = arg.substring(0, arg.length() - 1); }

            // check if the string is an argument or value for one
            if ( arg.startsWith("--") )
            {
                lastArg = arg;
                parsedArgs.put( arg, "" );
            }
            else if (!lastArg.isEmpty())
            {
                parsedArgs.put( lastArg, arg );
                lastArg = "";
            }
            else
            {
                parsedArgs.put(arg, arg);
            }
        }

        return parsedArgs;
    }
}
