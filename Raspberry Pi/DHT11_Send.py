import Adafruit_DHT
from firebase import firebase
DHT11_url = "https://homeautomationapp-42e27.firebaseio.com/RaspberryPi/DHT11"
firebase = firebase.FirebaseApplication(DHT11_url)


# Sensor should be set to Adafruit_DHT.DHT11,
# Adafruit_DHT.DHT22, or Adafruit_DHT.AM2302.
sensor = Adafruit_DHT.DHT11

# connected to GPIO16.
pin = 16
count = 1
print("entered program!")
try:
    while(True):
        #Get values from DHT11 sensor 
        humidity, temperature = Adafruit_DHT.read_retry(sensor, pin)
        if humidity is not None and temperature is not None:
            #update 'temperature' and 'humidity' children
            if(temperature < 70):
                firebase.patch(DHT11_url + '/Temperature', {'Value*C': temperature})
                firebase.patch(DHT11_url + '/Humidity', {'Value%': humidity})
                print("sent DHT11 info to database, count: " , count)
            count += 1
        else:
            print("error getting data from sensor!")
except KeyboardInterrupt:
    print("exited program!")

