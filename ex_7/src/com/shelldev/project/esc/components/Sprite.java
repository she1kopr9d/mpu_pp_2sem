package com.shelldev.project.esc.components;

import com.shelldev.project.esc.Component;
import java.util.function.BiConsumer;

import com.shelldev.utils.paint.Screen;
import com.shelldev.utils.paint.Brush;

public class Sprite extends Component {
    private BiConsumer<Screen, Brush> _sprite;

    public Sprite(BiConsumer<Screen, Brush> sprite) {
        this._sprite = sprite;
    }

    public BiConsumer<Screen, Brush> getSprite() {
        return _sprite;
    }

    public void setSprite(BiConsumer<Screen, Brush> sprite) {
        this._sprite = sprite;
    }
}
