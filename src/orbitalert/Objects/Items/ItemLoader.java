/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        ArrayList<String> itemTypes = new ArrayList<>(
                Arrays.asList(itemsFile.list()));
        int random = (int) (Math.random() * itemTypes.size());
        String itemType = itemTypes.get(random);
        itemType = itemType.substring(0,itemType.lastIndexOf(".txt"));
        return itemType;
    }
    
    public static Item loadItem(String areaType) {
        return loadItem(areaType, chooseItemFile(areaType));
    }
    
    public static Item loadItem(String areaType, String itemType){
        try{
            String path = OrbitAlert.getOrbitAlertPath();
            File itemFile = new File(path + "/raws/areas/" + areaType 
                    + "/items/" + itemType + ".txt");
            
            FileReader fileReader = new FileReader(itemFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;
            HashMap<String, String> itemAttributes = new HashMap<>();
            while (( line = reader.readLine()) != null){
                ArrayList<String> keyValuePair = new ArrayList<>(Arrays.asList(line.split("/")));
                if (keyValuePair.size() > 1){
                    itemAttributes.put(keyValuePair.get(0), keyValuePair.get(1));
                }
            }
            reader.close();
            return new Item(itemAttributes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
