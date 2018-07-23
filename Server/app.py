from flask import Flask, request, redirect, url_for, jsonify
import mysql
import qrcode, hashlib, random, json

app = Flask(__name__)
ranarr = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','x','y','z']

db = mysql.sql()

@app.route('/signin', methods = ['POST','GET'])
def sign_in():
    salt = get_random_arr(random.randint(5,10))
    hasher = hashlib.sha256()
    hasher.update((request.form['password']+salt).encode("UTF-8"))
    print(type(hasher.hexdigest()))
    info = {'id' : request.form['id'],
            'password' : hasher.hexdigest(),
            'name' : request.form['name'],
            'birthday' : request.form['birthday'], # 형식 0000-00-00
            'phone_number' : request.form['phone_number'],
            'email' : request.form['email'],
            'company' : request.form['company'],
            'wage' : request.form['wage']}

    db.sign_in(info,0,salt) #db flag 0이면 회원가입 1이면 회사생성
    return 'suc'

@app.route('/makecom', methods = ["POST","GET"])
def makecom():
    info = {'name' : request.form['name'],
            'location' : request.form['location'],
            'address' : request.form['address'], # 회사 가입을 위한 주소 설정 slack 처럼 그룹 주소 설장
            'crn' : request.form['crn'], # corporate registration number 사업자 등록번호 옵셔널 , 빈칸 가능
            'introduction' : request.form['introduction'],
            'inoutcode' : ''} # 옵셔널, 빈칸가능

    db.sign_in(info,1)
    return 'suc'

@app.route('/inout', methods = ['POST', 'GET']) # flag = in : 0 out : 1
def inout():
    info = {'company_id' : request.form['company_id'],
            'user_id' : request.form['user_id'],
            'in/out_flag' : request.form['flag']}

    db.inout(info)
    return 'suc'

@app.route('/qrinout', methods = ['POST','GET'])
def qrinout():
    info = {'code' : request.form['code'],
            'user_id' : request.form['user_id'],
            'company_id' : request.form['company_id'],
            'in/outflag' : request.form['flag']}
    check = db.inout(info)
    if check == 0:
        return 'fail'
    return 'suc'

@app.route('/login', methods = ['POST', 'GET'])
def login():
    salt = db.get_info(10, id=request.form['id']) # get_info 10 == salt값 추출
    hasher = hashlib.sha256()
    hasher.update((request.form['password']+salt).encode("UTF-8"))
    info = {'id' : request.form['id'],
            'password' : hasher.hexdigest()}
    db.login(info)
    return "hello {}".format(request.form['id'])

@app.route('/code/<address>') # 출퇴근 랜덤 코드 생성
def code(address):
    while 1:
        arr = get_random_arr(5)
        check = db.update_code(address,arr)
        if check == 1: break
    return arr

@app.route('/getinfo/<flag>')
def get_info(flag):
    info = db.get_info(flag)
    return json.dumps(info, indent=2, ensure_ascii=False)

@app.route('/test')
def test():
    salt = get_random_arr(random.randint(5,10))
    print(salt)
    hasher = hashlib.sha256()
    hasher.update("asd".encode("UTF-8"))
    print(hasher.hexdigest())
    return (hasher.hexdigest()+"    "+salt)

def get_random_arr(n):
    arr = ''
    for i in range(n):
        ran = random.randint(0,32)
        if ran <= 23:
            arr += ranarr[ran]
        else:
            arr += str(ran - 23)
    return arr

if __name__ == '__main__':
    app.run(debug = True)
