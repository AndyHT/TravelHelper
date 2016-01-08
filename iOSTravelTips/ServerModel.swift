//
//  ServerModel.swift
//  TravelTips
//
//  Created by Teng on 12/28/15.
//  Copyright © 2015 huoteng. All rights reserved.
//  连接服务端测试数据结果

import Foundation
import Alamofire

class ServerModel: NSObject {
    
    //登录，未测试
    static func login(userEmail: String, withPass userPassMD5: String) {
        Alamofire.request(.POST, "http://localhost:8088/travel_helper/login", parameters: ["email": userEmail, "password": userPassMD5])
            .responseJSON { response in
                print("Request:\(response.request)")  // original URL request
                print("Response:\(response.response)")// URL response
                print("Data:\(response.data)")     // server data
                print("Result\(response.result)")   // result of response serialization
                
                if let JSON = response.result.value {
                    print("JSON: \(JSON)")
                }
        }
    }
    
    //获取用户数据，未测试
    static func getData(sessionID: String, withType dataType:DataType) {
        Alamofire.request(.POST, "http://localhost:8088/travel_helper/login", parameters: ["sessionID": sessionID, "dataType": dataType.rawValue])
            .responseJSON { response in
                print("Request:\(response.request)")  // original URL request
                print("Response:\(response.response)")// URL response
                print("Data:\(response.data)")     // server data
                print("Result\(response.result)")   // result of response serialization
                
                if let JSON = response.result.value {
                    print("JSON: \(JSON)")
                }
        }
    }
    
    //注册新用户，未测试
    static func registerNewUser(userName: String, gender: String, email: String, passMD5: String) {
        Alamofire.request(.POST, "http://localhost:8088/travel_helper/login", parameters: ["userName:" : userName, "gender" : gender, "email" : email, "password" : passMD5])
            .responseJSON { response in
                print("Request:\(response.request)")  // original URL request
                print("Response:\(response.response)")// URL response
                print("Data:\(response.data)")     // server data
                print("Result\(response.result)")   // result of response serialization
                
                if let JSON = response.result.value {
                    print("JSON: \(JSON)")
                }
        }
    }
    
    //向后台发送新数据
    static func addNewData() {
        
    }
}

enum DataType:Int {
    case Bill = 1
    case Note
    case Plan
}