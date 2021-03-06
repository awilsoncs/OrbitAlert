
package orbitalert.Areas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import orbitalert.OrbitAlert;

/**
 * This class is responsible for loading an area from raw files.
 * @author Aaron
 */
public class AreaLoader {    
    
    public static String chooseArea(){
        String path = OrbitAlert.getOrbitAlertPath();
        File areasFile = new File(path + "/raws/areas");
        
        ArrayList<String> areaTypes = new ArrayList<>(
                Arrays.asList(areasFile.list()));

        int random = (int) (Math.random() * areaTypes.size());
        return areaTypes.get(random);
    }
    
    public static Area loadArea(){
        return loadArea(chooseArea());
    }
    
    /**
     *
     * @param areaToLoad
     * @return
     */
    public static Area loadArea(String areaToLoad){
        try {
            String path = OrbitAlert.getOrbitAlertPath();
            File areaDirectory = new File(path + "/raws/areas/");
            String[] areaFiles = areaDirectory.list();

            for (String areaFile:areaFiles){
                if (areaFile.equals(areaToLoad)){
                    //Found the right area folder, open the file.              
                    File areaText = new File(path + "/raws/areas/" + areaFile 
                            + "/" + areaFile + ".txt");
                    
                    FileReader fileReader = new FileReader(areaText);
                    HashMap<String, String> areaAttributes;
                    try (BufferedReader reader = new BufferedReader(fileReader)) {
                        String line;
                        areaAttributes = new HashMap<>();
                        while (( line = reader.readLine()) != null){
                            ArrayList<String> keyValuePair = new ArrayList<>(Arrays.asList(line.split("/")));
                            if (keyValuePair.size() > 1){
                                areaAttributes.put(keyValuePair.get(0), keyValuePair.get(1));
                            }
                        }
                    }
                    Area newArea = new Area(areaAttributes);    
                    return newArea;
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }
}
