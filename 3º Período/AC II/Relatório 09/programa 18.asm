.text
.globl main
main:
# x -> $s0
# y -> $s1

addi $t0,$zero,0x1001
sll  $t0,$t0,16

lw $s0, 0($t0) # x recebe primeiro valor livre na memoria
beq $s0, $zero, isZero
srl $t1, $s0, 31

bne $t1, $zero, menor

mult $s0, $s0
mflo $t2 # t2 = x ao quadrado
mult $t2, $s0
mflo $t2 # t2 = x ao cubo
addi $s1, $t2, 1
j fim

menor: 
mult $s0, $s0
mflo $t2 # t2 = x ao quadrado
mult $t2, $s0
mflo $t2 # t2 = x ao cubo 
mult $t2, $s0
mflo $t2 # t2 = x elevado a 4
sub  $s1, $t2, 1 
j fim

isZero:
addi $s1, $zero, -1

fim: 
sw $s1, 4($t0)
nop

.data
x: .word 5
