.text
.globl main
main:

addi $t0,$zero,0x1001
sll  $t0,$t0,16

lw  $s1,0($t0)    # S1 = A
srl $t1,$s1,31    # T1 = 0x00000001 
beq $t1,$zero,fim # If (t1 == 0 )
sub $s1,$zero,$s1 # S1 = |A|
sw  $s1,0($t0)    # A  = |A|

fim: nop
.data
A: .word -100




