import java.util.Collections;
import java.util.stream.Collectors;

public class CollectionComparisonResult {
//
//        CollectionComparisonResult<ELEMENT, ELEMENT> compareCollections(Collection<ELEMENT> collectionA, Collection<ELEMENT> collectionB, Function<ELEMENT, KEY> keyProvider) {
//        if (collectionA == null) {
//            collectionA = Collections.emptySet();
//        }
//        if (collectionB == null) {
//            collectionB = Collections.emptySet();
//        }
//        CollectionComparisonResult<ELEMENT, ELEMENT> result = new CollectionComparisonResult<>();
//        Map<KEY, ELEMENT> mapOfBForCompareProcess = collectionB.stream().collect(Collectors.toMap(keyProvider, e -> e));
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
//        Map<KEY, ELEMENT> mapOfAForCompareProcess = collectionA.stream().collect(Collectors.toMap(keyProvider, e -> e));
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
//}
}
