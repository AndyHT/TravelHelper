//
//  MD5.swift
//  TravelTips
//
//  Created by Teng on 1/5/16.
//  Copyright © 2016 huoteng. All rights reserved.
//  将密码转为MD5值

import Foundation
import CryptoSwift

func encryptPassword(userPass: String) -> String {
    return userPass.md5()
}

enum DataType:String {
    case Bill = "bill"
    case Note = "note"
    case Plan = "plan"
    case Item = "item"
}

struct Tip {
    let title:String
    let content:String
    let type:TipType
    let coverImgName:String
    init(title:String, content:String, type:TipType, coverImgName:String){
        self.title = title
        self.content = content
        self.type = type
        self.coverImgName = coverImgName
    }
}

enum TipType: String{
    case sceneIntro = "Attractions"
    case skill = "Life skills"
    case strategy = "Raiders"
    case diary = "Travel notes"
}

enum BillType: String {
    case Shopping = "shopping"
    case Food = "fodd"
    case Hotel = "hotel"
    case Traffic = "traffic"
    case Other = "other"
}

//通过继承实现泛化
class UserData {
    
}

struct Bill {
    var billID:Int
    var value:Double
    var descriptin:String
    var type:BillType
    var time:NSDate
    init(id: Int, value: Double, desc: String, type: BillType, time: NSDate) {
        self.billID = id
        self.value = value
        self.descriptin = desc
        self.type = type
        self.time = time
    }
    
    
    
}

class Note: UserData {
    
}

class Item: UserData {
    var id:Int
    var number:Int
    var description:String
    var name:String
    var time:NSDate
    var check = false
    
    init(id: Int, number: Int, desc: String, name: String, time: NSDate) {
        self.id = id
        self.number = number
        self.description = desc
        self.name = name
        self.time = time
    }
}

class Plan: UserData {
    var id:Int
    var destinationLat:Double
    var destinationLon:Double
    var destinationName:String
    var startDate:NSDate
    var endDate:NSDate
    
    init(id:Int, lat:Double, lon:Double, name:String, startDate:NSDate, endDate:NSDate) {
        self.id = id
        self.destinationLat = lat
        self.destinationLon = lon
        self.destinationName = name
        self.startDate = startDate
        self.endDate = endDate
    }
}