
// semaforo pedestre
int led2 = 2;
int led3 = 3;
//semaforo vertical
int led4 = 4;
int led5 = 5;
int led6 = 6;
// semaforo horizontal
int led7 = 7;
int led8 = 8;
int led9 = 9;
char entrada1 = '0';

void setup() {
	Serial.begin(9600);     
	pinMode(led2,OUTPUT);
	pinMode(led3,OUTPUT);
	pinMode(led4,OUTPUT);
	pinMode(led5,OUTPUT);
	pinMode(led6,OUTPUT);
	pinMode(led7,OUTPUT);
	pinMode(led8,OUTPUT);
	pinMode(led9,OUTPUT);
}

void loop() {
	
	if (Serial.available() > 0) {		
		entrada1 = Serial.read();	
		start(entrada1);
	}
}

void start ( char entrada ) {
	
	digitalWrite(led2,LOW);
	digitalWrite(led3,LOW);
	digitalWrite(led4,LOW);
	digitalWrite(led5,LOW);
	digitalWrite(led6,LOW);
	digitalWrite(led7,LOW);
	digitalWrite(led8,LOW);
	digitalWrite(led9,LOW);
	if (entrada1 == 'a')
	{
		// pedestre verde, horizontal vermelho e vertical verde
		// 2, 6 e 7 ligados 
		digitalWrite(led2,HIGH);
		digitalWrite(led3,LOW);
		digitalWrite(led4,LOW);
		digitalWrite(led5,LOW);
		digitalWrite(led6,HIGH);
		digitalWrite(led7,HIGH);
		digitalWrite(led8,LOW);
		digitalWrite(led9,LOW);
	}  
	if (entrada1 == 'b')
	{
		// pedestre verde, horizontal vermelho e vertical amarelo
		// 2, 6 e 8 ligados
		digitalWrite(led2,HIGH);
		digitalWrite(led3,LOW);
		digitalWrite(led4,LOW);    
		digitalWrite(led5,LOW);
		digitalWrite(led6,HIGH);
		digitalWrite(led7,LOW);
		digitalWrite(led8,HIGH);
		digitalWrite(led9,LOW);   
	}
	if (entrada1 == 'c')
	{
		// pedestre vermelho, horizontal verde e vertical vermelho
		// 3, 4 e 9 ligados         
		   
    digitalWrite(led2,LOW);
    digitalWrite(led3,HIGH);
		digitalWrite(led4,LOW);
		digitalWrite(led5,LOW);
		digitalWrite(led6,HIGH);
		digitalWrite(led7,LOW);
		digitalWrite(led8,HIGH);
		digitalWrite(led9,LOW); 
    delay(500);    
    digitalWrite(led3,LOW);
    delay(500);
    digitalWrite(led3,HIGH);
    delay(500);    
    digitalWrite(led3,LOW);
    delay(500);
    digitalWrite(led4,HIGH);
    digitalWrite(led6,LOW);
    digitalWrite(led3,HIGH);
    digitalWrite(led8,LOW);
    digitalWrite(led9,HIGH);  
  
    
    
	}
	if (entrada1 == 'd')
	{
		// pedestre vermelho, horizontal amarelo e vertical vermelho
		// 3, 5 e 9 ligados
		digitalWrite(led2,LOW);
		digitalWrite(led3,HIGH);
		digitalWrite(led4,LOW);   
		digitalWrite(led5,HIGH);
		digitalWrite(led6,LOW);
		digitalWrite(led7,LOW);
		digitalWrite(led8,LOW);
		digitalWrite(led9,HIGH);
	}
	
}