import pymysql.cursors

class sql:
    def __init__(self):
        user = input('MYSQL user 이름을 입력하세요 : ')
        ps = input('MYSQL 비밀번호를 입력하세요 : ')
        self.connection = pymysql.connect(host = 'localhost',
                                          user = user,
                                          password = ps,
                                          db = 'ict',
                                          charset = 'utf8mb4',
                                          cursorclass=pymysql.cursors.DictCursor)

    def sign_in(self, info):
        try:
            with self.connection.cursor() as cursor:
                sql = "INSERT INTO user (name, phone_number, birthday, email) VALUES (%s, %s, %s, %s)"
                cursor.execute(sql, (info['name'], info['phone_number'], info['birthday'], info['email']))

                self.connection.commit()

        finally:
            self.connection.close()

# try:
#     with connection.cursor() as cursor:
#         #새로운 기록 생성 레코드 row
#         sql = "INSERT INTO road (title,connected_intersection,len) VALUES (%s, %s, %s)"
#         cursor.execute(sql, ('가나다길','둘리교차', 100))
#
#     connection.commit()
#
# finally:
#     connection.close()
