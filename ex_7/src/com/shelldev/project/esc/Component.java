package com.shelldev.project.esc;

public abstract class Component implements ICountable {
    private static int _idCounter = 0;
    private int _id;

    public Component() {
        _id = _idCounter++;
    }

    @Override
    public int getId() {
        return _id;
    }
}