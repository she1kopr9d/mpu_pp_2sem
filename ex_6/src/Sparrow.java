public class Sparrow extends Bird{
    private String name;
    private static int count = 0;

    public Sparrow(){
        count++;
        System.out.println("Я воробей.");
        //System.out.println("Я воробей." + " Всего воробьев: " + count + ".");
        name = "Инокентий";
    }

    public Sparrow(String name){
        count++;
        System.out.println("Я воробей.");
        //System.out.println("Я воробей." + " Всего воробьев: " + count + ".");
        this.name = name;
    }

    @Override
    public void fly(){
       System.out.println("Я лечу по воробьиному!");
    }

    public void speak(){
        System.out.println("Меня зовут " + name + ".");
    }

    //public void rename(String name){
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name; //this.name
    }

    public static void printCount(){
        System.out.println("Всего воробьев: " + count + ".");
    }

    public void hello(Parrot p){
        System.out.println("Привет, попугай " + p.getName() + "! Я воробей, меня зовут " + this.name + "!");
    }

    public void hello(Penguin p){
        System.out.println("Привет, пингвин! Я воробей, меня зовут " + this.name + "!");
    }

    public void hello(Sparrow s){
        System.out.println("Привет, воробей " + s.getName() + "! Я тоже воробей, меня зовут " + this.name + "!");
    }
}
