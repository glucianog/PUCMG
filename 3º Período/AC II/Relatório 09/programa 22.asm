.text
.globl main
main:
# x -> $s0
# y -> $s1
# k -> $s2

addi $t0,$zero,0x1001
sll  $t0,$t0,16

lw $s0, 0($t0) # x = 1600000 (=0x186A00)
lw $s1, 4($t0) # y = 80000 (=0x13880)

addi $t1, $zero, 0 # t1 = 0
addi $t2, $zero, 0 # t2 = 0

do:
add $t2, $t2, $s0 # t2 = t2 + s0
addi $t1, $t1, 1  # t1 = t1 + 1
bne $t1, $s1, do

addi $s2, $t2, 0 # k = x * y
sw $s2, 8($t0) 


.data
x: .word 3
y: .word 2