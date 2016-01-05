##登录：
###URL:http://localhost:8088/travel_helper/login
post请求，数据用json格式发送
###Request post body:
```json
{
	"email" : "email",
	"password" : "password"
}
```
###Response post body:
*登陆成功(即用户名和密码正确)*
```json
{
	"result" : true,
	"sessionID" : "sessionID"
}
```
*登陆失败*
```json
{
	"result" : false
}
```
你定一下返回的内容，我觉得需要一个sessionID之类的东西用来验证用户，其他的你觉得还需要啥


##注册：
~~###URL:http://localhost:8888/signup/~~
###URL:http://localhost:8088/travel_helper/register
post请求，数据用json格式发送
###Request post body:
```json
{
	"userName" : "userName",
	"gender" : "gender",
	"email" : "email",
	"password" : "password"
}
```
注册需要啥字段你填一下，我在前台获取发给你

###Response post body:
*注册成功*
```josn
{
	"result" : true,
	"sessionID" : "sessionID"
}
```
*注册失败*
```json
{
	"result" : false
}
```
返回个注册成功或者失败的标记，然后顺便就相当于登录了

##获得该用户所有计划：
###URL:http://localhost:8088/teavel_helper/plans/
post请求，数据用json格式发送
###Request post body:
```json
{
	"sessionID" : "sessionID"
}
```
###Response post body:
```jsonArr
[{
	"start" : "start time",
	"end" : "end time",
	"description" : "description",
	"plan_num" : "plan number"
},
{
	"start" : "start time",
	"end" : "end time",
	"description" : "description",
	"plan_num" : "plan number"
}]
```

##获得该用户所有备忘录：
###URL:http://localhost:8088/travel_helper/notes/
post请求，数据用json格式发送
###Request post body:
```json
{
	"sessionID" : "sessionID"
}
```
###Response post body
```jsonArr
[{
	"content" : "content",
	"time" : "time"
},
{
	"content" : "content",
	"time" : "time"
}]
```

##获得该用户所有账单
###URL:http://localhost:8088/travel_helper/bills/
post请求，数据用json格式发送
###Request post body:
```json
{
	"sessionID" : "sessionID"
}
```

###Response post body:
```json
[{
	"value" : "value",
	"description" : "description",
	"type" : "bill type",
	"time" : "create time"
},
{
	"value" : "value",
	"description" : "description",
	"type" : "bill type",
	"time" : "create time"
}]
```

