

    
    
    
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
 
 
public class Build_Class_Tables<R,A> implements GJVisitor<R,A> {



   //
   // Auto class visitors--probably don't need to be overridden.
   //
   
    public static HashMap<String,String> Function_Map;
    public static HashMap<String,String> Var_Map;
    public static Stack<String> Scope_Stack=new Stack<String>();
    public static Vector<String> Class_Vec;
    public static HashMap<String,String> Par;
      public static HashMap<String,Vector<String>> Class_Var=new HashMap<String,Vector<String>>();
    public static HashMap<String,Vector<String>> Class_Func=new HashMap<String,Vector<String>>();
    public static HashMap<String,Vector<String>> Class_Var_Ordered;
    public static HashMap<String,Vector<String>> Class_Func_Ordered;
    public static String ClassMain=new String();
    String Formal_Param_List="";
    //Utility Functions:
    
    public String Convert_Stack_to_String(Stack<String> V)
    {
        String ret="";
        Iterator iter = V.iterator();
        while(iter.hasNext())
        {
            ret=ret+"/"+iter.next();
        }
        return ret;
    }
   
    public void Construct_Var_Table()
    {
        int cnt=Class_Var.size();
        HashMap<String,String> Vis=new HashMap<String,String>();
        HashMap<String,Vector<String>> Class_Var_Ordered_Temp=new HashMap<String,Vector<String>>();
        while(cnt>0)
        {
        	Iterator iter5=Class_Var.entrySet().iterator();
        	while(iter5.hasNext()){
        		Map.Entry HashMap_element = (Map.Entry)iter5.next();
				String St=(String)HashMap_element.getKey();
            	Vector<String> Val=(Vector<String>)HashMap_element.getValue();
            	if((!(Vis.get(St)==null))&&Vis.get(St).equals("YES"))
            	{
            		continue;
            	}
            	else
            	{
            		if(Par.get(St)==null)
            		{
            			Vector<String> Temp_Vec=new Vector<String>();
            			for(int i=0;i<Class_Var.get(St).size();i++)
            			{
            				Temp_Vec.add(Class_Var.get(St).get(i));
            			}
            			Vector<String> Temp_Vec1=new Vector<String>();
                        //System.out.println(St);
            			for(int i=0;i<Class_Var.get(St).size();i++)
            			{
            				Temp_Vec1.add(Class_Var.get(St).get(i));
                            //System.out.println(Class_Var.get(St).get(i));
            			}
            			Class_Var_Ordered.put(St,Temp_Vec);
            			Class_Var_Ordered_Temp.put(St,Temp_Vec1);
            			Vis.put(St,"YES");
            			cnt--;
            		}
            		else
            		{
            			if(Vis.get(Par.get(St))==null||(!Vis.get(Par.get(St)).equals("YES")))
            			{
            				continue;
            			}
            			else
            			{
            				Vector<String> Temp_Vec_Par1=Class_Var_Ordered_Temp.get(Par.get(St));
            				Vector<String> Temp_Vec_Par=Class_Var_Ordered.get(Par.get(St));
            				Vector<String> Temp_Vec=new Vector<String>();
            				for(int i=0;i<Temp_Vec_Par.size();i++)
            				{
            					Temp_Vec.add(Temp_Vec_Par.get(i));
            				}
                            for(int i=0;i<Temp_Vec_Par1.size();i++)
                            {
                                String Cur=Temp_Vec_Par1.get(i);
                                boolean done=false;
                                for(int j=0;j<Class_Var.get(St).size();j++)
                                {
                                    if(Cur.equals(Class_Var.get(St).get(j)))
                                    {
                                        done=true;
                                        break;
                                    }
                                }
                                if(!done)
                                {
                                    Var_Map.put("/"+St+"/"+Cur,Var_Map.get("/"+Par.get(St)+"/"+Cur));
                                }
                            }
            				Vector<String> Temp_Vec1=Temp_Vec_Par1;
            				for(int i=0;i<Class_Var.get(St).size();i++)
            				{
            					String Cur=Class_Var.get(St).get(i);
            					boolean done=false;
            					for(int j=0;j<Temp_Vec_Par1.size();j++)
            					{
            						if(Cur.equals(Temp_Vec_Par1.get(j)))
            						{
            							done=true;
            							Temp_Vec.set(j,Cur);
            							break;
            						}
            					}
            					if(!done)
            					{
            						Temp_Vec.add(Cur);
            						Temp_Vec1.add(Cur);
            					}
            				}
                            for(int i=0;i<Temp_Vec.size();i++)
                            {
                                //System.out.println(Temp_Vec.get((i)));
                            }
            				Class_Var_Ordered.put(St,Temp_Vec);
            				Class_Var_Ordered_Temp.put(St,Temp_Vec1);
            				Vis.put(St,"YES");
            				cnt--;
            			}
            		}
            	}
            }
        }
        //Print_Table(Class_Var_Ordered);
        }
        public void Construct_Func_Table()
    	{
        int cnt=Class_Func.size();
        HashMap<String,String> Vis=new HashMap<String,String>();
        HashMap<String,Vector<String>> Class_Func_Ordered_Temp=new HashMap<String,Vector<String>>();
        while(cnt>0)
        {
        	Iterator iter5=Class_Func.entrySet().iterator();
        	while(iter5.hasNext()){
        		Map.Entry HashMap_element = (Map.Entry)iter5.next();
				String St=(String)HashMap_element.getKey();
            	Vector<String> Val=(Vector<String>)HashMap_element.getValue();
            	if((!(Vis.get(St)==null))&&Vis.get(St).equals("YES"))
            	{
            		continue;
            	}
            	else
            	{
            		if(Par.get(St)==null)
            		{
            			Vector<String> Temp_Vec=new Vector<String>();
            			for(int i=0;i<Class_Func.get(St).size();i++)
            			{
            				Temp_Vec.add(St+"_"+Class_Func.get(St).get(i));
            			}
            			Vector<String> Temp_Vec1=new Vector<String>();
            			for(int i=0;i<Class_Func.get(St).size();i++)
            			{
            				Temp_Vec1.add(Class_Func.get(St).get(i));
            			}
            			Class_Func_Ordered.put(St,Temp_Vec);
            			Class_Func_Ordered_Temp.put(St,Temp_Vec1);
            			Vis.put(St,"YES");
            			cnt--;
            		}
            		else
            		{
            			if(Vis.get(Par.get(St))==null||(!Vis.get(Par.get(St)).equals("YES")))
            			{
            				continue;
            			}
            			else
            			{
            				Vector<String> Temp_Vec_Par1=Class_Func_Ordered_Temp.get(Par.get(St));
            				Vector<String> Temp_Vec_Par=Class_Func_Ordered.get(Par.get(St));
            				Vector<String> Temp_Vec=new Vector<String>();
            				for(int i=0;i<Temp_Vec_Par.size();i++)
            				{
            					Temp_Vec.add(Temp_Vec_Par.get(i));
            				}
            				Vector<String> Temp_Vec1=Temp_Vec_Par1;
            				for(int i=0;i<Class_Func.get(St).size();i++)
            				{
            					String Cur=Class_Func.get(St).get(i);
            					boolean done=false;
            					for(int j=0;j<Temp_Vec_Par1.size();j++)
            					{
            						if(Cur.equals(Temp_Vec_Par1.get(j)))
            						{
            							done=true;
            							Temp_Vec.set(j,St+"_"+Cur);
            							
            							break;
            						}
            					}
            					if(!done)
            					{
            						Temp_Vec.add(St+"_"+Cur);
            						Temp_Vec1.add(Cur);
            					}
            				}
            				Class_Func_Ordered.put(St,Temp_Vec);
            				Class_Func_Ordered_Temp.put(St,Temp_Vec1);
            				Vis.put(St,"YES");
            				cnt--;
            			}
            		}
            	}
            }
        }
        //Print_Table(Class_Func_Ordered);
    }
   
