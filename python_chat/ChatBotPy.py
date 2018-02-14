import random
from datetime import datetime

class Chatbot():
    def __init__(self):
        self.answers = []
        self.botname = " Bot: "
        self.is_silent = False
        self.file = 'text.txt'
    def defaultAnswers(self, file):

        with open("./answers/" + self.file, "r") as f:
            self.answers = f.read().splitlines()
    def newAnswers(self, file):
        self.file = file
        self.defaultAnswers(self.file)
    def date(self):
        return datetime.now().date()
    def time(self):
        return datetime.now().strftime("%H:%M:%S")
    def silent(self):
        self.is_silent = True
    def getUp(self):
        if self.is_silent == True:
            self.is_silent = False
    def dialog(self, listen):
        self.listen = listen
        self.defaultAnswers(self.file)
        if self.is_silent == False:
            return (self.botname + self.answers[random.randint(0, len(self.answers)-1)] + "\n")
        else:
            return (self.botname)

def Main():
    newbot = Chatbot()
    while True:
        question = input(" You: ")
        if question == 'quit':
            break
        if question == 'silent':
            newbot.silent()
        if question == 'getUp':
            newbot.getUp()
            continue
        if question == 'date':
            print (newbot.date())
            continue
        if question == 'time':
            print(newbot.time())
            continue
        if question == 'change':
            change = input("Enter new filepath or filename: ")
            newbot.newAnswers(change)
            continue
        print(newbot.dialog(question))

if __name__ == '__main__':
    Main()
