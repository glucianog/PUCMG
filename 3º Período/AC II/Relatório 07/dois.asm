.text
.globl main
main:

# x -> $16
# y -> $17

addi $16,$0,1 # x = 1;
add $t0, $16,$16 # t0 = 2x
add $t0,$t0,$t0 #t0 = 4x;
add $t0,$t0,$16 # t0 = 5x
addi $17,$t0,15 # y = 5x + 15