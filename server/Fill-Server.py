import requests
from random import randint
import socket 

def create_user(user, user_info):
	dates_list = ['2019-02-24','2018-11-11','2019-05-31','2018-06-26','2018-06-28','2018-08-19','2019-04-09','2019-04-02','2018-12-08','2019-10-04','2018-10-29','2020-01-12','2018-07-02','2019-05-11','2018-04-24','2018-08-05','2019-04-12','2018-09-23','2020-01-06','2019-05-18','2018-06-07','2018-07-14','2019-03-17','2020-01-21','2019-09-20','2019-03-27','2018-10-04','2018-11-13','2019-02-08','2018-07-12','2019-01-02','2018-06-13','2018-08-12','2019-04-07','2020-02-21','2018-05-07','2018-12-12','2019-06-19','2019-11-23','2019-03-14','2018-04-30','2018-08-11','2019-10-11','2018-07-24','2018-11-17','2018-06-18','2019-03-09','2019-09-18','2020-03-03','2020-01-09','2018-11-13','2019-05-16','2019-05-10','2020-04-02','2018-06-06','2020-01-15','2018-05-14','2019-06-16','2019-04-08','2019-05-23','2019-07-11','2018-09-21','2018-05-18','2018-10-28','2018-06-23','2019-10-01','2018-08-08','2018-12-30','2018-11-21','2019-01-06','2020-04-02','2019-03-27','2018-08-12','2019-02-03','2019-03-29','2020-02-15','2020-02-05','2018-07-08','2019-12-15','2019-07-05','2019-10-03','2020-03-14','2018-05-15','2019-07-24','2019-01-06','2020-02-28','2019-01-12','2019-01-24','2019-09-16','2018-08-08','2019-08-06','2019-03-02','2019-06-04','2018-12-17','2020-01-18','2020-01-09','2018-08-04','2019-07-03','2018-07-17','2018-11-18']
	r = requests.post('http://localhost:8080/api/users', json = user_info)
	print("Carga de {}".format(user)+" -> status_code:"+str(r.status_code))
	
	for i in range(0,randint(25,50)):
		recycled = {}
		recycled['bottles'] = randint(0,50)
		recycled['tetrabriks'] = randint(0,50)
		recycled['glass'] = randint(0,50)
		recycled['paperboard'] = randint(0,50)
		recycled['cans'] = randint(0,50)
		recycled['date'] = dates_list[randint(0, 99)]
		r = requests.post('http://localhost:8080/api/users/{}/recycling/'.format(user), json=recycled)
		
	r = requests.get('http://localhost:8080/api/users/{}/total/'.format(user))
	print(r.status_code)
	print(r.json())
	print('')	

	
print('')

bau_user = 'bau'
bau_info = {
	'firstName': 'Bautista',
	'lastName': 'Carpintero',
	'mail': 'bj.carpintero@gmail.com',
	'username': 'bau',
	'address': {
		'department': 'Tandil',
		'number': 706,
		'streetAddress': '11 de Septiembre',
		'city': 'Tandil',
		'state': 'Buenos Aires',
		'zipCode': '7000'
	}
}
create_user(bau_user,bau_info)

nati_user = 'nati'
nati_info = {
	'firstName': 'Natalia',
	'lastName': 'Severino',
	'mail': 'natiseverino96@gmail.com',
	'username': 'nati',
	'address': {
		'department': 'Tandil',
		'number': 60,
		'streetAddress': 'Moreno',
		'city': 'Tandil',
		'state': 'Buenos Aires',
		'zipCode': '7000'
	}
}
create_user(nati_user,nati_info)

host_ip = socket.gethostbyname(socket.gethostname()) 
print("IPv4 - (localhost) : ",host_ip) 
print('')





