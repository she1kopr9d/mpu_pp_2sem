package com.shelldev.project.esc;
import java.util.ArrayList;

public abstract class Entity implements ICountable {
    private static int _idCounter = 0;
    private int _id;
    private ArrayList<Component> _components;

    public Entity() {
        this._id = ++_idCounter;
        this._components = new ArrayList<>();
    }

    @Override
    public int getId() {
        return _id;
    }

    public void addComponent(Component component) {
        _components.add(component);
    }

    public void removeComponent(Component component) {
        _components.remove(component);
    }

    public <T extends Component> T getComponent(Class<T> componentClass) {
        for (Component component : _components) {
            if (componentClass.isInstance(component)) {
                return componentClass.cast(component);
            }
        }
        return null;
    }
}
