#include "FastLED.h"

#define RING_DATA_PIN 5
#define STRIP_LEFT_DATA_PIN 6
#define STRIP_RIGHT_DATA_PIN 7

#define RING_NUM_LEDS 10
#define STRIP_LEFT_NUM_LEDS 19
#define STRIP_RIGHT_NUM_LEDS 19

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

unsigned long currentTime = 0;
unsigned long prevTime = 0;
unsigned long interval = 15;
byte controlByte;

/*
 * Control Bytes
 *  0. No comm
 *  1. Disabled
 *  2. Standby
 *  3. Auto Intake
 *  4. Has ball
 *  5. Flywheel spinup
 *  6. Shoot
 *  7. Scale mode
 */

long i = 0;

void loop() {
  if (Serial.available() > 0) {
    controlByte = Serial.read() - '0';
    Serial.println(controlByte);
    if (controlByte == 0) {
      interval = 15;
    }
    else if (controlByte == 1) {
      interval = 10;
    }
    else if (controlByte == 2) {
      i = 0;
      interval = 60;
    }
    else if (controlByte == 3) {
      i = 0;
      interval = 20;
    }
    else if (controlByte == 4) {
      i = 0;
      interval = 60;
    }
    else if (controlByte == 5) {
      i = 0;
      interval = 15;
    }
    else if (controlByte == 6) {
      i = 0;
      interval = 100;
    }
    else if (controlByte == 7) {
      i = 0;
      interval = 100;
    }
  }

  currentTime = millis();

  if ((unsigned long)(millis() - prevTime) >= interval) {
    prevTime = millis();
    if (controlByte == 0) {

    }
    else if (controlByte == 1) {
      patternDisabled(i);
      i = i + 1;
      if (i == 255) {
        i = 0;
      }
    }
    else if (controlByte == 2) {
      patternHasBall(i);
      i = i + 1;
      if (i == STRIP_LEFT_NUM_LEDS) {
        i = 0;
      }
    }
    else if (controlByte == 3) {
      patternAutoIntake(i);
      i = i + 1;
      if (i == STRIP_LEFT_NUM_LEDS) {
        i = 0;
      }
    }
    else if (controlByte == 4) {
      patternHasBall(i);
      i = i + 1;
      if (i == STRIP_LEFT_NUM_LEDS) {
        i = 0;
      }
    }
    else if (controlByte == 5) {
      patternFlywheelStartup(i);
      i = i + 1;
      if (i == STRIP_LEFT_NUM_LEDS) {
        i = 0;
      }
    }
    else if (controlByte == 6) {
      i++;
      patternShoot(i);
    }
    else if (controlByte == 7) {
      patternScaleMode(i);
      i = i + 1;
    }
  }
}

/////////////////// Patterns

void patternDisabled(int i) {
  setAll(i, i/5, 0);
}

void patternAutoIntake(int i) {
  if (i == 0) {
    setAll(255, 50, 0);
  }
  setPixel(STRIP_LEFT_NUM_LEDS - i - 1, 255, 255, 255);
  FastLED.show();
}

void patternHasBall(int i) {
  setPixel(i - 2, 255, 50, 0);
  setPixel(i - 1, 0, 127, 255);
  setPixel(i, 0, 127, 255);

  setPixel(STRIP_LEFT_NUM_LEDS - i, 255, 50, 0);
  setPixel(STRIP_LEFT_NUM_LEDS - i - 1, 0, 127, 255);
  setPixel(STRIP_LEFT_NUM_LEDS - i - 2, 0, 127, 255);
  FastLED.show();
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

void patternShoot(int i) {
  if (i % 2) {
    for (int i = 0; i < STRIP_LEFT_NUM_LEDS; i++) {
      strip_left_leds[i][0] = strip_left_leds[i][0] = 255;
      strip_left_leds[i][1] = strip_left_leds[i][1] = 50;
      strip_left_leds[i][2] = strip_left_leds[i][2] = 0;
    }
  }
  else {
    for (int i = 0; i < STRIP_LEFT_NUM_LEDS; i++) {
      strip_left_leds[i][0] = strip_left_leds[i][0] = 255;
      strip_left_leds[i][1] = strip_left_leds[i][1] = 255;
      strip_left_leds[i][2] = strip_left_leds[i][2] = 255;
    }
  }
  FastLED.show();
}

void patternScaleMode(int i) {
  if(i%4 == 0) {
    setAll(255, 50, 0);
  }
  else if(i%4 == 1) {
    setAll(255, 255, 255);
  }
  else if(i%4 == 2) {
    setAll(0, 127, 255);
  }
  else if(i%4 == 3) {
    setAll(0, 255, 0);
  }
}

/////////////////////
void setAll(int r, int g, int b) {
  for (int i = 0; i < STRIP_LEFT_NUM_LEDS; i++) {
    strip_left_leds[i].setRGB(r, g, b);
    strip_right_leds[i].setRGB(r, g, b);
  }
  FastLED.show();
}


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

