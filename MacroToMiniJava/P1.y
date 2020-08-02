%{
	#include <stdio.h>
	#include <string.h>
    #include<stdlib.h>
	int yyerror (char* h);
	int yylex(void) ;

 struct macro
{
    int arg_sz;
    char *macro_name;
    char *arg_string;
    char *rep_string;
    int is_statement;
};

struct macro Mlist[1000];
int macros=0;

void new_macro(char *name,char *args,char *reps,int ch)
{
    struct macro cur;
    cur.macro_name=malloc(strlen(name)*sizeof(char));
    strcpy(cur.macro_name,name);
    cur.arg_string=malloc(strlen(args)*sizeof(char));
    strcpy(cur.arg_string,args);
    cur.rep_string=malloc(strlen(reps)*sizeof(char)+5);
    strcpy(cur.rep_string,reps);
    cur.is_statement=ch;
    cur.arg_sz=1;
    for(int i=0;i<strlen(cur.arg_string);i++)
    {
        if(cur.arg_string[i]==',')
        {
            cur.arg_sz++;
        }
    }
    Mlist[macros]=cur;
    macros++;
}


int find_macro(char *ident)
{
    char *str1=strdup(ident);
    char *tok=strtok(str1," ");
    for(int i=macros-1;i>=0;i--)
    {
        char *str2=strdup(Mlist[i].macro_name);
        char *tok1=strtok(str2," ");
        if(strcmp(tok,tok1)==0)
        {
            return i;
        }
    }
    return -1;
}

char * convert(char linne[],int i,int ch)
{
    char* retval=(char*)malloc(1000*sizeof(char));
    char* args[30];
    int num=0;
    char *str1=strdup(Mlist[i].arg_string);
    char *tok=strtok(str1,", ");
    while(tok!=NULL)
    {
        args[num]=(char*)malloc(sizeof(tok)+1);
        strcpy(args[num],tok);
        tok=strtok(NULL,", ");
        num++;
    }
    char* str2=strdup(linne);
    char *tok1=strtok(str2,",");
    char* expr[30];num=0;
    while(tok1!=NULL)
    {
        expr[num]=(char*)malloc(sizeof(tok1)+1);
        strcpy(expr[num],tok1);
        tok1=strtok(NULL,",");
        num++;
    }
    
  /* for(int i=0;i<num;i++)
    {
    	printf("%s\n",expr[i]);
    }*/
    
    char* str3=strdup(Mlist[i].rep_string);
    char *tok2=strtok(str3," \n");
    while(tok2!=NULL)
    {
        int c=1;
        char *temp=strdup(tok2);
        for(int j=0;j<num;j++)
        {
            if(strcmp(temp,args[j])==0)
            {
                strcat(retval,expr[j]);
                c=0;
                break;
            }
        }
        tok2=strtok(NULL," \n");
        if(c&&(!(((ch&&tok2==NULL)&&(strcmp(temp,";")==0)))))
        {
            strcat(retval,temp);
            strcat(retval," ");
        }
    }
    
    return retval;
}

%}



%union
{
    char *str;
}

%start Goal
%token<str> Plus
%token<str> Minus
%token<str> LeftP
%token<str> RightP
%token<str> NotEqual
%token<str> Not
%token<str> And
%token<str> Mult
%token<str> Equal
%token<str> LeftCP
%token<str> RightCP
%token<str> LeftSP
%token<str> RightSP
%token<str> Or
%token<str> Less_Than_Equal
%token<str> Greater_Than_Equal
%token<str> Dot
%token<str> Semicolon
%token<str> Question_Mark
%token<str> Divide
%token<str> Comma
%token<str> Class
%token<str> Public
%token<str> Int
%token<str> This
%token<str> If
%token<str> Main
%token<str> Else
%token<str> Boolean
%token<str> Extends
%token<str> Return
%token<str> While
%token<str> String
%token<str> Static
%token<str> Void
%token<str> New
%token<str> Length
%token<str> H
%token<str> Define
%token<str> Dotlength
%token<str> SystemPrint
%token<str> True
%token<str> False
%token<str> identifier
%token<str> integer

