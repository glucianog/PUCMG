#include <stdio.h>
#include <stdlib.h>

/**
 * ==========================================================
 *            CÓDIGOS TESTADOS NO SERVIDOR PARCODE             
 * ==========================================================
 * Tempo código sequencial    = 1m6.872s 
 * Tempo código paralelo      = 0m17.669s ---- SpeedUp: 03,78
 * Tempo código paralelo GPU  = 0m5.449s  ---- SpeedUp: 12,27
 * Tempo código paralelo CUDA = 0m2.812s  ---- SpeedUp: 23,78
 * ==========================================================
 *      WARPS PARA MELHOR VERSÃO DE PARALELIZÇÃO NA GPU       
 * ==========================================================
 * Event Name                 Min     Max     Avg     Total
 * warps_launched              72      72      72        72
 * ==========================================================
 * Metric Name                     Min       Max       Avg
 * warp_execution_efficiency      86,81%    86,81%    86,81%
 * ==========================================================
 */

__global__ void mm_cuda(double* a, double* b, double* c, int width) {
  int i = blockIdx.x*blockDim.x+threadIdx.x;
  int j = blockIdx.y*blockDim.y+threadIdx.y;

    if (i < width) {
        if (j < width) {
            double sum = 0;
            for (int k = 0; k < width; k++) {
                double x = a[i * width + k];
                double y = b[k * width + j];
                sum += x * y;
            }
            c[i * width + j] = sum;
        }
    }
}

int main()
{
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

  int size = (width * width)*sizeof(double);
  double *d_a, *d_b, *d_c;

  cudaMalloc((void **) &d_a, size);
  cudaMemcpy(d_a, a, size, cudaMemcpyHostToDevice);

  cudaMalloc((void **) &d_b, size);
  cudaMemcpy(d_b, b, size, cudaMemcpyHostToDevice);

  cudaMalloc((void **) &d_c, size);

  int block_size = 32;
  dim3 dimGrid((width-1)/block_size + 1, (width-1)/block_size + 1, 1);
  dim3 dimBlock(block_size,block_size,1);

  mm_cuda<<<dimGrid,dimBlock>>>(d_a, d_b, d_c, width);

  cudaMemcpy(c, d_c, size, cudaMemcpyDeviceToHost);
  
  cudaFree(d_a);
  cudaFree(d_b);
  cudaFree(d_c);

}