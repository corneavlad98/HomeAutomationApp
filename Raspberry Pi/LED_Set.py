import RPi.GPIO as GPIO
import time
from firebase import firebase
LED_url = "https://homeautomationapp-42e27.firebaseio.com/RaspberryPi/LED"
rgbLED_url = "https://homeautomationapp-42e27.firebaseio.com/RaspberryPi/LED/rgbLED"
firebase = firebase.FirebaseApplication(LED_url)

led1Pin = 20
led2Pin = 21
redPin   = 13
greenPin = 19
bluePin  = 26

#setup leds
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

GPIO.setup(led1Pin,GPIO.OUT)
GPIO.setup(led2Pin,GPIO.OUT)
GPIO.setup(redPin,GPIO.OUT)
GPIO.setup(greenPin,GPIO.OUT)
GPIO.setup(bluePin,GPIO.OUT)

#reset LEDs
GPIO.output(led1Pin,GPIO.LOW)
GPIO.output(led2Pin,GPIO.LOW)
#for RGB we reset the leds with HIGH value
GPIO.output(redPin,GPIO.HIGH)
GPIO.output(greenPin,GPIO.HIGH)
GPIO.output(bluePin,GPIO.HIGH)

def turnOn(pin):
    if(pin == led1Pin or pin == led2Pin):
        GPIO.output(pin,GPIO.HIGH)
    if(pin == redPin or pin == greenPin or pin == bluePin):
        GPIO.output(pin,GPIO.LOW)

def turnOff(pin):
    if(pin == led1Pin or pin == led2Pin):
        GPIO.output(pin,GPIO.LOW)
    if(pin == redPin or pin == greenPin or pin == bluePin):
        GPIO.output(pin,GPIO.HIGH)

def ledHandle(value, pin):
    if(value == 1):
        turnOn(pin)
    elif(value == 0):
        turnOff(pin)

try:
    while(True):
        led1Value = firebase.get(LED_url + '/LED1', '')
        led2Value = firebase.get(LED_url + '/LED2', '')

        redValue = firebase.get(rgbLED_url + '/Red', '')
        greenValue = firebase.get(rgbLED_url + '/Green', '')
        blueValue = firebase.get(rgbLED_url + '/Blue', '')

        print("LED1: " + str(led1Value) + " LED2: " + str(led2Value) + " R: " + str(redValue) + " G: " + str(greenValue) + " B: " + str(blueValue))
        ledHandle(led1Value, led1Pin)
        ledHandle(led2Value, led2Pin)
        ledHandle(redValue, redPin)
        ledHandle(greenValue, greenPin)
        ledHandle(blueValue, bluePin)

        # if(led1Value == 1):
        #     turnOn(led1Pin)        
        # else:
        #     turnOff(led1Pin)           
        # if(led2Value == 1):
        #     GPIO.output(led2Pin,GPIO.HIGH)
        # else:
        #     GPIO.output(led2Pin,GPIO.LOW)

        # if(redValue == 1):
        #     GPIO.output(redPin,GPIO.LOW)
        # else:
        #     GPIO.output(redPin,GPIO.HIGH)
        # if(greenValue == 1):
        #     GPIO.output(greenPin,GPIO.LOW)
        # else:
        #     GPIO.output(greenPin,GPIO.HIGH)
        # if(blueValue == 1):
        #     GPIO.output(bluePin,GPIO.LOW)
        # else:
        #     GPIO.output(bluePin,GPIO.HIGH)
except KeyboardInterrupt:
    GPIO.output(led1Pin,GPIO.LOW)
    GPIO.output(led2Pin,GPIO.LOW)
    GPIO.output(redPin,GPIO.HIGH)
    GPIO.output(greenPin,GPIO.HIGH)
    GPIO.output(bluePin,GPIO.HIGH)

    