# x = 1600000 (=0x186A00)
# y = 80000 (=0x13880)
# z = 400000 (=0x61A80)
.text
.globl main
main:
# x -> $s0
# y -> $s1
# z -> $s2

addi $t0,$zero,0x1001
sll  $t0,$t0,16

lw $s0, 0($t0) # x = 1600000 (=0x186A00)
lw $s1, 4($t0) # y = 80000 (=0x13880)
lw $s2, 8($t0) # z = 400000 (=0x61A80)

div $s0, $s2
mflo $t1

mult $t1, $s1
mflo $t1



.data
x: .word 0x00186A00
y: .word 0x00013880
z: .word 0x00061A80
