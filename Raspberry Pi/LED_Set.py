import RPi.GPIO as GPIO
import time
from firebase import firebase
LED_url = "https://homeautomationapp-42e27.firebaseio.com/RaspberryPi/LED"
firebase = firebase.FirebaseApplication(LED_url)

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(21,GPIO.OUT)
GPIO.setup(20,GPIO.OUT)

while(True):
    led1 = firebase.get(LED_url + '/LED1', '')
    led2 = firebase.get(LED_url + '/LED2', '')
    print("LED1: " + str(led1) + " LED2: " + str(led2))
    if(led1 == 1):
        GPIO.output(21,GPIO.HIGH)
    else:
        GPIO.output(21,GPIO.LOW)
    if(led2 == 1):
        GPIO.output(20,GPIO.HIGH)
    else:
        GPIO.output(20,GPIO.LOW)