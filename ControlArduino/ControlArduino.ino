const int buttonPin1 = 2;  // Pin del primer botón
const int buttonPin2 = 3;  // Pin del segundo botón
const int buttonPin3 = 4;  // Pin del tercer botón
const int buttonPin4 = 5;  // Pin del cuarto botón



void setup() {
  Serial.begin(9600);                 // Inicializar el puerto serie
  pinMode(buttonPin1, INPUT_PULLUP);  // Configurar el primer botón como entrada con resistencia pull-up
  pinMode(buttonPin2, INPUT_PULLUP);  // Configurar el segundo botón como entrada con resistencia pull-up
  pinMode(buttonPin3, INPUT_PULLUP);  // Configurar el primer botón como entrada con resistencia pull-up
  pinMode(buttonPin4, INPUT_PULLUP);
}

void loop() {
  if (digitalRead(buttonPin1) == HIGH) {  // Si el primer botón se presiona
    Serial.println("1");                  // Escribir un punto en el puerto serie
    delay(250);                           // Esperar 250 ms para evitar rebotes
  }

  if (digitalRead(buttonPin2) == HIGH) {  // Si el segundo botón se presiona
    Serial.println("2");                  // Escribir una barra baja en el puerto serie
    delay(250);                           // Esperar 250 ms para evitar rebotes
  }

  if (digitalRead(buttonPin3) == HIGH) {  // Si el segundo botón se presiona
    Serial.println("3");                  // Escribir una barra baja en el puerto serie
    delay(250);                           // Esperar 250 ms para evitar rebotes
  }

  if (digitalRead(buttonPin4) == HIGH) {  // Si el segundo botón se presiona
    Serial.println("4");                  // Escribir una barra baja en el puerto serie
    delay(250);                           // Esperar 250 ms para evitar rebotes
  }

}
