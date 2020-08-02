%{
	#include <stdio.h>
	#include "P1.tab.h"
	#include<stdlib.h>
	#include <string.h>
%}

digit [0-9]
integer (0|([1-9]{digit}*))
space "\t"|"\n"|"\r"|"\f"|" "
identifier ([_a-zA-Z][_a-zA-Z0-9]*)
%% 
"//".* {;}
"+" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Plus;}
"-" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Minus;}

")" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return RightP;}
"(" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return LeftP;}
"!=" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return NotEqual;}
"!" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Not;}
"&&" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return And;}
"*" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Mult;}
"=" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Equal;}
"{" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str,"\n");return LeftCP;}
"}" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str,"\n");return RightCP;}
"[" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return LeftSP;}
"]" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return RightSP;}
"||" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Or;}
"<=" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Less_Than_Equal;}
">=" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Greater_Than_Equal;}
";" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str,"\n");return Semicolon;}
"?" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Question_Mark;}
"/" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Divide;}
"," {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Comma;}
"class" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Class;}
"public" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Public;}
"int" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Int;}
"this" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return This;}
"if" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return If;}
"main" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Main;}
"else" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Else;}
"boolean" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Boolean;}
"extends" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Extends;}
"return" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Return;}
"while" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return While;}
"String" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return String;}
"static" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Static;}
"void" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Void;}
"new" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return New;}
"length" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Length;}
"#" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return H;}
"define" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Define;}
".length" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Dotlength;}
"System.out.println" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return SystemPrint;}
"." {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return Dot;}
"true" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return True;}
"false" {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return False;}
{integer} {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return integer;}
{space} {;}
{identifier} {yylval.str=malloc((strlen(yytext)+5)*sizeof(char));strcpy(yylval.str,yytext);strcat(yylval.str," ");return identifier;}

%%

