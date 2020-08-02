import syntaxtree.*;
import visitor.*;
import java.util.*;
public class B1 {
   public static void main(String [] args) {
      try {
      		HashMap<String,String> Function_Map=new HashMap<String,String>();
   			HashMap<String,String> Var_Map=new HashMap<String,String>();
    		Vector<String> Class_Vec=new Vector<String>();
   	 		HashMap<String,String> Par=new HashMap<String,String>();
   	 		HashMap<String,Vector<String>> Class_Var=new HashMap<String,Vector<String>>();
   	 		HashMap<String,Vector<String>> Class_Func=new HashMap<String,Vector<String>>();
         Node root = new MiniJavaParser(System.in).Goal();
         root.accept(new Build_Sym_tables(Function_Map,Var_Map,Class_Vec,Par),null); // Your assignment part is invoked here.
         root.accept(new Build_Class_Tables(Function_Map,Var_Map,Class_Vec,Par,Class_Var,Class_Func),null);
         root.accept(new BuildIR(Function_Map,Var_Map,Class_Vec,Par,Class_Var,Class_Func),null); 
         //root.accept(new Type_Check(Function_Map,Var_Map,Class_Vec,Par),null);
         
         //System.out.println("Program type checked successfully");
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 


