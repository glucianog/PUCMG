.text
.globl main
main:

# i -> $s0
# soma -> $s1
# vetor -> $s2

addi $t0,$zero,0x1001
sll  $s2,$t0,16
addi $s1, $zero, 0
addi $s0, $zero, 99
addi $t2, $zero, -1

do: sll $t0, $s0, 2 # t0 = 4i
add $t0, $t0, $s2   # t0 = 4i + end base
sll $t1, $s0, 1 # t1 = 2i
addi $t1, $t1, 1
add $s1, $s1, $t1
sw $t1, 0($t0)  #vetor[i] = 2i + 1
addi $s0, $s0, -1
bne $s0, $t2, do



fim: nop


