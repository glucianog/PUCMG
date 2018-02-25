.text
.globl main
main:

# x -> $s0
# y -> $s1

addi $t0,$zero,0x1001
sll  $t0,$t0,16

lw $s0, 0($t0) # x recebe primeiro valor livre na memoria
sll $t1, $s0, 31
srl $t1, $t1, 31

beq $t1, $zero, par

mult $s0,$s0
mflo $t2 # t2 = x ao quadrado
mult $t2,$s0
mflo $t2 # t2 = x ao cubo
mult $t2,$s0 
mflo $t3 # t3 = x elevado a 4
mult $t3,$s0 
mflo $t3 # t3 = x elevado a 5
sub $t4, $t3, $t2
addi $s1, $t4, 1 
j fim

par:
mult $s0,$s0
mflo $t2 # t2 = x ao quadrado
mult $t2,$s0
mflo $t3 # t3 = x ao cubo
mult $t3,$s0 
mflo $t4 # t4 = x elevado a 4
add $t3,$t3,$t4
sll $t2, $t2, 1 # t2 = 2(x ao quadrado)
sub $s1, $t3, $t2 # armazena no y

fim: sw $s1, 4($t0)

.data
x: .word 2