   public void Print_Table(HashMap<String,Vector<String>> H)
   {
   		Iterator iter4=H.entrySet().iterator();
        while(iter4.hasNext())
        {
            Map.Entry HashMap_element = (Map.Entry)iter4.next();
            String St=(String)HashMap_element.getKey();
            Vector<String> Val=(Vector<String>)HashMap_element.getValue();
            System.out.println(St+":");
            for(int i=0;i<Val.size();i++)
            {
             System.out.println(Val.get(i));
            }
            
        }
   }
   
   public R visit(NodeList n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n, A argu) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this,argu);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n, A argu) {
      if ( n.present() )
         return n.node.accept(this,argu);
      else
         return null;
   }

   public R visit(NodeSequence n, A argu) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this,argu);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n, A argu) { return null; }



   public Build_Class_Tables(HashMap<String,String> Function_Map,HashMap<String,String> Var_Map,Vector<String> Class_Vec,HashMap<String,String> Par,HashMap<String,Vector<String>> h1,HashMap<String,Vector<String>> h2)
   {
   		this.Function_Map=Function_Map;
   		this.Var_Map=Var_Map;
   		this.Class_Vec=Class_Vec;
   		this.Par=Par;
   		this.Class_Var_Ordered=h1;
   		this.Class_Func_Ordered=h2;
   }



   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
    
    
   public R visit(Goal n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
       Construct_Var_Table();
       Construct_Func_Table();
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> "public"
    * f4 -> "static"
    * f5 -> "void"
    * f6 -> "main"
    * f7 -> "("
    * f8 -> "String"
    * f9 -> "["
    * f10 -> "]"
    * f11 -> Identifier()
    * f12 -> ")"
    * f13 -> "{"
    * f14 -> PrintStatement()
    * f15 -> "}"
    * f16 -> "}"
    */
    
    
    
   public R visit(MainClass n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      Scope_Stack.push(n.f1.f0.toString());
       ClassMain=n.f1.f0.toString();
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
      n.f13.accept(this, argu);
       Scope_Stack.push("main");
      n.f14.accept(this, argu);
      n.f15.accept(this, argu);
       Scope_Stack.pop();
      n.f16.accept(this, argu);
       Scope_Stack.pop();
      return _ret;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
    
    
    
   public R visit(TypeDeclaration n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> ( VarDeclaration() )*
    * f4 -> ( MethodDeclaration() )*
    * f5 -> "}"
    */
    
    
    
   public R visit(ClassDeclaration n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String a  =(String)n.f1.accept(this, argu);
       Class_Var.put(a,new Vector<String>());
       Class_Func.put(a,new Vector<String>());
      n.f2.accept(this, argu);
       Scope_Stack.push(n.f1.f0.toString());
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
       Scope_Stack.pop();
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "extends"
    * f3 -> Identifier()
    * f4 -> "{"
    * f5 -> ( VarDeclaration() )*
    * f6 -> ( MethodDeclaration() )*
    * f7 -> "}"
    */
    
    
    
   public R visit(ClassExtendsDeclaration n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      String a=(String)n.f1.accept(this, argu);
       Class_Var.put(a,new Vector<String>());
       Class_Func.put(a,new Vector<String>());
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
       Scope_Stack.push(n.f1.f0.toString());
       
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
       Scope_Stack.pop();
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
    
    
    
   public R visit(VarDeclaration n, A argu) {
      R _ret=null;
      String a=(String)n.f0.accept(this, argu);
      String b=(String)n.f1.accept(this, argu);
      if(Scope_Stack.size()==1){
     	 String Temp=Convert_Stack_to_String(Scope_Stack);
     	 String Cur_Class=Temp.split("/",3)[1];
     	 if(Class_Var.get(Cur_Class)!=null)
     	 {
     	 	Vector<String>Temp_Vec=(Class_Var.get(Cur_Class));
     	 	Temp_Vec.add(b);
     	 	Class_Var.put(Cur_Class,Temp_Vec);
     	 }
    	  else
     	 {
    	  	Vector<String> Temp_Vec=new Vector<String>();
			Temp_Vec.add(b);
    	  	Class_Var.put(Cur_Class,Temp_Vec);
      	}
      }
      n.f2.accept(this, argu);
      return _ret;
      }
   
   /**
    * f0 -> "public"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )?
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> ( VarDeclaration() )*
    * f8 -> ( Statement() )*
    * f9 -> "return"
    * f10 -> Expression()
    * f11 -> ";"
    * f12 -> "}"
    */
    
    
   public R visit(MethodDeclaration n, A argu) {
      R _ret=null;
       String Func_Sign="";
      n.f0.accept(this, argu);
       Func_Sign=Func_Sign+n.f1.accept(this, argu);
       Func_Sign=Func_Sign+",";
      n.f2.accept(this, argu);
      String Func_name=n.f2.f0.toString();
      	String Temp=Convert_Stack_to_String(Scope_Stack);
     	 String Cur_Class=Temp.split("/",3)[1];
     	 if(Class_Func.get(Cur_Class)!=null)
     	 {
     	 	Vector<String> Temp_Vec=(Class_Func.get(Cur_Class));
     	 	Temp_Vec.add(Func_name);
     	 	Class_Func.put(Cur_Class,Temp_Vec);
     	 }
    	  else
     	 {
    	  	Vector<String> Temp_Vec=new Vector<String>();
			Temp_Vec.add(Func_name);
    	  	Class_Func.put(Cur_Class,Temp_Vec);
      	}
      
      
      
      
       Scope_Stack.push(n.f2.f0.toString());
      n.f3.accept(this, argu);
       String b=(String)n.f4.accept(this, argu);
       Func_Sign=Func_Sign+Formal_Param_List;
       Formal_Param_List="";
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      n.f7.accept(this, argu);
      n.f8.accept(this, argu);
      n.f9.accept(this, argu);
      n.f10.accept(this, argu);
      n.f11.accept(this, argu);
      n.f12.accept(this, argu);
       Scope_Stack.pop();
      return _ret;
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public R visit(FormalParameterList n, A argu) {
      R _ret=null;
      String a=(String)n.f0.accept(this, argu);
       String b=(String)n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public R visit(FormalParameter n, A argu) {
      R _ret=null;
      String a=(String)(n.f0.accept(this, argu));
       _ret=(R)(a+",");
      String b=(String)n.f1.accept(this, argu);
       Formal_Param_List=Formal_Param_List+_ret;
      // System.out.println(Convert_Stack_to_String(Scope_Stack)+"/"+b+":"+a+"\n");
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
   public R visit(FormalParameterRest n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
       _ret=(R)((String)n.f1.accept(this, argu));
      return _ret;
   }

   /**
    * f0 -> ArrayType()
    *       | BooleanType()
    *       | IntegerType()
    *       | Identifier()
    */
   public R visit(Type n, A argu) {
      R _ret=null;

      return (R)n.f0.accept(this, argu);
       
   }

   /**
    * f0 -> "int"
    * f1 -> "["
    * f2 -> "]"
    */
   public R visit(ArrayType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
           _ret=(R)"int[]";
      return (R)_ret;
   }

   /**
    * f0 -> "boolean"
    */
   public R visit(BooleanType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
           _ret=(R)"boolean";
      return (R)_ret;
   }

   /**
    * f0 -> "int"
    */
   public R visit(IntegerType n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
           _ret=(R)"int";
      return (R)_ret;
   }

   /**
    * f0 -> Block()
    *       | AssignmentStatement()
    *       | ArrayAssignmentStatement()
    *       | IfStatement()
    *       | WhileStatement()
    *       | PrintStatement()
    */
   public R visit(Statement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
   public R visit(Block n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
   public R visit(AssignmentStatement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Expression()
    * f3 -> "]"
    * f4 -> "="
    * f5 -> Expression()
    * f6 -> ";"
    */
   public R visit(ArrayAssignmentStatement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> IfthenElseStatement()
    *       | IfthenStatement()
    */
   public R visit(IfStatement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public R visit(IfthenStatement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
   public R visit(IfthenElseStatement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      n.f6.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public R visit(WhileStatement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
   public R visit(PrintStatement n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> OrExpression()
    *       | AndExpression()
    *       | CompareExpression()
    *       | neqExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | DivExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | MessageSend()
    *       | PrimaryExpression()
    */
   public R visit(Expression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "&&"
    * f2 -> PrimaryExpression()
    */
   public R visit(AndExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "||"
    * f2 -> PrimaryExpression()
    */
   public R visit(OrExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "<="
    * f2 -> PrimaryExpression()
    */
   public R visit(CompareExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "!="
    * f2 -> PrimaryExpression()
    */
   public R visit(neqExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "+"
    * f2 -> PrimaryExpression()
    */
   public R visit(PlusExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "-"
    * f2 -> PrimaryExpression()
    */
   public R visit(MinusExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "*"
    * f2 -> PrimaryExpression()
    */
   public R visit(TimesExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "/"
    * f2 -> PrimaryExpression()
    */
   public R visit(DivExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "["
    * f2 -> PrimaryExpression()
    * f3 -> "]"
    */
   public R visit(ArrayLookup n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> "length"
    */
   public R visit(ArrayLength n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ExpressionList() )?
    * f5 -> ")"
    */
   public R visit(MessageSend n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      n.f5.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
   public R visit(ExpressionList n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> Expression()
    */
   public R visit(ExpressionRest n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> IntegerLiteral()
    *       | TrueLiteral()
    *       | FalseLiteral()
    *       | Identifier()
    *       | ThisExpression()
    *       | ArrayAllocationExpression()
    *       | AllocationExpression()
    *       | NotExpression()
    *       | BracketExpression()
    */
   public R visit(PrimaryExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "true"
    */
   public R visit(TrueLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "false"
    */
   public R visit(FalseLiteral n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public R visit(Identifier n, A argu) {
      R _ret=null;
      n.f0.accept(this,argu);
      _ret=(R)n.f0.toString();
           //System.out.println(_ret);
      return _ret;
   }

   /**
    * f0 -> "this"
    */
   public R visit(ThisExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Expression()
    * f4 -> "]"
    */
   public R visit(ArrayAllocationExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      n.f4.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
   public R visit(AllocationExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      n.f3.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "!"
    * f1 -> Expression()
    */
   public R visit(NotExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> "("
    * f1 -> Expression()
    * f2 -> ")"
    */
   public R visit(BracketExpression n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      n.f2.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> ( IdentifierRest() )*
    */
   public R visit(IdentifierList n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> Identifier()
    */
   public R visit(IdentifierRest n, A argu) {
      R _ret=null;
      n.f0.accept(this, argu);
      n.f1.accept(this, argu);
      return _ret;
   }

}
