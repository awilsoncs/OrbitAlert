
package orbitalert.Areas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is responsible for loading an area from raw files.
 * @author Aaron
 */
public class AreaLoader {    
    
    public static String chooseArea(){
        File areasFile = new File("raws/areas/");
        ArrayList<String> areaTypes = new ArrayList<>(
                Arrays.asList(areasFile.list()));
        int random = (int) (Math.random() * areaTypes.size());
        return areaTypes.get(random);
    }
    
    public static Area loadArea(){
        return loadArea(chooseArea());
    }
    
    public static Area loadArea(String areaToLoad){
        try {
            File areaDirectory = new File("raws/areas/");
            String[] areaFiles = areaDirectory.list();

            for (String areaFile:areaFiles){
                if (areaFile.equals(areaToLoad)){
                    //Found the right area folder, open the file.              
                    File areaText = new File("raws/areas/" + areaFile 
                            + "/" + areaFile + ".txt");
                    
                    FileReader fileReader = new FileReader(areaText);
                    BufferedReader reader = new BufferedReader(fileReader);

                    String line;
                    HashMap<String, String> areaAttributes = new HashMap<>();
                    while (( line = reader.readLine()) != null){
                        ArrayList<String> keyValuePair = new ArrayList<>(Arrays.asList(line.split("/")));
                        if (keyValuePair.size() > 1){
                            areaAttributes.put(keyValuePair.get(0), keyValuePair.get(1));
                        }
                    }
                    Area newArea = new Area(areaAttributes);    
                    return newArea;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
