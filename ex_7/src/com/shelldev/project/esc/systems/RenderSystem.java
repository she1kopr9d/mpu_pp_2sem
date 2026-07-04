package com.shelldev.project.esc.systems;

import com.shelldev.project.esc.System;
import com.shelldev.project.esc.Entity;
import com.shelldev.project.esc.components.Sprite;

import com.shelldev.utils.paint.Screen;
import com.shelldev.utils.paint.Brush;

import java.util.ArrayList;

public class RenderSystem extends System{
    private Screen _screen;
    private Brush _brush;

    public RenderSystem(Screen screen, Brush brush){
        _screen = screen;
        _brush = brush;
    }

    @Override
    public void Update(ArrayList<Entity> entities, double deltatime){
        for (Entity entity : entities){
            Sprite sprite = entity.getComponent(Sprite.class);
            sprite.getSprite().accept(_screen, _brush);
        }
    }
}
