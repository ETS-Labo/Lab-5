package Controller;

import java.util.List;
import java.util.ArrayList;

public class CommandManager {
    private static CommandManager instance;
    private List<Command> history = new ArrayList<>();

    private CommandManager() {}

    public static synchronized CommandManager getInstance() {
        if (instance == null) instance = new CommandManager();
        return instance;
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            Command last = history.remove(history.size()-1);
            last.undo();
        }
    }

    public void executeCommand(Command c) {
        c.execute();
        history.add(c);
    }
}
