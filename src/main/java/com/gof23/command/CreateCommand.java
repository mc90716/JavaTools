package com.gof23.command;

/**
 * 将一个接收者对象绑定于一个动作，调用接收者相应的操作，以实现excute
 *
 */
public class CreateCommand implements Command{
    private Receiver receiver;
    
    public CreateCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }

    @Override
    public void undo() {
        receiver.unAction();
    }
    
}
