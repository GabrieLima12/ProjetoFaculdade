int pino_trigger = 7;
int pino_echo = 6;

float cm;
float cmAnterior;
 
void setup()
{
  Serial.begin(9600);
  pinMode(pino_echo, INPUT);
  pinMode(pino_trigger, OUTPUT);

  cmAnterior = -1.0f;
}
 
void loop()
{
  cm = distancia();
  
  float diferenca = cm - cmAnterior;
  if (diferenca > abs(0.0001) && cm < 70 && cmAnterior != -1.0f)
  {
    Serial.print(true);
    delay(1000);
  }
  cmAnterior = cm;
}

float distancia()
{
  digitalWrite(pino_trigger, LOW);
  delayMicroseconds(5);
  digitalWrite(pino_trigger, HIGH);
  delayMicroseconds(10);
  digitalWrite(pino_trigger, LOW);
  
  float dist = pulseIn(pino_echo, HIGH);
  dist = dist / 58;
  return dist;
}