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
class Exit {
    private Task solutionTask;

    public Exit(){
    }
    
    public Exit(Task newTask) {
        solutionTask = newTask;
    }

    private boolean getIsOpen() {
        if (solutionTask == null) {
            return true;
        } else {
            return solutionTask.getIsSolved();
        }
    }
    
}
