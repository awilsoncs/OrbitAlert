package orbitalert.Areas;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import orbitalert.Objects.Obj;
import orbitalert.OrbitAlert;

/**
 *
 * @author Aaron
 */
public abstract class RoomLoader {
    public static Room loadRoom(String areaType){
        return loadRoom(areaType, chooseRoomFile(areaType));
    };
    
    public static String chooseRoomFile(String areaType){        
        try {
            String path = OrbitAlert.getOrbitAlertPath();
            File areaRoomsFile = new File(path + "/raws/areas/" + areaType + "/rooms/");
            ArrayList<String> roomTypes = new ArrayList<>(
                Arrays.asList(areaRoomsFile.list()));
            int random = (int) (Math.random() * roomTypes.size());
            
            
            String roomType = roomTypes.get(random);
            roomType = roomType.substring(0,roomType.lastIndexOf(".txt"));
            return roomType;
        } catch (Exception ex) {
        }
        return null;
    }
    
    public static Room loadRoom(String areaType, String roomText){
        try {
            String path = OrbitAlert.getOrbitAlertPath();
            File roomFile = new File(path + "/raws/areas/" + areaType 
                    + "/rooms/" + roomText + ".txt");
                    
            FileReader fileReader = new FileReader(roomFile);
            Room newRoom;
            try (BufferedReader reader = new BufferedReader(fileReader)) {
                String line;
                HashMap<String, String> roomAttributes = new HashMap<>();
                while (( line = reader.readLine()) != null){
                    ArrayList<String> keyValuePair = new ArrayList<>(
                            Arrays.asList(line.split("/")));
                    if (keyValuePair.size() > 1){
                        roomAttributes.put(
                                keyValuePair.get(0), keyValuePair.get(1));
                    }
                }
                HashMap<String, Exit> newExitMap = new HashMap<>();
                ArrayList<Obj> newObjList = new ArrayList<>();
                newRoom = new Room(roomAttributes,
                   areaType, newObjList, newExitMap);
            }
            return newRoom;
        } catch (Exception ex) {
        }
        return null;
    }
}
