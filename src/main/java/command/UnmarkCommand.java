package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;

import java.util.ArrayList;

/**
 * Represent an unmark command
 */
public class UnmarkCommand extends Command {
    private String cmd;

    public UnmarkCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a deconstruct command based on cmd
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        int j = Integer.valueOf(cmd.substring(7).trim());
        tasklist.get(j).setMark(false);
        StringBuilder output = new StringBuilder("Got it. I've mark this task as not done:\n");
        output.append(String.format("List %d: ", j) + tasklist.get(j).toString());
        storage.save();
        return output.toString();
        
    }
    
}
