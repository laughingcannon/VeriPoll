import collections
import matplotlib.pyplot as plt
import pandas as pd

f = open("sample_textfile.txt", "r")
a = f.read()
wordict = []
wordcount = {}

for word in a.lower().split():
    word = word.replace(".","")
    word = word.replace(",","")
    word = word.replace(":","")
    word = word.replace("\"","")
    word = word.replace("!","")
    wordict.append(word)
    if word not in wordcount:
        wordcount[word] = 1
    else:
        wordcount[word] += 1

n_print = int(input("How many most common words to print: "))  #
word_counter = collections.Counter(wordcount)
for word, count in word_counter.most_common(n_print):
    print(word, ": ", count)

f.close()

lst = word_counter.most_common(n_print)
df = pd.DataFrame(lst, columns = ['Word', 'Count'])
df.plot(x='Word',y='Count', kind = 'bar')
plt.show()
