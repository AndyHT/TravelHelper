//
//  NewItemTableViewController.swift
//  TravelTips
//
//  Created by Teng on 1/15/16.
//  Copyright © 2016 huoteng. All rights reserved.
//

import UIKit
import DatePickerCell

protocol AddNewItemDelegate {
    func addNewItem(newItem: Item)
}

class NewItemTableViewController: UITableViewController {

    @IBOutlet weak var itemNameTextField: UITextField!
    @IBOutlet weak var itemDescTextView: UITextView!
    @IBOutlet weak var itemNameCell: UITableViewCell!
    @IBOutlet weak var itemDescriptionCell: UITableViewCell!
    @IBOutlet weak var itemTimeCell: DatePickerCell!
    @IBOutlet var newItemTableView: UITableView!
    
    var cells:NSArray = []
    var newItemDelegate:AddNewItemDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
        self.newItemTableView.rowHeight = UITableViewAutomaticDimension
        self.newItemTableView.estimatedRowHeight = 44
        
        self.itemTimeCell.leftLabel.text = "选择使用时间"
        self.itemTimeCell.datePicker.datePickerMode = UIDatePickerMode.Date
        
        let formatter = NSDateFormatter()
        formatter.dateFormat = "yyyy.MM.dd"
        self.itemTimeCell.rightLabel.text = formatter.stringFromDate(self.itemTimeCell.date)
        
        cells = [[itemNameCell, itemDescriptionCell, itemTimeCell]]

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
        return 3
    }
    
    @IBAction func saveNewItem(sender: AnyObject) {
        let name = itemNameTextField.text!
        let description = itemDescTextView.text!
        
        if name != "" && description != "" {
            let sessionID = NSUserDefaults.standardUserDefaults().valueForKey("sessionID") as! String!
            let dateFormat = NSDateFormatter()
            dateFormat.dateFormat = "yyyy-MM-dd"
            
            let itemDate = self.cells[0][2].date!
            
            let tmp = ["sessionID", "dataType", "item_description", "item_num", "item_name", "item_time"]
            let tmp2 = [sessionID, DataType.Item.rawValue, description, "1", "name", dateFormat.stringFromDate(itemDate)]
            
            let newItem = NSDictionary(objects: tmp2, forKeys: tmp) as! [String: AnyObject]
            
            ServerModel.addNewData(newItem, dataType: DataType.Item, callbcak: { (isSuccess) -> Void in
                if isSuccess {
                    print("item save suceess")
                    
                    if let delegate = self.newItemDelegate {
                        delegate.addNewItem(Item(id: 0, number: 1, desc: description, name: "name", time: itemDate))
                    }
                    
                    self.navigationController?.popViewControllerAnimated(true)
                } else {
                    print("item save fail")
                }
            })
        }
    }
    
    override func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
        let cell = self.tableView(tableView, cellForRowAtIndexPath: indexPath)
        if cell.isKindOfClass(DatePickerCell) {
            return (cell as! DatePickerCell).datePickerHeight()
        }
        return super.tableView(tableView, heightForRowAtIndexPath: indexPath)
    }
    
    override func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let cell = self.tableView(tableView, cellForRowAtIndexPath: indexPath)
        if cell.isKindOfClass(DatePickerCell) {
            let datePickerTableViewCell = cell as! DatePickerCell
            datePickerTableViewCell.selectedInTableView(tableView)
            self.tableView.deselectRowAtIndexPath(indexPath, animated: true)
        }
    }
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        // Configure the cell...
        
        return cells[indexPath.section][indexPath.row] as! UITableViewCell
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
