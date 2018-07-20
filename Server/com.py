import requests as r

def ge():
    try:
        re = r.get("http://naver.com")
    except:
        return '인터넷 연결을 확인하세요!'
    return re.text

if __name__ == '__main__':
    print(ge())
