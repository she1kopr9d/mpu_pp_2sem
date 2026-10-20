import java.util.ArrayList;
//Класс "Выпуклая оболочка".
class Convex{
    private Figure fig;
    private ArrayList<Plugin> _plugins;

    public Convex(){
        fig = new Void();
        _plugins = new ArrayList<>();
    }

    public void includePlugin(Plugin plugin){
        _plugins.add(plugin);
    }

    public void includePlugin(Plugin[] plugins){
        for(Plugin plugin : plugins){
            includePlugin(plugin);
        }
    }

    public void add(R2Point p){
        fig = fig.add(p);
        R2Point[] points = fig.getPoints();
        for(Plugin plugin : _plugins){
            plugin.PostAdd(points);
        }
    }

    public double area(){
        return fig.area();
    }

    public double perimeter(){
        return fig.perimeter();
    }

    public R2Point[] getPoints() {
        return fig.getPoints();
    }
}
