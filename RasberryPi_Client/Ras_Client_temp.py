from tkinter import *
import requests as r
import time

ip = ''


class first:
    def __init__(self):
        self.root = Tk()
        self.root.title("Duties Rasberry")
        self.root.geometry("320x240")
        self.root.resizable(False,False)
        self.addr = Label(self.root, text="Address")
        #addr.grid(row = 0, column = 0)
        self.addr.pack()

        self.addr_t = Entry(self.root)
        #addr_t.grid(row = 0, column = 1)
        self.addr_t.pack()

        self.code = Label(self.root, text="Code")
        #code.grid(row = 1, column = 0)
        self.code.pack()

        self.code_t = Entry(self.root)
        #code_t.grid(row = 1, column = 1)
        self.code_t.pack()

        self.blank = Label(self.root)
        #blank.grid(row = 2, column = 1)
        self.blank.pack()

        self.button = Button(self.root, text = 'login', width = 30, command = self.login)
        #button.grid(row = 3, column = 1)
        self.button.pack()

        self.root.mainloop()

    def login(self):
        data = {'address' : self.addr_t.get(), 'code' : self.code_t.get()}
        print(data)
        re = r.post('http://localhost:5000/in/5', data=data)
        print(re.text)

        if re.text == '1':
            self.root.destroy()

            second()


class second:
    def __init__(self):
        self.root = Tk()
        self.root.title("Duties Rasberry")
        self.root.geometry('320x240')
        self.root.resizable(False, False)

        self.label1=Label(self.root,justify='center')
        self.label1.pack()

        img = PhotoImage(file = 'qr.png')
        self.qr = Label(self.root, image = img)
        self.qr.pack()




        self.clock()
        self.root.mainloop()

    def clock(self):
        t=time.strftime('%I:%M:%S',time.localtime())
        if t!='':
            self.label1.config(text=t,font='100')
            self.root.after(100,self.clock)

first()
