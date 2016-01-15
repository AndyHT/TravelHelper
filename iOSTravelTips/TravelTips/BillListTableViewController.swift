//
//  BillListTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/15/16.
//  Copyright © 2016 huoteng. All rights reserved.
//

import UIKit
import DGElasticPullToRefresh


class BillListTableViewController: UITableViewController {
    
    var billArr = [Bill]()
    @IBOutlet var billListTableView: UITableView!

    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
//        billArr.append(Bill(id: 0, value: 100.0, desc: "lalala", type: .Hotel, time: NSDate()))
        
        let loadingView = DGElasticPullToRefreshLoadingViewCircle()
        loadingView.tintColor = UIColor(red: 255/255.0, green: 208/255.0, blue: 80/255.0, alpha: 1.0)
        billListTableView.dg_addPullToRefreshWithActionHandler ({ [weak self] () -> Void in
            print("Refreshing")
            self?.tableView.dg_stopLoading()
            }, loadingView: loadingView)
        tableView.dg_setPullToRefreshFillColor(UIColor(red: 59/255.0, green: 130/255.0, blue: 176/255.0, alpha: 1.0))
        tableView.dg_setPullToRefreshBackgroundColor(tableView.backgroundColor!)

    
    }
    
    
    deinit {
        tableView.dg_removePullToRefresh()
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        
        if billArr.count == 0 {
            let dateFormatter = NSDateFormatter()
            dateFormatter.dateFormat = "yyyy-MM-dd"
            
            if let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as? String {
                ServerModel.getData(sessionID, withType: DataType.Bill) { (bills) -> Void in
                    for index in 1..<bills.count {
                        print("get bill data")
                        let id = bills[index]["bill_id"].int!
                        let value = bills[index]["value"].double!
                        let description = bills[index]["bill_description"].string!
                        
                        let typeStr = bills[index]["bill_type"].string!
                        let type = BillType(rawValue: typeStr)!
                        
                        let timeStr = bills[index]["bill_time"].string!
                        let time = dateFormatter.dateFromString(timeStr)!
                        
                        
                        let newBill = Bill(id: id, value: value, desc: description, type: type, time: time)
                        
                        self.billArr.append(newBill)
                    }
                    self.billListTableView.reloadData()
                }
                
                
            } else {
                print("没有获得session")
            }
        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSectionsInTableView(tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return billArr.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)

        let valueLabel = cell.viewWithTag(100) as! UILabel
        let descLabel = cell.viewWithTag(101) as! UILabel
        let typeImage = cell.viewWithTag(102) as! UIImageView
        
        valueLabel.text = "－\(billArr[indexPath.row].value)"
        descLabel.text = "\(billArr[indexPath.row].descriptin)"
        switch billArr[indexPath.row].type {
        case .Food:
            typeImage.image = UIImage(named: "food_bill")
        case .Hotel:
            typeImage.image = UIImage(named: "hotel_bill")
        case .Traffic:
            typeImage.image = UIImage(named: "traffic_bill")
        case .Shopping:
            typeImage.image = UIImage(named: "shopping_bill")
        case .Other:
            typeImage.image = UIImage(named: "other_bill")
        }

        return cell
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(tableView: UITableView, canEditRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(tableView: UITableView, commitEditingStyle editingStyle: UITableViewCellEditingStyle, forRowAtIndexPath indexPath: NSIndexPath) {
        if editingStyle == .Delete {
            // Delete the row from the data source
            tableView.deleteRowsAtIndexPaths([indexPath], withRowAnimation: .Fade)
        } else if editingStyle == .Insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(tableView: UITableView, moveRowAtIndexPath fromIndexPath: NSIndexPath, toIndexPath: NSIndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(tableView: UITableView, canMoveRowAtIndexPath indexPath: NSIndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        
    }
    

}
