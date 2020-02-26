import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SomeTest {
    public static void main(String[] args) {
        Map<String, List<Address>> someMap = new HashMap<>();
        Map<String, List<Address>> someMap2 = new HashMap<>();
        Map<String, List<Address>> someMap3 = new HashMap<>();
        List<Address> someList = new ArrayList<>();

        Address hsn = new Address(1, "1");
        Address hsn1 = new Address(2, "2");
        Address hsn2 = new Address(3, "3");
        Address hsn6 = new Address(5, "3");
        Address hsn4 = new Address(4, "1");
        Address hsn5 = new Address(4, "2");
        Address hsn3 = new Address(3, "3");


        someList.add(hsn);
        someList.add(hsn1);
        someList.add(hsn2);
        someList.add(hsn3);
        someList.add(hsn4);
        someList.add(hsn5);
        someList.add(hsn6);

        System.out.println("method 1");
        long startTime = System.nanoTime();
        populateMap(someMap, someList);
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("method 1 time: " + duration);


//        System.out.println("method 2");
//        populateMap2(someMap, someList);


        System.out.println("method 3");
        long startTime2 = System.nanoTime();
        populateMap3(someMap2, someList);
        long endTime2 = System.nanoTime();

        long duration2 = (endTime2 - startTime2);  //divide by 1000000 to get milliseconds.
        System.out.println("method 3 time: " + duration2);


        System.out.println("method 4");
        long startTime4 = System.nanoTime();
        someMap3 = populateMap4(someList);
        long endTime4 = System.nanoTime();

        long duration4 = (endTime4 - startTime4);  //divide by 1000000 to get milliseconds.
        System.out.println("method 4 time: " + duration4);

        System.out.println("map 1");
        for (Map.Entry<String, List<Address>> stringListEntry : someMap.entrySet()) {
            if (stringListEntry.getValue().size() > 0) {
                System.out.println("nihao " + stringListEntry.getKey());
            }
        }

        System.out.println("map 2");
        for (Map.Entry<String, List<Address>> stringListEntry : someMap2.entrySet()) {
            if (stringListEntry.getValue().size() > 0) {
                System.out.println("nihao " + stringListEntry.getKey());
            }
        }

        System.out.println("map 3");
        for (Map.Entry<String, List<Address>> stringListEntry : someMap3.entrySet()) {
            if (stringListEntry.getValue().size() > 0) {
                System.out.println("nihao " + stringListEntry.getKey());
            }
        }
    }

    private static Map<String, List<Address>> populateMap4(List<Address> someList) {
        return someList.stream()
                .filter(e -> someList.stream().anyMatch(f -> f.getHsn().equals(e.getHsn()) && f.getLangCode() != e.getLangCode()))
                .collect(Collectors.groupingBy(Address::getHsn));
    }

    private static void populateMap(Map<String, List<Address>> someMap, List<Address> someList) {
        for (int i = 0; i < someList.size(); i++) {
            for (int j = i + 1; j < someList.size(); j++) {
                Address address = someList.get(i);
                Address refAddress = someList.get(j);

                if (address.getHsn().equals(refAddress.getHsn()) && address.getLangCode() != refAddress.getLangCode()) {
                    List<Address> hsns = someMap.getOrDefault(address.getHsn(), new ArrayList<>());

                    hsns.add(address);
                    someMap.put(address.getHsn(), hsns);

                }
            }
        }
    }

    private static void populateMap2(Map<String, List<Address>> someMap, List<Address> someList) {
        for (int i = 0; i < someList.size() - 1; i++) {
            Address address = someList.get(i);
            Address refAddress = someList.get(i + 1);

            if (address.getHsn().equals(refAddress.getHsn()) && address.getLangCode() != refAddress.getLangCode()) {
                List<Address> hsns = someMap.getOrDefault(address.getHsn(), new ArrayList<>());

                hsns.add(address);
                someMap.put(address.getHsn(), hsns);

            }
        }
    }

    private static void populateMap3(Map<String, List<Address>> someMap, List<Address> someList) {

//        List<Address> collect = someList.stream()
//                .filter(e -> someList.stream().anyMatch(f -> f.getHsn().equals(e.getHsn()) && f.getLangCode() != e.getLangCode()))
//                .collect(Collectors.toList());
        someList.stream()
                .filter(e -> someList.stream().anyMatch(f -> f.getHsn().equals(e.getHsn()) && f.getLangCode() != e.getLangCode()))
                .collect(Collectors.toList()).forEach(address -> {
            List<Address> hsns = someMap.getOrDefault(address.getHsn(), new ArrayList<>());
            hsns.add(address);
            someMap.put(address.getHsn(), hsns);
        });
    }
}

//        for (Address address : collect) {
//
//
//            List<Address> hsns = someMap.getOrDefault(address.getHsn(), new ArrayList<>());
//            hsns.add(address);
//            someMap.put(address.getHsn(), hsns);
//        }


//}
