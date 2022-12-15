package dsl.kisled.demo;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import dsl.kisled.demo.psi.KisledTypes;
import com.intellij.psi.TokenType;

%%

%class KisledLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

END_OF_LINE_COMMENT=("//")[^\r\n]*

%%

    /* ==== Keywords ==== */
"sensor"                { yybegin(YYINITIAL); return KisledTypes.KSENSOR; }
"actuator"              { yybegin(YYINITIAL); return KisledTypes.KACTUATOR; }
"HIGH"                  { yybegin(YYINITIAL); return KisledTypes.KHIGH; }
"LOW"                   { yybegin(YYINITIAL); return KisledTypes.KLOW; }
"ON"                    { yybegin(YYINITIAL); return KisledTypes.KON; }
"LONG"                  { yybegin(YYINITIAL); return KisledTypes.KLONG; }
"SHORT"                 { yybegin(YYINITIAL); return KisledTypes.KSHORT; }
"OFF"                   { yybegin(YYINITIAL); return KisledTypes.KOFF; }
"=>"                    { yybegin(YYINITIAL); return KisledTypes.RIGHT; }
"<="                    { yybegin(YYINITIAL); return KisledTypes.LEFT; }
"->"                    { yybegin(YYINITIAL); return KisledTypes.INITSTATE; }
"AND"                   { yybegin(YYINITIAL); return KisledTypes.KAND; }
"x"                     { yybegin(YYINITIAL); return KisledTypes.KTIMES; }
":"                     { yybegin(YYINITIAL); return KisledTypes.ASSIGN; }
"{"                     { yybegin(YYINITIAL); return KisledTypes.BLOCK_START; }
"}"                     { yybegin(YYINITIAL); return KisledTypes.BLOCK_END; }


    /* ==== port numbers (as strings) and identifiers */
[1-9]|(1[012])          { yybegin(YYINITIAL); return KisledTypes.PORT; }
[a-zA-Z][a-zA-Z0-9_]+   { yybegin(YYINITIAL); return KisledTypes.NAME; }

    /* ==== Spaces and comments ==== */
[ \t\r\n]+              { }
<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return KisledTypes.COMMENTS; }
