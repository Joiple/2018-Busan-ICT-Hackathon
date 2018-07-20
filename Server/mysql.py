import pymysql.cursors

connection = pymysql.connect(host = 'localhost',
                             user = 'jeong',
                             password = 'qwe123',
                             db = 'trf',
                             charset = 'utf8mb4',
                             cursorclass=pymysql.cursors.DictCursor)

try:
    with connection.cursor() as cursor:
        #새로운 기록 생성 레코드 row
        sql = "INSERT INTO road (title,connected_intersection,len) VALUES (%s, %s, %s)"
        cursor.execute(sql, ('가나다길','둘리교차', 100))

    connection.commit()

finally:
    connection.close()
