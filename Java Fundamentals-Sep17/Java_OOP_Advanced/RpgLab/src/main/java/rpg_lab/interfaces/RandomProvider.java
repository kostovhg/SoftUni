package rpg_lab.interfaces;

import java.util.Random;

public interface RandomProvider {

    int next(int max);

    class Implementation {

        private Random random;

        public Implementation() {
            this.random = new Random();
        }

        public int next(int max){
            return this.random.nextInt(max);
        }
    }
}
