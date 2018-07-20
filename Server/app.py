from flask import Flask, request, redirect, url_for, jsonify
import mysql
import qrcode, hashlib, random

app = Flask(__name__)
ranarr = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','x','y','z']

db = mysql.sql()

@app.route('/signin', methods = ['POST','GET'])
def sign_in():
    print(request.form['name'])
    info = {'name' : request.form['name'],
            'birthday' : request.form['birthday'], # 형식 0000-00-00
            'phone_number' : request.form['phone_number'],
            'email' : request.form['email'],
            'company' : request.form['company'],
            'wage' : request.form['wage']}
    db.sign_in(info)
    return 'suc'

@app.route('/login', methods = ['POST', 'GET'])
def login():
   if request.method == 'POST':
      user = request.form['myName']
      print(user)
      return f'suc {user}'
   else:
      user = request.args.get('myName')
      return 'suc'

@app.route('/code') # 출퇴근 랜덤 코드 생성
def code():
    arr = ''
    for i in range(5):
        ran = random.randint(0,32)
        if ran <= 23:
            arr += ranarr[ran]
        else:
            arr += str(ran - 23)
    return arr

if __name__ == '__main__':
    app.run(debug = True)
