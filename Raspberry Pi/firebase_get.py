from firebase import firebase

firebase = firebase.FirebaseApplication("https://homeautomationapp-42e27.firebaseio.com/")
result = firebase.get('/homeautomationapp-42e27/TestTable', '')
print(result)