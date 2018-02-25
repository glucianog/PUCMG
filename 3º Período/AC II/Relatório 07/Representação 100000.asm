# x = 100000
# y = 200000
# z = x+y
# x -> $16 ; y -> $17 ; z -> $18


.text
.globl main
main:

addi $8,$zero,1 # x = 00000001
sll $8,$8,16    # x = 00010000 
ori $16,$8,0x86A0




