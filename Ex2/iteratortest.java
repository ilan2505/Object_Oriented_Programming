import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class iteratortest {
    // Java program to traverse through
// a hashmap using iterator



        public static void main(String[] args) {
            System.out.println("2");
            // Consider the hashmap contains
            // student name and their marks
            HashMap<String, Integer> hm =
                    new HashMap<String, Integer>();

            // Adding mappings to HashMap
            hm.put("GeeksforGeeks", 54);
            hm.put("A computer portal", 80);
            hm.put("For geeks", 82);

            // Printing the HashMap
            System.out.println("Created hashmap is" + hm);

            // Getting an iterator
            Iterator hmIterator = hm.entrySet().iterator();

            // Iterate through the hashmap
            // and add some bonus marks for every student
            System.out.println("HashMap after adding bonus marks:");

            while (hmIterator.hasNext()) {
                Map.Entry mapElement = (Map.Entry)hmIterator.next();
                int marks = ((int)mapElement.getValue() + 10);
                System.out.println(mapElement.getKey() + " : " + marks);
            }
        }
    }

