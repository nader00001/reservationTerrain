#include <WiFi.h>
#include <HTTPClient.h>

const char* ssid = "123";
const char* password = "133";

const char* serverUrl = "http://localhost:8080/api/terrains/updateSensorData/1"; 

#define PIR_TERRAIN_ENTREE 2
#define PIR_TERRAIN_SORTIE 3
#define PIR_SALLE_ENTREE 4
#define PIR_SALLE_SORTIE 5
#define SOIL_MOISTURE_PIN A0

int terrain_capacity = 20;
int gym_capacity = 15;
int terrain_count = 0;
int gym_count = 0;

void setup() {
  Serial.begin(115200);

  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connexion au Wi-Fi...");
  }
  Serial.println("Connecté au Wi-Fi");
  
  pinMode(PIR_TERRAIN_ENTREE, INPUT);
  pinMode(PIR_TERRAIN_SORTIE, INPUT);
  pinMode(PIR_SALLE_ENTREE, INPUT);
  pinMode(PIR_SALLE_SORTIE, INPUT);
}

void loop() {
  if (digitalRead(PIR_TERRAIN_ENTREE) == HIGH) {
    if (terrain_count < terrain_capacity) terrain_count++;
    delay(500);
  }
  if (digitalRead(PIR_TERRAIN_SORTIE) == HIGH) {
    if (terrain_count > 0) terrain_count--;
    delay(500);
  }

  if (digitalRead(PIR_SALLE_ENTREE) == HIGH) {
    if (gym_count < gym_capacity) gym_count++;
    delay(500);
  }
  if (digitalRead(PIR_SALLE_SORTIE) == HIGH) {
    if (gym_count > 0) gym_count--;
    delay(500);
  }

  int soil_value = analogRead(SOIL_MOISTURE_PIN);
  int soil_percentage = map(soil_value, 0, 1023, 0, 100);

  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;
    http.begin(serverUrl);

    String jsonData = "{\"terrain_count\": " + String(terrain_count) +
                      ", \"gym_count\": " + String(gym_count) +
                      ", \"soil_moisture\": " + String(soil_percentage) + "}";

    http.addHeader("Content-Type", "application/json");

    int httpResponseCode = http.POST(jsonData);
    if (httpResponseCode > 0) {
      Serial.println("Données envoyées avec succès : " + jsonData);
    } else {
      Serial.println("Erreur lors de l'envoi : " + String(httpResponseCode));
    }
    http.end();
  } else {
    Serial.println("Wi-Fi déconnecté");
  }

  delay(5000); 
}