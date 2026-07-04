package com.shelldev.project.esc.components;

import com.shelldev.project.esc.Component;
import com.shelldev.project.math.Point;

public class Position extends Component {
    private Point _point;

    public Position(double x, double y) {
        this._point = new Point(x, y);
    }

    public Point getPoint() {
        return _point;
    }

    public void setPoint(Point point) {
        this._point = point;
    }
}