%type<str> Identifier
%type<str> Integer
%type<str> Goal
%type<str> Code
%type<str> MainClass
%type<str> TypeDeclarationList
%type<str> TypeDeclaration
%type<str> MethodDeclarationList
%type<str> MethodDeclaration
%type<str> TypeIdentifierListWithSemiColon
%type<str> TypeIdentifierList
%type<str> NonEmptyTypeIdentifierList
%type<str> Type
%type<str> StatementList
%type<str> Statement
%type<str> Expression
%type<str> ExpressionList
%type<str> NonemptyExpressionList
%type<str> PrimaryExpression
%type<str> MacroDefinitionList
%type<str> MacroDefinition
%type<str> MacroDefStatement
%type<str> MacroDefExpression
%type<str> IdentifierList
%type<str> Non_emptyIdentifierList

%%

Goal : Code {$$=malloc((((strlen($1)+10)+10)+5)); strcpy($$,$1); printf("%s\n",$$);
};


Code    :   MacroDefinitionList MainClass TypeDeclarationList {$$=malloc((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)); strcpy($$,$1);  strcat($$,$2); strcat($$,$3);
};


MainClass    :   Class Identifier LeftCP Public Static Void Main LeftP String LeftSP RightSP Identifier RightP LeftCP SystemPrint LeftP Expression RightP Semicolon RightCP RightCP{$$=malloc((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6)+strlen($7)+strlen($8)+strlen($9)+strlen($10)+strlen($11)+strlen($12)+strlen($13)+strlen($14)+strlen($15)+strlen($16)+strlen($17)+strlen($18)+strlen($19)+strlen($20)+strlen($21)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6);  strcat($$,$7);  strcat($$,$8);  strcat($$,$9);  strcat($$,$10);  strcat($$,$11);  strcat($$,$12);  strcat($$,$13);  strcat($$,$14);  strcat($$,$15);  strcat($$,$16);  strcat($$,$17);  strcat($$,$18);  strcat($$,$19);  strcat($$,$20); strcat($$,$21);};





TypeDeclarationList : TypeDeclarationList TypeDeclaration{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2))*sizeof(char)); strcpy($$,$1);  strcat($$,$2); }

| {$$="";};




TypeDeclaration    :  Class Identifier LeftCP  TypeIdentifierListWithSemiColon  MethodDeclarationList RightCP{$$=malloc((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6); }


|    Class Identifier Extends Identifier LeftCP TypeIdentifierListWithSemiColon MethodDeclarationList RightCP{$$=malloc((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6)+strlen($7)+strlen($8)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6);  strcat($$,$7);  strcat($$,$8); };




MethodDeclarationList:  MethodDeclarationList MethodDeclaration{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2))*sizeof(char)); strcpy($$,$1);  strcat($$,$2); }


|{$$="";};





MethodDeclaration    :    Public Type Identifier LeftP TypeIdentifierList RightP LeftCP TypeIdentifierListWithSemiColon StatementList Return Expression Semicolon RightCP{$$=malloc(2+(((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6)+strlen($7)+strlen($8)+strlen($9)+strlen($10)+strlen($11)+strlen($12)+strlen($13)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6);  strcat($$,$7);  strcat($$,$8);  strcat($$,$9);  strcat($$,$10);  strcat($$,$11);  strcat($$,$12);  strcat($$,$13); strcat($$,"\n");};




TypeIdentifierListWithSemiColon : TypeIdentifierListWithSemiColon Type Identifier Semicolon{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4); }

|  TypeIdentifierListWithSemiColon Type Identifier Equal Expression Semicolon{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6); }


|{$$="";};



TypeIdentifierList : NonEmptyTypeIdentifierList{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }

|{$$="";};

NonEmptyTypeIdentifierList : NonEmptyTypeIdentifierList Comma Type Identifier{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4); }


|Type Identifier{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2))*sizeof(char)); strcpy($$,$1);  strcat($$,$2); };





Type    :   Int LeftSP RightSP {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    Boolean{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }
|    Int{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }
|    Identifier{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }


StatementList : StatementList Statement{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2))*sizeof(char)); strcpy($$,$1);  strcat($$,$2); }
| Statement {$$=malloc((((strlen($1)+10)+10)+5)*sizeof(char)); strcpy($$,$1); };

| {$$="";};


Statement    :  LeftCP StatementList RightCP {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    SystemPrint LeftP Expression RightP Semicolon{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);strcat($$,$5); }
|    Identifier Equal Expression Semicolon{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4); }

