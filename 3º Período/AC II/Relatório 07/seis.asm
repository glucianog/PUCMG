.text
.globl main
main:

# x -> $16
# y -> $17
# z -> $18

# Maior inteiro  = 7FFFFFFF (16)
#300000 (10) = 493E0(16)
 



addi $t0,$0,0x7FFF      # t0 = 0x7FFF;
sll  $t0,$t0,16         # t0 = 0x7FFF0000;
ori  $16,$t0,0xFFFF     # x   = 0x7FFFFFFF;

addi $t0,$0,4           # t0 = 4;
sll  $t0,$t0,16         # t0 = 0x40000;
ori  $17,$t0,0x93E0     # y  = 0x493E0;

sll  $t1,$17,2          # t1 = 4y;

sub $18,$16,$t1         # z = x - 4y;

