f = open("blabla.txt", "r")
a = f.read()
# print f.read()
wordict = []


# for word in f:
# 	# print (word)
# 	wordict.append(str(word))

for word in a.lower().split():
    word = word.replace(".","")
    word = word.replace(",","")
    word = word.replace(":","")
    word = word.replace("\"","")
    word = word.replace("!","")
    wordict.append(word)

print wordict

# unique = [
# for i in wordict:
#     unique.extend(i)

unique2 = list(set(wordict))
print unique2
# print unique
# print unique2
"""the a an and what you this that him her he she
to be of have I it for not on with as do but at by from they we say
"""