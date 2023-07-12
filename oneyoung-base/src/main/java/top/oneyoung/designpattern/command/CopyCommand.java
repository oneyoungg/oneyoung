package top.oneyoung.designpattern.command;

/**
 * CopyCommand
 *
 * @author oneyoung
 * @since 2023/7/12 15:29
 */
public class CopyCommand extends Command {

    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }
}
