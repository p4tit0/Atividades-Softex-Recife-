from pygame import mixer  # para instalar a biblioteca digite "pip install pygame" no cmd
import eyed3  # para instalar a biblioteca digite "pip install eyed3" no cmd
import time
import random
import string
import os


class Message:
    def __init__(self):
        self.sender = None
        self.receiver = None
        self.content = None

    def set_message(self, sender, receiver, content):
        self.sender = sender
        self.receiver = receiver
        self.content = content

    def get_message(self):
        length = 0
        for line in self.content.split('\n'):
            if len(line) > length:
                length = len(line)
        length += 2
        msg = f" {self.receiver} ".center(length, '-') + '\n'
        msg += ' ' + self.content + '\n\n'
        msg += f" ass. {self.sender}\n"
        msg += '-' * length
        return msg

    def delete_message(self):
        self.sender = None
        self.receiver = None
        self.content = None


class Song:
    class InvalidFilePath(Exception):
        """Chamado quando o aquvo de audio não existe"""

    pass

    def __init__(self):
        self.file_path = None
        self.title = None
        self.album = None
        self.artist = None

    def set_song(self, file_path: str):
        if not os.path.exists(file_path):
            raise self.InvalidFilePath
        self.file_path = file_path
        audio = eyed3.load(file_path)
        self.title = audio.tag.title
        self.album = audio.tag.album
        self.artist = audio.tag.artist

    def get_info(self):
        return self.title, self.album, self.artist

    def play(self):
        mixer.init()
        mixer.music.load(self.file_path)
        mixer.music.play()
        while mixer.music.get_busy():
            time.sleep(1)


class Notebook:
    class NotebookIsFull(Exception):
        """Chamado quando todas as páginas do caderno estão cheias"""
    pass

    class PageOutOfRange(Exception):
        """Chamado quando se tenta acessar uma página que não esta no caderno"""

    pass

    def __init__(self):
        self.number_of_pages = 30
        self.lines_per_page = 10
        self.char_per_line = 50
        self.content = list()

    def get_filled_pages(self):
        return len(self.content)

    def remove_page(self, page_idx: int):
        if page_idx not in range(len(self.content)):
            raise self.PageOutOfRange
        self.content.pop(page_idx)

    def get_page(self, page_idx: int):
        if page_idx not in range(len(self.content)):
            raise self.PageOutOfRange
        page_list = self.content[page_idx].split('\n')
        for p in range(len(page_list)):
            page_list[p] = f'|{page_list[p].ljust(self.char_per_line)}|\n'
        page_list.insert(0, f' PÁGINA {page_idx + 1} '.center(self.char_per_line + 2, '_') + '\n')
        page_list.append('‾' * (self.char_per_line + 2))
        return ''.join(page_list)

    def write(self, txt: str):
        is_empty = False
        if not self.content:
            is_empty = True

        page_idx = 0 if is_empty else len(self.content) - 1
        while True:
            if page_idx == self.number_of_pages:
                raise self.NotebookIsFull
            prev_content = [] if is_empty else self.content[page_idx].split('\n')
            num_of_lines = 0 if is_empty else len(prev_content)
            if num_of_lines < self.lines_per_page:
                raw_page = []
                if prev_content is not None:
                    for line in prev_content:
                        raw_page.append(line + '\n')
                br_cont = len(raw_page)
                for line in txt.split('\n'):
                    raw_page.append(line + '\n')
                while True:
                    done = True
                    for line_idx in range(br_cont, len(raw_page)):
                        if len(raw_page[line_idx]) > self.char_per_line:
                            done = False
                            rest_of_the_line = raw_page[line_idx][self.char_per_line - 1:len(raw_page[line_idx])]
                            raw_page[line_idx] = raw_page[line_idx][0:self.char_per_line - 1]
                            raw_page[line_idx] += '-\n' if raw_page[line_idx][-1] != ' ' else '\n'
                            raw_page[line_idx] = raw_page[line_idx].lstrip()
                            raw_page.insert(line_idx + 1, rest_of_the_line)
                        br_cont += 1
                    if done:
                        break
                for j in range(len(raw_page) // self.lines_per_page + 1):
                    end = (j + 1) * self.lines_per_page
                    page_text = ''.join(
                        raw_page[j * self.lines_per_page: (end if end < len(raw_page) else len(raw_page))])
                    if len(self.content) <= page_idx + j:
                        self.content.append(page_text)
                    else:
                        self.content[page_idx + j] = page_text
                    self.content[page_idx + j] = self.content[page_idx + j][:-1]
                break
            page_idx += 1


class Computer:
    class PCNameLenOutOfRange(Exception):
        """Chamado quando o novo nome do dispositivo tem mais de 15 caracteres"""
        pass

    class OsNotCompatible(Exception):
        """Chamado quando o OS em questão nã é compatível com o dispositivo"""
        pass

    class NoOS(Exception):
        """Chamado quando se tenta ligar o computador sem um sistema operacional"""
        pass

    class UserAledyExists(Exception):
        """Chamado quando se tenta ligar o computador sem um sistema operacional"""
        pass

    def __init__(self):
        self.os = None
        self.name = 'DESKTOP-' + ''.join(random.choices(string.ascii_uppercase + string.digits, k=7))
        self.users = dict()
        self.compatible_os = ['Ubuntu', 'Mac OS', 'Windows XP', 'Windows 7', 'Windows 10', 'Windows 11']
        self.current_user = None

    def change_name(self, new_name: str):
        """
        Troca o nome do dispositivo.
        :param new_name: Novo nome do dispositivo.
        :return: Nenhum
        """
        if len(new_name) > 15:
            raise self.PCNameLenOutOfRange
        self.name = new_name

    def change_os(self, new_os: int):
        """
        Troca o sistema operacional do dispositivo.
        :param new_os: índice do sistema operacional desejado.
        :return: Nenhum
        """
        if new_os not in range(len(self.compatible_os)):
            self.os = None
            raise self.OsNotCompatible
        self.os = self.compatible_os[new_os]

    def create_user(self, user_name, user_password):
        if user_name in self.users.keys():
            raise self.UserAledyExists
        self.users[user_name] = user_password

    def turn_on(self):
        if self.os is None:
            raise self.NoOS
        mixer.init()
        mixer.music.load(str(os.getcwd()) + f'/res/{self.os.replace(" ", "")}Startup.mp3')
        mixer.music.play()
        while mixer.music.get_busy():
            time.sleep(1)

