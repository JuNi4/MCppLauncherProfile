import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.lang.ProcessBuilder.Redirect;

public class Main {

    public static void main(String[] args) {

        // get all the arguments
        Map<String, String> arguments = argParse.parse( args );

        // check if an executable was supplied
        if ( !arguments.containsKey( "--execute" ) )
        {
            // print error message an exit
            throw new RuntimeException("Unable to find executable");
        }

        // assembled list of arguments
        ArrayList<String> argsPass = new ArrayList<>();
        String filePath = "";
        String workDir = "";

        for ( String key : arguments.keySet() )
        {
            // dont add execute key
            if ( key.equals("--execute") )
            {
                // set file for execution
                filePath = arguments.get(key);
            }
            else if (key.equals("--workdir"))
            {
                // set working directory
                workDir = arguments.get(key);
            }
            else
            {
                if (key != arguments.get(key))
                {
                    argsPass.add(key);
                }
                argsPass.add(arguments.get(key));
            }
        }

        // call the executable
        try {
            argsPass.add(0,filePath);
            ProcessBuilder pr = new ProcessBuilder( argsPass );

            if (!workDir.isEmpty())
            {
                pr.directory(new File(workDir));
            }
            // redirect process errors to standart output
            pr.redirectErrorStream(true);
            // redicrect the input stream
            pr.redirectInput(Redirect.INHERIT);
            // redirect the output stream
            pr.redirectOutput(Redirect.INHERIT);
            // redirect errors
            pr.redirectError(Redirect.INHERIT);
            
            Process running = pr.start();
            
            while (running.isAlive()) {}

            System.out.println("Process finished!");

        } catch ( IOException e ) {
            System.out.println("Could not execute command");
            e.printStackTrace();
        }
    }
}