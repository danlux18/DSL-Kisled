%{
#include "syntax.h"
%}

%x  COMMENT
%option yylineno

%%

    /* ==== Keywords ==== */
"sensor"                return KSENSOR;
"actuator"              return KACTUATOR;
"HIGH"                  return KHIGH;
"LOW"                   return KLOW;
"CONTINUE"              return KCONTINUE;
"LONG"                  return KLONG;
"SHORT"                 return KSHORT;
"SILENT"                return KSILENT;
"=>"                    return RIGHT;
"<="                    return LEFT;
"->"                    return INITSTATE;


    /* ==== port numbers (as strings) and identifiers */
[1-9]|(1[012])          { yylval.value = atoi(yytext); return PORT_NUMBER; }
[a-zA-Z][a-zA-Z0-9_]+   { yylval.name  = strdup(yytext); return IDENT; }

    /* ==== Spaces and comments ==== */
[ \t\r\n]+              { }
"/*"                    { BEGIN COMMENT; }
<COMMENT>(.|\n)         ;
<COMMENT>"*/"           { BEGIN 0; }
"//".*          ;       /* mono line comment */

    /* ==== Default ==== */
.                       return *yytext;

%%
int yywrap(void) {
    return 1;
}