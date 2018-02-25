.text
.globl main
main:

# x -> $16
# y -> $17
# z -> $18

addi $16,$0,3 # x = 3;
addi $17,$0,4 # y = 4;

sll $t0,$16,4   # t0 = 16x;
sub $t0,$t0,$16 # t0 = 15x;

sll $t1,$17,6   # t1 = 64y;
add $t1,$t1,$17 # t1 = 65y;
add $t1,$t1,$17 # t1 = 66y;
add $t1,$t1,$17 # t1 = 67y;


add $t2, $t0,$t1 # t2 = 15x + 67y
sll $18,$t2,2 # z = 4 t2
