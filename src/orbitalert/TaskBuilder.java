package orbitalert;

import orbitalert.Areas.Area;

/**
 *
 * @author Aaron
 */
public class TaskBuilder {
    public static Task buildTask(Area previousArea){
        Task newTask = new DefaultTask();
        newTask.setSolved(true);
        return newTask;
    }
}
