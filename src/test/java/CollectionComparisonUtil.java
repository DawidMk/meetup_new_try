import java.util.*;

public class CollectionComparisonUtil {
//​
//    public static <A, B> CollectionComparisonResult<A, B> compareCollections(Collection<A> collectionA, Collection<B> collectionB, CollectionComparisonComparator<A, B> comparator) {
//        if (collectionA == null) {
//            collectionA = Collections.emptyList();
//        }
//        if (collectionB == null) {
//            collectionB = Collections.emptyList();
//        }
//        CollectionComparisonResult<A, B> result = new CollectionComparisonResult<>();
//        for (final A a : collectionA) {
//            if (a == null) {
//                continue;
//            }
//            Optional<B> equivalentB = collectionB.stream().filter(Objects::nonNull).filter(b -> comparator.isEqual(a, b)).findFirst();
//            if (equivalentB.isPresent()) {
//                result.getCommon().put(a, equivalentB.get());
//            } else {
//                result.getOnlyInFirst().add(a);
//            }
//        }
//        for (final B b : collectionB) {
//            if (b == null) {
//                continue;
//            }
//            Optional<A> equivalentA = collectionA.stream().filter(Objects::nonNull).filter(a -> comparator.isEqual(a, b)).findFirst();
//            if (!equivalentA.isPresent()) {
//                result.getOnlyInSecond().add(b);
//            }
//        }
//        return result;
//    }
//​
//        ​
//    public static <ELEMENT, KEY> CollectionComparisonResult<ELEMENT, ELEMENT> compareCollections(Collection<ELEMENT> collectionA, Collection<ELEMENT> collectionB, Function<ELEMENT, KEY> keyProvider) {
//        if (collectionA == null) {
//            collectionA = Collections.emptySet();
//        }
//        if (collectionB == null) {
//            collectionB = Collections.emptySet();
//        }
//        CollectionComparisonResult<ELEMENT, ELEMENT> result = new CollectionComparisonResult<>();
//        Map<KEY, ELEMENT> mapOfBForCompareProcess = collectionB.stream().collect(toMap(keyProvider, e -> e));
//        for (final ELEMENT a : collectionA) {
//            if (a == null) {
//                continue;
//            }
//            if (mapOfBForCompareProcess.containsKey(keyProvider.apply(a))) {
//                result.getCommon().put(a, mapOfBForCompareProcess.get(keyProvider.apply(a)));
//            } else {
//                result.getOnlyInFirst().add(a);
//            }
//        }
//        Map<KEY, ELEMENT> mapOfAForCompareProcess = collectionA.stream().collect(toMap(keyProvider, e -> e));
//        for (final ELEMENT b : collectionB) {
//            if (b == null) {
//                continue;
//            }
//            if (!mapOfAForCompareProcess.containsKey(keyProvider.apply(b))) {
//                result.getOnlyInSecond().add(b);
//            }
//        }
//        return result;
//    }
}








