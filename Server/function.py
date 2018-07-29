import hashlib, random
import mysql

db = mysql.sql()
ranarr = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','x','y','z']


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

def makecom():
    info = {'name' : request.form['name'],
            'location' : request.form['location'],
            'address' : request.form['address'], # 회사 가입을 위한 주소 설정 slack 처럼 그룹 주소 설장
            'crn' : request.form['crn'], # corporate registration number 사업자 등록번호 옵셔널 , 빈칸 가능
            'introduction' : request.form['introduction'],
            'inoutcode' : ''} # 옵셔널, 빈칸가능

    db.sign_in(info,1)

def inout():
    info = {'company_id' : request.form['company_id'],
            'user_id' : request.form['user_id'],
            'in/out_flag' : request.form['flag']} # flag = in : 0 out : 1

    db.inout(info)

def qrinout():
    info = {'code' : request.form['code'],
            'user_id' : request.form['user_id'],
            'company_id' : request.form['company_id'],
            'in/outflag' : request.form['flag']}
    db.inout(info)

def login():
    salt = db.get_info(10, id=request.form['id']) # get_info 10 == salt값 추출
    hasher = hashlib.sha256()
    hasher.update((request.form['password']+salt).encode("UTF-8"))
    info = {'id' : request.form['id'],
            'password' : hasher.hexdigest()}
    check = db.login(info)

def code():
    while 1: # 중복된 코드 생성시 다시 시도
        arr = get_random_arr(5)
        check = db.update_code(request.form['address'],arr)
        if check == 1: break
    return arr

def get_info():
    what = request.form['what']
    if what == 'user_profile':
        db.get_info(1, user_id = request.form['user_id'])

    elif what == 'company_profile':
        db.get_info(2, company_id = request.form['company_id'])

    elif what == 'inout_user':
        db.get_info(3, user_id = request.form['user_id'])

    elif what == 'inout_company':
        db.get_info(4, company_id = request.form['company_id'])

def raslogin():
    info = {'address' : request.form['address'],
            'code' : request.form['code']}
    check = db.comlogin(info)
    if check == 1:
        return '1'
    return '0'


def get_random_arr(n):
    arr = ''
    for i in range(n):
        ran = random.randint(0,32)
        if ran <= 23:
            arr += ranarr[ran]
        else:
            arr += str(ran - 23)
    return arr
