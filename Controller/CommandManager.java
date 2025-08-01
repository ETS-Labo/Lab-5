package Controller;

import java.util.Stack;

public class CommandManager {
    private static CommandManager instance;
    private Stack<Command> history = new Stack<>();

    private CommandManager() {}

    public static synchronized CommandManager getInstance() {
        if (instance == null) instance = new CommandManager();
        return instance;
    }

    public void undoLast() {
        if (!history.isEmpty()) {
            history.pop().undo();
        }
    }
    
    public void executeCommand(Command c) {
        c.execute();
        history.push(c);
    }
}
