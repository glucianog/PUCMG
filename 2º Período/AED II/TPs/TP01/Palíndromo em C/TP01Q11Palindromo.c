#include <stdio.h>

int length(char str[]){
    int i = 0;
    while(str[i] != '\0'){
        i++;
    }
    return i;
}

int main(){
    char string[1000];
    scanf("%s",string);
    int x = length(string);
    printf("%d",length(string));

    return 0;
}
