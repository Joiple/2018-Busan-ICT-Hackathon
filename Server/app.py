from flask import Flask, request, redirect, url_for, jsonify
import mysql
import function as f

app = Flask(__name__)

@app.route('/in/<flag>', methods = ['POST', 'GET'])
def in_control(flag):
    try:
        if flag == '0': # 사용자 생성
            f.sign_in()

        elif flag == '1': # 회사 만들기
            f.makecom()

        elif flag == '2': # 출 퇴근 정보 저장
            f.inout()

        elif flag == '3': # qr 코드 출 퇴근 정보 저장
            f.qrinout()

        elif flag == '4': # 로그인 정보 대조
            f.login()

        elif flag == '5': # 라즈베리 파이 단말기 로그인 대조
            f.raslogin()

        return '1'

    except Exception as ex:
        return '0,' + ex

@app.route('/out/<flag>', methods = ['POST','GET'])
def out_control(flag):
    try:
        if flag == '0': # 새로운 출 퇴근 코드 생성후 코드 리턴
            cd = f.code()
            return cd

        elif flag == '1': # 정보조회
            f.get_info()

        return '1'

    except Exception as ex:
        return '0,' + ex

if __name__ == '__main__':
    app.run(debug = True)
