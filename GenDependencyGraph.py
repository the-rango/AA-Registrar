import json
import urllib.parse
from collections import defaultdict
import bs4 as bs

sauce = dict(json.loads(open('course_info.json').read()))

d = {dept : {c : set() for c in courses.keys()}for dept, courses in sauce.items()}

for dept, courses in sauce.items():
    for course, info in courses.items():
##        if course.isnumeric() and int(course) > 199:
##            continue
        sp = bs.BeautifulSoup(info,'lxml')
        for p in sp.find_all('p'):
            if 'Prerequisite' in p.text:
                for link in p.find_all('a'):
                    name = link.text
                    temp = name.split()
                    c_d = ' '.join(temp[:-1])
                    num = temp[-1]
                    try:
                        d[c_d][num].add((dept,course))
                    except:
                        print(c_d,num,'is a prereq to',dept,course)
                        print(link.text)
                        try:
                            d[dept][num].add((dept,course))
                        except:
                            pass
                break

for dept, courses in d.items():
    for course, possibles in courses.items():
        sauce[dept][course] += '<hr><p>Is a prerequisite to {} class{}{}'.format(len(possibles),'es' if len(possibles)>1 else '',': ' if len(possibles)>0 else '.')
        sauce[dept][course] += ', '.join('<a href=\"http://catalogue.uci.edu//search/?P={}\" target=\"_blank\">{}</a>'.format(urllib.parse.quote(poss[0]+' '+poss[1]),poss[0]+' '+poss[1]) for poss in sorted(possibles, key=lambda x: x[1]))
        sauce[dept][course] += '</p>'

temp = json.dumps(sauce, indent=4)

with open('course_info.json', 'w') as out:
    out.write(temp)
