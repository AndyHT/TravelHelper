//
//  ViewController.swift
//  TravelTips
//
//  Created by Teng on 12/20/15.
//  Copyright © 2015 huoteng. All rights reserved.
//  首页内容展示

import UIKit
import Alamofire

class MainViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
       
        
        let data = ServerModel.getWeatherData(lat: 30.1, lon: 120.0)
        print("City:\(data?["name"])")
    }
    @IBAction func sendMsg(sender: AnyObject) {
        ServerModel.login("1111@11.com", withPass: "111")//OK
//        ServerModel.registerNewUser("Teng", gender: "male", email: "3456789", passMD5: "123456")//OK

    }

    @IBAction func getData(sender: AnyObject) {
        //获取sessionID
        let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as! String
        print("session:\(sessionID)")
//        ServerModel.getData(sessionID, withType: .Plan)//OK
        
        
//        let testData = [
//            "sessionID": sessionID,
//            "dataType": DataType.Bill.rawValue,
//            "value": "1000000",
//            "bill_description": "wertyui",
//            "bill_type": "kskdfjka"
//        ]
        
        let testData = [
            "sessionID": sessionID,
            "dataType": DataType.Note.rawValue,
            "deleteID": [2, 3]
        ]
        
        ServerModel.deleteDataRecord(testData as! [String : AnyObject])
        

    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

