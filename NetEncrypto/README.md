# Android网络安全之加解密
参考：https://www.imooc.com/learn/1214

![AES原理.png](doc/AES原理.png)
![AES特点.png](doc/AES特点.png)
![DH原理.png](doc/DH原理.png)
![DH特点.png](doc/DH特点.png)
![RSA原理.png](doc/RSA原理.png)
![RSA特点.png](doc/RSA特点.png)
![网络通信中加密流程.png](doc/网络通信中加密流程.png)

## OpenSSL相关命令

### 生成私钥
genrsa -out rsa_private_key.pem 1024

### 生成公钥
rsa -in rsa_private_key_cs8.pem -pubout -out rsa_public_key_cs8.pem

### 把RSA私钥转换成PKCS8格式
>以前的写法：
pkcs8 -topk8 -inform PEM -in rsa_private_key.pem -outform pem –nocrypt

>最新的写法：参考：https://www.jianshu.com/p/aa04380b7965
pkcs8 -in rsa_private_key.pem -topk8 -out rsa_private_key_cs8.pem -nocrypt