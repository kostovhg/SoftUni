import java.util.*;

public class p01_SecondNature {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // initialize double queues for flowers and buckets
        ArrayDeque<Integer> flowerQueue = new ArrayDeque<>();
        ArrayDeque<Integer> buckedSteck = new ArrayDeque<>();
        // initialize a list to add flowers with second nature
        List<Integer> secondNature= new ArrayList<>();
        // use tokenizer to read input
        StringTokenizer st = new StringTokenizer(scan.nextLine(), " ");
        // fill flowers in queue FIFO
        while(st.hasMoreTokens()){
            flowerQueue.add(Integer.valueOf(st.nextToken()));
        }
        // again use tokinizer
        st = new StringTokenizer(scan.nextLine(), " ");
        // fill buckets in steck LIFO
        while(st.hasMoreTokens()){
            buckedSteck.push(Integer.valueOf(st.nextToken()));
        }
        // until we have at least one member in each collection
        while(flowerQueue.size() > 0 && buckedSteck.size() > 0){
            // take the bucket and flower
            Integer bucket = buckedSteck.pop();
            Integer flower = flowerQueue.poll();
            if(flower > bucket){
                // if the water in the bucket is less than flower's dust (power)
                // lower the flower power with the diff (dif is negative)
                // and put it back
                flowerQueue.push(flower - bucket);
            } else if( flower < bucket){
                // if water in the bucket is more than the flower's dust
                // the remaining water in the bucket is added to next bucket or stays
                if(buckedSteck.isEmpty()) buckedSteck.push(bucket - flower);
                else  buckedSteck.push(buckedSteck.pop() + (bucket - flower));
            } else {
                // if difference is 0, remove the bucket and
                // place that flower with second nature to the list
                secondNature.add(flower);
            }
        }
        if(!buckedSteck.isEmpty()){
            while(!buckedSteck.isEmpty()){
                System.out.printf("%d ", buckedSteck.pop());
            }
        } else {
            while(!flowerQueue.isEmpty()){
                System.out.printf("%d ", flowerQueue.poll());
            }
        }
        System.out.println();
        if(secondNature.size() > 0){
            for (Integer flower :
                    secondNature) {
                System.out.printf("%d ", flower);
            }
        } else {
            System.out.printf("None");
        }
        System.out.println();
    }
}
