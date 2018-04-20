#encoding:utf-8
import csv
from urllib import parse

csv_file = csv.reader(open("D:\\bd\\123.csv","r",encoding="utf_16"))
csv_file2 = open('D:\\bd\\1234.csv','w', encoding="utf_16") # 设置newline，否则两行之间会空一行
print(csv_file)
for stu in csv_file:
    rows = stu[0].split('\t')
    str3 = parse.quote(rows[2])
    url = "http://fxmz.ycwlxx.com.cn/fxc?keyword="+ str3 + "&e_keywordid={keywordid}&e_matchtype={matchtype}&e_creative={creative}&e_adposition={adposition}&e_pagenum={pagenum}";
    if(rows[6] != "移动访问URL"):
        rows[6] = url
    str2 = "\t".join(str(i) for i in rows)
    # csv_file2.write(rows[0]+"\t"+rows[1]+"\t"+rows[2]+"\t"+rows[3]+"\t"+rows[4]+"\t"+rows[5]+"\t"+rows[6]+"\t"+rows[7]+"\t"+rows[8]+"\t"+"\n")
    # print(stu[0].split('\t'))
    csv_file2.write(str2 + "\n")







