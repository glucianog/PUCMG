.text
.globl main
main:

# x -> $16

ori $8, $0, 0x01 #inicio do programa

sll $t1,$8,1     # 0x2 0010
or  $t1,$t1,$8   # 0x3 0011 
sll $t2,$t1,2    # 0xC 1100
or  $t1,$t1,$t2  # 0xF 1111

sll $t2,$t1,4    # 0xF0 1111 0000
or  $t1,$t2,$t1  # 0xFF 1111 1111  

sll $t2,$t1,8    # 0xFF00 1111 1111 0000 0000 
or  $t1,$t2,$t1  # 0xFFFF 1111 1111 1111 1111

sll $t2,$t1,16   #0xFFFF0000 1111 1111 1111 1111 0000 0000 0000 0000
or  $8,$t2,$t1  #0xFFFFFFFF 1111 1111 1111 1111 1111 1111 1111 1111 

