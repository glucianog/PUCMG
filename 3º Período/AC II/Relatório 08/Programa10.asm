# x mapeado em $s1

#x1   -> s1
#x2   -> s2
#x3   -> s3
#x4   -> s4
#soma -> s5

.text
.globl teste
teste:
addi $t0,$zero,0x1001 #t0 = 0x1001
sll  $t0,$t0,16       #t0 = 0x10010000	    
lw   $s1,0($t0)       #x1 = 15
lw   $s2,4($t0)       #x2 = 25
lw   $s3,8($t0)       #x3 = 13
lw   $s4,12($t0)      #x4 = 17
nop

add $t1,$s1,$s2      #t1 = x1+x2
add $t1,$t1,$s3      #t2 = t1+x3
add $t1,$t1,$s4      #t3 = t1+x4

sw $t1,16($t0)       # soma = x1+x2+x3+x4




.data
x1: .word 15
x2: .word 25
x3: .word 13
x4: .word 17
soma: .word -1