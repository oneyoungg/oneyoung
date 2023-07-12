package top.oneyoung.designpattern.command;

import java.util.Stack;

/**
 * CommandHistory
 *
 * @author oneyoung
 * @since 2023/7/12 15:30
 */
public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() { return history.isEmpty(); }
}
