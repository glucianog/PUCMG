#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

/**
 * ==========================================================
 * Códigos testados no PARCODE
 * Tempo código sequencial   = 1m6.872s 
 * Tempo código paralelo     = 0m17.669s ---- SpeedUp: 3,78
 * Tempo código paralelo GPU = 0m5.449s  ---- SpeedUp: 12,27
 * ==========================================================
 * Event Name               Min     Max     Avg     Total
 * warps_launched            72      72      72        72
 * ==========================================================
 * Metric Name                     Min      Max     Avg
 * warp_execution_efficiency      86,81%   86,81%  86,81%
 * ==========================================================
 */

void mm(double* a, double* b, double* c, int width) {  
    int size = width * width;
    #pragma omp target map(to:a[:size]) map(to:b[:size]) map(tofrom:c[:size])        
    #pragma omp teams distribute parallel for simd 
    for (int i = 0; i < width; i++) {  
        for (int j = 0; j < width; j++) {
            double   sum = 0;
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