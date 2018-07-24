# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'mainwindow.ui'
#
# Created by: PyQt5 UI code generator 5.6
#
# WARNING! All changes made in this file will be lost!

from PyQt5 import QtCore, QtGui, QtWidgets
import requests, qrcode
from multiprocessing import Process
from time import strftime, sleep
import datetime

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName("MainWindow")
        MainWindow.resize(320, 240)
        sizePolicy = QtWidgets.QSizePolicy(QtWidgets.QSizePolicy.Maximum, QtWidgets.QSizePolicy.Maximum)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(MainWindow.sizePolicy().hasHeightForWidth())
        MainWindow.setSizePolicy(sizePolicy)
        self.centralWidget = QtWidgets.QWidget(MainWindow)
        self.centralWidget.setObjectName("centralWidget")
        self.lineEdit = QtWidgets.QLineEdit(self.centralWidget)
        self.lineEdit.setGeometry(QtCore.QRect(180, 60, 81, 31))
        self.lineEdit.setObjectName("address")
        self.label = QtWidgets.QLabel(self.centralWidget)
        self.label.setGeometry(QtCore.QRect(50, 50, 141, 51))
        font = QtGui.QFont()
        font.setFamily("Apple SD Gothic Neo")
        font.setPointSize(24)
        self.label.setFont(font)
        self.label.setObjectName("label")
        self.label_2 = QtWidgets.QLabel(self.centralWidget)
        self.label_2.setGeometry(QtCore.QRect(120, 30, 61, 16))
        self.label_2.setObjectName("label_2")
        self.lineEdit_2 = QtWidgets.QLineEdit(self.centralWidget)
        self.lineEdit_2.setGeometry(QtCore.QRect(150, 100, 111, 31))
        self.lineEdit_2.setObjectName("code")
        self.label_3 = QtWidgets.QLabel(self.centralWidget)
        self.label_3.setGeometry(QtCore.QRect(80, 110, 71, 21))
        self.pushButton = QtWidgets.QPushButton(self.centralWidget)
        self.pushButton.setGeometry(QtCore.QRect(180, 160, 121, 61))
        self.pushButton.setObjectName("pushButton")
        self.pushButton.clicked.connect(self.login)
        font = QtGui.QFont()
        font.setFamily("Apple SD Gothic Neo")
        font.setPointSize(24)
        self.label_3.setFont(font)
        self.label_3.setObjectName("label_3")
        MainWindow.setCentralWidget(self.centralWidget)

        self.retranslateUi(MainWindow)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        _translate = QtCore.QCoreApplication.translate
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow"))
        self.pushButton.setText(_translate("MainWindow", "Login"))
        self.label.setText(_translate("MainWindow", "duties.com /"))
        self.label_2.setText(_translate("MainWindow", "거치 단말용"))
        self.label_3.setText(_translate("MainWindow", "CODE"))

    def login(self):
        info = {'address' : self.lineEdit.text(), 'code' : self.lineEdit_2.text()}
        r = requests.post(url = "http://localhost:5000/comlogin",data = info)
        if r.text == '1':
            Dialog = QtWidgets.QDialog()
            second = Ui_Dialog()
            second.setupUi(Dialog, info)
            MainWindow.close()

            #오류 나서 멀티프로세스 처리
            p = Process(target= qr_gen, args = (info['address'],))
            p.start()
            p.join()

            Dialog.show()
        else:
            print("Please chech your address/code")

class Ui_Dialog(object):
        def setupUi(self, Dialog, info):
            self.info = info
            Dialog.setObjectName("Dialog")
            Dialog.resize(320, 240)
            self.timer = QtCore.QTimer(Dialog)
            self.timer.timeout.connect(self.time)
            self.timer.start(1000)

            current = datetime.datetime.now()
            if current.hour < 14:
                sleeptime = current.replace(hour = 14) - current
            else:
                sleeptime = datetime.datetime(current.year,current.month,current.day+1,2) - current

            self.timer2 = QtCore.QTimer(Dialog)
            self.timer2.timeout.connect(self.qrcode_reset)
            self.timer2.start(sleeptime.total_seconds() * 1000)


            self.lcdNumber = QtWidgets.QLCDNumber(Dialog)
            self.lcdNumber.setGeometry(QtCore.QRect(20, 10, 281, 71))
            self.lcdNumber.setObjectName("lcdNumber")
            self.label = QtWidgets.QLabel(Dialog)
            self.label.setGeometry(QtCore.QRect(90, 90, 141, 141))
            self.label.setObjectName("label")
            pixmap = QtGui.QPixmap('qr.png')
            self.label.setPixmap(pixmap)

            self.retranslateUi(Dialog)
            QtCore.QMetaObject.connectSlotsByName(Dialog)

        def retranslateUi(self, Dialog):
            _translate = QtCore.QCoreApplication.translate
            Dialog.setWindowTitle(_translate("Dialog", "Dialog"))

        def time(self):
            self.lcdNumber.display(strftime('%H'+':'+'%M'))
            self.timer.start(1000)

        def qrcode_reset(self):
            p = Process(target= qr_gen, args = (self.info['address'],))
            p.start()
            p.join()

            pixmap = QtGui.QPixmap('qr.png')
            self.label.setPixmap(pixmap)

            timer2.start(43200000) # 12시간


def qr_gen(address):
    r = requests.get('http://localhost:5000/getinfo/11/{}'.format(address))
    inoutcode = r.text
    qr = qrcode.QRCode(
    version=1,
    error_correction=qrcode.constants.ERROR_CORRECT_L,
    box_size=5,
    border=4,
    )
    qr.add_data(inoutcode)
    qr.make(fit=True)

    img = qr.make_image()
    img.save("qr.png")
    qr.clear()



if __name__=='__main__':
    import sys
    app = QtWidgets.QApplication(sys.argv)
    MainWindow = QtWidgets.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())
