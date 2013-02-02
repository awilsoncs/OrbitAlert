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

/**
 *
 * @author Aaron
 */
public class ItemLoader {

    private static File chooseItemFile(String areaType){
        File itemsFile = new File("raws/areas/" + areaType + "/items/");
        ArrayList<String> itemTypes = new ArrayList<>(
                Arrays.asList(itemsFile.list()));
        int random = (int) (Math.random() * itemTypes.size());
        File itemFile = new File("raws/areas/" + areaType 
                + "/items/" + itemTypes.get(random));
        return itemFile;
    }
    
    public static Item loadItem(String areaType) {
        return loadItem(chooseItemFile(areaType));
    }
    
    public static Item loadItem(File itemFile){
        try{
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
        return new Item(itemAttributes);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
