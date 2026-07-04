package com.shelldev.project.esc.components;

import com.shelldev.project.esc.Component;

public class Size extends Component{
    private int _width;
    private int _height;

    public Size(){
        _width = 1;
        _height = 1;
    }

    public Size(int width, int height){
        _width = width;
        _height = height;
    }


    public void setWidth(int width){
        _width = width;
    }

    public void setHight(int height){
        _height = height;
    }

    public int getWidth(){
        return _width;
    }

    public int getHeight(){
        return _height;
    }
}
