//
//  ItemListTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/15/16.
//  Copyright © 2016 huoteng. All rights reserved.
//

import UIKit

class ItemListTableViewController: UITableViewController {
    
    var itemArr = [Item]()

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        itemArr.append(Item(id: 1, number: 1, desc: "lalal", name: "haha", time: NSDate()))
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
        
        let dateFormatter = NSDateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
        
        if let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as? String {
            ServerModel.getData(sessionID, withType: DataType.Bill) { (items) -> Void in
                //将plan填入planArr，未完成
                for index in 1..<items.count {
                    let id = items[index]["bill_id"].int!
                    let number = items[index]["value"].int!
                    let description = items[index]["bill_description"].string!
                    let timeStr = items[index][""].string!
                    let name = items[index]["bill_type"].string!
                    
                    let time = dateFormatter.dateFromString(timeStr)!
                    
                    let newItem = Item(id: id, number: number, desc: description, name: name, time: time)
                    
                    self.itemArr.append(newItem)
                }
            }
            
            
        } else {
            print("没有获得session")
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
        return itemArr.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)
        
        let nameLabel = cell.viewWithTag(100) as! UILabel
        nameLabel.text = itemArr[indexPath.row].name
        
        let descriptionLabel = cell.viewWithTag(101) as! UILabel
        descriptionLabel.text = itemArr[indexPath.row].description
        
        if itemArr[indexPath.row].check {
            cell.accessibilityViewIsModal = false
        }
        
        cell.accessoryType = .Checkmark
        
        return cell
    }
    
    override func tableView(tableView: UITableView, accessoryButtonTappedForRowWithIndexPath indexPath: NSIndexPath) {
        
        itemArr[indexPath.row].check = !itemArr[indexPath.row].check
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

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepareForSegue(segue: UIStoryboardSegue, sender: AnyObject?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
