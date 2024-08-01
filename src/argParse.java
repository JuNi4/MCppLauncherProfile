import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class argParse {

    // parse a list of arguments
    public static Map<String, ArrayList<String>> parse( String[] args )
    {
        // the current position in the argument list
        int i = 0;

        // create the map
        Map<String, ArrayList<String>> parsedArgs = new HashMap<>();

        // the last argument being parsed
        String lastArg = "";

        for ( String arg : args )
        {
            // check if the string is an argument or value for one
            if ( arg.startsWith("--") )
            {
                lastArg = arg;
                parsedArgs.put( arg, new ArrayList<String>() );
            }
            else if (!lastArg.isEmpty())
            {
                // if were in a value, add it to the array
                ArrayList<String> vals = parsedArgs.get( lastArg );

                vals.add( arg );

                parsedArgs.put( lastArg, vals );
            }
        }

        return parsedArgs;
    }
}
