//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class GJDepthFirst<R,A> implements GJVisitor<R,A> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
   
    public static HashMap<String,HashMap<Integer,Integer>> Spills;
    public static HashMap<String,HashMap<Integer,String>> Allocs;
    public static HashMap<String,Integer> stksp;
    public static HashMap<String,Integer> maxarg=new HashMap<String,Integer>();
    public static HashMap<String,Integer> numarg=new HashMap<String,Integer>();
    public static HashMap<String,HashMap<String,String>> Labs;
    public static int args=0;  
    public static boolean iscall=false;
    public static String Code=new String();
    public static int call_cnt=0;
    public static String cur_func=new String();
    public Vector<Integer> calltemp=new Vector<Integer>();
   public GJDepthFirst(HashMap<String,HashMap<Integer,Integer>> a,HashMap<String,HashMap<Integer,String>> b,HashMap<String,Integer> c,HashMap<String,HashMap<String,String>> d)
   {
   		this.Spills=a;
   		this.Allocs=b;
       this.stksp=c;
       this.Labs=d;
   }
   
   public R visit(NodeList n, A argu) {
       R _ret=(R)"";
      int _count=0;
       String str=new String();
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         str=str+(String)e.nextElement().accept(this,argu);
         _count++;
      }
       _ret=(R)str;
      return _ret;
   }

   public R visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
          String str=new String();
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            str=str+(String)e.nextElement().accept(this,argu);
            _count++;
         }
          _ret=(R)str;
         return _ret;
      }
      else
         return (R)"";
   }

   public R visit(NodeOptional n, A argu) {
      if ( n.present() )
         return (R)(Labs.get(cur_func).get(((String)n.node.accept(this,argu)))+" ");
      else
         return (R)" ";
   }

   public R visit(NodeSequence n, A argu) {
      R _ret=null;
      int _count=0;
       String str=new String();
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         str=str+(String)e.nextElement().accept(this,argu);
         _count++;
      }
       _ret=(R)str;
      return _ret;
   }

   public R visit(NodeToken n, A argu) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> "MAIN"
    * f1 -> StmtList()
    * f2 -> "END"
    * f3 -> ( Procedure() )*
    * f4 -> <EOF>
    */
   public R visit(Goal n, A argu) {
      R _ret=null;
      cur_func="MAIN";
      String Temp=new String();
      call_cnt=0;
      n.f0.accept(this, argu);
      Temp=Temp+"MAIN [0]";
       numarg.put(cur_func,(0));
       maxarg.put(cur_func,0);
      String t1=(String)n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      int overflow=0;
      if(call_cnt>0)
      {
      	 overflow=overflow+10;//t0---t9
      }
      overflow=overflow+Spills.get(cur_func).size();
      Temp=Temp+" ["+Integer.toString(overflow)+"]";
       Temp=Temp+" ["+Integer.toString(maxarg.get(cur_func))+"]\n";
      call_cnt=0;
       Temp=Temp+t1+"\n";
      String Temp1=(String)n.f3.accept(this, argu);
       n.f4.accept(this, argu);
       if(Temp1==null)
       {
           Temp1="";
       }
       Code=Code+Temp+"END\n"+Temp1+"\n";
       _ret=(R)(Temp+"END\n"+Temp1+"\n");
       System.out.println(Code);
      return _ret;
   }

   /**
    * f0 -> ( ( Label() )? Stmt() )*
    */
   public R visit(StmtList n, A argu) {
      R _ret=null;
      _ret=(R)n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Label()
    * f1 -> "["
    * f2 -> IntegerLiteral()
    * f3 -> "]"
    * f4 -> StmtExp()
    */
   public R visit(Procedure n, A argu) {
      R _ret=null;
      call_cnt=0;
      cur_func=(String)n.f0.accept(this,argu);
       String Temp=new String();
       Temp=Temp+cur_func+" ";
      n.f1.accept(this, argu);
      String a=(String)n.f2.accept(this, argu);
       numarg.put(cur_func,(Integer.parseInt(a)));
       maxarg.put(cur_func,0);
       Temp=Temp+" ["+a+"]";
      n.f3.accept(this, argu);
      String t1=(String)n.f4.accept(this, argu);
       int overflow=0;
       overflow=overflow+8;
       if(call_cnt>0)
       {
           overflow=overflow+10;
       }
       overflow=overflow+Spills.get(cur_func).size();
       Temp=Temp+" ["+Integer.toString(overflow)+"]";
       if(maxarg.get(cur_func)!=null){
       Temp=Temp+" ["+Integer.toString(maxarg.get(cur_func))+"]\n";
       }
       else
       {
           Temp=Temp+" [0]\n";
       }
       for(int i=0;i<=7;i++)
       {
           Temp=Temp+"ASTORE"+" SPILLEDARG "+Integer.toString(i+Math.max(0,numarg.get(cur_func)-4))+" s"+Integer.toString(i)+"\n";
       }
       for(int i=0;i<Math.min(4,Integer.parseInt(a));i++)
       {
           if(Allocs.get(cur_func).containsKey(i))
           {
               Temp=Temp+"MOVE "+Allocs.get(cur_func).get(i)+" "+"a"+Integer.toString(i)+"\n";
           }
           else
           {
               
           }
       }
      call_cnt=0;
       Temp=Temp+t1+"\n";
       for(int i=0;i<=7;i++)
       {
           Temp=Temp+"ALOAD"+" s"+Integer.toString(i)+" SPILLEDARG "+Integer.toString(i+Math.max(0,numarg.get(cur_func)-4))+"\n";
       }
       Temp=Temp+"END\n";
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> NoOpStmt()
    *       | ErrorStmt()
    *       | CJumpStmt()
    *       | JumpStmt()
    *       | HStoreStmt()
    *       | HLoadStmt()
    *       | MoveStmt()
    *       | PrintStmt()
    */
   public R visit(Stmt n, A argu) {
      R _ret=(R)"";
      String Temp=(String)n.f0.accept(this, argu);
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "NOOP"
    */
   public R visit(NoOpStmt n, A argu) {
      R _ret=(R)"";
      n.f0.accept(this, argu);
       _ret=(R)"NOOP\n";
      return _ret;
   }

   /**
    * f0 -> "ERROR"
    */
   public R visit(ErrorStmt n, A argu) {
      R _ret=(R)"";
      n.f0.accept(this, argu);
       _ret=(R)"ERROR\n";
      return _ret;
   }

   /**
    * f0 -> "CJUMP"
    * f1 -> Temp()
    * f2 -> Label()
    */
   public R visit(CJumpStmt n, A argu) {
      R _ret=(R)"";
       String Temp="CJUMP ";
       
       n.f0.accept(this, argu);
      int x=Integer.parseInt((String)n.f1.accept(this, argu));
       if(Allocs.get(cur_func).containsKey(x))
       {
           Temp=Temp+" "+(Allocs.get(cur_func).get(x));
       }
       else
       {
           
       }
      Temp=Temp +" "+Labs.get(cur_func).get((String)n.f2.accept(this, argu))+"\n";
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "JUMP"
    * f1 -> Label()
    */
   public R visit(JumpStmt n, A argu) {
      R _ret=(R)"";
       String Temp="JUMP ";
      n.f0.accept(this, argu);
      Temp=Temp+Labs.get(cur_func).get((String)n.f1.accept(this, argu))+"\n";
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "HSTORE"
    * f1 -> Temp()
    * f2 -> IntegerLiteral()
    * f3 -> Temp()
    */
   public R visit(HStoreStmt n, A argu) {
      R _ret=(R)"";
       String Temp=new String();
      n.f0.accept(this, argu);
      int x=Integer.parseInt((String)n.f1.accept(this, argu));
      String t1=(String)n.f2.accept(this, argu);
      int y=Integer.parseInt((String)n.f3.accept(this, argu));
       Temp=Temp+"HSTORE ";
       if(Allocs.get(cur_func).containsKey(x))
       {
           Temp=Temp+" "+(Allocs.get(cur_func).get(x));
       }
       else
       {
           Temp="ALOAD v0 SPILLEDARG "+Integer.toString(Spills.get(cur_func).get(x))+"\n"+Temp;
           Temp=Temp+"v0";
       }
       Temp=Temp+" "+t1;
       if(Allocs.get(cur_func).containsKey(y))
       {
           Temp=Temp+" "+(Allocs.get(cur_func).get(y));
       }
       else
       {
           Temp="ALOAD v1 SPILLEDARG "+Integer.toString(Spills.get(cur_func).get(y))+"\n"+Temp;
           Temp=Temp+"v1";
       }
       Temp=Temp+"\n";
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "HLOAD"
    * f1 -> Temp()
    * f2 -> Temp()
    * f3 -> IntegerLiteral()
    */
   public R visit(HLoadStmt n, A argu) {
      R _ret=(R)"";
       String Temp=new String();
       Temp=Temp+"HLOAD ";
      n.f0.accept(this, argu);
     int x=Integer.parseInt((String)n.f1.accept(this, argu));
      int y=Integer.parseInt((String)n.f2.accept(this, argu));
      String t1=(String)n.f3.accept(this, argu);
       boolean c=false;
       if(Allocs.get(cur_func).containsKey(x))
       {
           Temp=Temp+" "+(Allocs.get(cur_func).get(x));
       }
       else
       {
           Temp=Temp+" v0 "+"\n";
           c=true;
       }
       if(Allocs.get(cur_func).containsKey(y))
       {
           Temp=Temp+" "+(Allocs.get(cur_func).get(y));
       }
       else
       {
           Temp="ALOAD v1 SPILLEDARG "+Integer.toString(Spills.get(cur_func).get(y))+"\n"+Temp;
           Temp=Temp+"v1 ";
       }
       Temp=Temp+" "+t1+"\n";
       if(c)
       {
           Temp=Temp+"ASTORE SPILLEDARG "+Integer.toString(Spills.get(cur_func).get(x))+" v0\n";;
       }
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "MOVE"
    * f1 -> Temp()
    * f2 -> Exp()
    */
   public R visit(MoveStmt n, A argu) {
      R _ret=null;
       String Temp="MOVE ";
      n.f0.accept(this, argu);
      int x=Integer.parseInt((String)n.f1.accept(this, argu));
       boolean c=false;
        if(Allocs.get(cur_func).containsKey(x))
        {
            Temp=Temp+" "+(Allocs.get(cur_func).get(x));
        }
        else
        {
            Temp=Temp+"v1 ";
            c=true;
        }
      String t=(String)n.f2.accept(this, argu);
           Temp=t+Temp;
           Temp=Temp+" v0\n";
       if(c){
       Temp=Temp+"ASTORE SPILLEDARG "+Integer.toString(Spills.get(cur_func).get(x))+"v1\n";
       }
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "PRINT"
    * f1 -> SimpleExp()
    */
   public R visit(PrintStmt n, A argu) {
      R _ret=(R)"";
       String Temp=new String();
       Temp=Temp+"PRINT ";
      n.f0.accept(this, argu);
      String t=(String)n.f1.accept(this, argu);
       Temp=t+Temp+"v0\n";
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> Call()
    *       | HAllocate()
    *       | BinOp()
    *       | SimpleExp()
    */
   public R visit(Exp n, A argu) {
      R _ret=(R)"";
      _ret=(R)n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "BEGIN"
    * f1 -> StmtList()
    * f2 -> "RETURN"
    * f3 -> SimpleExp()
    * f4 -> "END"
    */
   public R visit(StmtExp n, A argu) {
      R _ret=(R)"";
       String Temp=new String();Temp=Temp+"\n";
      n.f0.accept(this, argu);
      Temp=Temp+(String)n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      String t=(String)n.f3.accept(this, argu);
       Temp=Temp+t+"MOVE v0 v0"+"\n";
      n.f4.accept(this, argu);
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "CALL"
    * f1 -> SimpleExp()
    * f2 -> "("
    * f3 -> ( Temp() )*
    * f4 -> ")"
    */
   public R visit(Call n, A argu) {
       R _ret=(R)"";String Temp=new String();calltemp=new Vector<Integer>();
       iscall=true;
      call_cnt++;
      args=0;
      n.f0.accept(this, argu);
      String t=(String)n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      if(maxarg.get(cur_func)==null)
      {
          maxarg.put(cur_func,args);
      }
      else
      {
          maxarg.put(cur_func,Math.max(maxarg.get(cur_func),args));
      }
       
       for(int i=0;i<=9;i++)
       {
           if(cur_func=="MAIN"){
               Temp=Temp+"ASTORE "+"SPILLEDARG "+Integer.toString(i)+" t"+Integer.toString(i)+"\n";
           }
           else
           {
               Temp=Temp+"ASTORE "+"SPILLEDARG "+Integer.toString(i+8+Math.max(0,numarg.get(cur_func)-4))+" t"+Integer.toString(i)+"\n";
           }
       }
       for(int i=0;i<calltemp.size();i++)
       {
           if(i<4){
               String T="MOVE a"+Integer.toString(i)+" ";
           if(Allocs.get(cur_func).containsKey(calltemp.get(i)))
           {
               Temp=Temp+T+Allocs.get(cur_func).get(calltemp.get(i))+"\n";
           }
           else
           {
               T="ALOAD v1 SPILLEDARG "+Integer.toString(Spills.get(cur_func).get(calltemp.get(i)))+"\n"+T+"v1\n";
               Temp=Temp+T;
           }
           }
           else
           {
               String T="PASSARG "+Integer.toString(i-3)+" ";
               if(Allocs.get(cur_func).containsKey(calltemp.get(i)))
               {
                   Temp=Temp+T+Allocs.get(cur_func).get(calltemp.get(i))+"\n";
               }
               else
               {
                   T="ALOAD v1 SPILLEDARG "+Integer.toString(Spills.get(cur_func).get(calltemp.get(i)))+"\n"+T+"v1\n";
                   Temp=Temp+T;
               }
           }
       }
       Temp=t+Temp+"CALL v0"+"\n";
       for(int i=0;i<=9;i++)
       {
           if(cur_func=="MAIN"){
               Temp=Temp+"ALOAD"+" t"+Integer.toString(i)+" SPILLEDARG "+Integer.toString(i)+"\n";
           }
           else
           {
               Temp=Temp+"ALOAD"+" t"+Integer.toString(i)+" SPILLEDARG "+Integer.toString(i+8+Math.max(0,numarg.get(cur_func)-4))+"\n";
           }
       }
       
       iscall=false;
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "HALLOCATE"
    * f1 -> SimpleExp()
    */
   public R visit(HAllocate n, A argu) {
      R _ret=null;
       String Temp=new String();//Temp=Temp+"HALLOCATE ";
      n.f0.accept(this, argu);
      Temp=Temp+(String)n.f1.accept(this, argu);
       Temp=Temp+"MOVE v0 HALLOCATE v0\n";
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> Operator()
    * f1 -> Temp()
    * f2 -> SimpleExp()
    */
   public R visit(BinOp n, A argu) {
      R _ret=null;
       String Temp="";
      String op=(String)n.f0.accept(this, argu)+" ";
      int x=Integer.parseInt((String)n.f1.accept(this, argu));
       //Temp=(String)n.f2.accept(this,argu)+Temp;
       if(Allocs.get(cur_func).containsKey(x))
       {
           Temp=(String)n.f2.accept(this,argu)+Temp;
           //Temp=Temp+Allocs.get(cur_func).get(x)+" ";
           Temp=Temp+"MOVE v0 "+op+" "+Allocs.get(cur_func).get(x)+" v0 \n";
       }
       else
       {
           Temp="ALOAD v1 "+"SPILLEDARG "+Spills.get(cur_func)+"\n"+Temp;
           Temp=(String)n.f2.accept(this,argu)+Temp;
           Temp=Temp+"MOVE v0 "+op+" v1"+" v0 \n";
       }
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "LE"
    *       | "NE"
    *       | "PLUS"
    *       | "MINUS"
    *       | "TIMES"
    *       | "DIV"
    */
   public R visit(Operator n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
       if(n.f0.which==0)
       {
           _ret=(R)"LE ";
       }
       if(n.f0.which==1)
       {
           _ret=(R)"NE ";
       }
       if(n.f0.which==2)
       {
           _ret=(R)"PLUS ";
       }
       if(n.f0.which==3)
       {
           _ret=(R)"MINUS ";
       }
       if(n.f0.which==4)
       {
           _ret=(R)"TIMES ";
       }
       if(n.f0.which==5)
       {
           _ret=(R)"DIV ";
       }
      return _ret;
   }

   /**
    * f0 -> Temp()
    *       | IntegerLiteral()
    *       | Label()
    */
   public R visit(SimpleExp n, A argu) {
      R _ret=(R)"";
       String Temp=new String();
      String kk=(String)n.f0.accept(this, argu);
       if(n.f0.which==0)
       {
           if(iscall)
           {
               args--;
               if(calltemp.size()!=0)
               {
                   calltemp.remove(calltemp.size()-1);
               }
           }
       }
       if(n.f0.which==0){
       if(Allocs.get(cur_func).containsKey(Integer.parseInt(kk)))
       {
           Temp=Temp+"MOVE v0 "+Allocs.get(cur_func).get(Integer.parseInt(kk))+" \n";
       }
       else
       {
           Temp=Temp+"ALOAD v0 SPILLEDARG "+Spills.get(cur_func).get(Integer.parseInt(kk))+" \n";
       }
       }
       else
       {
           Temp=Temp+"MOVE v0 "+kk+"\n";
       }
       _ret=(R)Temp;
      return _ret;
   }

   /**
    * f0 -> "TEMP"
    * f1 -> IntegerLiteral()
    */
   public R visit(Temp n, A argu) {
      R _ret=(R)"";
      n.f0.accept(this, argu);
       _ret=(R)n.f1.accept(this,argu);
       if(iscall)
       {
           calltemp.add(Integer.parseInt((String)_ret));
           args++;
       }
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n, A argu) {
      R _ret=(R)"";
      n.f0.accept(this, argu);
       _ret=(R)n.f0.toString();
      return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public R visit(Label n, A argu) {
      R _ret=(R)"";
      n.f0.accept(this, argu);
       _ret=(R)n.f0.toString();
      return _ret;
   }

}
