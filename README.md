# codemonkey-security


## 浏览器认证使用

1. 导入依赖
```
gradle 导入

api("vip.codemonkey.security:codemonkey-security-browser:${version}")
```

2. 添加包扫描
```
@ComponentScan({"vip.codemonkey"})
```

3. 实现 UserDetailsService 接口