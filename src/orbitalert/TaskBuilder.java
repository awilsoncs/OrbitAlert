/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert;

import orbitalert.Areas.Area;

/**
 *
 * @author Aaron
 */
public class TaskBuilder {
    //We can use the output from buildTask to link an Exit.
    public static Task buildTask(Area previousArea){
        Task newTask = new DefaultTask();
        return newTask;
    }
}
