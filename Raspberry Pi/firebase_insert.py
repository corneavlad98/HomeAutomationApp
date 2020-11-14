from firebase import firebase

firebase = firebase.FirebaseApplication("https://homeautomationapp-42e27.firebaseio.com/")

result = firebase.post('/Raspberryi Pi','Hello from RPI3!')
print("sent hello to database")