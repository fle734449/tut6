package tut6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MinHeapArrayInvariant1Test {
   private static final long SEED = 42;
   private static final long VALUES_TO_TEST = 1000;
   private Random random;
   private HeapArray<Integer> heap;

   @BeforeEach
   public void setUp() {
       random = new Random(SEED);
       heap = new HeapArray<Integer>();
   }

   @Test
   public void testWithRandomAdds() {
       for (int i=0; i < VALUES_TO_TEST; i++) {
           int addMe = random.nextInt();
           heap.add(addMe);
           assertTrue(invariantHolds());
       }
   }
 
   @Test
   public void testWithRandomRemoves() {
       fillWithRandomValues(VALUES_TO_TEST);
       while (heap.size() > 0) {
           int indexToRemove = random.nextInt(heap.size());
           Integer removeMe = heap.get(indexToRemove);
           heap.remove(removeMe);
           assertTrue(invariantHolds());
       }
   }

   @Test
   public void testPop() {
       fillWithRandomValues(VALUES_TO_TEST);
       while (heap.size() > 0) {
           heap.pop();
           assertTrue(invariantHolds());
       }
   }

   @Test
   public void testClear() {
       fillWithRandomValues(VALUES_TO_TEST);
       heap.clear();
       assertTrue(invariantHolds());
   }

   private boolean invariantHolds() {
       Integer top = heap.peek();
       if (top == null) {
           return true;
       }

       Integer[] contents = new Integer[heap.size()];
       Integer min = Collections.min(Arrays.asList(heap.toArray(contents)));
       if (min > top) {
           System.out.println("Whoops!");
       }

       return min <= top;
   }

   private void fillWithRandomValues(long numValues) {
       for (int i = 0; i < numValues; i++){
           heap.add(random.nextInt());
       }
   }
}
