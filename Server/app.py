from flask import Flask, request, redirect, url_for, jsonify
import mysql
import qrcode, hashlib, random

app = Flask(__name__)
ranarr = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','x','y','z']

db = mysql.sql()

@app.route('/signin', methods = ['POST','GET'])
def sign_in():
    info = {'name' : request.form['name'],
            'birthday' : request.form['birthday'], # 형식 0000-00-00
            'phone_number' : request.form['phone_number'],
            'email' : request.form['email'],
            'company' : request.form['company'],
            'wage' : request.form['wage']}

    db.sign_in(info,0) #db flag 0이면 회원가입 1이면 회사생성
    return 'suc'

@app.route('/makecom', methods = ["POST","GET"])
def makecom():
    print(request.form['introduction'])
    info = {'name' : request.form['name'],
            'location' : request.form['location'],
            'address' : request.form['address'], # 회사 가입을 위한 주소 설정 slack 처럼 그룹 주소 설장
            'crn' : request.form['crn'], # corporate registration number 사업자 등록번호 옵셔널 , 빈칸 가능
            'introduction' : request.form['introduction']} # 옵셔널, 빈칸가능

    db.sign_in(info,1)
    return ''

@app.route('/login', methods = ['POST', 'GET'])
def login():
   if request.method == 'POST':
      user = request.form['myName']
      print(user)
      return f'suc {user}'
   else:
      user = request.args.get('myName')
      return 'suc'

@app.route('/code/<com>') # 출퇴근 랜덤 코드 생성
def code(com):
    arr = ''
    for i in range(5):
        ran = random.randint(0,32)
        if ran <= 23:
            arr += ranarr[ran]
        else:
            arr += str(ran - 23)
    db.check_rep(0,arr)
    return arr

if __name__ == '__main__':
    app.run(debug = True)
