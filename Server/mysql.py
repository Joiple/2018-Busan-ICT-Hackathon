import pymysql.cursors

class sql:
    def __init__(self):
        while 1:
            try:
                user = input('MYSQL user 이름을 입력하세요 : ')
                ps = input('MYSQL 비밀번호를 입력하세요 : ')
                self.connection = pymysql.connect(host = 'localhost',
                                                  user = user,
                                                  password = ps,
                                                  db = 'ict',
                                                  charset = 'utf8mb4',
                                                  cursorclass=pymysql.cursors.DictCursor); break

            except pymysql.err.OperationalError:
                print("user이름과 password를 확인하세요")

    def sign_in(self, info, dbflag):
        sql = "INSERT INTO user (name, phone_number, birthday, email) VALUES (%s, %s, %s, %s)" if dbflag == 0 else "INSERT INTO company (name, location, address, crn, introduction) VALUES (%s, %s, %s, %s, %s)"
        with self.connection.cursor() as cursor:
            #sql = "INSERT INTO {type} (name, phone_number, birthday, email) VALUES (%s, %s, %s, %s)"
            if dbflag == 0:
                cursor.execute(sql, (info['name'], info['phone_number'], info['birthday'], info['email']))
            else:
                cursor.execute(sql, (info['name'], info['location'], info['address'], info['crn'], info['introduction']))

            self.connection.commit()

    def check_rep(self, flag, content): # 중복검사 flag 0 : random text arr, 1 : ...
        if flag == 0:
            pass

    def inout(self, info):
        print(info)
        sql = "INSERT INTO inout_log (company_id, user_id, inout_flag) VALUES (%s, %s, %s)"
        with self.connection.cursor() as cursor:
            cursor.execute(sql, (info['company_id'], info['user_id'], info['in/out_flag']))

            self.connection.commit()

    def dblogout(self):
        self.connection.close()
