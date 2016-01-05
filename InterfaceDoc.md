##登录：
###URL:http://localhost:8888/login/
post请求，数据用json格式发送
###Request post body:
```json
{
	"name" : "test",
	"password" : "password"
}
```
###Response post body:
```josn
你定一下返回的内容，我觉得需要一个sessionID之类的东西用来验证用户，其他的你觉得还需要啥
```

##注册：
###URL:http://localhost:8888/signup/
post请求，数据用json格式发送
###Request post body:
```json
{
注册需要啥字段你填一下，我在前台获取发给你
}
```
###Response post body:
```josn
返回个注册成功或者失败的标记，然后顺便就相当于登录了
```

##获得该用户所有计划：
###URL:http://localhost:8888/plans/
post请求，数据用json格式发送
###Request post body:
```json
{

}
```

##获得该用户所有备忘录：
###URL:http://localhost:8888/notes/
post请求，数据用json格式发送
###Request post body:
```json
{

}
```

##获得该用户所有账单
###URL:http://localhost:8888/bills/
post请求，数据用json格式发送
###Request post body:
```json
{

}
```