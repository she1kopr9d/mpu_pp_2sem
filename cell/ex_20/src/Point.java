class Point implements Figure{
    private R2Point p;

    public Point(R2Point p){
        this.p = p;
    }

    public double perimeter(){
        return 0.0;
    }

    public double area(){
        return 0.0;
    }

    public Figure add(R2Point q){
        if(!p.equals(q))
            return new Segment(p, q);
        else
            return this;
    }

    @Override
    public R2Point[] getPoints() {
        R2Point[] points = new R2Point[] {p};
        return points;
    }
}