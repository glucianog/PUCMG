# y = x-y+300000
#x   -> s1
#z   -> s2
#y   -> s3

#300000 -> 493E0


.text
.globl teste
teste:
addi $t0,$zero,0x1001  #t0 = 0x1001
sll  $t0,$t0,16        #t0 = 0x10010000	    
lw   $s1,0($t0)        #x  = 100000
lw   $s2,4($t0)        #z  = 200000
nop

addi $t1,$zero,0x493E  # t1 = 0x493E
sll  $t1,$t1,4        # t1 = 0x493E0

sub $t2,$s1,$s2        # t2 = x-y
add $s3,$t2,$t1        # y  = x-y+300000

sw $s3,8($t0)          # y  = x-y+300000

.data
x: .word 100000
z: .word 200000
y: .word 0
