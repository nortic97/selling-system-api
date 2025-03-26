package com.nortic.posdash.ts.platforminitializer.command.contract;

public interface ICommandArgs<T> {
    void execute(T data);
}
