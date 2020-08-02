import syntaxtree.*;
import visitor.*;
import java.util.*;
public class P5 {
   public static void main(String [] args) {
      try {
         Node root = new microIRParser(System.in).Goal();
          HashMap<String,HashMap<Integer,Integer>> Spills=new HashMap<String,HashMap<Integer,Integer>>();
         HashMap<String,HashMap<Integer,String>> Allocs=new HashMap<String,HashMap<Integer,String>>();
         HashMap<String,HashMap<String,String>> Labs=new HashMap<String,HashMap<String,String>>();
         HashMap<String,Integer> stksp=new HashMap<String,Integer>();
         root.accept(new GJNoArguDepthFirst(Spills,Allocs,stksp,Labs)); // Your assignment part is invoked here.
         root.accept(new GJDepthFirst(Spills,Allocs,stksp,Labs),null);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 
