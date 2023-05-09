int pino_trigger = 7;
int pino_echo = 4;

float cm;
 
void setup()
{
  Serial.begin(9600);
  pinMode(pino_echo, INPUT);
  pinMode(pino_trigger, OUTPUT);
}
 
void loop()
{
  cm = distancia();
  
  if (cm < 50.0f)
  {
    Serial.print(true);
    // Serial.println(cm);
    delay(1000);
  }
  
}

float distancia()
{
  digitalWrite(pino_trigger, LOW);
  delayMicroseconds(5);
  digitalWrite(pino_trigger, HIGH);
  delayMicroseconds(10);
  digitalWrite(pino_trigger, LOW);

  float dist = pulseIn(pino_echo, HIGH);
  //Conversao para cm
  dist = dist / 58;
  return dist;
}