//
//  PlanTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/14/16.
//  Copyright © 2016 huoteng. All rights reserved.
//

import UIKit

class PlanTableViewController: UITableViewController {
    
    var planArr:[Plan] = []

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        
        
        planArr.append(Plan(lat: 30, lon: 120, name: "Shanghai", startDate: NSDate(), endDate: NSDate()))
    }
    
    override func viewWillAppear(animated: Bool) {
        super.viewWillAppear(animated)
//        let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as! String
        
        
//        ServerModel.getData(sessionID, withType: DataType.Plan) { (plans) -> Void in
//            //将plan填入planArr
//        }
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
        return planArr.count
    }

    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)
        
        let titleLabel = cell.viewWithTag(100) as! UILabel
        let startDateLabel = cell.viewWithTag(101) as! UILabel
        
        titleLabel.text = planArr[indexPath.row].destinationName
        startDateLabel.text = planArr[indexPath.row].startDate.description

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
        if segue.identifier == "destinationSegue" {
            let vc = segue.destinationViewController as! WeatherViewController
            if let index = tableView.indexPathForSelectedRow {
                vc.destination = self.planArr[index.row]
            }
        
        }
    }
    

}
