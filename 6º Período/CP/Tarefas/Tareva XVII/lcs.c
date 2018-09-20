/* Dynamic Programming solution to find length of the 
   longest common substring 
   Adapted from http://www.geeksforgeeks.org/longest-common-substring/
*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <omp.h>


/**
 * 
 * Tempo seqeencial: 3,785s
 * Tempo paralelo com 2 threads: 1.973s
 * SpeedUp = 1,91
 */ 

// Read input files
char* readFile(char* filename, int* size)
{
   char* buffer = NULL;
   *size = 0;

   /* Open your_file in read-only mode */
   FILE *fp = fopen(filename, "r");

   /* Get the buffer size */
   fseek(fp, 0, SEEK_END); /* Go to end of file */
   *size = ftell(fp); /* How many bytes did we pass ? */

   /* Set position of stream to the beginning */
   rewind(fp);

   /* Allocate the buffer (no need to initialize it with calloc) */
   buffer = malloc((*size + 1) * sizeof(*buffer)); /* size + 1 byte for the \0 */

   /* Read the file into the buffer */
   int err = fread(buffer, *size, 1, fp); /* Read 1 chunk of size bytes from fp into buffer */

   /* NULL-terminate the buffer */
   buffer[*size] = '\0';

   /* Print it ! */
   //   printf("%s\n", buffer);

   return(buffer);
}   

// A utility function to find maximum of two integers
int max(int a, int b)
{   return (a > b)? a : b; }

/* Returns length of longest common substring of X[0..m-1] 
   and Y[0..n-1] */
int LCSubStr(char *x, char *y, int m, int n)
{
  // Create a table to store lengths of longest common suffixes of
  // substrings.   Notethat LCSuff[i][j] contains length of longest
  // common suffix of X[0..i-1] and Y[0..j-1]. The first row and
  // first column entries have no logical meaning, they are used only
  // for simplicity of program
  int **LCSuff = (int**) malloc((m+1) * sizeof(int*));
  for(int i =0; i < m+1; i++)
    LCSuff[i] = (int*) malloc((n+1) * sizeof(int));

  int result = 0;  // To store length of the longest common substring

  /* Following steps build LCSuff[m+1][n+1] in bottom up fashion. */
  for (int i=0; i<=m; i++) {    
    #pragma omp parallel for schedule(guided, 1000) reduction(max:result)
    for (int j=0; j<=n; j++) {
      if (i == 0 || j == 0)
        LCSuff[i][j] = 0;
      else if (x[i-1] == y[j-1]) {
        LCSuff[i][j] = LCSuff[i-1][j-1] + 1;
        result = max(result, LCSuff[i][j]);
      }
      else LCSuff[i][j] = 0;
    }
  }
  return result;
}

/* Driver program to test above function */
int main()
{
  int m, n;
  char* x = readFile("seqA.txt",&m);
  char* y = readFile("seqB.txt",&n);

  printf("\nLength of Longest Common Substring is %d\n",LCSubStr(x, y, m, n));
  return 0;
}
