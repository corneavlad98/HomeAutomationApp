import RPi.GPIO as GPIO
import time
import threading

from firebase import firebase
LED_url = "https://homeautomationapp-42e27.firebaseio.com/RaspberryPi/LED"
rgbLED_url = "https://homeautomationapp-42e27.firebaseio.com/RaspberryPi/LED/rgbLED"
firebase = firebase.FirebaseApplication(LED_url)

led1Pin = 20
led2Pin = 21
redPin   = 13
greenPin = 19
bluePin  = 26

#global led values
led1Value = 0
led2Value = 0
redValue = 0
greenValue = 0
blueValue = 0
discoValue = 0

#thread stop flag
threadStop = False

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
def getValuesFromDb():
    global led1Value, led2Value, redValue, greenValue, blueValue, discoValue
    while(threadStop is False):
        led1Value = firebase.get(LED_url + '/LED1', '')
        led2Value = firebase.get(LED_url + '/LED2', '')
        redValue = firebase.get(rgbLED_url + '/Red', '')
        greenValue = firebase.get(rgbLED_url + '/Green', '')
        blueValue = firebase.get(rgbLED_url + '/Blue', '')
        discoValue = firebase.get(rgbLED_url + '/Disco', '')

        print("LED1: " + str(led1Value) + " LED2: " + str(led2Value) + " R: " + str(redValue) + " G: " + str(greenValue) + " B: " + str(blueValue) + " Disco: " + str(discoValue))       
    print("getter thread stopped!")

def handleLedValues():
    while(threadStop is False):
        if(discoValue == 0):
            ledHandle(led1Value, led1Pin)
            ledHandle(led2Value, led2Pin)
            ledHandle(redValue, redPin)
            ledHandle(greenValue, greenPin)
            ledHandle(blueValue, bluePin)    
        elif(discoValue == 1):
            discoMode()
       
    print("handler thread stopped!")

def discoMode():
    #turn red on
    turnOn(redPin)
    turnOff(greenPin)
    turnOff(bluePin)
    time.sleep(0.5)

    #turn green on
    turnOff(redPin)
    turnOn(greenPin)
    turnOff(bluePin)
    time.sleep(0.5)

    #turn blue on
    turnOff(redPin)
    turnOff(greenPin)
    turnOn(bluePin)
    time.sleep(0.5)

    #turn magenta on
    turnOn(redPin)
    turnOff(greenPin)
    turnOn(bluePin)
    time.sleep(0.5)

    #turn cyan on
    turnOff(redPin)
    turnOn(greenPin)
    turnOn(bluePin)
    time.sleep(0.5)

    #turn yellow on
    turnOn(redPin)
    turnOn(greenPin)
    turnOff(bluePin)
    time.sleep(0.5)

    #turn white on
    turnOn(redPin)
    turnOn(greenPin)
    turnOn(bluePin)
    time.sleep(0.5)
    
try:
    t1 = threading.Thread(target = getValuesFromDb)
    t2 = threading.Thread(target = handleLedValues)
    t1.start()     
    t2.start()       
    print("started: " + t1.getName())
    print("started: " + t2.getName())

    print("program is running...")
except KeyboardInterrupt:
    global threadStop
    threadStop = True
    print("exited program!")
    GPIO.output(led1Pin,GPIO.LOW)
    GPIO.output(led2Pin,GPIO.LOW)
    GPIO.output(redPin,GPIO.HIGH)
    GPIO.output(greenPin,GPIO.HIGH)
    GPIO.output(bluePin,GPIO.HIGH)

    