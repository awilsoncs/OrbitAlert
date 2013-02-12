package orbitalert.Objects.Items;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import orbitalert.OrbitAlert;

/**
 *
 * @author Aaron
 */
public class ItemLoader {

    public static String chooseItemFile(String areaType){
        String path = OrbitAlert.getOrbitAlertPath();
        File itemsFile = new File(path + "/raws/areas/" + areaType + "/items/");
        if(itemsFile.exists()){
            ArrayList<String> itemTypes = new ArrayList<>(
                Arrays.asList(itemsFile.list()));
            int random = (int) (Math.random() * itemTypes.size());
            String itemType = itemTypes.get(random);
            itemType = itemType.substring(0,itemType.lastIndexOf(".txt"));
            return itemType;
        }
        return null;
    }
    
    public static Item loadItem(String areaType) {
        return loadItem(areaType, chooseItemFile(areaType));
    }
    
    public static Item loadItem(String areaType, String itemType){
        try{
            String path = OrbitAlert.getOrbitAlertPath();
            File itemFile = new File(path + "/raws/areas/" + areaType 
                    + "/items/" + itemType + ".txt");
            if(itemFile.exists()){
                FileReader fileReader = new FileReader(itemFile);
                HashMap<String, String> itemAttributes;
                try (BufferedReader reader = new BufferedReader(fileReader)) {
                    String line;
                    itemAttributes = new HashMap<>();
                    while (( line = reader.readLine()) != null){
                        ArrayList<String> keyValuePair = new ArrayList<>(Arrays.asList(line.split("/")));
                        if (keyValuePair.size() > 1){
                            itemAttributes.put(keyValuePair.get(0), keyValuePair.get(1));
                        }
                    }
                }
                return new Item(itemAttributes);
            } else {
                return null;
            }
        } catch (Exception ex) {
        }
        return null;
    }
    
}
