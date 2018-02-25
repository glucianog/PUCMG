.text
.globl main

main:

# a -> $16
# b -> $17
# c -> $18
# d -> $19
# x -> $20
# y -> $21

addi $16,$0,2 # a = 2
addi $17,$0,3 # b = 3
addi $18,$0,4 # c = 4
addi $19,$0,5 # d = 5
add $t0,$16,$17 # t0 = a + b
add $t1,$18,$19 # t1 = c + d
sub $20,$t0,$t1 # x = t0 - t1


sub $t0,$16,$17  # t0 = a - b;
add $21,$t0,$20 # y = t0 + x;
sub $17,$20,$21 # b = x - y;


