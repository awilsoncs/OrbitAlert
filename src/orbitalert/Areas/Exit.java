/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orbitalert.Areas;

import orbitalert.Task;

/**
 *
 * @author Aaron
 */
public class Exit {
    private Task solutionTask;

    public Exit(){
    }
    
    public Exit(Task newTask) {
        solutionTask = newTask;
    }

    public boolean getIsOpen() {
        if (solutionTask == null) {
            return true;
        } else {
            return solutionTask.getIsSolved();
        }
    }
    
}
