.text
.globl main
main:
# x -> $s0
# y -> $s1
# A[0] -> $s2
addi $s2,$zero,0x1001
sll  $s2,$s2,16

addi $s0, $zero, 0 
addi $s1, $zero, 0
addi $t0, $zero, 100
if: slt $t1, $t0, $s0
bne $t1, $zero, parte2
sll $t2, $s1, 2 # t2 = 4j
add $t2, $t2, $s2 # t2 = 4j + end base
sw $s0, 0($t2) #A[j] = i
addi $s0, $s0, 2 # i = i + 2
addi $s1, $s1, 1 # j = j + 1
j if 
parte2:
addi $s0, $zero, 1 # i = 1
se: slti $t1, $s0, 100
beq $t1, $zero, fim
sll $t2, $s1, 2 # t2 = 4j
add $t2, $t2, $s2  # t2 = 4j + end base
sw $s0, 0 ($t2)
addi $s0, $s0, 2
addi $s1, $s1, 1
j se
fim: nop
