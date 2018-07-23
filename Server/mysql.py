import pymysql.cursors


class sql:
    def __init__(self):
        while 1:
            try:
                user = input('MYSQL user 이름을 입력하세요 : ')
                ps = input('MYSQL 비밀번호를 입력하세요 : ')
                self.infolist = ['user','company']
                self.connection = pymysql.connect(host='localhost',
                                                  user=user,
                                                  password=ps,
                                                  db='ict',
                                                  charset='utf8mb4',
                                                  cursorclass=pymysql.cursors.DictCursor)
                break

            except pymysql.err.OperationalError:
                print("user이름과 password를 확인하세요")

    def sign_in(self, info, dbflag, salt):
        print('run')
        sql = "INSERT INTO user (id, password, name, phone_number, birthday, email, salt) VALUES (%s, %s, %s, %s, %s, %s, %s)" if dbflag == 0 else "INSERT INTO company (name, location, address, crn, introduction, inoutcode) VALUES (%s, %s, %s, %s, %s, %s)"
        with self.connection.cursor() as cursor:
            #sql = "INSERT INTO {type} (name, phone_number, birthday, email) VALUES (%s, %s, %s, %s)"
            if dbflag == 0:
                print(info)
                cursor.execute(
                    sql, (info['id'], info['password'], info['name'], info['phone_number'], info['birthday'], info['email'], salt)) # 원래는 암호화 키값을 다른 서버에 보관하여야 한다.
            else:
                cursor.execute(sql, (info['name'], info['location'],
                                     info['address'], info['crn'], info['introduction'],info['inoutcode']))

            self.connection.commit()

    def inout(self, info):
        sql = "INSERT INTO inout_log (company_id, user_id, inout_flag) VALUES (%s, %s, %s)"
        with self.connection.cursor() as cursor:
            cursor.execute(
                sql, (info['company_id'], info['user_id'], info['in/out_flag']))

            self.connection.commit()

    def update_code(self, address, code):
        sql = "UPDATE company SET inoutcode=%s WHERE address=%s"
        with self.connection.cursor() as cursor:
            try:
                cursor.execute(sql, (code,address))
            except:
                return 0

        self.connection.commit()
        return 1

    def get_info(self, flag, **kwargs):
        if flag == 10:
            sql = "SELECT salt FROM user WHERE id='{}'".format(kwargs['id'])
            with self.connection.cursor() as cursor:
                cursor.execute(sql)
                result = cursor.fetchall()
                return result[0]['salt']

        sql = "SELECT * FROM {}".format(self.infolist[int(flag)])
        with self.connection.cursor() as cursor:
            cursor.execute(sql)
            result = cursor.fetchall()
            print(result)
            return result

    def login(self, info):
        sql = "SELECT password FROM user WHERE id = '{}'".format(info['id'])
        with self.connection.cursor() as cursor:
            cursor.execute(sql)
            password = cursor.fetchall()
            result = 1 if password == info['password'] else 0
            return result

    def dblogout(self):
        self.connection.close()
