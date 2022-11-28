#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "arduino.h"

#define COMPILER_VERSION "0.1"


char *progname    = NULL;  ///< Name of the compiler for error messages
char *input_path  = NULL;  ///< The name of the toy source file (NULL if stdin)
char *output_path = NULL;  ///< The name of the output file (NULL if stdout)
FILE *fout;         /* Compiler output file */

// Bison/Yacc objects
extern FILE *yyin;        ///< input stream used by flex (yyin is declared by flex)
extern int yyparse();

static void usage(int full) {
  fprintf(stderr, "Arduino compiler " COMPILER_VERSION "\n");
  if (full) {
    fprintf(stderr, "Usage: %s [options] input-file\n", progname);
    fprintf(stderr, "Options:\n"
                    "   -o f: output in file 'f'\n"
                    "   -v:   print number version\n"
                    "   -h|?: print this help message\n");
  }
  exit(full);
}

static int compile(char *in, char *out) {
  yyin = stdin; fout = stdout;

  if (in) {
    if (!(yyin =fopen(in, "r"))) {
      fprintf(stderr, "%s: cannot open source file %s\n", progname, in);
      exit(1);
    }
    input_path = in;
  }
  if (out) {
    if (!(fout=  fopen(out, "w"))) {
      fprintf(stderr, "%s: cannot open output file %s\n", progname, out);
      exit(1);
    }
    output_path = out;
  }

  return yyparse();
}

int main(int argc, char *argv[]) {
  char *out_file_name = NULL;

  /* Option analysis */
  progname = argv[0];
  for (argc--, argv++;  *argv && argv[0][0]=='-';  argv++, argc--) {
    char *opt;

    if (strcmp(*argv, "--") == 0) {
      /* special case '--' → end of options */
      argc--, argv++;
      break;
    }

    for (opt = &argv[0][1]; *opt; opt++)
      switch(*opt) {
        case 'o': if ((out_file_name = argv[1]) == NULL) {
                    fprintf(stderr, "output file name missing\n");
                    exit(1);
                  }
                  argv++; argc--;
                  break;
        case 'v': usage(0); break;
        case 'h':
        case '?': usage(1); break;
        default:  fprintf(stderr, "bad option %c\n", *opt);
      }
  }

  /* Parameters */
  if (argc > 1) usage(1);

  return compile(*argv, out_file_name);
}