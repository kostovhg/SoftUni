import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class p02_Earthquake {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayDeque<ArrayDeque<Integer>> queue = new ArrayDeque<>();
        ArrayDeque<Integer> activity = new ArrayDeque<>();
        int count = Integer.parseInt(reader.readLine());
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
            List<String> list = new ArrayList<>();
            while (st.hasMoreTokens())
                activity.add(Integer.valueOf(st.nextToken()));
            /*activity.addAll(Arrays
                    .stream()
                    .map(Integer::valueOf).collect(Collectors.toCollection(ArrayList::new)));*/
            queue.addLast(activity.clone());
            activity.clear();
        }
        // List to keep seismicities found
        List<Integer> seismicities = new ArrayList<>();
        while(queue.size() > 0){
            // poll the first activity
            activity = queue.pollFirst();
            // Take its first member to be compared with others
            Integer seismicity = activity.pollFirst();
            // save it in the list
            seismicities.add(seismicity);
            // check if we have any other waves in activity
            // take from the stack first list
            while(activity.size() > 0 && seismicity >= activity.peekFirst()){
                activity.removeFirst();
            }
            if(activity.size() > 0) {
                queue.addLast(activity.clone());
                activity.clear();
            }
        }

        System.out.println(seismicities.size());
        StringBuilder strbul  = new StringBuilder();
        Iterator<Integer> iter = seismicities.iterator();
        while(iter.hasNext()) {
            strbul.append(iter.next());
            if(iter.hasNext()){
                strbul.append(" ");
            }
        }
        System.out.println(strbul.toString());
    }
}
