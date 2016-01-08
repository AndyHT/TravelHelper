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

##获得用户数据：
###URL:http://localhost:8088/teavel_helper/data/
post请求，数据用json格式发送
###Request post body:
```json
{
	"sessionID" : "sessionID"
~~	"dataType" : "怎么表示要查询数据类型？后台确定一下，用字符串还是数字"~~
	"dataType" : "用字符串吧, 比如请求notes就用'note', 请求bill就用'bill', 请求plan就用'plan', 都用单数吧"
}
```
###Response post body:
```json
[{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
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

##向后台发送用户数据：
###URL:http://localhost:8088/travel_helper/后台确定一下URL/
post请求，数据用json格式发送
###Request post body:
```json
{
	"sessionID" : "sessionID",
	"dataType" : "怎么表示要查询数据类型？后台确定一下，用字符串还是数字"
}
```
###Response post body
```json
数据插入是否成功
```
