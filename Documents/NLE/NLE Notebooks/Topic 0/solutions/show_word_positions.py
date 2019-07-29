# coding: utf-8
def show_word_positions(filepath):
    input_file_path = filepath
    input_file = open(input_file_path)
    input_text = input_file.read()
    word_list = input_text.split()
    for word, position in zip(word_list,range(len(word_list))):
        print("'{0}' is in position {1}".format(word,position))
           

show_word_positions("/Users/davidw/Documents/teach/NLE/NLE Notebooks/Topic 0/sample_text.txt")
