//
//  NewItemTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/15/16.
//  Copyright © 2016 huoteng. All rights reserved.
//

import UIKit

class NewItemTableViewController: UITableViewController {

    @IBOutlet weak var itemNameTextField: UITextField!
    @IBOutlet weak var itemDescTextView: UITextView!
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
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
        return 5
    }
    
    @IBAction func saveNewItem(sender: AnyObject) {
        let name = itemNameTextField.text!
        let description = itemDescTextView.text!
        
        if name != "" && description != "" {
            let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as! String!
            let dateFormat = NSDateFormatter()
            dateFormat.dateFormat = "yyyy-MM-dd"
            
            let tmp = ["sessionID", "dataType", "item_description", "item_num", "item_name", "item_time"]
            let tmp2 = [sessionID, DataType.Item.rawValue, description, "1", "name", dateFormat.stringFromDate(NSDate())]
            
            let newItem = NSDictionary(objects: tmp2, forKeys: tmp) as! [String: AnyObject]
            
            ServerModel.addNewData(newItem, dataType: DataType.Item, callbcak: { (isSuccess) -> Void in
                if isSuccess {
                    print("item save suceess")
                    self.navigationController?.popViewControllerAnimated(true)
                } else {
                    print("item save fail")
                }
            })
        }
    }
    
    @IBAction func cancelSaved(sender: AnyObject) {
        self.navigationController?.popViewControllerAnimated(true)
    }

    /*
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("reuseIdentifier", forIndexPath: indexPath)

        // Configure the cell...

        return cell
    }
    */

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
