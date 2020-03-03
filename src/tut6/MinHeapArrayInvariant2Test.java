package tut6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MinHeapArrayInvariant2Test {
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
       int i = 0;
       HeapArray<Integer> contents = new HeapArray<Integer>(heap.size());
       for(int j = 0; j < heap.size(); j++) {
    	   contents.add(heap.get(j));
       }
       
       while(!contents.isEmpty()){
    	   int parent = contents.peek();
    	   int child1 = parent;
    	   int child2 = parent;
    	   if(2*i + 1 < contents.size()) {
    		   child1 = contents.get(2*i + 1);
    	   }
    	   if(2*i+2 < contents.size()) {
    		   child2 = contents.get(2*i +2);
    	   }
    	   if(parent > child1 || parent > child2) {
    		   return false;
    	   }
    	   contents.pop();
       }
       return true;
   }

   private void fillWithRandomValues(long numValues) {
       for (int i = 0; i < numValues; i++){
           heap.add(random.nextInt());
       }
   }
}
