# i -> $s0
# j -> $s1
# vet[0] -> $s2

.text
.globl main
main:

addi $t0,$zero,0x1001
sll  $s2,$t0,16

addi $s0,$zero,100 #i =  100
addi $s1,$zero,0  #j = 0


while1: 
beq $s0, $zero, fim # if ( i == 0 )? go to fim

while2:
slt $t4, $s1, $s0 # if ( j < i )? t4 = 1 : t4 = 0
beq $t4, $zero, parte3

sll $t0, $s1, 2 # t0 = 4j
add $t0, $t0, $s2 # t0 = 4j + end base
lw $t1, 0($t0) #t1 = vet[j]
lw $t2, -4($t0) #t2 = vet[j - 1]
slt $t3, $t1, $t2 # if (vet[j-1] > vet[j])? t3 = 1 : t3 =0;
beq $t3, $zero, parte2
sw $t2,  0($t0) # vet[j] = vet [j-1];
sw $t1, -4($t0) # vet[j-1] = aux;

parte2: addi $s1, $s1, 1
addi $1,$s1,1 # j = j + 1
j while2

parte3: addi $s0,$s0,-1 # i = i- 1
addi $s1,$zero,1
j while1

fim: nop



.data
A: .word 100
y: .word 99
z: .word 98
t: .word 97
l: .word 96
A1: .word 95
y1: .word 94
z1: .word 93
t1: .word 92
l1: .word 91
A2: .word 90
y2: .word 89
z2: .word 88
t2: .word 87
l2: .word 86
A3: .word 85
y3: .word 84
z3: .word 83
t3: .word 82
l3: .word 81
A4: .word 80
y4: .word 79
z4: .word 78
t4: .word 77
l4: .word 76
A5: .word 75
y5: .word 74
z5: .word 73
t5: .word 72
l5: .word 71
A6: .word 70
y6: .word 69
z6: .word 68
t6: .word 67
l6: .word 66
A7: .word 65
y7: .word 64
z7: .word 63
t7: .word 62
l7: .word 61
A8: .word 60
y8: .word 59
z8: .word 58
t8: .word 57
l8: .word 56
A9: .word 55
y9: .word 54
z9: .word 53
t9: .word 52
l9: .word 51
A10: .word 50
y10: .word 49
z10: .word 48
t10: .word 47
l10: .word 46
A11: .word 45
y11: .word 44
z11: .word 43
t11: .word 42
l11: .word 41
A12: .word 40
y12: .word 39
z12: .word 38
t12: .word 37
l12: .word 36
A13: .word 35
y13: .word 34
z13: .word 33
t13: .word 32
l13: .word 31
A14: .word 30
y14: .word 29
z14: .word 28
t14: .word 27
l14: .word 26
A15: .word 25
y15: .word 24
z15: .word 23
t15: .word 22
l15: .word 21
A16: .word 20
y16: .word 19
z16: .word 18
t16: .word 17
l16: .word 16
A17: .word 15
y17: .word 14
z17: .word 13
t17: .word 12
l17: .word 11
A18: .word 10
y18: .word 9
z18: .word 8
t18: .word 7
l18: .word 6
A19: .word 5
y19: .word 4
z19: .word 3
t19: .word 2
l19: .word 1