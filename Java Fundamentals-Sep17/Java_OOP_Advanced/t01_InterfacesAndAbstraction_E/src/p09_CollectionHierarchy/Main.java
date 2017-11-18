package p09_CollectionHierarchy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> collection = Arrays.stream(reader.readLine().split("\\s+"))
                .collect(Collectors.toList());

        AddRemoveCollection<String> addRemoveCollection = new AddRemoveCollectionImpl<>();
        AddCollection<String> addCollection = new AddCollectionImpl<>();
        MyList<String> myList = new MyListImpl<>();

        int count = Integer.parseInt(reader.readLine());
        StringBuilder addToAddCollection = new StringBuilder();
        StringBuilder addToAddRemoveCollection = new StringBuilder();
        StringBuilder addToMyList = new StringBuilder();
        StringBuilder removeFromAddRemoveCollection = new StringBuilder();
        StringBuilder removeFromMyList = new StringBuilder();

        for (String element : collection) {
            addToAddCollection.append(addCollection.add(element)).append(" ");
            addToAddRemoveCollection.append(addRemoveCollection.add(element)).append(" ");
            addToMyList.append(myList.add(element)).append(" ");
        }

        for (int i = 0; i < count; i++) {
            removeFromAddRemoveCollection.append(addRemoveCollection.remove()).append(" ");
            removeFromMyList.append(myList.remove()).append(" ");
        }

        System.out.println(addToAddCollection);
        System.out.println(addToAddRemoveCollection);
        System.out.println(addToMyList);
        System.out.println(removeFromAddRemoveCollection);
        System.out.println(removeFromMyList);

    }
}
