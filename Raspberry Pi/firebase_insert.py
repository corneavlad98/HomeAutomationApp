from firebase import firebase

firebase = firebase.FirebaseApplication("https://homeautomationapp-42e27.firebaseio.com/")
data = {
    'Name': 'test 1 2 3',
    'Type': 'test & research',
    'Data': 'ON'
}
result = firebase.post('/homeautomationapp-42e27/TestTable', data)
print(result)