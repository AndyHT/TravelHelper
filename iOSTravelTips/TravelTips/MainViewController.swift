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
       
        //test
//        ServerModel.login("test", withPass: "lalalala")
//        Alamofire.request(.GET, "https://httpbin.org/get", parameters: ["foo": "bar"])
//            .responseJSON { response in
//                print("Request:\t\(response.request)")  // original URL request
//                print("Response:\t\(response.response)")// URL response
//                print("Data:\t\(response.data)")     // server data
//                print("Result:\t\(response.result)")   // result of response serialization
//                
//                if let JSON = response.result.value {
//                    print("JSON: \(JSON)")
//                }
//
//        }

    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

