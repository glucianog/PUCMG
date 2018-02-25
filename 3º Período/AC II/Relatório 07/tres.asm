.text
.globl main
main:

# x -> $16
# y -> $17
# z -> $18

addi $16,$0,3 # x = 3;
addi $17,$0,4 # y = 4;

add $t0,$16,$16 # t0 = 2x;
add $t0,$t0,$t0 # t0 = 4x;
add $t0,$t0,$t0 # t0 = 8x;
add $t0,$t0,$t0 # t0 = 16x;
sub $t0,$t0,$16 # t0 = 15x;

add $t1,$17,$17 # t1 = 2y;
add $t1,$t1,$t1 # t1 = 4y;
add $t1,$t1,$t1 # t1 = 8y;
add $t1,$t1,$t1 # t1 = 16y;
add $t1,$t1,$t1 # t1 = 32y;
add $t1,$t1,$t1 # t1 = 64y;
add $t1,$t1,$17 # t1 = 65y;
add $t1,$t1,$17 # t1 = 66y;
add $t1,$t1,$17 # t1 = 67y;

add $t2, $t0,$t1 # t2 = 15x + 67y
add $t2,$t2,$t2 # t2 = 2t2
add $18,$t2,$t2 # z = 4t2






