.text
.globl main
main:
# x -> $s0
# y -> $s1
# A[0] -> $s2
addi $s2,$zero,0x1001
sll  $s2,$s2,16

addi $s0, $zero, 1 # i = 1
addi $s1, $zero, 0 # j = 0
if: slti $t0, $s0, 100
beq $t0, $zero, parte2
sll $t1, $s1, 2 # t1 = 4j
add $t1, $t1, $s2 # t1 = 4j + end base
sw $s0, 0($t1) # A[j] = i
addi $s0, $s0, 2 # i = i + 2
addi $s1, $s1, 1 # j = j + 1
j if
parte2: addi $s1, $zero, 0 # j = 0
addi $t0, $zero, 0 # t0 = 0
se: slti $t1, $s1, 50 
beq $t1, $zero, fim
sll $t2,$s1, 2 # t2 = 4
add $t2, $t2, $s2 # t2 = 4j + end base
lw $t3, 0($t2) #t3 = A[j]
add $t0, $t0, $t3 # t0 = t0 + t3
addi $s1, $s1, 1
j se
fim : sw $t0, 4($t2)

