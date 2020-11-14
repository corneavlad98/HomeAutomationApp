from firebase import firebase

firebase = firebase.FirebaseApplication("https://homeautomationapp-42e27.firebaseio.com/")

firebase.delete('/homeautomationapp-42e27/TestTable/', '-MM5RGtf67kNcWbkxkUp')
print("Record deleted")