#include "FastLED.h"

#define RING_DATA_PIN 5
#define STRIP_LEFT_DATA_PIN 6
#define STRIP_RIGHT_DATA_PIN 7

#define RING_NUM_LEDS 10
#define STRIP_LEFT_NUM_LEDS 20
#define STRIP_RIGHT_NUM_LEDS 20

CRGB ring_leds[RING_NUM_LEDS];
CRGB strip_left_leds[STRIP_LEFT_NUM_LEDS];
CRGB strip_right_leds[STRIP_RIGHT_NUM_LEDS];

void setAllToBlack();
void setPixel(int pixel, int r, int g, int b, CRGB strip);

void setup() {
  Serial.begin(115200);

  FastLED.addLeds<NEOPIXEL, RING_DATA_PIN>(ring_leds, RING_NUM_LEDS).setCorrection( TypicalLEDStrip );
  FastLED.addLeds<NEOPIXEL, STRIP_LEFT_DATA_PIN>(strip_left_leds, STRIP_LEFT_NUM_LEDS).setCorrection( TypicalLEDStrip );
  FastLED.addLeds<NEOPIXEL, STRIP_RIGHT_DATA_PIN>(strip_right_leds, STRIP_RIGHT_NUM_LEDS).setCorrection( TypicalLEDStrip );
  setAllToBlack();
}

unsigned long currentTime;
unsigned long prevTime = 0;
unsigned long interval = 15;
int controlByte;

/*
 * Control Bytes
 *  0. No comm
 *  1. Disabled
 *  2. Standby
 *  3. Auto Intake
 *  4. Flywheel spinup
 *  5. Shoot
 *  6. Scale mode
 */

int i = 0;

void loop() {
  //for (int i = 0; i < STRIP_LEFT_NUM_LEDS + 2; i++) {

  if (Serial.available() > 0) {
    controlByte = Serial.read();
    Serial.println(controlByte);
    if (controlByte == 0) {
      interval = 15;
    }
    else if (controlByte == 1) {
      interval = 15;
    }
    else if (controlByte == 2) {

    }
    else if (controlByte == 3) {

    }
    else if (controlByte == 4) {

    }
    else if (controlByte == 5) {
      interval = 100;
    }
    else if (controlByte == 6) {

    }
  }

  currentTime = millis();

  if ((unsigned long)(millis() - prevTime) >= interval) {
    prevTime = millis();
    if (controlByte == 0) {

    }
    else if (controlByte == 1) {

    }
    else if (controlByte == 2) {

    }
    else if (controlByte == 3) {

    }
    else if (controlByte == 4) {
      patternFlywheelStartup(i);
      i++;
      if (i <= STRIP_LEFT_NUM_LEDS) i = 0;
    }
    else if (controlByte == 5) {
      i++;
      patternShoot(i);
    }
    else if (controlByte == 6) {

    }
  }
  //}
}

void patternFlywheelStartup(int i) {
  setPixel(i - 2, 255, 50, 0);
  setPixel(i - 1, 255, 255, 255);
  setPixel(i, 255, 255, 255);

  setPixel(STRIP_LEFT_NUM_LEDS - i, 255, 50, 0);
  setPixel(STRIP_LEFT_NUM_LEDS - i - 1, 255, 255, 255);
  setPixel(STRIP_LEFT_NUM_LEDS - i - 2, 255, 255, 255);
  FastLED.show();
  // delay 15
}

/////////////////// Patterns

void patternAutotIntake() {

}

void patternShoot(int i) {
  if (i % 2) {
    for (int i = 0; i < STRIP_LEFT_NUM_LEDS; i++) {
      strip_left_leds[i][0] = 255;
      strip_left_leds[i][1] = 50;
      strip_left_leds[i][2] = 0;
    }
  }
  else {
    for (int i = 0; i < STRIP_LEFT_NUM_LEDS; i++) {
      strip_left_leds[i][0] = 255;
      strip_left_leds[i][1] = 255;
      strip_left_leds[i][2] = 255;
    }
  }
  FastLED.show();
}

/////////////////////

void setAllToBlack() {
  for (int i = 0; i < STRIP_LEFT_NUM_LEDS; i++) {
    strip_left_leds[i].setRGB(0, 0, 0);
    strip_right_leds[i].setRGB(0, 0, 0);
  }
  FastLED.show();
}

void setPixel(int pixel, int r, int g, int b) {
  strip_left_leds[pixel].setRGB(r, g, b);
  strip_right_leds[pixel].setRGB(r, g, b);
}

