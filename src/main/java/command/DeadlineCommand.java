package command;

import duke.Ui;
import duke.Storage;
import exception.InvalidFormatException;
import task.DukeTask;
import task.DukeTaskDeadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a deadline command
 */
public class DeadlineCommand extends Command {
    private String cmd;

    public DeadlineCommand(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Deconstruct a deadline command based on cmd
     * @param tasklist
     * @param ui
     * @param storage
     * @throws InvalidFormatException
     */
    @Override
    public String deconstruct(ArrayList<DukeTask> tasklist, Ui ui, Storage storage) throws InvalidFormatException {
        try {
            int index = cmd.indexOf('/');
            if (index == -1) {
                throw new InvalidFormatException();
            }
            String task = cmd.substring(0, index).trim();
            LocalDateTime ldt = LocalDateTime.parse(cmd.substring(index + 1), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            DukeTaskDeadline tD = new DukeTaskDeadline(task, false, 'D', ldt);
            tasklist.add(tD);
            StringBuilder output = new StringBuilder("Got it. I've added this task:\n");
            output.append(String.format("List %d: ", tasklist.size() - 1) + tD.toString());
            storage.save();
            return output.toString();
        } catch (InvalidFormatException e) {
            return "Please format your Deadline request with a /{deadline}";
        } catch (DateTimeParseException e) {
            return "Looks like your date time formatting is wrong, please format it like so: \"yyyy-mm-dd hh:mm\"";
        } catch (Exception e) {
            return "Something went wrong in DeadlineCommand" + e;
        }
    }
}
