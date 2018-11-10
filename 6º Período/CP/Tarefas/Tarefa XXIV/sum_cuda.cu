#include <stdio.h>
#include <stdlib.h>

__global__ void sum_cuda(double* a, double *s, int width) {
  int t = threadIdx.x;
  int b = blockIdx.x*blockDim.x;

 // __shared__ double o[1024];  

  //if(b+t < width)
    //o[t] = a[b+t];

 // __syncthreads();
  
  int i;
  for(i = blockDim.x/2; i > 0; i /= 2) {
    if(t < i && b+t+i < width)
      a[b+t] += a[b+t+i];
    
  	__syncthreads();
  }

  if(t == 0)
    s[blockIdx.x] = a[t+b];  
} 

int main()
{
  int width = 40000000;
  int size = width * sizeof(double);

  int block_size = 1024;
  int num_blocks = (width-1)/block_size+1;
  int s_size = (num_blocks * sizeof(double));  
 
  double *a = (double*) malloc (size);
  double *s = (double*) malloc (s_size);

  for(int i = 0; i < width; i++)
    a[i] = i;

  double *d_a, *d_s;

  // alocação e cópia dos dados
  cudaMalloc((void **) &d_a, size);
  cudaMemcpy(d_a, a, size, cudaMemcpyHostToDevice);

  cudaMalloc((void **) &d_s, s_size);

  // definição do número de blocos e threads
  dim3 dimGrid(num_blocks,1,1);
  dim3 dimBlock(block_size,1,1);

  // chamada do kernel
  sum_cuda<<<dimGrid,dimBlock>>>(d_a, d_s, width);

  // cópia dos resultados para o host
  cudaMemcpy(s, d_s, s_size, cudaMemcpyDeviceToHost);

  // soma das reduções parciais
  for(int i = 1; i < num_blocks; i++) 
    s[0] += s[i];

  printf("\nSum = %f\n",s[0]);
  
  cudaFree(d_a);
  cudaFree(d_s);
}
