from flask import Flask
import com
import qrcode, hashlib, random

app = Flask(__name__)
a = [a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,x,y,z]
arr = []

@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/get')
def get_naver():
    return com.ge()

@app.route('/qr')
def qr():
    for i in range(5:)
        ran = random.randint(0,24)
        arr.append(a[ran])

    sha = hashlib.new('sha')

    sha.update(str(ran).encode('utf-8'))
    img = qrcode.make(sha.hexdigest())
    img.save(f"{sha}.png")

if __name__ == '__main__':
    app.run()
