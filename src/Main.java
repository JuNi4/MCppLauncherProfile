import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // get all the arguments
        Map<String, ArrayList<String>> arguments = argParse.parse( args );

        // check if an executable was supplied
        if ( !arguments.containsKey( "--execute" ) )
        {
            // print error message an exit
            throw new RuntimeException("Unable to find executable");
        }

        // assembled list of arguments
        ArrayList<String> argsPass = new ArrayList<>();
        String filePath = "";

        for ( String key : arguments.keySet() )
        {
            // dont add execute key
            if ( !key.equals("--execute") ) {
                argsPass.add(" " + key);
            }

            // add all values
            for ( String arg : arguments.get( key ) )
            {
                if ( !key.equals("--execute") ) {
                    argsPass.add(" " + arg);
                } else {
                    filePath += arg + " ";
                }
            }
        }

        filePath = filePath.substring( 0, filePath.length() -1 );

        // call the executable
        try {
            argsPass.add(0,filePath);
            System.out.println(argsPass);
            ProcessBuilder pr = new ProcessBuilder( argsPass );
            pr.start();
        } catch ( IOException e ) {
            System.out.println("Could not execute command");
            e.printStackTrace();
        }
    }
}