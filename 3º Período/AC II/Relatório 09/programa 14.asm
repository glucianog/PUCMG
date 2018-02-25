.text
.globl main
main:

# flag -> $s0
# num -> $s1

addi $t0,$zero,0x1001
sll  $t0,$t0,16
lw  $s1,0($t0)    # S1 = A

slti $t1, $s1, 30
bne $t1, $zero, isNot
addi $t2, $zero, 50 # t2 = 100
slt $t1, $t2, $s1
beq $t1, $zero, is

isNot: 
addi $s0, $zero, 0 # flag = 0
j fim
is:
addi $s0, $zero, 1 # flag = 1

sw $s0, 4($t0)
fim: nop

.data
A: .word 50


