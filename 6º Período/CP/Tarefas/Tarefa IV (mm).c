#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

/**
 * Códigos testados em máquina pessoal com 4 núcleos
 * Tempo código sequencial = 1m57:00
 * Tempo código paralelo   = 0m26:49
 * 
 */


/**
 * Uso do schedule não defie uma ordem preditiva às treads. É mais efetivo 
 * com uso da diretiva ORDERED, mas consegue ser melhor que a Collapse que
 * realiza o threading em multiplas iterações aninhadas?
 */

void mm(double* a, double* b, double* c, int width) {
  
  #pragma omp parallel for collapse(2)
  for (int i = 0; i < width; i++) {        
    for (int j = 0; j < width; j++) {
      double   sum = 0;
      #pragma omp simd
      for (int k = 0; k < width; k++) {
        double x = a[i * width + k];
        double y = b[k * width + j];
        sum += x * y;
      }
      c[i * width + j] = sum;
    } 
  }
    
}

int main() {
  int width = 2000;
  double *a = (double*) malloc (width * width * sizeof(double));
  double *b = (double*) malloc (width * width * sizeof(double));
  double *c = (double*) malloc (width * width * sizeof(double));

  //#pragma omp parallel for
  for(int i = 0; i < width; i++) {
    for(int j = 0; j < width; j++) {
      a[i*width+j] = i;
      b[i*width+j] = j;
      c[i*width+j] = 0;
    }
  }

  mm(a,b,c,width);

  //  for(int i = 0; i < width; i++) {
  //  for(int j = 0; j < width; j++) {
  //    printf("\n c[%d][%d] = %f",i,j,c[i*width+j]);
  //  }
  // }
}