|    Identifier LeftSP Expression RightSP Equal Expression Semicolon{$$=malloc((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6)+strlen($7)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6);  strcat($$,$7); }
|    If LeftP Expression RightP Statement {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);strcat($$,$5); }

|    If LeftP Expression RightP Statement Else Statement {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6)+strlen($7))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6);  strcat($$,$7); }
|    While LeftP Expression RightP Statement {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5); };

|    Identifier LeftP ExpressionList RightP Semicolon {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);int m_index=find_macro($1);
    if(m_index!=-1)
    {
        free($$);
        $$=NULL;
        $$=malloc(100);
        strcpy($$,convert($3,m_index,1));
        strcat($$,$5);
    }
    else
    {
        yyerror("Hi");
    }
};

ExpressionList : NonemptyExpressionList {$$=malloc((((strlen($1)+10)+10)+5)); strcpy($$,$1); }

| {$$="";};

NonemptyExpressionList : NonemptyExpressionList Comma  Expression {$$=malloc((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)); strcpy($$,$1);  strcat($$,$2);strcat($$,$3); }

| Expression {$$=malloc((((strlen($1)+10)+10)+5)); strcpy($$,$1);};

Expression    :   PrimaryExpression And PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression Or PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression NotEqual PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression Less_Than_Equal PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression Plus PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression Minus PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression Mult PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression Divide PrimaryExpression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }
|    PrimaryExpression LeftSP PrimaryExpression RightSP{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4); }
|    PrimaryExpression Dotlength{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2))*sizeof(char)); strcpy($$,$1);  strcat($$,$2); }
|    PrimaryExpression Dot Identifier LeftP ExpressionList RightP {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5)+strlen($6))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5);  strcat($$,$6); }
|    PrimaryExpression {$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char));strcpy($$,$1);}
|    Identifier LeftP ExpressionList RightP{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);int m_index=find_macro($1);
    if(m_index!=-1)
    {
        free($$);
        $$=NULL;
        $$=malloc(100);
        strcpy($$,convert($3,m_index,0));
    }
    else
    {
        yyerror("Hi");
    }
};




PrimaryExpression    :   Integer{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }
|    True{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }
|    False{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }
|    Identifier{$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }
|    This {$$=malloc(((((strlen($1)+10)+10)+5))*sizeof(char)); strcpy($$,$1); }
|    New Int LeftSP Expression RightSP {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4)+strlen($5))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4);  strcat($$,$5); }
|    New Identifier LeftP RightP {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3)+strlen($4))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3);  strcat($$,$4); }

|    Not Expression{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2))*sizeof(char)); strcpy($$,$1);  strcat($$,$2); }

|    LeftP Expression RightP{$$=malloc(((((strlen($1)+10)+10)+5)+strlen($2)+strlen($3))*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); };







 



MacroDefinitionList : MacroDefinitionList MacroDefinition {$$="";}

|   {$$="";};


MacroDefinition    :   MacroDefExpression {$$="";}

|    MacroDefStatement {$$="";};


MacroDefStatement     :   H Define Identifier LeftP IdentifierList RightP LeftCP StatementList RightCP {$$="";new_macro($3,$5,$8,1);};



MacroDefExpression    :   H Define Identifier LeftP IdentifierList RightP LeftP Expression RightP {$$="";new_macro($3,$5,$8,0);};










IdentifierList : Non_emptyIdentifierList  {$$=malloc((((strlen($1)+10)+10)+5)*sizeof(char)); strcpy($$,$1); }

| {$$="";};

Non_emptyIdentifierList : Non_emptyIdentifierList Comma Identifier {$$=malloc(((((strlen($1)+10)+10)+5)+strlen($3)+2)*sizeof(char)); strcpy($$,$1);  strcat($$,$2);  strcat($$,$3); }

|Identifier {$$=malloc((((strlen($1)+10)+10)+5)*sizeof(char)); strcpy($$,$1); };


Identifier    :  identifier{$$=malloc((((strlen($1)+10)+10)+5)+2); strcpy($$,$1); strcat($$," ");};


Integer    :  integer{$$=malloc((((strlen($1)+10)+10)+5)+2); strcpy($$,$1); strcat($$," ");};

%%



int yyerror(char *s)
{
	printf ("// Failed to parse macrojava code.");
	return 0;
}



int main ()
{
	yyparse();
	return 0;
}
