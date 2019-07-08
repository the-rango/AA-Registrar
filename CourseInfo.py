import bs4 as bs
import urllib.request
import json
from collections import OrderedDict

ROOT = 'http://catalogue.uci.edu'

c = OrderedDict()

with urllib.request.urlopen(ROOT+'/allcourses') as inp:
    soup = bs.BeautifulSoup(inp, 'lxml')

index = soup.find("div", {"id": "atozindex"})
for dept in index.find_all('li'):
    d_name = dept.text
    d_short = d_name[d_name.find('(')+1:-1]
    print(d_short)
    
    c[d_short] = OrderedDict()
    
    url = ROOT + dept.find('a')['href']

    with urllib.request.urlopen(url) as inp:
        soup = bs.BeautifulSoup(inp, 'lxml')

    for course in soup.find_all('div', {'class': "courseblock"}):
        l_name = course.find('p', {'class': 'courseblocktitle'}).text
        name = l_name.split('.')[0]
        num = name.split()[-1]

        info = ''
        for p in course.find_all('p'):
            for a in p.find_all('a'):
                a['href'] = 'http://catalogue.uci.edu/'+a['href']
                a['onclick'] = ''
                a['target'] = '_blank'
            info += str(p)
            
        c[d_short][num] = info

temp = json.dumps(c, indent=4)
temp = temp.replace('onclick=\"\"','')

with open('course_info.json', 'w') as out:
    out.write(temp)

print(temp)
print('All done')


