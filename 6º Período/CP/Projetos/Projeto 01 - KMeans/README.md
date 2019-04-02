# Compilação #

O código deverá ser compilado seguindos as diretivas básicas de compilação paralela, sendo elas:
  ```
  g++ kmeans.cpp -o kmeans -fopenmp
  ```
Já para a execução, basta executar:
  ```  
    ./kmeans
  ```   
   
## Sobre o K-means ##

O KMeans é um algoritmo que é utilizado para calcular centroides de alguns clusters. Uma das aplicações dele é a verificação de zonas de interesse, por exemplo polos de furtos, que é adcionado um novo 'ponto' (incidente), a área onde ocorreu o crime (cluster). Junto a isso, o algoritmo realiza recalculos de distância entre cluters, aprimorando a fronteira e a torna mais precisa em relação à zona de interesse.
