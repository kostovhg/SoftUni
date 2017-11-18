package p01_JarOfT;

public class Main {

    public static void main(String[] args) {

        Jar<Pickle> jarOfPickles = new JarofPickles();

        jarOfPickles.add(new Pickle());
        jarOfPickles.add(new Pickle());

        Pickle pickle = jarOfPickles.remove();

        //System.out.println(pickle);
        //System.out.println(jarOfPickles);
    }


}

class Pickle {

    private static int currentCount = 0;
    private int count;

    public Pickle() {
        this.count = ++currentCount;
    }

    @Override
    public String toString(){
        return "Pickle" + count;
    }
}