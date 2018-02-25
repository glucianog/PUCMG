#include <mbq.h>
#include <PingIRReceiver.h>

void setup()
{
	initBoard();
	float chave = 0;
	float tempototal = 20000;
	float tempoverde = (tempototal*0.40);
	float ciclos = 1000;
	float tempoamarelo = (tempototal*0.10);
	float tempovermelho = (tempototal*0.35);
	float temposeguranca = (tempototal*0.10);
	float tempopiscar = (temposeguranca/5);
	float contador = 0;
	while(true)
	{
		//Semaforo Pedestre
		//Transicao Verde para Vermelho Pedestre
		DigitalWrite(D7, false);
		DigitalWrite(D5, true);
		delay(tempopiscar);
		DigitalWrite(D7, false);
		DigitalWrite(D5, false);
		delay(tempopiscar);
		DigitalWrite(D7, false);
		DigitalWrite(D5, true);
		delay(tempopiscar);
		DigitalWrite(D7, false);
		DigitalWrite(D5, false);
		delay(tempopiscar);
		DigitalWrite(D7, false);
		DigitalWrite(D5, true);
		delay(tempopiscar);
		//Semaforo Carros
		//D12 = Sinal Verde Carros
		DigitalWrite(D8, false);
		DigitalWrite(D10, false);
		DigitalWrite(D12, true);
		contador = 0;
		chave = (false);
		while(((contador<ciclos)&&(!((DigitalRead(D3)||((int)(chave)==(int)((true)))))||(contador<(ciclos/2)))))
		{
			//If controlando se a botoeira foi pressionada
			if(DigitalRead(D3))
			{
				chave = (DigitalRead(D3));
				//Valor se botoeira for pressionada -  executar.
			}
			else
			{
			}
			serial0.println(contador);
			delay((tempoverde/ciclos));
			contador = (contador+1);
		}
		//Tempo controle abotoeira
		//D10 = Sinal Amarelo Carros
		DigitalWrite(D8, false);
		DigitalWrite(D10, true);
		DigitalWrite(D12, false);
		contador = AnalogRead(sensor0);
		delay(tempoamarelo);
		//D8 = Sinal Vermelho Carros
		DigitalWrite(D8, true);
		DigitalWrite(D10, false);
		DigitalWrite(D12, false);
		delay(temposeguranca);
		//Tempo que antecede a transição do vermelho ao verde dos carros.
		//Transicao vermelho para verde pedestre
		DigitalWrite(D7, true);
		DigitalWrite(D5, false);
		delay(tempovermelho);
	}
}

void loop()
{
}
