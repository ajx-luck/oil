import urllib.request  
import re
urlPath = 'http://39.108.5.151/nav/product.html'
req = urllib.request.urlopen(urlPath)
buf = req.read()  
webstr = buf.decode()  
urlList = re.findall(r'src="(.+?\.(jpg|jpeg|png|bmp|gif))"', webstr);
i = 0  
for url in urlList:
    try:
        url = url[0]
        print(urlPath+url)
        f = open("D:\\gitproject\\oil\\src\\main\\resources\\static\\"+url,'wb') #注意第二个参数要写成wb，写成w会报错
        req = urllib.request.urlopen(urlPath+url)
        buf = req.read()
        #bufstr = buf.decode('utf-8','ignore')
        f.write(buf)
        i += 1
    except BaseException:
        pass