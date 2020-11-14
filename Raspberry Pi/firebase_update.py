from firebase import firebase

firebase = firebase.FirebaseApplication("https://homeautomationapp-42e27.firebaseio.com/")
firebase.put('/homeautomationapp-42e27/TestTable/-MM5QxnhRyCX-ibdI9rM', 'Name', 'Michael')

print("Record updated")