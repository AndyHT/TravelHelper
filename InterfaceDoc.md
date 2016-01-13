##登录：
###URL:http://localhost:8088/travel_helper/login/
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
	"sessionID" : "sessionID123"
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
###URL:http://localhost:8088/travel_helper/register/
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
	"sessionID" : "sessionID123"
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
1.后台返回所请求的计划数据:
```json
[{
	"sessionID" : "sessionID1234",
	"dataType" : "dataType"
},
{
	"schedule_id" : "schedule_id",
	"start" : "start time",
	"end" : "end time",
	"description" : "description",
	"plan_num" : "plan number"
},
{
	"schedule_id" : "schedule_id",
	"start" : "start time",
	"end" : "end time",
	"description" : "description",
	"plan_num" : "plan number"
}]
```
2.后台返回所请求的账单数据
```json
[{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
	"bill_id" : "bill_id",
	"value" : "value",
	"bill_description" : "bill_description",
	"bill_type" : "bill_type",
	"bill_time" : "bill_time(转成字符串吧)"
},
{
	"bill_id" : "bill_id",
	"value" : "value",
	"bill_description" : "bill_description",
	"bill_type" : "bill_type",
	"bill_time" : "bill_time(转成字符串吧)"
}]
```
3.后台返回所请求的备忘录数据
```json
[{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
	"note_id" : "note_id",
	"content" : "content",
	"time" : "time(转成字符串吧)"
},
{
	"note_id" : "note_id",
	"content" : "content",
	"time" : "time(转成字符串吧)"
}]
```
4.后台返回所请求的必备物品数据
```json
[
{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
	"item_id" : "item_id",
	"item_num" : "item_num",
	"item_description" : "item_description",
	"item_name" : "item_name"
},
{
	"item_id" : "item_id",
	"item_num" : "item_num",
	"item_description" : "item_description",
	"item_name" : "item_name"
}
]
##向后台发送用户数据：
###URL:http://localhost:8088/travel_helper/returnData/
post请求，数据用json格式发送
###Request post body:
```json
{
	"sessionID" : "sessionID",
~~	"dataType" : "怎么表示要查询数据类型？后台确定一下，用字符串还是数字"
	"dataType" : "用字符串吧, 比如返回notes就用'note', 返回bill就用'bill', 返回plan就用'plan', 都用单数吧"
}
```

##删除数据
###URL:http://localhost:8088/travel_helper/delete/
post请求，数据用json格式发送
###Request post body:
```json
[{
	"sessionID" : "sessionID",
~~	"dataType" : "怎么表示要查询数据类型？后台确定一下，用字符串还是数字"
	"dataType" : "用字符串吧, 比如删除notes就用'note', 删除bill就用'bill', 删除plan就用'plan', 删除item就用'item',都用单数吧"
},
{
	"id" : "要删除数据的ID"
},
{
	"id" : "要删除数据的ID"
}]
```
###Response post body
```json
{
	"sessionID" : "sessionID",
	"result" : ture/false
}
*数据删除成功*
1.向后台发送备忘录数据:
```json
[
{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
	"content" : "content",
	"time" : "time(转成字符串吧)"
}
]
```
2.向后台发送账单数据:
```json
[
{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
	"value" : "value",
	"bill_description" : "bill_description",
	"bill_type" : "bill_type",
	"bill_time" : "bill_time(转成字符串吧)"
}
]
```
3.向后台发送账单数据计划数据
```json
[
{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
	"plan_num" : "plan number",
	"start_date" : "start time",
	"end_date" : "end time",
	"description" : "description"
}
]
```
4.向后台发送必备物品数据
```json
[
{
	"sessionID" : "sessionID",
	"dataType" : "dataType"
},
{
	"item_description" : "item_description",
	"item_num" : "item_num",
	"item_name" : "item_name"
}
]
###Response post body  
```json
{
	"sessionID" : "sessionID",
	"result" : true/false
}
```
