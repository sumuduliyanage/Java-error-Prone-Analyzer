class Superclass {
    public int a;
    public int b;
    public int c;

    public Superclass(Superclass superclass) {
        this.a = superclass.a;
        this.b = superclass.b;
        this.c = superclass.c;
    }   
}

        
class Subclass extends Superclass {
    public int x;
    
    public Subclass(Superclass superclass, int x) {
        super(superClass);
        this.x = x;
    }
}
